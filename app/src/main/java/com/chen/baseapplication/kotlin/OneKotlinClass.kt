package com.chen.baseapplication.kotlin

/**
 * Created by Administrator on 2017/12/2.
 */
class OneKotlinClass(name : String) {

    val kt = TwoKotlinClass();

    fun getClassName(name : String?) : String {
        return "hello ${name}";
    }

    fun getOtherClassName() : String {

        return kt.getTwoKotlinClassName();
    }

    fun getOtherClassNameStep2() : String = kt.getValue();
}