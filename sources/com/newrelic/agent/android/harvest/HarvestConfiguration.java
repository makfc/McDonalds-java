package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.activity.config.ActivityTraceConfiguration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class HarvestConfiguration {
    private static final int DEFAULT_ACTIVITY_TRACE_LENGTH = 65534;
    private static final int DEFAULT_ACTIVITY_TRACE_MAX_REPORT_ATTEMPTS = 1;
    private static final float DEFAULT_ACTIVITY_TRACE_MIN_UTILIZATION = 0.3f;
    private static final int DEFAULT_ERROR_LIMIT = 50;
    private static final int DEFAULT_MAX_TRANSACTION_AGE = 600;
    private static final int DEFAULT_MAX_TRANSACTION_COUNT = 1000;
    private static final int DEFAULT_REPORT_PERIOD = 60;
    private static final int DEFAULT_RESPONSE_BODY_LIMIT = 2048;
    private static final int DEFAULT_STACK_TRACE_LIMIT = 100;
    private static HarvestConfiguration defaultHarvestConfiguration;
    private int activity_trace_max_report_attempts;
    private int activity_trace_max_size;
    private double activity_trace_min_utilization;
    private ActivityTraceConfiguration at_capture;
    private boolean collect_network_errors;
    private String cross_process_id;
    private int data_report_period;
    private int[] data_token;
    private int error_limit;
    private int report_max_transaction_age;
    private int report_max_transaction_count;
    private int response_body_limit;
    private long server_timestamp;
    private int stack_trace_limit;

    public HarvestConfiguration() {
        setDefaultValues();
    }

    public void setDefaultValues() {
        setData_token(new int[2]);
        setCollect_network_errors(true);
        setData_report_period(60);
        setError_limit(50);
        setResponse_body_limit(2048);
        setStack_trace_limit(100);
        setReport_max_transaction_age(DEFAULT_MAX_TRANSACTION_AGE);
        setReport_max_transaction_count(1000);
        setActivity_trace_max_size(DEFAULT_ACTIVITY_TRACE_LENGTH);
        setActivity_trace_max_report_attempts(1);
        setActivity_trace_min_utilization(0.30000001192092896d);
        setAt_capture(ActivityTraceConfiguration.defaultActivityTraceConfiguration());
    }

    public static HarvestConfiguration getDefaultHarvestConfiguration() {
        if (defaultHarvestConfiguration != null) {
            return defaultHarvestConfiguration;
        }
        defaultHarvestConfiguration = new HarvestConfiguration();
        return defaultHarvestConfiguration;
    }

    public void reconfigure(HarvestConfiguration configuration) {
        setCollect_network_errors(configuration.isCollect_network_errors());
        if (configuration.getCross_process_id() != null) {
            setCross_process_id(configuration.getCross_process_id());
        }
        setData_report_period(configuration.getData_report_period());
        if (configuration.getDataToken().isValid()) {
            setData_token(configuration.getData_token());
        }
        setError_limit(configuration.getError_limit());
        setReport_max_transaction_age(configuration.getReport_max_transaction_age());
        setReport_max_transaction_count(configuration.getReport_max_transaction_count());
        setResponse_body_limit(configuration.getResponse_body_limit());
        setServer_timestamp(configuration.getServer_timestamp());
        setStack_trace_limit(configuration.getStack_trace_limit());
        setActivity_trace_min_utilization(configuration.getActivity_trace_min_utilization());
        setActivity_trace_max_report_attempts(configuration.getActivity_trace_max_report_attempts());
        if (configuration.getAt_capture() != null) {
            setAt_capture(configuration.getAt_capture());
        }
    }

    public void setCollect_network_errors(boolean collect_network_errors) {
        this.collect_network_errors = collect_network_errors;
    }

    public void setCross_process_id(String cross_process_id) {
        this.cross_process_id = cross_process_id;
    }

    public void setData_report_period(int data_report_period) {
        this.data_report_period = data_report_period;
    }

    public void setData_token(int[] data_token) {
        this.data_token = data_token;
    }

    public DataToken getDataToken() {
        if (this.data_token == null) {
            return null;
        }
        return new DataToken(this.data_token[0], this.data_token[1]);
    }

    public void setError_limit(int error_limit) {
        this.error_limit = error_limit;
    }

    public void setReport_max_transaction_age(int report_max_transaction_age) {
        this.report_max_transaction_age = report_max_transaction_age;
    }

    public void setReport_max_transaction_count(int report_max_transaction_count) {
        this.report_max_transaction_count = report_max_transaction_count;
    }

    public void setResponse_body_limit(int response_body_limit) {
        this.response_body_limit = response_body_limit;
    }

    public void setServer_timestamp(long server_timestamp) {
        this.server_timestamp = server_timestamp;
    }

    public void setStack_trace_limit(int stack_trace_limit) {
        this.stack_trace_limit = stack_trace_limit;
    }

    public void setActivity_trace_max_size(int activity_trace_max_size) {
        this.activity_trace_max_size = activity_trace_max_size;
    }

    public void setActivity_trace_max_report_attempts(int activity_trace_max_report_attempts) {
        this.activity_trace_max_report_attempts = activity_trace_max_report_attempts;
    }

    public boolean isCollect_network_errors() {
        return this.collect_network_errors;
    }

    public String getCross_process_id() {
        return this.cross_process_id;
    }

    public int getData_report_period() {
        return this.data_report_period;
    }

    public int[] getData_token() {
        return this.data_token;
    }

    public int getError_limit() {
        return this.error_limit;
    }

    public int getReport_max_transaction_age() {
        return this.report_max_transaction_age;
    }

    public long getReportMaxTransactionAgeMilliseconds() {
        return TimeUnit.MILLISECONDS.convert((long) this.report_max_transaction_age, TimeUnit.SECONDS);
    }

    public int getReport_max_transaction_count() {
        return this.report_max_transaction_count;
    }

    public int getResponse_body_limit() {
        return this.response_body_limit;
    }

    public long getServer_timestamp() {
        return this.server_timestamp;
    }

    public int getStack_trace_limit() {
        return this.stack_trace_limit;
    }

    public int getActivity_trace_max_size() {
        return this.activity_trace_max_size;
    }

    public int getActivity_trace_max_report_attempts() {
        return this.activity_trace_max_report_attempts;
    }

    public ActivityTraceConfiguration getAt_capture() {
        return this.at_capture;
    }

    public void setAt_capture(ActivityTraceConfiguration at_capture) {
        this.at_capture = at_capture;
    }

    public double getActivity_trace_min_utilization() {
        return this.activity_trace_min_utilization;
    }

    public void setActivity_trace_min_utilization(double activity_trace_min_utilization) {
        this.activity_trace_min_utilization = activity_trace_min_utilization;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HarvestConfiguration that = (HarvestConfiguration) o;
        if (this.collect_network_errors != that.collect_network_errors || this.data_report_period != that.data_report_period || this.error_limit != that.error_limit || this.report_max_transaction_age != that.report_max_transaction_age || this.report_max_transaction_count != that.report_max_transaction_count || this.response_body_limit != that.response_body_limit || this.stack_trace_limit != that.stack_trace_limit || this.activity_trace_max_size != that.activity_trace_max_size || this.activity_trace_max_report_attempts != that.activity_trace_max_report_attempts) {
            return false;
        }
        if (this.cross_process_id == null && that.cross_process_id != null) {
            return false;
        }
        if (this.cross_process_id != null && that.cross_process_id == null) {
            return false;
        }
        if ((this.cross_process_id == null || this.cross_process_id.equals(that.cross_process_id)) && ((int) this.activity_trace_min_utilization) * 100 == ((int) that.activity_trace_min_utilization) * 100) {
            return Arrays.equals(this.data_token, that.data_token);
        }
        return false;
    }

    public int hashCode() {
        int result;
        int hashCode;
        int i = 0;
        if (this.collect_network_errors) {
            result = 1;
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.cross_process_id != null) {
            hashCode = this.cross_process_id.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (((i2 + hashCode) * 31) + this.data_report_period) * 31;
        if (this.data_token != null) {
            hashCode = Arrays.hashCode(this.data_token);
        } else {
            hashCode = 0;
        }
        result = ((((((((((((((i2 + hashCode) * 31) + this.error_limit) * 31) + this.report_max_transaction_age) * 31) + this.report_max_transaction_count) * 31) + this.response_body_limit) * 31) + this.stack_trace_limit) * 31) + this.activity_trace_max_size) * 31) + this.activity_trace_max_report_attempts;
        long temp = Double.doubleToLongBits(this.activity_trace_min_utilization);
        hashCode = ((result * 31) + ((int) ((temp >>> 32) ^ temp))) * 31;
        if (this.at_capture != null) {
            i = this.at_capture.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "HarvestConfiguration{collect_network_errors=" + this.collect_network_errors + ", cross_process_id='" + this.cross_process_id + '\'' + ", data_report_period=" + this.data_report_period + ", data_token=" + Arrays.toString(this.data_token) + ", error_limit=" + this.error_limit + ", report_max_transaction_age=" + this.report_max_transaction_age + ", report_max_transaction_count=" + this.report_max_transaction_count + ", response_body_limit=" + this.response_body_limit + ", server_timestamp=" + this.server_timestamp + ", stack_trace_limit=" + this.stack_trace_limit + ", activity_trace_max_size=" + this.activity_trace_max_size + ", activity_trace_max_report_attempts=" + this.activity_trace_max_report_attempts + ", activity_trace_min_utilization=" + this.activity_trace_min_utilization + ", at_capture=" + this.at_capture + '}';
    }
}
