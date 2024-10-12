package edu.up.cs301.quarto;

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

	// to satisfy Serializable interface
	private static final long serialVersionUID = 7737393762469851826L;

	//game board
	private int[][] board;

	//each individual piece ID
	private int piece1 = 0;
	private int piece2 = 1;
	private int piece3 = 2;
	private int piece4 = 3;
	private int piece5 = 4;
	private int piece6 = 5;
	private int piece7 = 6;
	private int piece8 = 7;
	private int piece9 = 8;
	private int piece10 = 9;
	private int piece11 = 10;
	private int piece12 = 11;
	private int piece13 = 12;
	private int piece14 = 13;
	private int piece15 = 14;
	private int piece16 = 15;


	//0 = human and 1 == other or AI
	private int playerID;

	//ID of piece selected
	private int currentPiece;

	//True for selection phase and false for placement phase of current player
	//Initialized as true because when the game starts you start off by selecting

	//If true, display "your turn" text as well
	private boolean phase;

	//0 = active, 1 = won, 2 = lose
	private int gameStatus;

	//If you have placed the piece given to you and then selected a piece for the other player, you have completed all the actions for your turn
	private boolean turnComplete;

	private String result;

	//constructor, initializing the quarto value from the parameter
	public QuartoState() {
		board = new int[4][4];

		piece1 = 1;
		piece2 = 2;
		piece3 = 3;
		piece4 = 4;
		piece5 = 5;
		piece6 = 6;
		piece7 = 7;
		piece8 = 8;
		piece9 = 9;
		piece10 = 10;
		piece11 = 11;
		piece12 = 12;
		piece13 = 13;
		piece14 = 14;
		piece15 = 15;
		piece16 = 16;

		playerID = 0;

		//set at 0 by default since there is no piece with 0 as an ID
		currentPiece = 0;

		phase = true;

		gameStatus = 0;

		turnComplete = false;

		result = "";
	}

	//copy constructor; makes a copy of the original object
	public QuartoState(QuartoState other) {
		this.board = other.board;

		this.piece1 = other.piece1;
		this.piece2 = other.piece2;
		this.piece3 = other.piece3;
		this.piece4 = other.piece4;
		this.piece5 = other.piece5;
		this.piece6 = other.piece6;
		this.piece7 = other.piece7;
		this.piece8 = other.piece8;
		this.piece9 = other.piece9;
		this.piece10 = other.piece10;
		this.piece11 = other.piece11;
		this.piece12 = other.piece12;
		this.piece13 = other.piece13;
		this.piece14 = other.piece14;
		this.piece15 = other.piece15;
		this.piece16 = other.piece16;


		this.playerID = other.playerID;

		this.currentPiece = other.currentPiece;

		this.phase = other.phase;

		this.gameStatus = other.gameStatus;

		this.turnComplete = other.turnComplete;

		this.result = other.result;
	}


	public boolean placePieceAction(GameAction Action)
	{
		if(gameStatus == 0)
		{
			if(phase == false)
			{
				phase = true;
				return true;
			}
		}
			return false;
	}

	public boolean selectPieceAction(GameAction Action)
	{
		if(gameStatus == 0)
		{
			if(phase == true)
			{
				phase = false;
				return true;
			}
		}
		return false;
	}

	public boolean quitAction(GameAction Action)
	{
		if(gameStatus == 0 || gameStatus == 1 || gameStatus == 2)
		{
			return true;
		}
		return false;
	}

	public boolean endTurnAction(GameAction Action)
	{
		if(gameStatus == 0)
		{
			if(phase == true)
			{
				if(turnComplete == true)
				{

					return true;
				}
			}
		}
		return false;
	}

	//requirements to be able to declare victory have to be made in a method for future assignment
	public boolean declareVictoryAction(GameAction Action)
	{
		if(gameStatus == 1)
		{
			return true;
		}
		return false;
	}

	@Override
	public String toString()
	{
		if (playerID == 0)
		{
			result += "Your turn";
		}
		 else if (playerID == 1)
		{
			result += "Ai's Turn";
		}
		 return "";
	}


}



	///**
	 //* getter method for the quarto
	 //*
	 //* @return
	 //* 		the value of the quarto
	// */
	//public int getquarto() {
		//return quarto;
	//}

	///**
	// * setter method for the quarto
	// *
	 //* @param quarto
	 //* 		the value to which the quarto should be set
	 //*/
	//public void setquarto(int quarto) {
		//this.quarto = quarto;
	//}

