package edu.up.cs301.quarto;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class placePieceAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */

    private int row;
    private int col;
    public Piece p;

    public placePieceAction(GamePlayer player, float x, float y, Piece p) {
        super(player);
        this.p = p;
        squareTouched(x,y);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }


    public void squareTouched(float x, float y) {

        if (x >= 100 && x <= 300 && y >= 50 && y <= 250) {
            row = 0;
            col = 0;
        } else if (x >= 300 && x <= 500 && y >= 50 && y <= 250) {
            row = 0;
            col = 1;
        } else if (x >= 500 && x <= 700 && y >= 50 && y <= 250) {
            row = 0;
            col = 2;
        } else if (x >= 700 && x <= 900 && y >= 50 && y <= 250) {
            row = 0;
            col = 3;
        } else if (x >= 100 && x <= 300 && y >= 250 && y <= 450) {
            row = 1;
            col = 0;
        } else if (x >= 300 && x <= 500 && y >= 250 && y <= 450) {
            row = 1;
            col = 1;
        } else if (x >= 500 && x <= 700 && y >= 250 && y <= 450) {
            row = 1;
            col = 2;
        } else if (x >= 700 && x <= 900 && y >= 250 && y <= 450) {
            row = 1;
            col = 3;
        } else if (x >= 100 && x <= 300 && y >= 450 && y <= 650) {
            row = 2;
            col = 0;
        } else if (x >= 300 && x <= 500 && y >= 450 && y <= 650) {
            row = 2;
            col = 1;
        } else if (x >= 500 && x <= 700 && y >= 450 && y <= 650) {
            row = 2;
            col = 2;
        } else if (x >= 700 && x <= 900 && y >= 450 && y <= 650) {
            row = 2;
            col = 3;
        } else if (x >= 100 && x <= 300 && y >= 650 && y <= 850) {
            row = 3;
            col = 0;
        } else if (x >= 300 && x <= 500 && y >= 650 && y <= 850) {
            row = 3;
            col = 1;
        } else if (x >= 500 && x <= 700 && y >= 650 && y <= 850) {
            row = 3;
            col = 2;
        } else if (x >= 700 && x <= 900 && y >= 650 && y <= 850) {
            row = 3;
            col = 3;
        }
    }
}
