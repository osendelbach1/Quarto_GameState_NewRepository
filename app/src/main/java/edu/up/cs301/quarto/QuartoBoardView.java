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
    private QuartoState qs;
    Piece p;

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
        /**
         * External citation
         * Date: November 19, 2024
         * Problem: Surface View looked good only in landscape, not in portrait mode
         *
         * Resource: Chloe Pham
         * Solution: Implemented code that was suggested in her report
         */

        int width = getWidth();
        int height = getHeight();

        int boardSize = 4;
        float margin = width / (boardSize + (float) 0.75);
        float top = (height - (margin * boardSize)) / 2;
        float leftStart = (width - (margin * boardSize)) / 2;

        float left = leftStart;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                canvas.drawRect(left, top, left + margin, top + margin, blackPaint); // draws each iteration of the board by iterating through a for loop (row & col)
                left += margin;
            }
            top += margin;
            left = leftStart;
        }
    }
}
