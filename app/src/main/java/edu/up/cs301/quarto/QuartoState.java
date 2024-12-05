package edu.up.cs301.quarto;

import android.app.Activity;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.utilities.MessageBox;


/**
 * This contains the state for the Quarto game. The state consist of the
 * pieces, the board, whether the player is in the selection or placement
 * phase, the status of the game, and whether the player has completed
 * their turn.
 *
 * @author Olivia Sendelbach, Magnus Graham, Becca Biukoto, Aaron Chu
 * @Date 10/06/24
 */
public class QuartoState extends GameState implements Serializable {

	//constants for piece characteristics
	public static final Boolean SHORT = true;
	public static final Boolean TALL = false;
	public static final Boolean HOLE = true;
	public static final Boolean SOLID = false;
	public static final Boolean DARK = true;
	public static final Boolean LIGHT = false;
	public static final Boolean SQUARE =  true;
	public static final Boolean CIRCLE = false;


	// to satisfy Serializable interface
	private static final long serialVersionUID = 1126202473;

	//game board
	private Piece[][] board = new Piece[4][4];
	public Piece[][] getBoard() {
		return board;
	}
	private Piece[] pieces = new Piece[16];

	//each individual piece ID

	private ArrayList<Piece> unPlaced = new ArrayList<Piece>(16);

	//0 = human and 1 == other or AI
	private int playerID;

	public static final int HUMANPLAYER = 0;
	public static final int CPUPLAYER = 1;

	public int getPlayerId() {
		return this.playerID;
	}

	//ID of piece selected
	private Piece currentPiece = new Piece(false, false, false, false);
	public Piece getCurrentPiece() {
		return currentPiece;
	}
	//True for selection phase and false for placement phase of current player
	//Initialized as true because when the game starts you start off by selecting

	//If true, display "your turn" text as well
	private boolean phase;
	public static final boolean SELECTION = true;
	public static final boolean PLACEMENT = false;

	public boolean getPhase() {
		return phase;
	}
	//0 = active, 1 = won, 2 = lose
	private int gameStatus;
	public int getGameStatus() { return gameStatus; }
	//constants for game state
	public static final int ACTIVE = 0;
	public static final int WON = 1;
	public static final int LOST = 2;
	public static final int QUITTED = 3;


	//If you have placed the piece given to you and then selected a piece for the other player, you have completed all the actions for your turn
	//private boolean Player1TurnComplete;
	//private boolean Player2TurnComplete;
	public static final boolean INCOMPLETE = false;
	public static final boolean COMPLETE = true;



	private String result;

	//constructor, initializing the quarto value from the parameter
	public QuartoState() {

		pieces[0] = new Piece(SHORT, HOLE, DARK, SQUARE);
		pieces[1] = new Piece(SHORT, HOLE, DARK, CIRCLE);
		pieces[2] = new Piece(SHORT, HOLE, LIGHT, SQUARE);
		pieces[3] = new Piece(SHORT, HOLE, LIGHT, CIRCLE);
		pieces[4] = new Piece(SHORT, SOLID, DARK, SQUARE);
		pieces[5] = new Piece(SHORT, SOLID, DARK, CIRCLE);
		pieces[6] = new Piece(SHORT, SOLID, LIGHT, SQUARE);
		pieces[7] = new Piece(SHORT, SOLID, LIGHT, CIRCLE);
		pieces[8] = new Piece(TALL, HOLE, DARK, SQUARE);
		pieces[9] = new Piece(TALL, HOLE, DARK, CIRCLE);
		pieces[10] = new Piece(TALL, HOLE, LIGHT, SQUARE);
		pieces[11] = new Piece(TALL, HOLE, LIGHT, CIRCLE);
		pieces[12] = new Piece(TALL, SOLID, DARK, SQUARE);
		pieces[13] = new Piece(TALL, SOLID, DARK, CIRCLE);
		pieces[14] = new Piece(TALL, SOLID, LIGHT, SQUARE);
		pieces[15] = new Piece(TALL, SOLID, LIGHT, CIRCLE);

		//initialize unplaced with pieces 1-16
		unPlaced = new ArrayList<>(Arrays.asList(pieces));

		// Initialize game state
		playerID = HUMANPLAYER;
		Piece currentPiece = new Piece(false, false, false, false);  // No piece selected yet
		phase = SELECTION;
		gameStatus = ACTIVE;
		result = "";
	}

