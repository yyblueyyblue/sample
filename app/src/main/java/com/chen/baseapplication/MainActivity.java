package com.chen.baseapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.chen.baseapplication.TouchEvent.TouchEventMainActivity;
import com.chen.baseapplication.activitylanchmode.LaunchModeActivity;
import com.chen.baseapplication.animation.AnimationMainActivity;
import com.chen.baseapplication.animation.activity.PropertyAnimationActivity;
import com.chen.baseapplication.camera.CameraActivity;
import com.chen.baseapplication.canvas.CanvasDrawViewActivity;
import com.chen.baseapplication.handelrThread.HandlerThreadActivity;
import com.chen.baseapplication.jni.JNIActivity;
import com.chen.baseapplication.kotlin.OneKotlinClass;
import com.chen.baseapplication.maxtri.MaxtriDemoActivity;
import com.chen.baseapplication.opengl.OpenGLOneActivity;
import com.chen.baseapplication.service.ServiceActivity;
import com.chen.baseapplication.tagview.TagViewActivity;
import com.chen.baseapplication.test.TestAddViewActivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView mListView;
    private ArrayAdapter mAdapter;
    private ArrayList<String> mListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView)findViewById(R.id.lv_main);
        addData();
        mAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,mListData);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }
    private void addData() {
        mListData = new ArrayList<String>();
        mListData.add("animation");
        mListData.add("TouchEvent");
        mListData.add("TagView");
        mListData.add("Camera");
        mListData.add("OpenGL");
        mListData.add("HandlerThread");
        mListData.add("LaunchMode");
        mListData.add("service");
        mListData.add("JNI");
        mListData.add("Maxtri");
        mListData.add("Canvas");
        mListData.add("Test");

        OneKotlinClass kt = new OneKotlinClass("oneKotlinClass");
        mListData.add(kt.getClassName("oneKotlinClass"));
        mListData.add(kt.getOtherClassName());
        mListData.add(kt.getOtherClassNameStep2());

        Log.e("tag", findLibrary1(this, "native-lib"));
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(position ) {
            case 0:
//                startActivity(new Intent(this, AnimationMainActivity.class));
                startActivity(new Intent(this,  PropertyAnimationActivity.class));
            break;
            case 1:
                startActivity(new Intent(this, TouchEventMainActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, TagViewActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, CameraActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, OpenGLOneActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, HandlerThreadActivity.class));
                break;
            case 6:
                startActivity(new Intent(this, LaunchModeActivity.class));
                break;
            case 7:
                startActivity(new Intent(this, ServiceActivity.class));
                break;
            case 8:
                startActivity(new Intent(this, JNIActivity.class));
                break;
            case 9:
                startActivity(new Intent(this, MaxtriDemoActivity.class));
                break;

            case 10:
                startActivity(new Intent(this,CanvasDrawViewActivity.class));
                break;

            case 11:
                startActivity(new Intent(this,TestAddViewActivity.class));
                break;

            default:
                showToast(mListData.get(position));
                break;

        }
    }

    void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    public  String findLibrary1(Context context, String libName) {
        String result = null;
        ClassLoader classLoader = (context.getClassLoader());
        if (classLoader != null) {
            try {
                Method findLibraryMethod = classLoader.getClass().getMethod("findLibrary", new Class<?>[] { String.class });
                if (findLibraryMethod != null) {
                    Object objPath = findLibraryMethod.invoke(classLoader, new Object[] { libName });
                    if (objPath != null && objPath instanceof String) {
                        result = (String) objPath;
                    }
                }
            } catch (NoSuchMethodException e) {
                Log.e("findLibrary1", e.toString());
            } catch (IllegalAccessException e) {
                Log.e("findLibrary1", e.toString());
            } catch (IllegalArgumentException e) {
                Log.e("findLibrary1", e.toString());
            } catch (InvocationTargetException e) {
                Log.e("findLibrary1", e.toString());
            } catch (Exception e) {
                Log.e("findLibrary1", e.toString());
            }
        }

        return result;
    }

}
