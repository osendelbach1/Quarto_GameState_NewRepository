package edu.up.cs301.quarto;
import java.io.Serializable;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

/**
 * An object that the player sends to the game to declare their victory
 *
 * @author Magnus Graham
 * @author Olivia Sendelbach
 * @author Becca Biukoto
 * @version 11/25/2024
 */
public class declareVictoryAction extends GameAction implements Serializable {
    private static final long serialVersionUID = 1126202472;

    public declareVictoryAction(GamePlayer player) {
        super(player);
    }

}
