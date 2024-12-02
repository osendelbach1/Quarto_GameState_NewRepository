package edu.up.cs301.quarto;

import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.LocalGame;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import android.util.Log;

import java.io.Serializable;

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
public class QuartoLocalGame extends LocalGame implements Serializable {
	// the game's state
	private QuartoState gameState;

	/**
	 * can this player move
	 *
	 * @return player index
	 */
	@Override
	protected boolean canMove(int playerIdx) {
		return gameState.getTurn() == playerIdx;
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
		if (gameState.getGameStatus() == QuartoState.WON) {
			return "You have won! ";
		}
		if (gameState.getGameStatus() == QuartoState.LOST){
			return "You have lost. ";
		}
		return null;
	}

	/**
	 * This ctor should be called when a new game is started
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
	 * Determines what move is being made
	 */
	@Override
	protected boolean makeMove(GameAction action) {
		Log.i("action", action.getClass().toString());

		if(action instanceof selectPieceAction) {
			selectPieceAction spa = (selectPieceAction)action;
			boolean result = this.gameState.selectPieceAction(
					spa.selected.getHeight(),
					spa.selected.getHole(),
					spa.selected.getColor(),
					spa.selected.getShape());
			Log.d("State:", "" + state.toString());
			return result;
		}

		else if (action instanceof placePieceAction) {
			placePieceAction ppa = (placePieceAction)action;
			boolean result = this.gameState.placePieceAction(ppa.getRow(),ppa.getCol());
			Log.d("State:", "" + state.toString());
			return result;
		}

		else if (action instanceof declareVictoryAction) {
			//declareVictoryAction dva = (declareVictoryAction)action;
			boolean result = this.gameState.declareVictoryAction();

			return result;
		}

		else if(action instanceof QuitGameAction) {
			QuitGameAction qga = (QuitGameAction)action;
			boolean result = this.gameState.quitAction(qga.myActivity);
			Log.d("State: ", "" + state.toString());
			return result;
		}
		else {
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