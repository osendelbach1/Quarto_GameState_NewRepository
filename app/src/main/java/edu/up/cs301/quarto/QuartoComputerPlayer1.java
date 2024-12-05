package edu.up.cs301.quarto;

import android.util.Log;
import android.widget.ImageButton;

import java.io.Serializable;
import java.util.Random;
import edu.up.cs301.GameFramework.players.GameComputerPlayer;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.GameFramework.utilities.Tickable;
import edu.up.cs301.GameFramework.infoMessage.NotYourTurnInfo;

/**
 * A computer-version of a quarto-player. It chooses a random piece from
 * the unplaced array in QuartoState and places it in a random position
 * on the board.
 *
 *
 * @author Olivia Sendelbach
 * @author Magnus Graham
 * @author Becca Biukoto
 * @version November 16, 2024
 */
public class QuartoComputerPlayer1 extends GameComputerPlayer implements Tickable, Serializable {
	
    /**
     * Constructor for objects of class quartoComputerPlayer1
     * 
     * @param name
     * 		the player's name
     */
	private QuartoState state;
	private int randomNumber3;

    public QuartoComputerPlayer1(String name) {
        // invoke superclass constructor
        super(name);
    }
	/**
     * callback method--game's state has changed
     * 
     * @param info
     * 		the information (presumably containing the game's state)
     */
	@Override
	//calls actions for the dumb AI's turn
	protected void receiveInfo(GameInfo info) {

		if (info instanceof NotYourTurnInfo) {
			return;
		}

		// if we don't have a game-state, ignore
		if (info instanceof QuartoState) {
			// update our state variable
			state = (QuartoState)info;

			//check if it's AI's turn
			if (state.getPlayerId() != 1 ) {
				return; // It's not the AI's turn, so do nothing
			}

			//creates a random int for the row and col that the AI can place a piece
			// bug here: working on turns
			Random random = new Random();
			int randomNumber1 = random.nextInt(801) + 100; // x: 100 to 900
			int randomNumber2 = random.nextInt(801) + 50;  // y: 50 to 850
			sleep(2);

			placePieceAction ppa = new placePieceAction(this, randomNumber1, randomNumber2, state.getCurrentPiece());
			Log.d("Dumb AI", "ppa");
			game.sendAction(ppa);

			declareVictoryAction dva = new declareVictoryAction(this);
			Log.d("Dumb AI", "dva");
			game.sendAction(dva);
			sleep(2);
			randomNumber3 = (int)random.nextInt(state.getUnPlaced().size());
			Piece q = state.getUnPlaced().get(randomNumber3);
			selectPieceAction spa = new selectPieceAction(this, q);
			Log.d("Dumb AI", "spa");
			game.sendAction(spa);
		}




	}
}
