# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

#CMake最小版本使用的是3.4.1。
#用来设置在编译本地库时我们需要的最小的cmake版本，AndroidStudio自动生成，我们几乎不需要自己管
cmake_minimum_required(VERSION 3.4.1)

#设置生成的so动态库最后输出的路径
#set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI})


# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
#创建和命名一个库，设置为静态库或共享，并提供其源代码的相对路径。
#您可以定义多个库，并且CMake为您构建它们。
# Gradle自动将共享库与APK打包。

#配置so库信息（为当前当前脚本文件添加库）
#add_library用来设置编译生成的本地库的名字为native-lib，
#SHARED表示编译生成的是动态链接库(这个概念前面已经提到过了)，
#src/main/cpp/native-lib.cpp表示参与编译的文件的路径，这里面可以写多个文件的路径。
add_library( # Sets the name of the library.
            native-lib
            #这个是声明引用so库的名称，
            #在项目中，如果需要使用这个so文件，引用的名称就是这个。
            #值得注意的是，实际上生成的so文件名称是libnative-lib。
            #当Run项目或者build项目是，在Module级别的build文件下的intermediates\
            #transforms\mergeJniLibs\debug\folders\2000\1f\main下会生成相应的so库文件。



             # Sets the library as a shared library.
             SHARED
             #这个参数表示共享so库文件，
             #也就是在Run项目或者build项目时会在目录intermediates\transforms
             #\mergeJniLibs\debug\folders\2000\1f\main下生成so库文。
             #此外，so库文件都会在打包到.apk里面，可以通过选择菜单栏的Build->Analyze Apk...*
             #查看apk中是否存在so库文件，一般它会存放在lib目录下。
             #STATIC：静态库，是目标文件的归档文件，在链接其它目标的时候使用。
             #SHARED：动态库，会被动态链接，在运行时被加载。
             #MODULE：模块库，是不会被链接到其它目标中的插件，但是可能会在运行时使用dlopen-系列的函数动态链接。


             # Provides a relative path to your source file(s).
             #构建so库的源文件。
             src/main/cpp/native-lib.cpp
             src/main/cpp/func.cpp)

             add_library( # Sets the name of the library.
                         nativetemp-lib

                         SHARED

                         src/main/cpp/nativetemp-lib.cpp)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.
#搜索指定的预建库并将路径存储为a变量。
#因为CMake在搜索路径中包含了系统库
#默认情况下，只需要指定您想要添加的公共NDK库的名称
#CMake验证该库是否存在
#完成构建。


#find_library 是用来添加一些我们在编译我们的本地库的时候需要依赖的一些库，
#由于cmake已经知道系统库的路径，所以我们这里只是指定使用log库，
#然后给log库起别名为log-lib便于我们后面引用，
#此处的log库是我们后面调试时需要用来打log日志的库,是NDK为我们提供的。
find_library( # Sets the name of the path variable.
                #这个指定的是在NDK库中每个类型的库会存放一个特定的位置，而log库存放在log-lib中
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              #指定使用log库
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
#指定库CMake应该链接到目标库。
#您可以链接多个库,比如库定义在这个构建脚本,预先构建的第三方库,或者系统库


#target_link_libraries 是为了关联我们自己的库和一些第三方库或者系统库，
#这里把我们把自己的库native-lib库和log库关联起来。
#如果你本地的库（native-lib）想要调用log库的方法，
#那么就需要配置这个属性，意思是把NDK库关联到本地库。
target_link_libraries( # Specifies the target library.
                       #要被关联的库名称
                       native-lib
                       nativetemp-lib
                       # Links the target library to the log library
                       # included in the NDK.
                       #要关联的库名称，要用大括号包裹，前面还要有$符号去引用。
                       ${log-lib} )
#target_link_libraries( # Specifies the target library.
                       #要被关联的库名称
                       #nativetemp-lib

                       # Links the target library to the log library
                       # included in the NDK.
                       #要关联的库名称，要用大括号包裹，前面还要有$符号去引用。
                       #${log-lib} )

#拓展之使用第三方so库
#在一些情况下，我们没有能力开发so库，当别人抛一个库过来的时候我们直接使用就好了。
#首先，我们告诉脚本我们只需要导入so库，不需要构建操作。
#add_library( imported-lib
#             SHARED
#             IMPORTED )
#•IMPORTED
#表示只需要导入，不需要构建so库。
#接着，我们要设置so库的路径了
#set_target_properties(target1 target2 ...
#                      PROPERTIES prop1 value1
#                      prop2 value2 ...)
#举例子：set_target_properties(
#                      imported-lib // so库的名称
#                      PROPERTIES IMPORTED_LOCATION // import so库
#                      libs/libimported-lib.so // so库路径)
#当使用已经存在so库时，不应该配置target_link_libraries()方法，
#因为只有在build 库文件时才能进行link操作.

#当前cmakelists.txt所在目录的所有.c .cpp源文件
#AUX_SOURCE_DIRECTORY(. SRC_LIST)
#增加其他目录的源文件到集合变量中
#list(APPEND SRC_LIST
#       ../common/1.c
#       ../common/2.c
#       ../common/3.c
#       ../common/4.c
#        ../common/5.c
#       ../common/WriteLog.c
#    )
#生成so库，直接使用变量代表那些.c文件集合
#add_library(mylib SHARED ${SRC_LIST})