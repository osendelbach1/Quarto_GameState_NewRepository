/* 
 * @author Olivia Sendelbach
 * @author Magnus Graham
 * @author Aaron Chu
 * @author Becca Biukoto
 * @version October 11, 2024
 */

package edu.up.cs301.quarto;

import android.util.Log;

import java.io.Serializable;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;


public class selectPieceAction extends GameAction implements Serializable {
    private static final long serialVersionUID = 1126202475;

    public Piece selected;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */

// Uses parameters passed in select a piece
    public selectPieceAction(GamePlayer player, Piece initSelected) {
            super(player);
            //game.getUnPlaced().remove(initSelected);
            //Log.d("Piece removed", "" + initSelected.getShape() + initSelected.getColor() + initSelected.getHole() + initSelected.getHeight());
            this.selected = initSelected;
            //Log.d("Error", "Piece already placed");

    }
}




