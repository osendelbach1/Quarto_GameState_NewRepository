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

        if(x > 0 && x < 10) {
            col = 0;
        }
        else if (x > 10 && x < 20) {
            col = 1;
        }
        else if (x > 20 && x < 30) {
            col = 2;
        }

        if (y > 0 && y < 10) {
            row = 0;
        }
        else if (y > 10 && y < 20) {
            row = 1;
        }
        else if (y > 20 && y < 30) {
            row = 2;
        }


    }
}
