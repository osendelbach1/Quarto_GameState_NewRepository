package edu.up.cs301.quarto;

import java.util.ArrayList;
import java.util.Arrays;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.infoMessage.GameState;


/**
 * This contains the state for the Quarto game. The state consist of the
 * pieces, the board, whether the player is in the selection or placement
 * phase, the status of the game, and whether the player has completed
 * their turn.
 *
 * @author Olivia Sendelbach, Magnus Graham, Becca Biukoto, Aaron Chu
 * @Date 10/06/24
 */
public class QuartoState extends GameState {

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
	private static final long serialVersionUID = 7737393762469851826L;

	//game board
	private Piece[][] board = new Piece[4][4];
	private Piece[] pieces = new Piece[16];

	//each individual piece ID

	private ArrayList<Piece> unPlaced = new ArrayList<Piece>(16);

	//0 = human and 1 == other or AI
	private int playerID;
	public static final int HUMANPLAYER = 0;
	public static final int CPUPLAYER = 1;


	//ID of piece selected
	private Piece currentPiece = new Piece(false, false, false, false);

	//True for selection phase and false for placement phase of current player
	//Initialized as true because when the game starts you start off by selecting

	//If true, display "your turn" text as well
	private boolean phase;
	public static final boolean SELECTION = true;
	public static final boolean PLACEMENT = false;

	//0 = active, 1 = won, 2 = lose
	private int gameStatus;
	//constants for game state
	public static final int ACTIVE = 0;
	public static final int WON = 1;
	public static final int LOST = 2;
	public static final int QUITTED = 3;


