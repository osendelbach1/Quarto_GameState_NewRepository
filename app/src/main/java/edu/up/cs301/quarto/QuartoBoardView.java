package edu.up.cs301.quarto;

import static edu.up.cs301.quarto.QuartoState.CIRCLE;
import static edu.up.cs301.quarto.QuartoState.DARK;
import static edu.up.cs301.quarto.QuartoState.HOLE;
import static edu.up.cs301.quarto.QuartoState.LIGHT;
import static edu.up.cs301.quarto.QuartoState.SHORT;
import static edu.up.cs301.quarto.QuartoState.SOLID;
import static edu.up.cs301.quarto.QuartoState.SQUARE;
import static edu.up.cs301.quarto.QuartoState.TALL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    private Bitmap SHDC;
    private Bitmap SHDS;
    private Bitmap SHLC;
    private Bitmap SHLS;
    private Bitmap SSDC;
    private Bitmap SSDS;
    private Bitmap SSLC;
    private Bitmap SSLS;
    private Bitmap THDC;
    private Bitmap THDS;
    private Bitmap THLC;
    private Bitmap THLS;
    private Bitmap TSDC;
    private Bitmap TSDS;
    private Bitmap TSLC;
    private Bitmap TSLS;
    private Bitmap BLANK;


    public QuartoBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setWillNotDraw(false);

        // paint to draw board
        blackPaint = new Paint();
        blackPaint.setColor(0xFF000000);
        blackPaint.setStyle(Paint.Style.STROKE);

        setBackgroundColor(0xFFFFFFFF);
        renderPieces(context);
    }

    // setter method for the shogi state
    public void setQuartoState(QuartoState state){
        this.qs = state;
        invalidate();
    }

    public void renderPieces(Context context){
         SHDC = BitmapFactory.decodeResource(context.getResources(), R.drawable.shortholedarkcircle);
          SHDS = BitmapFactory.decodeResource(context.getResources(), R.drawable.shortholedarksquare);
          SHLC =  BitmapFactory.decodeResource(context.getResources(), R.drawable.shortholelightcircle);
          SHLS = BitmapFactory.decodeResource(context.getResources(), R.drawable.shortholelightsquare);
          SSDC = BitmapFactory.decodeResource(context.getResources(), R.drawable.shortsoliddarkcircle);
          SSDS = BitmapFactory.decodeResource(context.getResources(), R.drawable.shortsoliddarksquare);
          SSLC = BitmapFactory.decodeResource(context.getResources(), R.drawable.shortsolidlightcircle);
          SSLS = BitmapFactory.decodeResource(context.getResources(), R.drawable.shortsolidlightsquare);
          THDC = BitmapFactory.decodeResource(context.getResources(), R.drawable.tallholedarkcircle);
          THDS = BitmapFactory.decodeResource(context.getResources(), R.drawable.tallholedarksquare);
          THLC = BitmapFactory.decodeResource(context.getResources(), R.drawable.tallholelightcircle);
          THLS = BitmapFactory.decodeResource(context.getResources(), R.drawable.tallholelightsquare);
          TSDC = BitmapFactory.decodeResource(context.getResources(), R.drawable.tallsoliddarkcircle);
          TSDS = BitmapFactory.decodeResource(context.getResources(), R.drawable.tallsoliddarksquare);
          TSLC = BitmapFactory.decodeResource(context.getResources(), R.drawable.tallsolidlightcircle);
          TSLS = BitmapFactory.decodeResource(context.getResources(), R.drawable.tallsolidlightsquare);
          BLANK = BitmapFactory.decodeResource(context.getResources(), R.drawable.white);
    }


    protected Bitmap pieceToDraw(Piece piece) {
        if (piece.getHeight() == SHORT && piece.getHole() == HOLE && piece.getColor() == DARK &&  piece.getShape() == CIRCLE) {
            return SHDC;
        }
        else if (piece.getHeight() == SHORT && piece.getHole() == HOLE && piece.getColor() == DARK &&  piece.getShape() == SQUARE) {
            return SHDS;
        }
        else if(piece.getHeight() == SHORT && piece.getHole() == HOLE && piece.getColor() == LIGHT &&  piece.getShape() == CIRCLE) {
            return SHLC;
        }
        else if(piece.getHeight() == SHORT && piece.getHole() == HOLE && piece.getColor() == LIGHT &&  piece.getShape() == SQUARE) {
            return SHLS;
        }
        else if(piece.getHeight() == SHORT && piece.getHole() == SOLID && piece.getColor() == DARK &&  piece.getShape() == CIRCLE) {
            return SSDC;
        }
        else if(piece.getHeight() == SHORT && piece.getHole() == SOLID && piece.getColor() == DARK &&  piece.getShape() == SQUARE) {
            return SSDS;
        }
        else if(piece.getHeight() == SHORT && piece.getHole() == SOLID && piece.getColor() == LIGHT &&  piece.getShape() == CIRCLE) {
            return SSLC;
        }
        else if(piece.getHeight() == SHORT && piece.getHole() == SOLID && piece.getColor() == LIGHT &&  piece.getShape() == SQUARE) {
            return SSLS;
        }
        else if(piece.getHeight() == TALL && piece.getHole() == HOLE && piece.getColor() == DARK &&  piece.getShape() == CIRCLE) {
            return THDC;
        }
        else if(piece.getHeight() == TALL && piece.getHole() == HOLE && piece.getColor() == DARK &&  piece.getShape() == SQUARE) {
            return THDS;
        }
        else if(piece.getHeight() == TALL && piece.getHole() == HOLE && piece.getColor() == LIGHT &&  piece.getShape() == CIRCLE) {
            return THLC;
        }
        else if(piece.getHeight() == TALL && piece.getHole() == HOLE && piece.getColor() == LIGHT &&  piece.getShape() == SQUARE) {
            return THLS;
        }
        else if(piece.getHeight() == TALL && piece.getHole() == SOLID && piece.getColor() == DARK &&  piece.getShape() == CIRCLE) {
            return TSDC;
        }
        else if(piece.getHeight() == TALL && piece.getHole() == SOLID && piece.getColor() == DARK &&  piece.getShape() == SQUARE) {
            return TSDS;
        }
        else if(piece.getHeight() == TALL && piece.getHole() == SOLID && piece.getColor() == LIGHT &&  piece.getShape() == CIRCLE) {
            return TSLC;
        }
        else if(piece.getHeight() == TALL && piece.getHole() == SOLID && piece.getColor() == LIGHT &&  piece.getShape() == SQUARE) {
            return TSLS;
        }
        else { return null; }
    }

    /**
     * method draws pieces on the board when player decides to place
     *
     * @param canvas
     * @param left
     * @param top
     * @param row
     * @param col
     */
    private void drawPieces(Canvas canvas, float left, float top, int row, int col){
        // existing game state?
        if (qs == null) {
            return;
        }

        if (qs.getBoard()[row] == null || qs.getBoard()[row][col] == null) {
            return;
        }

        else if (qs.getBoard()[row][col] != null){
            int width = getWidth(); // width of board
            int height = getHeight(); // height of board
            int boardSize = 4; // 4x4 board

            int squareWidth = (int)(width / (boardSize + (float) 1)); // width of a single square on the board
            int squareHeight = (int)(height / (boardSize + (float) 1)); // height of a single square on the board
            Piece pieceToDraw = qs.getBoard()[row][col]; // the piece selected

            Bitmap centeredPiece;

            // centers piece in square that's clicked, dependent on piece's height
            if (pieceToDraw.getHeight() == SHORT){
                centeredPiece = Bitmap.createScaledBitmap(pieceToDraw(pieceToDraw), squareWidth/2, squareHeight/2, false);
                canvas.drawBitmap(centeredPiece, left + (float)(squareWidth / 3.5), top + (float)(squareHeight / 2.5), blackPaint);

            } else {
                centeredPiece = Bitmap.createScaledBitmap(pieceToDraw(pieceToDraw), squareWidth / 2, squareHeight, false);
                canvas.drawBitmap(centeredPiece, left + (float)(squareWidth / 3.5) , top + (float)(squareHeight / 8), blackPaint);
            }
        }
    }

    private void drawBoard(Canvas canvas) {
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
                drawPieces(canvas, left, top, row, col);
                left += margin;
            }
            top += margin;
            left = leftStart;
        }
    }

    /**
     * draws a 4x4 board with squares
     * @param canvas
     */
    @Override
    protected void onDraw (Canvas canvas) {

        drawBoard(canvas);


    }
}
