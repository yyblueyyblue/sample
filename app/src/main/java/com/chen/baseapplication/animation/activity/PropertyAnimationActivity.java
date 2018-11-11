package com.chen.baseapplication.animation.activity;

 import com.chen.baseapplication.R;
 import com.chen.baseapplication.animation.adapter.CustomPageAdapter;

 import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
 import android.support.v7.app.ActionBarActivity;


public class PropertyAnimationActivity extends ActionBarActivity {

	private ViewPager mViewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_propertyanimation);

	    
	    mViewPager = (ViewPager)findViewById(R.id.property_animation_viewpager);
	    mViewPager.setAdapter(new CustomPageAdapter(this, mViewPager));
	    mViewPager.setOffscreenPageLimit(0);
	    
	}
}
