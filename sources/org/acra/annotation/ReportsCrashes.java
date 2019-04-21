package org.acra.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.acra.BaseCrashReportDialog;
import org.acra.CrashReportDialog;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.sender.HttpSender.Method;
import org.acra.sender.HttpSender.Type;

@Inherited
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReportsCrashes {
    String[] additionalDropBoxTags() default {};

    String[] additionalSharedPreferences() default {};

    String applicationLogFile() default "";

    int applicationLogFileLines() default 100;

    Class buildConfigClass() default Object.class;

    int connectionTimeout() default 5000;

    ReportField[] customReportContent() default {};

    boolean deleteOldUnsentReportsOnApplicationStart() default true;

    boolean deleteUnapprovedReportsOnApplicationStart() default true;

    int dropboxCollectionMinutes() default 5;

    String[] excludeMatchingSettingsKeys() default {};

    String[] excludeMatchingSharedPreferencesKeys() default {};

    boolean forceCloseDialogAfterToast() default false;

    String formUri() default "";

    String formUriBasicAuthLogin() default "ACRA-NULL-STRING";

    String formUriBasicAuthPassword() default "ACRA-NULL-STRING";

    Method httpMethod() default Method.POST;

    boolean includeDropBoxSystemTags() default false;

    String[] logcatArguments() default {"-t", "100", "-v", "time"};

    boolean logcatFilterByPid() default false;

    String mailTo() default "";

    ReportingInteractionMode mode() default ReportingInteractionMode.SILENT;

    Class<? extends BaseCrashReportDialog> reportDialogClass() default CrashReportDialog.class;

    Type reportType() default Type.FORM;

    int resDialogCommentPrompt() default 0;

    int resDialogEmailPrompt() default 0;

    int resDialogIcon() default 17301543;

    int resDialogNegativeButtonText() default 17039360;

    int resDialogOkToast() default 0;

    int resDialogPositiveButtonText() default 17039370;

    int resDialogText() default 0;

    int resDialogTitle() default 0;

    int resNotifIcon() default 17301624;

    int resNotifText() default 0;

    int resNotifTickerText() default 0;

    int resNotifTitle() default 0;

    int resToastText() default 0;

    boolean sendReportsAtShutdown() default true;

    boolean sendReportsInDevMode() default true;

    int sharedPreferencesMode() default 0;

    String sharedPreferencesName() default "";

    int socketTimeout() default 8000;
}
