package com.android.gandan.customview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;

public class TicketUpsideView extends View {

    private int mDefaultTextSize = 40;
    private int mTextColor = Color.LTGRAY;
    private Paint mPaint = makePaint(mTextColor);
    private Paint mTestPaint = makePaint(mTextColor);
    private String mMessage = "Android";

    public TicketUpsideView(Context context) {
        super(context);
        ViewUtils.generateBackgroundWithShadow(this, R.color.black, R.dimen.horizontal_margin, R.color.black, R.dimen.vertical_margin, Gravity.BOTTOM);
    }

    public TicketUpsideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ViewUtils.generateBackgroundWithShadow(this, R.color.black, R.dimen.horizontal_margin, R.color.black, R.dimen.vertical_margin, Gravity.BOTTOM);
    }

    public TicketUpsideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ViewUtils.generateBackgroundWithShadow(this, R.color.black, R.dimen.horizontal_margin, R.color.black, R.dimen.vertical_margin, Gravity.BOTTOM);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //int textWidth = width - getPaddingLeft() - getPaddingRight();
        //int textHeight = height - getPaddingTop() - getPaddingBottom();
        //adjustTextSizeToFit(Math.min(textWidth, textHeight));
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        canvas.translate(getWidth()/2, 0);
        canvas.drawRect(0, 0, 0, getHeight(), mPaint);
        canvas.drawColor(mTextColor);
        Paint paintText = new Paint();
        paintText.setColor(Color.BLACK);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int dp = (int) displayMetrics.density * 16;

        canvas.drawCircle(-getWidth()/2, getHeight(), dp, paint);
        canvas.drawCircle(getWidth()/2, getHeight(), dp, paint);
        /*//: 중점으로 이동
        canvas.translate(viewWidth/2, viewHeight/2);
        for(int i=0; i<10; i++) {
            canvas.drawText(mMessage, 0, 0, mPaint);
            canvas.rotate(36); // 36 * 10 = 360*/
    }

    private Paint makePaint(int color) {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(color);
        p.setShadowLayer(0.3f, 0, 0.1f, Color.LTGRAY);
        p.setAlpha(20);
        return p;
    }

}