	//If you have placed the piece given to you and then selected a piece for the other player, you have completed all the actions for your turn
	private boolean Player1TurnComplete;
	private boolean Player2TurnComplete;
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
		this.Player1TurnComplete = other.Player1TurnComplete;
		this.Player2TurnComplete = other.Player2TurnComplete;
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
				this.board[row][col] = currentPiece;
				phase = SELECTION;
				return true;
			}
		}
		return false;
	}

	public boolean selectPieceAction(boolean height, boolean hole, boolean color, boolean shape)
	{
		if(gameStatus == ACTIVE)
		{
			if(phase == SELECTION)
			{
				for (int i = 0; i < unPlaced.size(); i++) {
					if (unPlaced.get(i).getHeight() == height && unPlaced.get(i).getHole() == hole &&  unPlaced.get(i).getColor() == color && unPlaced.get(i).getShape() == shape) {
						currentPiece = unPlaced.get(i);
						unPlaced.remove(i);
						if (playerID == HUMANPLAYER) {
							Player1TurnComplete = true;
							Player2TurnComplete = false;
							break;
						}
						else {
							Player2TurnComplete = true;
							Player1TurnComplete = false;
							break;
						}
					}
				}
				phase = PLACEMENT;
				return true;
			}
		}
		return false;
	}

	public boolean quitAction()
	{
		if(gameStatus == ACTIVE || gameStatus == WON || gameStatus == LOST)
		{
			gameStatus = QUITTED;
		}
		return true;
	}

	public boolean endTurnAction()
	{
		if(gameStatus == ACTIVE)
		{
			if(phase == PLACEMENT)
			{
				if(Player1TurnComplete == COMPLETE)
				{

					return true;
				}
			}
		}
		return false;
	}

	public boolean PieceEquals(Piece p1, Piece p2) {
		return p1.getHeight() == p2.getHeight() && p1.getHole() == p2.getHole() && p1.getColor() == p2.getColor() && p1.getShape() == p2.getShape();
	}

	//requirements to be able to declare victory have to be made in a method for future assignment
	public boolean declareVictoryAction()
	{
		boolean rowcheck = true, colcheck = true, diagcheck = true;
		for (int i = 0; i < 4; i++) {
			if (!checkRow(board, i)) {
				rowcheck = false;
			}
			if (!checkCol(board, i)) {
				colcheck = false;
			}
			if (!checkDiagonal(board, true)) {
				diagcheck = false;
			}
			if (!checkDiagonal(board, false)) {
				diagcheck = false;
			}
		}

		//check which player has won
		if(rowcheck || colcheck || diagcheck) {
			if (playerID == HUMANPLAYER) {
				gameStatus = WON;
				return true;
			}
			else if (playerID == CPUPLAYER) {
				gameStatus = LOST;
				return true;
			}
		}
		return false;
	}

	public boolean checkRow(Piece[][] boardCheck, int row) {
		// Check if all values in the row are true
		boolean firstHeight = boardCheck[row][0].getHeight();
		boolean firstColor = boardCheck[row][0].getColor();
		boolean firstHole = boardCheck[row][0].getHole();
		boolean firstShape = boardCheck[row][0].getShape();
		boolean heightcheck = true, colorcheck = true, holecheck = true, shapecheck = true;


		for (int j = 0; j < 4; j++) {

			if (boardCheck[row][j].getHeight() != firstHeight) {
				heightcheck = false;
			}
			if (boardCheck[row][j].getHeight() != firstColor) {
				colorcheck = false;
			}
			if (boardCheck[row][j].getHeight() != firstHole) {
				holecheck = false;
			}
			if (boardCheck[row][j].getHeight() != firstShape) {
				shapecheck = false;
			}
		}
		return heightcheck || colorcheck || holecheck || shapecheck;
	}

	public boolean checkCol(Piece[][] boardCheck, int col) {
		// Check if all values in the row are true
		boolean firstHeight = boardCheck[0][col].getHeight();
		boolean firstColor = boardCheck[0][col].getColor();
		boolean firstHole = boardCheck[0][col].getHole();
		boolean firstShape = boardCheck[0][col].getShape();
		boolean heightcheck = true, colorcheck = true, holecheck = true, shapecheck = true;


		for (int i = 0; i < 4; i++) {

			if (boardCheck[i][col].getHeight() != firstHeight) {
				heightcheck = false;
			}
			if (boardCheck[i][col].getHeight() != firstColor) {
				colorcheck = false;
			}
			if (boardCheck[i][col].getHeight() != firstHole) {
				holecheck = false;
			}
			if (boardCheck[i][col].getHeight() != firstShape) {
				shapecheck = false;
			}
		}
		return heightcheck || colorcheck || holecheck || shapecheck;
	}

	public boolean checkDiagonal(Piece[][] boardcheck, boolean leftToRight) {
		boolean heightcheck = true, colorcheck = true, holecheck = true, shapecheck = true;

		// Initialize the first piece's properties based on the diagonal direction
		Piece firstPiece = leftToRight ? boardcheck[0][0] : boardcheck[0][3];
		boolean firstHeight = firstPiece.getHeight();
		boolean firstColor = firstPiece.getColor();
		boolean firstHole = firstPiece.getHole();
		boolean firstShape = firstPiece.getShape();

		// Check the diagonal according to the direction
		for (int i = 1; i < 4; i++) {

			int col = leftToRight ? i : (3 - i);

			// Update checks if any property doesn't match the first piece
			if (boardcheck[i][col].getHeight() != firstHeight) heightcheck = false;
			if (boardcheck[i][col].getColor() != firstColor) colorcheck = false;
			if (boardcheck[i][col].getHole() != firstHole) holecheck = false;
			if (boardcheck[i][col].getShape() != firstShape) shapecheck = false;
		}

		// Return true if any of the property checks succeeded
		return heightcheck || colorcheck || holecheck || shapecheck;
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

		// Player turn completion
		sb.append("\nPlayer 1 Turn Complete: ").append(Player1TurnComplete ? "Yes" : "No");
		sb.append("\nPlayer 2 Turn Complete: ").append(Player2TurnComplete ? "Yes" : "No");

		// Result message
		sb.append("\nResult: ").append(result);

		return sb.toString();
	}

}


