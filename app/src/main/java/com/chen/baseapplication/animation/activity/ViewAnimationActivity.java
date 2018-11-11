package com.chen.baseapplication.animation.activity;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AlphabetIndexer;
import android.widget.ImageView;
import android.os.Build;

import com.chen.baseapplication.R;

public class ViewAnimationActivity extends ActionBarActivity {

	private ImageView mImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewanimation);
		mImageView = (ImageView)findViewById(R.id.view_animation_imageview);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.view_animation_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Animation mAnimation = null;
		switch (item.getItemId()) {
		case R.id.view_animation_alpha:
			mAnimation = getAlphaAnimation();
			break;
		case R.id.view_animation_scale:
			mAnimation = getScaleAnimation();
			break;
		case R.id.view_animation_translate:
			mAnimation = getTranslateAnimation();
			break;
		case R.id.view_animation_rotate:
			mAnimation = getRotateAnimation();
			break;
		case R.id.view_animation_set:
			mAnimation = getSetAnimation();
			break;
		default:
			break;
		}
		
		//���ÿؼ���ʼִ�ж���
		if(mImageView != null){
			ImageView mImageView = (ImageView)findViewById(R.id.view_animation_imageview);
			//���ÿؼ���ʼִ�ж���
			mImageView.startAnimation(mAnimation);
		}
		return super.onOptionsItemSelected(item);
	}
	
	public Animation getAlphaAnimation(){
		Animation mAnimation = null;
		/**
		 * ���봴��ͼƬ����(Alpha)����
		 * ʵ����AlphaAnimation����
		 * mAnimation = new AlphaAnimation(fromAlpha, toAlpha);
		 * fromAlpha:���ö�����ʼʱ�ؼ���͸���ȣ�0.0Ϊ͸�����ؼ�����ʾ��1.0Ϊ��͸�����ؼ�ȫ����ʾ
		 * toAlpha:���ö�������ʱ�Ŀؼ���͸����
		 */
		mAnimation = new AlphaAnimation(0.0f, 1.0f);
		
		if(mAnimation != null){
			setAnimationFlags(mAnimation);
		}
		
		/**
		 * xml�ļ�����ͼƬ����(Alpha)����
		 */
		// mAnimation = AnimationUtils.loadAnimation(this, R.anim.view_animation_alpha);
		return mAnimation;
	}
	
	public Animation getScaleAnimation(){
		Animation mAnimation = null;
		/**
		 * ���봴��ͼƬ����(Scale)����
		 * mAnimation = new ScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType, pivotYValue);
		 * fromX:���ö�����ʼʱX�᷽���������ʼֵ��0.0ΪX�᷽��Ŀؼ�����һ�㣬1.0ΪX�᷽��Ŀؼ�������;
		 * toX:���ö�������ʱX�᷽������Ž���ֵ;
		 * fromY:���ö�����ʼʱY�᷽���������ʼֵ��0.0ΪY�᷽��Ŀؼ�����һ�㣬1.0ΪY�᷽��Ŀؼ�������;
		 * toY:���ö�������ʱY�᷽������Ž���ֵ;
		 * pivotXtype:������X����������λ������ ,��ΪRELATIVE_TO_SELF��RELATIVE_TO_PARENT��ABSOLUTE�������ͣ�
		 * 						1��RELATIVE_TO_SELF������ڿؼ�����
		 * 						2��RELATIVE_TO_PARENT������ڸ��ؼ���
		 * 						3��ABSOLUTE���������ꣻ
		 * pivotXValue:��������������X����Ŀ�ʼλ��;
		 * pivotYType:������Y����������λ������ ,��ΪRELATIVE_TO_SELF��RELATIVE_TO_PARENT��ABSOLUTE��������;
		 * pivotYValue:��������������Y����Ŀ�ʼλ��;
		 */
		//mAnimation = new ScaleAnimation(fromX, toX, fromY, toY)
		//mAnimation = new ScaleAnimation(fromX, toX, fromY, toY, pivotX, pivotY)
		//mAnimation = new ScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType, pivotYValue)
		mAnimation = new ScaleAnimation(0.0f, 1.0f, 0.5f, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f,
				Animation.RELATIVE_TO_SELF, 1.0f);
		
		if(mAnimation != null){
			setAnimationFlags(mAnimation);
		}
		
		/**
		 * xml�ļ�����ͼƬ����(Scale)����
		 */
		mAnimation = AnimationUtils.loadAnimation(this, R.anim.view_animation_scale);
		return mAnimation;
	}
	
	public Animation getTranslateAnimation(){
		Animation mAnimation = null;
		/**
		 * ���봴��ͼƬλ��λ��(Translate)����
		 * mAnimation = new TranslateAnimation(fromXType, fromXValue, toXType, toXValue, fromYType,
		 *  fromYValue, toYType, toYValue);
		 * fromXType:������ʼִ��ʱ��X����������λ������
		 * fromXValue:������ʼִ��ʱX�᷽��ĵ���ʼλ�ã���λ������ΪRELATIVE_TO_SELFʱ��Valueȡ0.0f~1.0f֮�䣬
		 * ��λ������ΪRELATIVE_TO_PARENT��ABSOLUTEʱ�� Valueʹ��(px)����ֵ
		 * toXType:��������ִ��ʱ��X����������λ������
		 * toXValue:��������ִ��ʱX�᷽��ĵĽ���λ�ã�Valueȡֵ��ʽͬ��
		 * fromYType:������ʼִ��ʱ��Y����������λ������
		 * fromYValue:������ʼִ��ʱY�᷽��ĵ���ʼλ�ã�Valueȡֵ��ʽͬ��
		 * toYType:�����ڽ���ִ��ʱ��Y����������λ������
		 * toYValue:��������ִ��ʱY�᷽��ĵĽ���λ�ã�Valueȡֵ��ʽͬ��
		 */
		//mAnimation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
		mAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, 
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
		
		if(mAnimation != null){
			setAnimationFlags(mAnimation);
		}
		
		//xml�ļ�����ͼƬλ��λ��(Translate)����
		mAnimation = AnimationUtils.loadAnimation(this, R.anim.view_animation_translate);
		return mAnimation;
	}
	
	public Animation getRotateAnimation(){
		Animation mAnimation = null;
		/**
		 * ���봴��ͼƬ��ת(Rotate)����
		 * mAnimation = new RotateAnimation(fromDegrees, toDegrees, pivotXType, pivotXValue, pivotYType, pivotYValue)��
		 * fromDegrees��������ʼִ��ʱ�Ŀؼ���ʼ״̬�ĽǶ�;
		 * toDegrees����������ִ��ʱ�Ŀؼ�����״̬�ĽǶȣ�
		 * pivotXType��������X����������λ������ ,��ΪRELATIVE_TO_SELF��RELATIVE_TO_PARENT��ABSOLUTE�������ͣ�
		 * 						1��RELATIVE_TO_SELF������ڿؼ�����
		 * 						2��RELATIVE_TO_PARENT������ڸ��ؼ���
		 * 						3��ABSOLUTE���������ꣻ
		 * pivotXValue��������ʼִ��ʱX�᷽��ĵ���ʼλ�ã���λ������ΪRELATIVE_TO_SELFʱ��Valueȡ0.0f~1.0f֮�䣬��λ������ΪRELATIVE_TO_PARENT��ABSOLUTEʱ��
		 * 			   Valueʹ��(px)����ֵ;
		 * pivotYType��������Y����������λ������ ,��ΪRELATIVE_TO_SELF��RELATIVE_TO_PARENT��ABSOLUTE��������;
		 * pivotYValue����ת���������ĵ��Y�᷽��������꣬ԭ��ͬ�ϣ�
		 */
		//mAnimation = new RotateAnimation(fromDegrees, toDegrees)
		//mAnimation = new RotateAnimation(fromDegrees, toDegrees, pivotX, pivotY)
		mAnimation = new RotateAnimation(-50f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		
		if(mAnimation != null){
			setAnimationFlags(mAnimation);
		}
		
		/**
		 * xml�ļ�����ͼƬ��ת(Rotate)����
		 */
		//mAnimation = AnimationUtils.loadAnimation(this, R.anim.view_animation_rotate);
		return mAnimation;
	}
	
	public Animation getSetAnimation(){
		Animation mAnimation = null;
		/**
		 * ���봴��ͼƬ����(Set)����
		 */
		AnimationSet mAnimationSet = new AnimationSet(true);
		//���彥�䶯������
		AlphaAnimation mAlphaAnimation = new AlphaAnimation(1.0f, 0.0f);
		mAlphaAnimation.setRepeatCount(1);
		mAlphaAnimation.setRepeatMode(Animation.REVERSE);
		mAlphaAnimation.setFillAfter(true);
		mAlphaAnimation.setDuration(1000);
		//�������Ŷ�������
		ScaleAnimation mScaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
				Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
		mScaleAnimation.setRepeatCount(1);
		mScaleAnimation.setRepeatMode(Animation.REVERSE);
		mScaleAnimation.setFillAfter(true);
		mScaleAnimation.setDuration(1000);
		//����λ�ƶ�������
		TranslateAnimation mTranslateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, 
				Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
		mTranslateAnimation.setRepeatCount(1);
		mTranslateAnimation.setRepeatMode(Animation.REVERSE);
		mTranslateAnimation.setFillAfter(true);
		mTranslateAnimation.setDuration(1000);
		//������ת��������
		RotateAnimation mRotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		mRotateAnimation.setRepeatCount(1);
		mRotateAnimation.setRepeatMode(Animation.REVERSE);
		mRotateAnimation.setFillAfter(true);
		mRotateAnimation.setDuration(1000);
		//��Ӷ��������϶���������
		mAnimationSet.addAnimation(mAlphaAnimation);
		mAnimationSet.addAnimation(mScaleAnimation);
		mAnimationSet.addAnimation(mTranslateAnimation);
		mAnimationSet.addAnimation(mRotateAnimation);
		
		//mAnimation = mAnimationSet;
		
		if(mAnimation != null){
			setAnimationFlags(mAnimation);
		}
		
		/**
		 * xml�ļ�����ͼƬ����(Set)����
		 */
		mAnimation = AnimationUtils.loadAnimation(this, R.anim.view_animation_set);
		return mAnimation;
	}
	
	public void setAnimationFlags(Animation mAnimation){
		/**
		 * �������ö���Ч��
		 */
		//���ö����ظ����ŵĴ���
		mAnimation.setRepeatCount(2);
		/*
		 * �����ظ����ŵ�ģʽ��
		 * reverseΪ0.0f -> 1.0f ->0.0f -> 1.0f,ͼƬ������������
		 * restartΪ0.0f -> 1.0f , 0.0f -> 1.0f��ͼƬ����ÿ�ζ������¿�ʼ����
		 */
		mAnimation.setRepeatMode(Animation.REVERSE);
		//����ÿһ�ζ���������������ʱ�䣬��λΪms(����)
		mAnimation.setDuration(2000);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			finish();
			overridePendingTransition(R.anim.page_right_in, R.anim.page_right_out);
			return true;
		}
		 return false;
	}
}
