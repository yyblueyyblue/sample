package com.chen.baseapplication.canvas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.chen.baseapplication.R
import kotlinx.android.synthetic.main.activity_canvas_draw_view.*

class CanvasDrawViewActivity : AppCompatActivity(), View.OnClickListener {
    var b:Float = 0.1f;
    override fun onClick(v: View?) {
        if(b <1) {
            canvas_view.setScaleX(b)
            b+=0.1f;
        }else{
            canvas_view.setScaleX(b)
            b=0.1f;
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canvas_draw_view)
        canvas_btn.setOnClickListener(this)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when(event!!.action){
            MotionEvent.ACTION_MOVE ->{
                canvas_view.scrollBy(5,5);
            }

        }
        return super.onTouchEvent(event)
    }
}
