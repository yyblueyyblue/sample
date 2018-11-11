#include <jni.h>
#include <string>
#include <iostream>
#include "func.h"
#include <android/log.h>
#include <dlfcn.h>

extern "C"{

JNIEXPORT jstring JNICALL
Java_com_chen_baseapplication_jni_JNISample_getVersionInfo(JNIEnv *env,jobject /* this */) {
    std::string hello = "Android NDK information from C++";
    return env->NewStringUTF(hello.c_str());
}

JNIEXPORT jstring JNICALL
Java_com_chen_baseapplication_jni_JNISample_getCount(JNIEnv *env,jobject /* this */) {
std::string hello = "1+1 = 2";
return env->NewStringUTF(hello.c_str());
}

JNIEXPORT jstring JNICALL
Java_com_chen_baseapplication_jni_JNISample_getNext(JNIEnv *env,jobject /* this */) {

    jclass jnisample = env->FindClass("com/chen/baseapplication/jni/JNISample");
    if(NULL == jnisample){//获取类空判断
        std::string hello = "null class";
        return env->NewStringUTF(hello.c_str());
    }
    jmethodID getvalue = env->GetStaticMethodID(jnisample,"getStringFromJavaStatic","()V");
    if(NULL == getvalue){
        (env)->DeleteLocalRef(jnisample);
        std::string hello = "null method";
        printf("can't find method getStringFromStatic from JniHandle ");
        return env->NewStringUTF(hello.c_str());
    }
    env -> CallStaticVoidMethod(jnisample, getvalue);


/**
 * 以下是非静态方法调用，这个需要创建对象
 */
    jmethodID constructor = env->GetMethodID(jnisample, "<init>", "()V");
    if (NULL == constructor) {
        std::string hello = "null constructor";
        return env->NewStringUTF(hello.c_str());
    }
    jobject jniHandleObject = env->NewObject(jnisample, constructor);
    if (NULL == jniHandleObject) {
        std::string hello = "null object";
        return env->NewStringUTF(hello.c_str());
    }
    jmethodID getStringForJavas = env->GetMethodID(jnisample,"getStringForJava","()Ljava/lang/String;");


    if(NULL != getStringForJavas ){
        jstring jstring1 = (jstring) env->CallObjectMethod(jniHandleObject, getStringForJavas);
        return jstring1;
//        char*   rtn   =   NULL;
//        jclass   clsstring   =   env->FindClass("java/lang/String");
//        jstring   strencode   =   env->NewStringUTF("UTF-8");
//        jmethodID   mid   =   env->GetMethodID(clsstring,   "getBytes",   "(Ljava/lang/String;)[B");
//        jbyteArray   barr=   (jbyteArray)env->CallObjectMethod(jstring1,mid,strencode);
//        jsize   alen   =   env->GetArrayLength(barr);
//        jbyte*   ba   =   env->GetByteArrayElements(barr,JNI_FALSE);
//        if(alen   >   0)
//        {
//            rtn   =   (char*)malloc(alen+1);
//            memcpy(rtn,ba,alen);
//            rtn[alen]=0;
//        }
//        env->ReleaseByteArrayElements(barr,ba,0);
//        std::string stemp(rtn);
//        free(rtn);
//        return env->NewStringUTF(stemp.c_str());
    }

    std::string OKend = "OK";
    return env->NewStringUTF(OKend.c_str());
//    return env->NewStringUTF(hello.c_str());
}

JNIEXPORT jint JNICALL
Java_com_chen_baseapplication_jni_JNISample_getFunc(JNIEnv *env,jobject /* this */,jint a, jint b) {
//    return 12345;

    int result = func(a,b);

    jint tm = result;
    return tm;
}

}
