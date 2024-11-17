package edu.up.cs301.quarto;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class QuartoBoardView extends SurfaceView {

    private Paint blackPaint;

    public QuartoBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setWillNotDraw(false);

        blackPaint = new Paint();
        blackPaint.setColor(0xFF000000);
        blackPaint.setStyle(Paint.Style.STROKE);

        setBackgroundColor(0xFFF5F5DC);
    }

    @Override
    protected void onDraw (Canvas canvas) {

        float margin = 200;
        float top = 50;
        float left = 350;

        for(int row = 0; row < 4; row++) {
            for(int col = 0; col < 4; col++) {
                canvas.drawRect(left, top, left + margin, top + margin, blackPaint);
                left += margin;
            }
            top += margin;
            left = 350;
        }
    }
}
