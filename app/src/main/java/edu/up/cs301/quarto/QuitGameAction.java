package edu.up.cs301.quarto;

import static androidx.core.app.ActivityCompat.finishAffinity;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

import java.io.Serializable;


/**
 * This contains class declaration for the QuitGameAction. This class consist of a GamePlayer,
 * a canQuitAction that will always return true so that you can always quit the game, and the quitAction.
 * @author Olivia Sendelbach, Magnus Graham, Becca Biukoto
 * Code Reference: Qwirkle QuitGameAction Class
 * @Date 11/27/24
 */
public class QuitGameAction extends GameAction implements Serializable
{
    private static final long serialVersionUID = 1126202468;

    public QuitGameAction(GamePlayer player)
    {
        super(player);
    }

    //Makes it so you can always quit
    public boolean canQuitAction()
    {
        return true;
    }

    public void quitAction()
    {
        GamePlayer GamePlayer = this.getPlayer();
        //System.exit(0);
    }


}
