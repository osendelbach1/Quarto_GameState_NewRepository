package edu.up.cs301.quarto;

import java.io.Serializable;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class declareVictoryAction extends GameAction implements Serializable {


    public declareVictoryAction(GamePlayer player) {
        super(player);
    }
    //private static final long serialVersionUID = 1126202472;
}