	//copy constructor; makes a copy of the original object
	public QuartoState(QuartoState other) {
		// Deep copy the board
		this.board = new Piece[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				this.board[i][j] = (other.board[i][j] != null) ? new Piece(other.board[i][j].getHeight(), other.board[i][j].getHole(), other.board[i][j].getColor(), other.board[i][j].getShape()) : null;
			}
		}

		// Deep copy the pieces array
		this.pieces = new Piece[16];
		for (int i = 0; i < 16; i++) {
			this.pieces[i] = new Piece(other.pieces[i].getHeight(), other.pieces[i].getHole(), other.pieces[i].getColor(), other.pieces[i].getShape());
		}

		// Deep copy the unPlaced list, calling Piece's copy ctor
		this.unPlaced = new ArrayList<>();
		for (Piece p : other.unPlaced) {
			this.unPlaced.add(new Piece(p));
		}

		// Copy primitive values and immutables directly
		this.playerID = other.playerID;
		this.currentPiece = new Piece(other.currentPiece);
		this.phase = other.phase;
		this.gameStatus = other.gameStatus;
		this.result = other.result;
	}

	/** getter method for unPlaced */
	public ArrayList<Piece> getUnPlaced() {
		return this.unPlaced;
	}

	public Piece[] getPieces() {
		return pieces;
	}

	public boolean placePieceAction(int row, int col)
	{
		if(gameStatus == ACTIVE) 
		{
			if(phase == PLACEMENT)
			{
				if (this.board[row][col] == null) {
					this.board[row][col] = currentPiece; //sets current piece to the board
					phase = SELECTION; //sets the phase of the game to select a piece
					this.endTurnAction(); //ends turn
					return true;
				}
				else {
					return false;
				}
			}
		}
		return false;
	}

	//returns true or false based on the piece selected is available
	public boolean selectPieceAction(boolean height, boolean hole, boolean color, boolean shape)
	{
		if(gameStatus == ACTIVE)
		{
			if(phase == SELECTION)
			{
				for (int i = 0; i < unPlaced.size(); i++) {
					if (unPlaced.get(i).getHeight() == height && unPlaced.get(i).getHole() == hole &&  unPlaced.get(i).getColor() == color && unPlaced.get(i).getShape() == shape) { //checks to see if the qualities of the piece are the same as whats in the element
						currentPiece = unPlaced.get(i); // if its not, it will set whatever is at spot (i) in unplaced to current piece
						unPlaced.remove(i); //will remove that piece from spot i once it has been set to current piece
						if (playerID == HUMANPLAYER) {
							playerID = CPUPLAYER; //switches turn
							//Player1TurnComplete = true; //completes turn
							//Player2TurnComplete = false; //completes turn
							//break;
						}
						else {
							playerID = HUMANPLAYER;
							//Player2TurnComplete = true;
							//Player1TurnComplete = false;
							//break;
						}
					}
				}
				phase = PLACEMENT;
				return true;
			}
		}
		return false;
	}

	public boolean quitAction(Activity myActivity)
	{
		String quitQuestion =
				"Do you really want to quit?";
		String posLabel =
				"Quit";
		String negLabel =
				"Continue playing game";
		MessageBox.popUpChoice(quitQuestion, posLabel, negLabel,
				new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface di, int val) {
						// if the user says that he wants to quit, exit the
						// application
						System.exit(0);
					}},
				null,
				myActivity);
		// return 'true' because we have handled this event
		return true;
	}

	//changes the player's turn
	public boolean endTurnAction()
	{
		if(gameStatus == ACTIVE)
		{
			if(phase == PLACEMENT)
			{
				playerID = 1 - playerID;
				return true;
			}
		}
		return false;
	}

	public boolean PieceEquals(Piece p1, Piece p2) {
		return p1.getHeight() == p2.getHeight() && p1.getHole() == p2.getHole() && p1.getColor() == p2.getColor() && p1.getShape() == p2.getShape();
	}

	public boolean declareVictoryAction() {

		boolean rowcheck = false, colcheck = false, diagcheck = false;

		for (int i = 0; i < 4; i++) {
			if (checkRow(board, i)) {
				rowcheck = true;
				break;
			}

			if (checkCol(board, i)) {
				colcheck = true;
				break;
			}
		}

		if (checkDiagonal(board, true)) {
			diagcheck = true;
		} else if (checkDiagonal(board, false)) {
			diagcheck = true;
		}

		if (rowcheck || colcheck || diagcheck) {
			if (playerID == HUMANPLAYER) {
				Log.d("DVA", "Human player wins!");
				gameStatus = WON;
				return true;
			} else if (playerID == CPUPLAYER) {
				Log.d("DVA", "CPU player wins!");
				gameStatus = LOST;
				return true;
			}
		}
		return false;
	}

	public boolean checkRow(Piece[][] boardCheck, int row) {
		if (boardCheck == null || boardCheck[row] == null) {
			throw new IllegalArgumentException("Board or specified row cannot be null.");
		}

		if (boardCheck[row][0] == null) {
			return false;
		}

		boolean firstHeight = boardCheck[row][0].getHeight();
		boolean firstColor = boardCheck[row][0].getColor();
		boolean firstHole = boardCheck[row][0].getHole();
		boolean firstShape = boardCheck[row][0].getShape();
		boolean heightcheck = true, colorcheck = true, holecheck = true, shapecheck = true;

		for (int j = 0; j < 4; j++) {
			if (boardCheck[row][j] == null) {
				return false;
			}

			if (boardCheck[row][j].getHeight() != firstHeight) heightcheck = false;
			if (boardCheck[row][j].getColor() != firstColor) colorcheck = false;
			if (boardCheck[row][j].getHole() != firstHole) holecheck = false;
			if (boardCheck[row][j].getShape() != firstShape) shapecheck = false;

		}

		boolean result = heightcheck || colorcheck || holecheck || shapecheck;
		return result;
	}

	public boolean checkCol(Piece[][] boardCheck, int col) {

		if (boardCheck[0][col] == null) {
			return false;
		}

		boolean firstHeight = boardCheck[0][col].getHeight();
		boolean firstColor = boardCheck[0][col].getColor();
		boolean firstHole = boardCheck[0][col].getHole();
		boolean firstShape = boardCheck[0][col].getShape();
		boolean heightcheck = true, colorcheck = true, holecheck = true, shapecheck = true;

		for (int i = 0; i < 4; i++) {
			if (boardCheck[i][col] == null) {
				return false;
			}

			if (boardCheck[i][col].getHeight() != firstHeight) heightcheck = false;
			if (boardCheck[i][col].getColor() != firstColor) colorcheck = false;
			if (boardCheck[i][col].getHole() != firstHole) holecheck = false;
			if (boardCheck[i][col].getShape() != firstShape) shapecheck = false;
		}

		boolean result = heightcheck || colorcheck || holecheck || shapecheck;
		return result;
	}

	public boolean checkDiagonal(Piece[][] boardCheck, boolean leftToRight) {

		int startCol = leftToRight ? 0 : 3;
		Piece firstPiece = boardCheck[0][startCol];
		if (firstPiece == null) {
			return false;
		}

		boolean firstHeight = firstPiece.getHeight();
		boolean firstColor = firstPiece.getColor();
		boolean firstHole = firstPiece.getHole();
		boolean firstShape = firstPiece.getShape();
		boolean heightcheck = true, colorcheck = true, holecheck = true, shapecheck = true;

		for (int i = 1; i < 4; i++) {
			int col = leftToRight ? i : (3 - i);
			if (boardCheck[i][col] == null) {
				return false;
			}

			if (boardCheck[i][col].getHeight() != firstHeight) heightcheck = false;
			if (boardCheck[i][col].getColor() != firstColor) colorcheck = false;
			if (boardCheck[i][col].getHole() != firstHole) holecheck = false;
			if (boardCheck[i][col].getShape() != firstShape) shapecheck = false;
		}

		boolean result = heightcheck || colorcheck || holecheck || shapecheck;
		return result;
	}



	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		// Board state
		sb.append("Game Board:\n");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] != null) {
					sb.append(board[i][j]).append(" ");
				} else {
					sb.append("[Empty] ");
				}
			}
			sb.append("\n");
		}

		// Unplaced pieces
		sb.append("\nUnplaced Pieces:\n");
		for (Piece piece : unPlaced) {
			sb.append(piece).append(" ");
		}

		// Current player information
		sb.append("\n\nCurrent Player: ")
				.append(playerID == HUMANPLAYER ? "Human" : "CPU")
				.append("\nCurrent Phase: ")
				.append(phase ? "Selection" : "Placement")
				.append("\nCurrent Piece ID: ").append(currentPiece);

		// Game status
		sb.append("\nGame Status: ");
		switch (gameStatus) {
			case ACTIVE:
				sb.append("Active");
				break;
			case WON:
				sb.append("Won");
				break;
			case LOST:
				sb.append("Lost");
				break;
			default:
				sb.append("Unknown");
		}

		// Result message
		sb.append("\nResult: ").append(result);

		return sb.toString();
	}

}


