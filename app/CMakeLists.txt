# Sets the minimum version of CMake required to build your native library.
# This ensures that a certain set of CMake features is available to
# your build.

cmake_minimum_required(VERSION 3.4.1)

# Specifies a library name, specifies whether the library is STATIC or
# SHARED, and provides relative paths to the source code. You can
# define multiple libraries by adding multiple add.library() commands,
# and CMake builds them for you. When you build your app, Gradle
# automatically packages shared libraries with your APK.




#####################################
##           OpenCV setup          ##
#####################################





set(OPENCV_DIR /home/shubham/Downloads/OpenCV-android-sdk/sdk/native/jni/include)
include_directories(${OPENCV_DIR})

add_library( lib_opencv SHARED IMPORTED )
set_target_properties(lib_opencv PROPERTIES IMPORTED_LOCATION ${CMAKE_CURRENT_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}/libopencv_java3.so)


#add_library( lib_opencv SHARED IMPORTED )

add_library( # Sets the name of the library.
              MyOpenCV
             # Sets the library as a shared library.
             SHARED
              # Provides a relative path to your source file(s).
             # Associated headers in the same location as their source
             # file are automatically included.
              src/main/jni/NativeOpenCV.cpp
             )

find_library( # Sets the name of the path variable.
                           log-lib

                           # Specifies the name of the NDK library that
                           # you want CMake to locate.
                          log )

target_link_libraries( # Specifies the target library.
                      MyOpenCV
                       # OpenCV lib
                       lib_opencv
                       # Links the target library to the log library
                       # included in the NDK.
                      ${log-lib}
                     )






