# for fabrics
-keep public class * extends java.lang.Exception
#-keepresourcexmlelements manifest/application/meta-data@name=io.fabric.ApiKey
-printmapping mapping.txt
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**
# for google play service

-dontwarn com.google.android.gms.location.**
-dontwarn com.google.android.gms.gcm.**
-dontwarn com.google.android.gms.iid.**

-keep class com.google.android.gms.gcm.** { *; }
-keep class com.google.android.gms.iid.** { *; }
-keep class com.google.android.gms.location.** { *; }

#retrofit

# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**

-keep public enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class com.google.firebase.iid.FirebaseInstanceId {
    public *;
}

# Jackson
-keep @com.fasterxml.jackson.annotation.JsonIgnoreProperties class * { *; }
-keep class com.fasterxml.** { *; }
-keep class org.codehaus.** { *; }
-keepnames class com.fasterxml.jackson.** { *; }
-keepclassmembers public final enum com.fasterxml.jackson.annotation.JsonAutoDetect$Visibility {
    public static final com.fasterxml.jackson.annotation.JsonAutoDetect$Visibility *;
}
-keep class com.lkb.baseandroidproject.model.** { *; }

-keep class com.github.claucookie.** { *; }
#-keep class io.reactivex.** { *; }
#-keep class androidx.** { *; }
# for kotlin
-keep class kotlin.** { *; }
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
}

# General
-keepattributes SourceFile,LineNumberTable,*Annotation*,EnclosingMethod,Signature,Exceptions,InnerClasses