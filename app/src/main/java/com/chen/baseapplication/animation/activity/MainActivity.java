package com.chen.baseapplication.animation.activity;

 import android.app.Activity;
 import android.content.Intent;

import android.os.Bundle;

import android.view.View;

 import com.chen.baseapplication.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_main_new);
	}
	
	public void propertyAnimation(View view){
		intentChange(PropertyAnimationActivity.class);
	}
	
	public void viewAnimation(View view){
		intentChange(ViewAnimationActivity.class);
		overridePendingTransition(R.anim.page_right_in,R.anim.page_right_out);
	}

	public void drawableAnimation(View view){
		intentChange(DrawableAnimationActivity.class);
		
	}
	
	public <T> void intentChange(Class<T> mClass){
    	Intent mIntent = new Intent();
    	mIntent.setClass(getApplicationContext(), mClass);
    	this.startActivity(mIntent);
    }
}
