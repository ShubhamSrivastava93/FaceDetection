

package com.example.shubham.facedetection;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;



public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2{

   JavaCameraView javaCameraView;
   Mat mat;
   int MY_PERMISSIONS_REQUEST_CAMERA=1;
   boolean isDataReady=false;

    static {
        System.loadLibrary("opencv_java3");
        System.loadLibrary("MyOpenCV");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        javaCameraView= findViewById(R.id.javaCameraView);
        javaCameraView.setCvCameraViewListener(this);
    }


    public void checkPermission() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                ) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        }
       else {
            NativeClass.load();
            isDataReady=true;
        }

    }

    void writeData(){

        try {
            File file = new File(Environment.getExternalStorageDirectory(), "shubham");
            if(!file.exists()) {
                file.mkdir();
                InputStream inputStream= getAssets().open("haarcascade_frontalface_alt.xml");
                File wf= new File(file,"haarcascade_frontalface_alt.xml");
                byte []data= new byte[inputStream.available()];
                inputStream.read(data);
                FileOutputStream fileOutputStream= new FileOutputStream(wf);
                fileOutputStream.write(data);
                inputStream.close();
                fileOutputStream.close();
                isDataReady=true;
                NativeClass.load();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==MY_PERMISSIONS_REQUEST_CAMERA){
            writeData();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        javaCameraView.enableView();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(javaCameraView!=null)
            javaCameraView.disableView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        javaCameraView.disableView();
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mat = new Mat(height,width, CvType.CV_8UC4);

    }

    @Override
    public void onCameraViewStopped() {
        mat.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mat =inputFrame.rgba();
        if(isDataReady) {
        NativeClass.detectFace(mat.getNativeObjAddr());
        return mat;
        }else {
            return mat;
        }

    }
}
