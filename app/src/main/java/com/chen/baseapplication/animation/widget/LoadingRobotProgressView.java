package com.chen.baseapplication.animation.widget;


import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.chen.baseapplication.R;

public class LoadingRobotProgressView extends ImageView {

	private AnimationDrawable mAnimationDrawable;
	
	public LoadingRobotProgressView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	public LoadingRobotProgressView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	public LoadingRobotProgressView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	 private void init() {
		   //���ñ���
	        this.setBackgroundResource(R.drawable.drawable_animation_robot);
	        //��ȡ��ǰ�ı���
	        mAnimationDrawable = (AnimationDrawable) this.getBackground();
	    }

	    /**
	     * �����뵱ǰ����ʱ����������
	     */
	    @Override
	    protected void onAttachedToWindow() {
	        super.onAttachedToWindow();
	        mAnimationDrawable.start();
	    }
        /**
         * ���뿪��ǰ����ʱ���رն���
         */
	    @Override
	    protected void onDetachedFromWindow() {
	        super.onDetachedFromWindow();
	        mAnimationDrawable.stop();
	    }
}
