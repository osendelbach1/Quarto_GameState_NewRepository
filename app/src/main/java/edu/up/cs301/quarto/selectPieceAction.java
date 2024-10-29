package edu.up.cs301.quarto;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class selectPieceAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public selectPieceAction(GamePlayer player) {
        super(player);
    }
}
