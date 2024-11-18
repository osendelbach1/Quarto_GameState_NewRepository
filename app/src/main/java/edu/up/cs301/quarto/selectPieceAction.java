package edu.up.cs301.quarto;

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
            if (game.getUnPlaced().contains(initSelected)) {
                this.selected = initSelected;
            }

            else {

            }

        }
    }



