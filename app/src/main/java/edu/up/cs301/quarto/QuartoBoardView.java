package edu.up.cs301.quarto;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * draws the Quarto board so it appears in surface view
 *
 * @author Becca Biukoto
 * @author Olivia Sendelbach
 * @author Magnus Graham
 * @version 11/13/2024
 */

public class QuartoBoardView extends SurfaceView {

    private Paint blackPaint; // for color

    public QuartoBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setWillNotDraw(false);

        // paint to draw board
        blackPaint = new Paint();
        blackPaint.setColor(0xFF000000);
        blackPaint.setStyle(Paint.Style.STROKE);

        setBackgroundColor(0xFFFFFFFF);
    }

    /**
     * draws a 4x4 board with squares
     * @param canvas
     */
    @Override
    protected void onDraw (Canvas canvas) {

        float margin = 200; // how big square is
        float top = 50; // distance from top of screen
        float left = 100; // distance from left of screen

        for(int row = 0; row < 4; row++) {
            for(int col = 0; col < 4; col++) {
                canvas.drawRect(left, top, left + margin, top + margin, blackPaint); // draws each iteration of the board by iterating through a for loop (row & col)
                left += margin;
            }
            top += margin;
            left = 100;
        }
    }
}
