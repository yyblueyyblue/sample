package com.chen.baseapplication.kotlin

/**
 * Created by Administrator on 2017/12/2.
 */
class TwoKotlinClass {

    public fun getTwoKotlinClassName() :String{
        return "TwoKotlinClass";
    }

    fun getJavaClass(){
        val list = ArrayList<Int>();
        list.add(1)
        list.add(2)
        list.add(3)
        for(item in list){

        }
    }

    public fun getValue() : String = "hello kotlin two";
}