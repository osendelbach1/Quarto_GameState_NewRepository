package edu.up.cs301.quarto;

import android.util.Log;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.quarto.QuartoState;

public class selectPieceAction extends GameAction {

    public Piece selected;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */


    public selectPieceAction(GamePlayer player, Piece initSelected, QuartoState game) {
            super(player);
            //game.getUnPlaced().remove(initSelected);
            //Log.d("Piece removed", "" + initSelected.getShape() + initSelected.getColor() + initSelected.getHole() + initSelected.getHeight());
            this.selected = initSelected;
            //Log.d("Error", "Piece already placed");
        }
    }




