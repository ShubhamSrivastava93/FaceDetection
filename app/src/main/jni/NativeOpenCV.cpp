/*
 * Created by Shubham Srivastava (shubhamvns115@gmail.com)
 */

#include "NativeOpenCV.h"

JNIEXPORT jint JNICALL Java_com_example_shubham_facedetection_NativeClass_isComputing(JNIEnv *, jclass){

    return (jint)Computing;
}


JNIEXPORT void JNICALL Java_com_example_shubham_facedetection_NativeClass_load(JNIEnv *, jclass){

    face_cascade.load(face_cascade_name);
}

JNIEXPORT void JNICALL Java_com_example_shubham_facedetection_NativeClass_detectFace(JNIEnv *, jclass,jlong matAddr){
    Computing=0;
    Mat & mat = *(Mat *)matAddr;
    detectFace(mat);
    Computing=1;
}

void detectFace(Mat& mat){
        vector<Rect> faces;
        Mat frame_gray;
        cvtColor( mat, frame_gray, COLOR_BGRA2GRAY );
        equalizeHist( frame_gray,frame_gray );

        //-- Detect faces
        face_cascade.detectMultiScale( frame_gray, faces, 1.1, 2, 0|CASCADE_SCALE_IMAGE, Size(30, 30) );
        for ( size_t i = 0; i < faces.size(); i++ )
        {
            Point center( faces[i].x + faces[i].width/2, faces[i].y + faces[i].height/2 );
            ellipse( mat, center, Size( faces[i].width/2, faces[i].height/2 ), 0, 0, 360, Scalar( 255, 0, 255 ), 4, 8, 0 );
            Mat faceROI = frame_gray( faces[i] );
        }

}

