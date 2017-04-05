# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Program\Learning\Android\adt-bundle-windows-x86_64-20140702\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-optimizationpasses 9                                                           # 指定代码的压缩级别
-dontusemixedcaseclassnames                                                     # 是否使用大小写混合
-dontskipnonpubliclibraryclasses                                                # 是否混淆第三方jar
-dontpreverify                                                                  # 混淆时是否做预校验
-keepattributes SourceFile,LineNumberTable                                      # 混淆号错误信息里带上代码行
-verbose                                                                        # 混淆时是否记录日志
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*        # 混淆时所采用的算法

-repackageclasses ''
-allowaccessmodification
-dontwarn


# keep 4大组件， application
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

# 自定义的view类
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}
#To maintain custom components names that are used on layouts XML:
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
}
-keep public class * extends android.view.View {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keep public class * extends android.view.View {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# Serializables类
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# support v7类
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }

#Compatibility library
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment

# 第三方包类
#-libraryjars libs/jackson-all-1.9.11.jar
#-keep class org.codehaus.jackson.**{*;}

# keep 类成员
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# keep parcelable
-keepclassmembers class * implements android.os.Parcelable {
    static android.os.Parcelable$Creator CREATOR;
}

#Keep the R
-keepclassmembers class **.R$* {
    public static <fields>;
}

-keepclassmembers public class * extends android.view.View {
  void set*(***);
  *** get*();
}

#Maintain java native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

#Maintain enums
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Remove Logging
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** w(...);
    public static *** v(...);
    public static *** i(...);
}

-ignorewarnings



# others =============================================================================== 参考

# Android Support Library类
-keep class android.** {*;}

# 不混淆的第三方包

-keep class com.xui.launcher.icarmusic.view.**{*; }
-keep class org.mozilla.universalchardet.**{*;}
-keep class org.jaudiotagger.**{*;}
-keep class org.codehaus.jackson.**{*;}




# Baidu Map
-keep class com.baidu.mapapi.** {*; }
-keep class com.baidu.location.** {*; }
-keep class com.baidu.platform.** {*; }
-keep class com.baidu.vi.** {*; }
-keep class vi.com.gdi.bgl.android.java.** {*; }

# Baidu Push
-keep class com.baidu.android.** {*; }
-keep class com.baidu.loctp.** {*; }

-keep class com.nineoldandroids.** {*; }

# Baidu Tongji
-keep class com.baidu.a.a.a.** {*; }
-keep class com.baidu.mobstat.** {*; }

# slidingmenu
-keep class com.jeremyfeinstein.slidingmenu.lib.** {*; }

# listviewAnimations
-keep class com.haarman.listviewanimations.** {*; }

#ACRA specifics
# we need line numbers in our stack traces otherwise they are pretty useless
-renamesourcefileattribute SourceFile

# ACRA needs "annotations" so add this...
-keepattributes *Annotation*