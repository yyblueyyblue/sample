//
// Created by Administrator on 2017/12/10.
//
#include "func.h"
#include<iostream>
#include <dlfcn.h>//打开SO库
using namespace std;

int func(int i , int j)
{

    int result;
    result = i + j;
    cout<<result;
    return  result;
}

int max(int num1, int num2)
{
    // 局部变量声明
    int result;

    if (num1 > num2)
        result = num1;
    else
        result = num2;

    return result;
}
