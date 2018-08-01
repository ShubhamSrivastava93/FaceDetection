/*
 * Created by Shubham Srivastava (shubhamvns115@gmail.com)
 */

#include <opencv2/core/mat.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/objdetect.hpp>
#include <opencv2/highgui.hpp>
#include <opencv2/highgui.hpp>
#include <android/asset_manager.h>
#include <android/asset_manager_jni.h>
#include <android/log.h>
#include <jni.h>


#ifndef FACEDETECTION_NATIVEOPENCV_H
#define FACEDETECTION_NATIVEOPENCV_H

#endif

using namespace std;
using namespace cv;

#ifdef __cplusplus
extern "C" {
#endif

cv::String face_cascade_name = "/storage/emulated/0/shubham/haarcascade_frontalface_alt.xml";
cv::String eyes_cascade_name = "/storage/emulated/0/shubham/haarcascade_eye_tree_eyeglasses.xml";
CascadeClassifier face_cascade;
CascadeClassifier eyes_cascade;
int Computing= 0;

void detectFace(Mat & mat);

JNIEXPORT jint JNICALL Java_com_example_shubham_facedetection_NativeClass_isComputing(JNIEnv *, jclass);
JNIEXPORT void JNICALL Java_com_example_shubham_facedetection_NativeClass_detectFace(JNIEnv *, jclass, jlong);
JNIEXPORT void JNICALL Java_com_example_shubham_facedetection_NativeClass_load(JNIEnv *, jclass);
#ifdef __cplusplus
}
#endif