/*
 * Created by Shubham Srivastava (shubhamvns115@gmail.com)
 */

package com.example.shubham.facedetection;

public class NativeClass {

    public native static void detectFace(long matAddr);
    public native static void load();
    public native static int isComputing();

}
