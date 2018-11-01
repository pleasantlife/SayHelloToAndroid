package com.android.gandan.customview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

public class TicketDownSideView extends android.support.v7.widget.AppCompatImageView {

    private int mDefaultTextSize = 40;
    private int mTextColor = Color.LTGRAY;
    private Paint mPaint = makePaint(mTextColor);
    private Paint mTestPaint = makePaint(mTextColor);
    private String mMessage = "Android";

    public TicketDownSideView(Context context) {
        super(context);
    }

    public TicketDownSideView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TicketDownSideView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        canvas.translate(getWidth()/2, 0);
        canvas.drawRect(0, 0, 0, getHeight(), mPaint);
        canvas.drawColor(mTextColor);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int dp = (int) displayMetrics.density * 16;
        canvas.drawCircle(-getWidth()/2, 0, dp, paint);
        canvas.drawCircle(getWidth()/2, 0, dp, paint);
    }

    private Paint makePaint(int color) {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(color);
        p.setShadowLayer(0.3f, 0, 0.1f, Color.LTGRAY);
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        p.setAlpha(20);
        return p;
    }
}
