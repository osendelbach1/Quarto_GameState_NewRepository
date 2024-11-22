package edu.up.cs301.quarto;

import java.util.Random;

import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GameComputerPlayer;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.GameFramework.utilities.Tickable;
import edu.up.cs301.GameFramework.infoMessage.NotYourTurnInfo;

/**
 * A computer-version of a quarto-player.  Since this is such a simple game,
 * it just sends "+" and "-" commands with equal probability, at an average
 * rate of one per second. 
 * 
 @author Olivia Sendelbach
 * @author Magnus Graham
 * @author Becca Biukoto
 * @version November 16, 2024
 * 
 * @version September 2013
 */
public class QuartoComputerPlayer1 extends GameComputerPlayer implements Tickable {
	
    /**
     * Constructor for objects of class quartoComputerPlayer1
     * 
     * @param name
     * 		the player's name
     */
	private QuartoState state;


    public QuartoComputerPlayer1(String name) {
        // invoke superclass constructor
        super(name);
        
        // start the timer, ticking 20 times per second
        getTimer().setInterval(5000);
        getTimer().start();
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
		if (!(info instanceof QuartoState)) {
			return;
		}

		// update our state variable
		state = (QuartoState)info;

		//check if it's AI's turn
		if (state.getTurn() != 1 ) {
			return; // It's not the AI's turn, so do nothing
		}

		//creates a random int for the row and col that the AI can place a piece
		// bug here: working on turns
		Random random = new Random();
		int randomNumber1 = (int)random.nextInt(900);
		int randomNumber2 = (int)random.nextInt(850);
		placePieceAction ppa = new placePieceAction(this, randomNumber1, randomNumber2, state.getCurrentPiece());
		game.sendAction(ppa);

		sleep(2);
		int randomNumber3 = (int)random.nextInt(state.getUnPlaced().size());
		Piece q = state.getUnPlaced().get(randomNumber3);
		selectPieceAction spa = new selectPieceAction(this, q);
		game.sendAction(spa);

	}
	
	/**
	 * callback method: the timer ticked
	 */
	protected void timerTicked() {
		// 5% of the time, increment or decrement the quarto
		if (Math.random() >= 0.05) return; // do nothing 95% of the time

		// "flip a coin" to determine whether to increment or decrement
		boolean move = Math.random() >= 0.5;
	}
}
