package com.ensighten.google.gson;

import com.ensighten.google.gson.internal.C$Gson$Preconditions;
import com.ensighten.google.gson.internal.Excluder;
import com.ensighten.google.gson.internal.bind.TypeAdapters;
import com.ensighten.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GsonBuilder {
    private boolean complexMapKeySerialization;
    private String datePattern;
    private int dateStyle = 2;
    private boolean escapeHtmlChars = true;
    private Excluder excluder = Excluder.DEFAULT;
    private final List<TypeAdapterFactory> factories = new ArrayList();
    private FieldNamingStrategy fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
    private boolean generateNonExecutableJson;
    private final List<TypeAdapterFactory> hierarchyFactories = new ArrayList();
    private final Map<Type, InstanceCreator<?>> instanceCreators = new HashMap();
    private LongSerializationPolicy longSerializationPolicy = LongSerializationPolicy.DEFAULT;
    private boolean prettyPrinting;
    private boolean serializeNulls;
    private boolean serializeSpecialFloatingPointValues;
    private int timeStyle = 2;

    public final GsonBuilder setVersion(double ignoreVersionsAfter) {
        this.excluder = this.excluder.withVersion(ignoreVersionsAfter);
        return this;
    }

    public final GsonBuilder excludeFieldsWithModifiers(int... modifiers) {
        this.excluder = this.excluder.withModifiers(modifiers);
        return this;
    }

    public final GsonBuilder generateNonExecutableJson() {
        this.generateNonExecutableJson = true;
        return this;
    }

    public final GsonBuilder excludeFieldsWithoutExposeAnnotation() {
        this.excluder = this.excluder.excludeFieldsWithoutExposeAnnotation();
        return this;
    }

    public final GsonBuilder serializeNulls() {
        this.serializeNulls = true;
        return this;
    }

    public final GsonBuilder enableComplexMapKeySerialization() {
        this.complexMapKeySerialization = true;
        return this;
    }

    public final GsonBuilder disableInnerClassSerialization() {
        this.excluder = this.excluder.disableInnerClassSerialization();
        return this;
    }

    public final GsonBuilder setLongSerializationPolicy(LongSerializationPolicy serializationPolicy) {
        this.longSerializationPolicy = serializationPolicy;
        return this;
    }

    public final GsonBuilder setFieldNamingPolicy(FieldNamingPolicy namingConvention) {
        this.fieldNamingPolicy = namingConvention;
        return this;
    }

    public final GsonBuilder setFieldNamingStrategy(FieldNamingStrategy fieldNamingStrategy) {
        this.fieldNamingPolicy = fieldNamingStrategy;
        return this;
    }

    public final GsonBuilder setExclusionStrategies(ExclusionStrategy... strategies) {
        for (ExclusionStrategy withExclusionStrategy : strategies) {
            this.excluder = this.excluder.withExclusionStrategy(withExclusionStrategy, true, true);
        }
        return this;
    }

    public final GsonBuilder addSerializationExclusionStrategy(ExclusionStrategy strategy) {
        this.excluder = this.excluder.withExclusionStrategy(strategy, true, false);
        return this;
    }

    public final GsonBuilder addDeserializationExclusionStrategy(ExclusionStrategy strategy) {
        this.excluder = this.excluder.withExclusionStrategy(strategy, false, true);
        return this;
    }

    public final GsonBuilder setPrettyPrinting() {
        this.prettyPrinting = true;
        return this;
    }

    public final GsonBuilder disableHtmlEscaping() {
        this.escapeHtmlChars = false;
        return this;
    }

    public final GsonBuilder setDateFormat(String pattern) {
        this.datePattern = pattern;
        return this;
    }

    public final GsonBuilder setDateFormat(int style) {
        this.dateStyle = style;
        this.datePattern = null;
        return this;
    }

    public final GsonBuilder setDateFormat(int dateStyle, int timeStyle) {
        this.dateStyle = dateStyle;
        this.timeStyle = timeStyle;
        this.datePattern = null;
        return this;
    }

    public final GsonBuilder registerTypeAdapter(Type type, Object typeAdapter) {
        boolean z = (typeAdapter instanceof JsonSerializer) || (typeAdapter instanceof JsonDeserializer) || (typeAdapter instanceof InstanceCreator) || (typeAdapter instanceof TypeAdapter);
        C$Gson$Preconditions.checkArgument(z);
        if (typeAdapter instanceof InstanceCreator) {
            this.instanceCreators.put(type, (InstanceCreator) typeAdapter);
        }
        if ((typeAdapter instanceof JsonSerializer) || (typeAdapter instanceof JsonDeserializer)) {
            this.factories.add(TreeTypeAdapter.newFactoryWithMatchRawType(TypeToken.get(type), typeAdapter));
        }
        if (typeAdapter instanceof TypeAdapter) {
            this.factories.add(TypeAdapters.newFactory(TypeToken.get(type), (TypeAdapter) typeAdapter));
        }
        return this;
    }

    public final GsonBuilder registerTypeAdapterFactory(TypeAdapterFactory factory) {
        this.factories.add(factory);
        return this;
    }

    public final GsonBuilder registerTypeHierarchyAdapter(Class<?> baseType, Object typeAdapter) {
        boolean z;
        if ((typeAdapter instanceof JsonSerializer) || (typeAdapter instanceof JsonDeserializer) || (typeAdapter instanceof TypeAdapter)) {
            z = true;
        } else {
            z = false;
        }
        C$Gson$Preconditions.checkArgument(z);
        if ((typeAdapter instanceof JsonDeserializer) || (typeAdapter instanceof JsonSerializer)) {
            this.hierarchyFactories.add(0, TreeTypeAdapter.newTypeHierarchyFactory(baseType, typeAdapter));
        }
        if (typeAdapter instanceof TypeAdapter) {
            this.factories.add(TypeAdapters.newTypeHierarchyFactory(baseType, (TypeAdapter) typeAdapter));
        }
        return this;
    }

    public final GsonBuilder serializeSpecialFloatingPointValues() {
        this.serializeSpecialFloatingPointValues = true;
        return this;
    }

    public final Gson create() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.factories);
        Collections.reverse(arrayList);
        arrayList.addAll(this.hierarchyFactories);
        addTypeAdaptersForDate(this.datePattern, this.dateStyle, this.timeStyle, arrayList);
        return new Gson(this.excluder, this.fieldNamingPolicy, this.instanceCreators, this.serializeNulls, this.complexMapKeySerialization, this.generateNonExecutableJson, this.escapeHtmlChars, this.prettyPrinting, this.serializeSpecialFloatingPointValues, this.longSerializationPolicy, arrayList);
    }

    private void addTypeAdaptersForDate(String datePattern, int dateStyle, int timeStyle, List<TypeAdapterFactory> factories) {
        Object defaultDateTypeAdapter;
        if (datePattern != null && !"".equals(datePattern.trim())) {
            defaultDateTypeAdapter = new DefaultDateTypeAdapter(datePattern);
        } else if (dateStyle != 2 && timeStyle != 2) {
            defaultDateTypeAdapter = new DefaultDateTypeAdapter(dateStyle, timeStyle);
        } else {
            return;
        }
        factories.add(TreeTypeAdapter.newFactory(TypeToken.get(Date.class), defaultDateTypeAdapter));
        factories.add(TreeTypeAdapter.newFactory(TypeToken.get(Timestamp.class), defaultDateTypeAdapter));
        factories.add(TreeTypeAdapter.newFactory(TypeToken.get(java.sql.Date.class), defaultDateTypeAdapter));
    }
}
