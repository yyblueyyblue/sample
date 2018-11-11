package com.chen.baseapplication.camera;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chen.baseapplication.R;

import java.io.IOException;

public class CameraActivity extends Activity implements View.OnClickListener{

    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private Camera mCamera;
    private ImageView showCameraImage;
    private int viewWidth,viewHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        int cameras = Camera.getNumberOfCameras();
        Log.e("tag","--------------"+cameras);
        initView();
    }

    void initView(){
        mSurfaceView = (SurfaceView)findViewById(R.id.surface_view_camera_activity);
        showCameraImage = (ImageView)findViewById(R.id.iv_show_camera_activity);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                initCamera();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                // 释放Camera资源
                if (mCamera != null) {
                    mCamera.stopPreview();
                    mCamera.release();
                }
            }
        });
        mSurfaceView.setOnClickListener(this);
    }

    void initCamera(){
        mCamera = Camera.open();
        mCamera.setDisplayOrientation(90);
        if(Build.VERSION.SDK_INT >= 17){
            mCamera.enableShutterSound(false);
        }
        if(mCamera != null){
            try {
                Camera.Parameters parameters = mCamera.getParameters();
                //设置预览照片的大小
                parameters.setPreviewFpsRange(viewWidth, viewHeight);
                //设置相机预览照片帧数
                parameters.setPreviewFpsRange(4, 10);
                //设置图片格式
                parameters.setPictureFormat(ImageFormat.JPEG);
                //设置图片的质量
                parameters.set("jpeg-quality", 90);
                //设置照片的大小
                parameters.setPictureSize(viewWidth, viewHeight);
                //通过SurfaceView显示预览
                mCamera.setPreviewDisplay(mSurfaceHolder);
                //开始预览
                mCamera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    Camera.AutoFocusCallback autoFocusCallback = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            if (success) {//对焦成功

                camera.takePicture(new Camera.ShutterCallback() {//按下快门
                    @Override
                    public void onShutter() {
                        //按下快门瞬间的操作
                    }
                }, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {//是否保存原始图片的信息

                    }
                }, pictureCallback);
            }
        }
    };

    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            final Bitmap resource = BitmapFactory.decodeByteArray(data, 0, data.length);
            if (resource == null) {
                Toast.makeText(CameraActivity.this, "拍照失败", Toast.LENGTH_SHORT).show();
            }
            final Matrix matrix = new Matrix();
            matrix.setRotate(90);
            final Bitmap bitmap = Bitmap.createBitmap(resource, 0, 0, resource.getWidth(), resource.getHeight(), matrix, true);
            if (bitmap != null && showCameraImage != null && showCameraImage.getVisibility() == View.GONE) {
                mCamera.stopPreview();
                showCameraImage.setVisibility(View.VISIBLE);
                mSurfaceView.setVisibility(View.GONE);
                Toast.makeText(CameraActivity.this, "拍照", Toast.LENGTH_SHORT).show();
                showCameraImage.setImageBitmap(bitmap);
            }
        }
    };

    @Override
    public void onClick(View view) {
        if (mCamera == null) return;
        //自动对焦后拍照
        mCamera.autoFocus(autoFocusCallback);
    }
}
