//
// Created by Administrator on 2017/12/10.
//
#include <jni.h>
#include <string>
#include <iostream>
#include <dlfcn.h>//打开SO库

extern "C" {

JNIEXPORT jstring JNICALL
Java_com_chen_baseapplication_jni_JNISample_getSecondInfo(JNIEnv *env, jobject /* this */) {
    std::string hello = "第二个JNI文件，是否可行";
    return env->NewStringUTF(hello.c_str());
}

/**
 * 调用native-lib.so中方法
 * @param env
 * @param a
 * @param b
 * @return
 */
JNIEXPORT jint JNICALL
Java_com_chen_baseapplication_jni_JNISample_getSecondMax(JNIEnv *env,jobject /* this */,jint a, jint b) {

    jint ret;
    // dlopen 函数还会自动解析共享库中的依赖项。这样，如果您打开 了一个依赖于其他共享库的对象，它就会自动加载它们。
    // 函数返回一个句柄，该句柄用于后续的 API 调用
    void *  filehandle = dlopen("./libnative-lib.so", RTLD_LAZY );
    // 如果返回 NULL 句柄，表示无法找到对象文件，过程结束。
    // 否则的话，将会得到对象的一个句柄，可以进一步询问对象
    if (filehandle)
    {
//        typedef  int (* _maxnative)(int,int);
//        _maxnative    maxnative = NULL;
//        // 使用 dlsym 函数，尝试解析新打开的对象文件中的符号。
//        // 您将会得到一个有效的指向该符号的指针，或者是得到一个 NULL 并返回一个错误
//        maxnative = (_maxnative) dlsym(filehandle, "Java_com_chen_baseapplication_jni_JNISample_getFunc");
//        if (maxnative)
//        {
////            ret = env->(env, maxnative(a,b));
//            ret = maxnative(10,30);
//
//
//        }
//        else
//        {
//            // 如果返回 NULL 句柄,通过dlerror方法可以取得无法访问对象的原因
//            printf("Open Error:%s.\n",dlerror());
//            ret = 0;
//        }

        jint mun = 0;
        jint (*addFunc)(jint,jint) = NULL;
        addFunc = (jint (*)(jint,jint))dlsym(filehandle, "Java_com_chen_baseapplication_jni_JNISample_getFunc");
        if(addFunc)
            mun = addFunc(a, b);

        ret = mun;

        // 调用filehandle对象中的目标函数后，通过调用 dlclose 来关闭对它的访问
        dlclose(filehandle);
        filehandle = NULL;
    }
    else
    {
        // 如果返回 NULL 句柄,通过dlerror方法可以取得无法访问对象的原因
        printf("Open Error:%s.\n",dlerror());
        ret = -1;
    }

    return ret;

    return a > b? a : b;
}

}
