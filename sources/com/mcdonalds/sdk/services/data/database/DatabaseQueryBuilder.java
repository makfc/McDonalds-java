package com.mcdonalds.sdk.services.data.database;

import android.text.TextUtils;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.DatabaseField;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class DatabaseQueryBuilder {
    static final String ACTION_DELETE = "delete";
    static final String ACTION_SELECT = "select";
    static final String ACTION_UPDATE = "update";
    private static final String ORDER_ASC = "ASC";
    private static final String ORDER_DESC = "DESC";
    private final String mAction;
    private final List<QueryColumn> mColumns = new ArrayList();
    private FromClause mCurrentFromClause;
    private final LinkedHashSet<FromClause> mFromClauses = new LinkedHashSet();
    private final List<QueryColumn> mGroupByColumns = new ArrayList();
    private final List<OrderByClause> mOrderByClauses = new ArrayList();
    private final List<Comparison> mWhereClauses = new ArrayList();

    private static class Comparison {
        private List<ComparisonColumn> inColumns;
        private List<String> inValues;
        private QueryColumn left;
        private String operator;
        /* renamed from: or */
        private boolean f6685or;
        private QueryColumn right;
        private String value;

        private static Comparison equals(ComparisonColumn left, ComparisonColumn right) {
            return new Comparison("=", left, right);
        }

        private static Comparison equals(ComparisonColumn left, String value) {
            return new Comparison("=", left, value);
        }

        /* renamed from: in */
        private static Comparison m7740in(ComparisonColumn left, ComparisonColumn... inColumns) {
            return new Comparison("in", left, inColumns);
        }

        /* renamed from: in */
        private static Comparison m7741in(ComparisonColumn left, String... values) {
            return new Comparison("in", left, values);
        }

        private static Comparison like(ComparisonColumn left, String value) {
            return new Comparison("like", left, value);
        }

        private Comparison(String operator, ComparisonColumn left, ComparisonColumn right) {
            this.left = left;
            this.right = right;
            this.operator = operator;
        }

        private Comparison(String operator, ComparisonColumn left, ComparisonColumn... inColumns) {
            this.left = left;
            this.inColumns = Arrays.asList(inColumns);
            this.operator = operator;
        }

        private Comparison(String operator, ComparisonColumn left, String value) {
            this.left = left;
            this.value = value;
            this.operator = operator;
        }

        private Comparison(String operator, ComparisonColumn left, String... inValues) {
            this.left = left;
            this.inValues = Arrays.asList(inValues);
            this.operator = operator;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(this.left.toString()).append(" ").append(this.operator).append(" ");
            if (this.value != null) {
                builder.append(this.value);
            } else if (this.right != null) {
                builder.append(this.right.toString());
            }
            if (this.operator.equals("in")) {
                builder.append("(");
                if (!ListUtils.isEmpty(this.inValues)) {
                    Iterator<String> iterator = this.inValues.iterator();
                    while (iterator.hasNext()) {
                        builder.append((String) iterator.next());
                        if (iterator.hasNext()) {
                            builder.append(", ");
                        }
                    }
                } else if (!ListUtils.isEmpty(this.inColumns)) {
                    Iterator<ComparisonColumn> iterator2 = this.inColumns.iterator();
                    while (iterator2.hasNext()) {
                        builder.append(((QueryColumn) iterator2.next()).toString());
                        if (iterator2.hasNext()) {
                            builder.append(", ");
                        }
                    }
                }
                builder.append(")");
            }
            return builder.toString();
        }
    }

    public static class QueryColumn {
        private String alias;
        private String column;
        private QueryColumn nullFallBackColumn;
        private String nullFallBackValue;
        private String table;
        private String tableAlias;

        public QueryColumn(String table, String column) {
            this(table, column, null, null);
        }

        public QueryColumn(String table, String column, String alias) {
            this(table, column, alias, null);
        }

        public QueryColumn(String table, String column, String alias, String tableAlias) {
            this.table = table;
            this.column = column;
            this.alias = alias;
            this.tableAlias = tableAlias;
        }

        public QueryColumn setNullFallBackColumn(QueryColumn nullFallBackColumn) {
            this.nullFallBackColumn = nullFallBackColumn;
            return this;
        }

        public QueryColumn setNullFallBackValue(String nullFallBackValue) {
            this.nullFallBackValue = nullFallBackValue;
            return this;
        }

        public String toString() {
            String columnAlias = null;
            String columnName = this.column;
            if (this.alias != null) {
                columnAlias = this.alias;
                columnName = this.alias;
            }
            String tableName = this.table;
            if (this.tableAlias != null) {
                tableName = this.tableAlias;
                if (columnAlias == null) {
                    columnAlias = DatabaseQueryBuilder.format(tableName, "_", columnName);
                }
            }
            String columnString = DatabaseQueryBuilder.format(tableName, ".", this.column);
            if (this.nullFallBackValue != null) {
                columnString = DatabaseQueryBuilder.format("ifnull(", columnString, ", ", this.nullFallBackValue, ")");
            } else if (this.nullFallBackColumn != null) {
                columnString = DatabaseQueryBuilder.format("ifnull(", columnString, ", ", this.nullFallBackColumn.toString(), ")");
            }
            if (columnAlias == null) {
                return columnString;
            }
            return DatabaseQueryBuilder.format(columnString, " ", columnAlias);
        }
    }

    public static class ComparisonColumn extends QueryColumn {
        public ComparisonColumn(String table, String column) {
            super(table, column, null, null);
        }

        public ComparisonColumn(String table, String column, String tableAlias) {
            super(table, column, null, tableAlias);
        }
    }

    private static class FromClause {
        private Join currentJoin;
        private List<Join> joins;
        private Table table;

        private static class Join {
            private List<Comparison> onClauses = new ArrayList();
            private Table table;
            private String type;

            public Join(String type, Table table) {
                this.type = type;
                this.table = table;
            }

            /* renamed from: on */
            public void mo31339on(Comparison comparison) {
                this.onClauses.add(comparison);
            }

            public String toString() {
                StringBuilder builder = new StringBuilder();
                builder.append(this.type).append(" join ").append(this.table.toString());
                if (!ListUtils.isEmpty(this.onClauses)) {
                    builder.append(" on");
                }
                for (int i = 0; i < this.onClauses.size(); i++) {
                    Comparison comparison = (Comparison) this.onClauses.get(i);
                    if (i > 0) {
                        if (comparison.f6685or) {
                            builder.append(" or");
                        } else {
                            builder.append(" and");
                        }
                    }
                    builder.append(" ").append(comparison.toString());
                }
                return builder.toString();
            }
        }

        private static class Table {
            private String alias;
            private String name;

            public Table(String name, String alias) {
                this.name = name;
                this.alias = alias;
            }

            public String toString() {
                StringBuilder builder = new StringBuilder(this.name);
                if (!TextUtils.isEmpty(this.alias)) {
                    builder.append(" ").append(this.alias);
                }
                return builder.toString();
            }

            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }
                Table table = (Table) o;
                if (this.name.equals(table.name)) {
                    if (this.alias != null) {
                        if (this.alias.equals(table.alias)) {
                            return true;
                        }
                    } else if (table.alias == null) {
                        return true;
                    }
                }
                return false;
            }

            public int hashCode() {
                return (this.name.hashCode() * 31) + (this.alias != null ? this.alias.hashCode() : 0);
            }
        }

        private FromClause(String tableName, String tableAlias) {
            this.joins = new ArrayList();
            this.table = new Table(tableName, tableAlias);
        }

        private FromClause innerJoin(String tableName, String tableAlias) {
            innerJoin(new Table(tableName, tableAlias));
            return this;
        }

        private void innerJoin(Table table) {
            Join join = new Join("inner", table);
            this.joins.add(join);
            this.currentJoin = join;
        }

        private FromClause leftJoin(String tableName, String tableAlias) {
            leftJoin(new Table(tableName, tableAlias));
            return this;
        }

        private void leftJoin(Table table) {
            Join join = new Join("left", table);
            this.joins.add(join);
            this.currentJoin = join;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder(this.table.toString());
            for (Join join : this.joins) {
                builder.append(" ").append(join.toString());
            }
            return builder.toString();
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            return this.table.equals(((FromClause) o).table);
        }

        public int hashCode() {
            return this.table.hashCode();
        }
    }

    private static class OrderByClause {
        private String direction;
        private QueryColumn mQueryColumn;

        public OrderByClause(QueryColumn queryColumn) {
            this(queryColumn, null);
        }

        public OrderByClause(QueryColumn queryColumn, String direction) {
            this.mQueryColumn = queryColumn;
            this.direction = direction;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder(this.mQueryColumn.toString());
            if (this.direction != null) {
                builder.append(" ").append(this.direction);
            }
            return builder.toString();
        }
    }

    DatabaseQueryBuilder(String action) {
        this.mAction = action;
    }

    public DatabaseQueryBuilder addColumn(String table, String column) {
        this.mColumns.add(new QueryColumn(table, column));
        this.mFromClauses.add(new FromClause(table, null));
        return this;
    }

    public DatabaseQueryBuilder addColumn(String table, String column, String alias) {
        this.mColumns.add(new QueryColumn(table, column, alias));
        this.mFromClauses.add(new FromClause(table, null));
        return this;
    }

    public DatabaseQueryBuilder addColumn(String table, String column, String alias, String tableAlias) {
        this.mColumns.add(new QueryColumn(table, column, alias, tableAlias));
        this.mFromClauses.add(new FromClause(table, tableAlias));
        return this;
    }

    public DatabaseQueryBuilder addColumn(QueryColumn column) {
        this.mColumns.add(column);
        this.mFromClauses.add(new FromClause(column.table, column.tableAlias));
        return this;
    }

    public DatabaseQueryBuilder addAllFromTable(DatabaseModel model) {
        List<DatabaseField> modelFields = model.getFields();
        String tableName = model.getTableName();
        for (int i = 0; i < modelFields.size(); i++) {
            this.mColumns.add(new QueryColumn(tableName, ((DatabaseField) modelFields.get(i)).name));
        }
        this.mFromClauses.add(new FromClause(tableName, null));
        return this;
    }

    public DatabaseQueryBuilder addAllFromTable(DatabaseModel model, String tableAlias) {
        List<DatabaseField> modelFields = model.getFields();
        String tableName = model.getTableName();
        for (int i = 0; i < modelFields.size(); i++) {
            this.mColumns.add(new QueryColumn(tableName, ((DatabaseField) modelFields.get(i)).name, null, tableAlias));
        }
        this.mFromClauses.add(new FromClause(tableName, tableAlias));
        return this;
    }

    public DatabaseQueryBuilder from(String tableName) {
        from(tableName, null);
        return this;
    }

    public DatabaseQueryBuilder from(String tableName, String tableAlias) {
        FromClause clause = new FromClause(tableName, tableAlias);
        this.mFromClauses.remove(clause);
        this.mFromClauses.add(clause);
        this.mCurrentFromClause = clause;
        return this;
    }

    public DatabaseQueryBuilder innerJoin(String tableName) {
        return innerJoin(tableName, null);
    }

    public DatabaseQueryBuilder innerJoin(String tableName, String tableAlias) {
        if (this.mCurrentFromClause == null) {
            throw new IllegalStateException("You should call `from` before adding a join");
        }
        this.mCurrentFromClause.innerJoin(tableName, tableAlias);
        updateColumnsWithAlias(tableName, tableAlias);
        this.mFromClauses.remove(new FromClause(tableName, tableAlias));
        return this;
    }

    public DatabaseQueryBuilder leftJoin(String tableName) {
        return leftJoin(tableName, null);
    }

    public DatabaseQueryBuilder leftJoin(String tableName, String tableAlias) {
        if (this.mCurrentFromClause == null) {
            throw new IllegalStateException("You should call `from` before adding a join");
        }
        this.mCurrentFromClause.leftJoin(tableName, tableAlias);
        updateColumnsWithAlias(tableName, tableAlias);
        this.mFromClauses.remove(new FromClause(tableName, tableAlias));
        return this;
    }

    public DatabaseQueryBuilder onEqual(String tableLeft, String columnLeft, String tableRight, String columnRight) {
        m7743on(Comparison.equals(new ComparisonColumn(tableLeft, columnLeft), new ComparisonColumn(tableRight, columnRight)));
        return this;
    }

    public DatabaseQueryBuilder onEqual(String tableLeft, String columnLeft, String value) {
        m7743on(Comparison.equals(new ComparisonColumn(tableLeft, columnLeft), value));
        return this;
    }

    public DatabaseQueryBuilder onEqualArgument(String tableLeft, String columnLeft) {
        return onEqual(tableLeft, columnLeft, "?");
    }

    public DatabaseQueryBuilder orOnEqual(String tableLeft, String columnLeft, String tableRight, String columnRight) {
        Comparison comparison = Comparison.equals(new ComparisonColumn(tableLeft, columnLeft), new ComparisonColumn(tableRight, columnRight));
        comparison.f6685or = true;
        m7743on(comparison);
        return this;
    }

    public DatabaseQueryBuilder orOnEqual(String tableLeft, String columnLeft, String value) {
        Comparison comparison = Comparison.equals(new ComparisonColumn(tableLeft, columnLeft), value);
        comparison.f6685or = true;
        m7743on(comparison);
        return this;
    }

    public DatabaseQueryBuilder orOnEqualArgument(String tableLeft, String columnLeft) {
        return orOnEqual(tableLeft, columnLeft, "?");
    }

    public DatabaseQueryBuilder onIn(String tableLeft, String columnLeft, ComparisonColumn... columns) {
        m7743on(Comparison.m7740in(new ComparisonColumn(tableLeft, columnLeft), columns));
        return this;
    }

    public DatabaseQueryBuilder onIn(String tableLeft, String columnLeft, String... values) {
        m7743on(Comparison.m7741in(new ComparisonColumn(tableLeft, columnLeft), values));
        return this;
    }

    public DatabaseQueryBuilder orOnIn(String tableLeft, String columnLeft, ComparisonColumn... columns) {
        Comparison comparison = Comparison.m7740in(new ComparisonColumn(tableLeft, columnLeft), columns);
        comparison.f6685or = true;
        m7743on(comparison);
        return this;
    }

    public DatabaseQueryBuilder orOnIn(String tableLeft, String columnLeft, String... values) {
        Comparison comparison = Comparison.m7741in(new ComparisonColumn(tableLeft, columnLeft), values);
        comparison.f6685or = true;
        m7743on(comparison);
        return this;
    }

    /* renamed from: on */
    private void m7743on(Comparison comparison) {
        if (this.mCurrentFromClause == null) {
            throw new IllegalStateException("You should call `from` before adding a join");
        } else if (this.mCurrentFromClause.currentJoin == null) {
            throw new IllegalStateException("You should add a Join before calling `on`");
        } else {
            this.mCurrentFromClause.currentJoin.mo31339on(comparison);
        }
    }

    private void updateColumnsWithAlias(String tableName, String tableAlias) {
        for (QueryColumn column : this.mColumns) {
            if (column.table.equals(tableName) && column.tableAlias == null) {
                column.tableAlias = tableAlias;
            }
        }
    }

    public DatabaseQueryBuilder whereEqual(String tableLeft, String columnLeft, String tableRight, String columnRight) {
        where(Comparison.equals(new ComparisonColumn(tableLeft, columnLeft), new ComparisonColumn(tableRight, columnRight)));
        return this;
    }

    public DatabaseQueryBuilder whereEqual(String tableLeft, String columnLeft, String value) {
        where(Comparison.equals(new ComparisonColumn(tableLeft, columnLeft), value));
        return this;
    }

    public DatabaseQueryBuilder whereEqualArgument(String tableLeft, String columnLeft) {
        return whereEqual(tableLeft, columnLeft, "?");
    }

    public DatabaseQueryBuilder whereLike(String tableLeft, String columnLeft, String value) {
        where(Comparison.like(new ComparisonColumn(tableLeft, columnLeft), value));
        return this;
    }

    public DatabaseQueryBuilder whereLikeArgument(String tableLeft, String columnLeft) {
        where(Comparison.like(new ComparisonColumn(tableLeft, columnLeft), "?"));
        return this;
    }

    private void where(Comparison comparison) {
        this.mWhereClauses.add(comparison);
    }

    public DatabaseQueryBuilder groupBy(String tableName, String column) {
        this.mGroupByColumns.add(new QueryColumn(tableName, column));
        return this;
    }

    public DatabaseQueryBuilder groupBy(QueryColumn column) {
        this.mGroupByColumns.add(column);
        return this;
    }

    public DatabaseQueryBuilder orderBy(String tableName, String column) {
        this.mOrderByClauses.add(new OrderByClause(new QueryColumn(tableName, column)));
        return this;
    }

    public DatabaseQueryBuilder orderByAsc(String tableName, String column) {
        this.mOrderByClauses.add(new OrderByClause(new QueryColumn(tableName, column), ORDER_ASC));
        return this;
    }

    public String getSQL() {
        int i;
        StringBuilder stringBuilder = new StringBuilder(this.mAction);
        for (i = 0; i < this.mColumns.size(); i++) {
            if (i > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(" ").append(((QueryColumn) this.mColumns.get(i)).toString());
        }
        stringBuilder.append(" from");
        Iterator<FromClause> iterator = this.mFromClauses.iterator();
        while (iterator.hasNext()) {
            stringBuilder.append(" ").append(((FromClause) iterator.next()).toString());
            if (iterator.hasNext()) {
                stringBuilder.append(",");
            }
        }
        if (!ListUtils.isEmpty(this.mWhereClauses)) {
            stringBuilder.append(" where");
            for (i = 0; i < this.mWhereClauses.size(); i++) {
                Comparison comparison = (Comparison) this.mWhereClauses.get(i);
                if (i > 0) {
                    if (comparison.f6685or) {
                        stringBuilder.append(" or");
                    } else {
                        stringBuilder.append(" and");
                    }
                }
                stringBuilder.append(" ").append(comparison.toString());
            }
        }
        if (!ListUtils.isEmpty(this.mGroupByColumns)) {
            stringBuilder.append(" group by");
            for (i = 0; i < this.mGroupByColumns.size(); i++) {
                if (i > 0) {
                    stringBuilder.append(",");
                }
                stringBuilder.append(" ").append(((QueryColumn) this.mGroupByColumns.get(i)).toString());
            }
        }
        if (!ListUtils.isEmpty(this.mOrderByClauses)) {
            stringBuilder.append(" order by");
            for (i = 0; i < this.mOrderByClauses.size(); i++) {
                if (i > 0) {
                    stringBuilder.append(",");
                }
                stringBuilder.append(" ").append(((OrderByClause) this.mOrderByClauses.get(i)).toString());
            }
        }
        return stringBuilder.toString();
    }

    private static String format(String... args) {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg);
        }
        return sb.toString();
    }
}
