package edu.up.cs301.quarto;

import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.LocalGame;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import android.util.Log;

/**
 * A class that represents the state of a game. In our Quarto game,
 * we have to know who's turn it is and whether they are placing a piece on
 * the board or selecting it for the opponent.
 *
 * @author Olivia Sendelbach
 * @author Magnus Graham
 * @author Aaron Chu
 * @author Becca Biukoto
 * @version October 11, 2024
 */
public class QuartoLocalGame extends LocalGame {


	// When a quarto game is played, any number of players. The first player
	// is trying to get the quarto value to TARGET_MAGNITUDE; the second player,
	// if present, is trying to get the quarto to -TARGET_MAGNITUDE. The
	// remaining players are neither winners nor losers, but can interfere by
	// modifying the quarto.
	public static final int TARGET_MAGNITUDE = 10;

	// the game's state
	private QuartoState gameState;

	/**
	 * can this player move
	 *
	 * @return true, because all player are always allowed to move at all times,
	 * as this is a fully asynchronous game
	 */
	@Override
	protected boolean canMove(int playerIdx) {
		return true;
	}

	/**
	 * Check if the game is over. It is over, return a string that tells
	 * who the winner(s), if any, are. If the game is not over, return null;
	 *
	 * @return
	 * 		a message that tells who has won the game, or null if the
	 * 		game is not over
	 */
	@Override
	protected String checkIfGameOver() {
		return "";
	}

	/**
	 * This ctor should be called when a new quarto game is started
	 */
	public QuartoLocalGame(GameState state) {
		// initialize the game state, with the quarto value starting at 0
		if (!(state instanceof QuartoState)) {
			state = new QuartoState();
		}
		this.gameState = (QuartoState) state;
		super.state = state;
	}

	/**
	 * Will add actions: select piece, place piece, quit, end turn, and declare victory
	 */
	@Override
	protected boolean makeMove(GameAction action) {
		Log.i("action", action.getClass().toString());

		if (action instanceof QuartoMoveAction) {

			// cast so that we Java knows it's a quartoMoveAction
			QuartoMoveAction cma = (QuartoMoveAction) action;

			// Update the quarto values based upon the action
			//int result = gameState.getquarto() + (cma.isPlus() ? 1 : -1);
			//gameState.setquarto(result);

			// denote that this was a legal/successful move
			return true;
		} else {
			// denote that this was an illegal move
			return false;
		}
	}//makeMove

	/**
	 * send the updated state to a given player
	 */
	@Override
	protected void sendUpdatedStateTo(GamePlayer p) {
		// this is a perfect-information game, so we'll make a
		// complete copy of the state to send to the player
		p.sendInfo(new QuartoState(this.gameState));

	}//sendUpdatedSate
} // class quartoLocalGame


/** the following comments are the original code from the counter game
 * that may be useful for future use.
 */

//	@Override
//	protected String checkIfGameOver() {
//	}

		 //get the value of the quarto
//		int quartoVal = this.gameState.getquarto();
//
//		if (quartoVal >= TARGET_MAGNITUDE) {
//			 quarto has reached target magnitude, so return message that
//			 player 0 has won.
//			return playerNames[0]+" has won.";
//		}
//		else if (quartoVal <= -TARGET_MAGNITUDE) {
//			 quarto has reached negative of target magnitude; if there
//			 is a second player, return message that this player has won,
//			 otherwise that the first player has lost
//			if (playerNames.length >= 2) {
//				return playerNames[1]+" has won.";
//			}
//			else {
//				return playerNames[0]+" has lost.";
//			}
//		}else {
//			game is still between the two limit: return null, as the game
//			 is not yet over
//			return null;
//			return "";
//		}

