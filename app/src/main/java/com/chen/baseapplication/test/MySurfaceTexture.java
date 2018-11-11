package com.chen.baseapplication.test;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.TextureView;

@RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class MySurfaceTexture extends TextureView implements InterfaceView {

    public MySurfaceTexture(final Context context) {
        this(context, null, 0);
    }

    public MySurfaceTexture(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySurfaceTexture(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }
}
