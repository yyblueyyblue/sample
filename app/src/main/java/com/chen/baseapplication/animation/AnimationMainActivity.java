package com.chen.baseapplication.animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.chen.baseapplication.R;
import com.chen.baseapplication.animation.activity.MainActivity;

public class AnimationMainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_main);
        TextView alpha = (TextView)findViewById(R.id.anim_tv_alpha);
        ImageView scale = (ImageView)findViewById(R.id.anim_img_scale);
        ImageView translate = (ImageView)findViewById(R.id.anim_img_translate);
        ImageView rotate = (ImageView)findViewById(R.id.anim_img_rotate);
        ImageView set = (ImageView)findViewById(R.id.anim_img_set);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.res_anim_alpha);
        alpha.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this,R.anim.res_anim_scale);
        scale.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this,R.anim.res_anim_translate);
        translate.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this,R.anim.res_anim_rotate);
        rotate.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this,R.anim.res_anim_set);
        set.startAnimation(animation);
        alpha.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
