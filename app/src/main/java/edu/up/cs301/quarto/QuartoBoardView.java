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
        blackPaint.setColor(0x000000);
        blackPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw (Canvas canvas) {

        float margin = 100;
        float top = 0;
        float left = 0;

        for(int row = 0; row < 4; row++) {
            for(int col = 0; col < 4; col++) {
                canvas.drawRect(left, top, left + margin, top + margin, blackPaint);
                left += margin;
            }
            top += margin;
        }
    }
}
