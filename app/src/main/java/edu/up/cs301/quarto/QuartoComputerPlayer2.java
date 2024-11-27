package edu.up.cs301.quarto;

import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;


import java.io.Serializable;
import java.util.Hashtable;
import java.util.ArrayList;

import java.util.Random;

import edu.up.cs301.quarto.Quadruplet;




/**
 * Smart AI:
 * Compared to the dumb AI, this one is meticulous about which piece to select
 * and finds the most optimal place to put the piece on the board. It's main goal is to win
 *
 * @author Magnus Graham
 * @author Olivia Sendelbach
 * @author Becca Biukoto
 * @version 11/22/2024
 */
public class QuartoComputerPlayer2 extends QuartoComputerPlayer1 implements Serializable {

	//constants for characteristics of pieces
	public static final Boolean SHORT = true;
	public static final Boolean TALL = false;
	public static final Boolean HOLE = true;
	public static final Boolean SOLID = false;
	public static final Boolean DARK = true;
	public static final Boolean LIGHT = false;
	public static final Boolean SQUARE =  true;
	public static final Boolean CIRCLE = false;

	// Initialize Row instances
	Quadruplet Row0 = new Quadruplet();
	Quadruplet Row1 = new Quadruplet();
	Quadruplet Row2 = new Quadruplet();
	Quadruplet Row3 = new Quadruplet();

	// Initialize Column instances
	Quadruplet Col0 = new Quadruplet();
	Quadruplet Col1 = new Quadruplet();
	Quadruplet Col2 = new Quadruplet();
	Quadruplet Col3 = new Quadruplet();

	// Initialize Diagonal instances
	Quadruplet Diag0 = new Quadruplet();
	Quadruplet Diag1 = new Quadruplet();

	ArrayList<Quadruplet> rankedArrayPlace = new ArrayList<>();


	/*
	 * instance variables
	 */
	
	// the most recent game state, as given to us by the quartoLocalGame
	private QuartoState currentGameState = null;
	
	// If this player is running the GUI, the activity (null if the player is
	// not running a GUI).
	private Activity activityForGui = null;
	
	// If this player is running the GUI, the widget containing the quarto's
	// value (otherwise, null);
	private TextView quartoValueTextView = null;
	
	// If this player is running the GUI, the handler for the GUI thread (otherwise
	// null)
	private Handler guiHandler = null;
	
	/**
	 * constructor
	 * 
	 * @param name
	 * 		the player's name
	 */
	public QuartoComputerPlayer2(String name) {
		super(name);
	}
	
    /**
     * callback method--game's state has changed
     * 
     * @param info
     * 		the information (presumably containing the game's state)
     */
	@Override
	protected void receiveInfo(GameInfo info) {
		// perform superclass behavior
		super.receiveInfo(info);
		
		Log.i("computer player", "receiving");
		
		// if there is no game, ignore
		if (game == null) {
			return;
		}
		else if (info instanceof QuartoState) {
			// if we indeed have a quarto-state, update the GUI
			currentGameState = (QuartoState)info;
			updateDisplay();
		}

		//check if it's AI's turn
		if (currentGameState.getTurn() != 1 ) {
			return; // It's not the AI's turn, so do nothing
		}

		this.smartPlace(rankedArrayPlace);
		declareVictoryAction dva = new declareVictoryAction(this);
		this.smartSelect(currentGameState.getUnPlaced());


	}


	private void smartPlace(ArrayList<Quadruplet> rankedArray) {

		//instantiate array containing all rows, cols, and diagonals
		 Quadruplet[] quads = new Quadruplet[10];
		 quads[0] = Row0;
		 quads[1] = Row1;
		 quads[2] = Row2;
		 quads[3] = Row3;
		 quads[4] = Col0;
		 quads[5] = Col1;
		 quads[6] = Col2;
		 quads[7] = Col3;
		 quads[8] = Diag0;
		 quads[9] = Diag1;

		Hashtable<Quadruplet, Integer> corresponding = new Hashtable<>(); //table contains the corresponding row/col/diag for each quadruplet

		 for (int i = 0; i < 10; i++) {
			 corresponding.put(quads[i], i%4);
		 }

		Hashtable<Quadruplet, ArrayList<Integer>> openSpots = new Hashtable<>(); //hashtable declaring which quadruplet corresponds to which table index

		//for loop through board, updating each quadruplet's "placeable" characteristic and count of each characteristic
		for (int i = 0; i < 10; i++) {
			if (i < 4) {
				checkRow(quads[i], i, openSpots); //rows
			} else if (i < 8) {
				checkCol(quads[i], i % 4, openSpots); //cols
			} else if (i == 8) {
				checkDiagonal(quads[i], true, openSpots); //diag left to right
			} else if (i == 9) {
				checkDiagonal(quads[i], false, openSpots); //diag right to left
			}
			addRanked(rankedArray, quads[i]); // add row/col/diag to arraylist
		}

		//based on the ranked array, the most highly patterned Row/col/diag is chosen.
		Quadruplet chosen = rankedArray.get(0);
		int x = 0;
		int y = 0;

		Random random = new Random();
		int rand = random.nextInt(openSpots.get(chosen).size());
		//assign index within the selected row/col/diag
		for (int i = 0; i < 10; i++) {
			if (i < 4 && chosen.equals(quads[i])) {
				x = corresponding.get(chosen);
				y = openSpots.get(quads[i]).get(rand);
			}
			else if (i < 8 && chosen.equals(quads[i])) {
				y = corresponding.get(chosen);
				x = openSpots.get(quads[i]).get(rand);
			}
			else if (i == 8 && chosen.equals(quads[i])) {
				x = openSpots.get(quads[i]).get(rand);
				y = x;
			}
			else if (i == 9 && chosen.equals(quads[i])) {
				x = openSpots.get(quads[i]).get(rand);
				y = x;
			}

		}

		//pass the x and y to placePieceAction
		placePieceAction ppa = new placePieceAction(this, x, y, currentGameState.getCurrentPiece());
		game.sendAction(ppa);

	}


	public void addRanked(ArrayList<Quadruplet> rankedList, Quadruplet q) {
		if (!q.getPlaceable()) return;

		for (int i = 0; i < rankedList.size(); i++) {
			if (q.getTotal() > rankedList.get(i).getTotal()) {
				rankedList.add(i, q);
				return; // Exit after inserting
			}
		}
		rankedList.add(q); // Add at the end if no larger total found
	}

	public void checkRow(Quadruplet q, int row, Hashtable<Quadruplet, ArrayList<Integer>> openSpots) {
		// Check if all values in the row are true

		for (int j = 0; j < 4; j++) {

			Piece boardsquare = currentGameState.getBoard()[row][j];

			if (boardsquare == null) {
				openSpots.putIfAbsent(q, new ArrayList<>());
				openSpots.get(q).add(j);
				if (!q.getPlaceable()) {
					q.setPlaceable(true);
				}
			}
			if(boardsquare != null) {
				addCharacteristic(boardsquare, q);
			}
		}
	}
	public void checkCol(Quadruplet q, int col, Hashtable<Quadruplet, ArrayList<Integer>> openSpots) {
		// Check if all values in the row are true

		for (int i = 0; i < 4; i++) {

			Piece boardsquare = currentGameState.getBoard()[i][col];


			if (boardsquare == null) {
				openSpots.putIfAbsent(q, new ArrayList<>());
				openSpots.get(q).add(i);
				if (!q.getPlaceable()) {
				q.setPlaceable(true);
				}
			}

			if(boardsquare != null) {
				addCharacteristic(boardsquare, q);
			}
		}
	}
	public void checkDiagonal(Quadruplet q, boolean leftToRight, Hashtable<Quadruplet, ArrayList<Integer>> openSpots) {

		// Initialize the first piece's properties based on the diagonal direction
		// Check the diagonal according to the direction
		for (int i = 1; i < 4; i++) {

			int col = leftToRight ? i : (3 - i);
			Piece boardsquare = currentGameState.getBoard()[i][col];


			// Update checks if any property doesn't match the first piece

				if (boardsquare == null) {
					openSpots.putIfAbsent(q, new ArrayList<>());
					openSpots.get(q).add(i);
					if (!q.getPlaceable()) {
					q.setPlaceable(true);
					}
				}
			if(boardsquare != null) {
				addCharacteristic(boardsquare, q);
			}
		}
		// Return true if any of the property checks succeeded
	}


	public void addCharacteristic(Piece b, Quadruplet q) {
		if(b.getShape() == CIRCLE) {
			q.addConsecCircle();
		}
		if (b.getShape() == SQUARE) {
			q.addConsecSquare();
		}
		if (b.getColor() == LIGHT) {
			q.addConsecLight();
		}
		if (b.getColor() == DARK) {
			q.addConsecDark();
		}
		if (b.getHole() == HOLE) {
			q.addConsecHole();
		}
		if (b.getHole() == SOLID) {
			q.addConsecHole();
		}
		if (b.getHeight() == TALL) {
			q.addConsecTall();
		}
		if (b.getHeight() == SHORT) {
			q.addConsecShort();
		}
	}


	public void smartSelect(ArrayList<Piece> unplaced) {
		int[] characteristics = new int[8]; // Array to store counts for each characteristic in unPlaced array.

		// Count occurrences of each characteristic
		for (int i = 0; i < unplaced.size(); i++) { //assigning each characteristic to an array index
			if (unplaced.get(i).getHeight() == SHORT) characteristics[0]++;
			if (unplaced.get(i).getHeight() == TALL) characteristics[1]++;
			if (unplaced.get(i).getHole() == HOLE) characteristics[2]++;
			if (unplaced.get(i).getHole() == SOLID) characteristics[3]++;
			if (unplaced.get(i).getColor() == DARK) characteristics[4]++;
			if (unplaced.get(i).getColor() == LIGHT) characteristics[5]++;
			if (unplaced.get(i).getShape() == CIRCLE) characteristics[6]++;
			if (unplaced.get(i).getShape() == SQUARE) characteristics[7]++;
		}

		int maxCount = 0;
		ArrayList<Integer> prevalent = new ArrayList<>(); //arraylist keeping track of the most prevalent characteristics
		for (int i = 0; i < characteristics.length; i++) { //loop through, only keeping characteristics appearing the most
			if (characteristics[i] > maxCount) {
				maxCount = characteristics[i]; //add new, most prevalent characteristic
				prevalent.clear(); // Clear previous less-prevalent characteristics
				prevalent.add(i);
			} else if (characteristics[i] == maxCount) {
				prevalent.add(i); // Add characteristics with count equal to maxCount
			}
		}

		// Lambda function, sorting pieces by how many prevalent characteristics they have
		unplaced.sort((a, b) -> {
			int aScore = 0, bScore = 0;

			// Count how many prevalent characteristics each piece has (prevalence determined in previous step)
			for (int p : prevalent) {
				if (p == 0 && a.getHeight() == SHORT) aScore++;
				if (p == 1 && a.getHeight() == TALL) aScore++;
				if (p == 2 && a.getHole() == HOLE) aScore++;
				if (p == 3 && a.getHole() == SOLID) aScore++;
				if (p == 4 && a.getColor() == DARK) aScore++;
				if (p == 5 && a.getColor() == LIGHT) aScore++;
				if (p == 6 && a.getShape() == CIRCLE) aScore++;
				if (p == 7 && a.getShape() == SQUARE) aScore++;

				if (p == 0 && b.getHeight() == SHORT) bScore++;
				if (p == 1 && b.getHeight() == TALL) bScore++;
				if (p == 2 && b.getHole() == HOLE) bScore++;
				if (p == 3 && b.getHole() == SOLID) bScore++;
				if (p == 4 && b.getColor() == DARK) bScore++;
				if (p == 5 && b.getColor() == LIGHT) bScore++;
				if (p == 6 && b.getShape() == CIRCLE) bScore++;
				if (p == 7 && b.getShape() == SQUARE) bScore++;
			}
			return Integer.compare(bScore, aScore); //return sorted arraylist
		});
		selectPieceAction spa = new selectPieceAction(this, unplaced.get(0)); //call selectPieceAction on the piece that has the least common characteristics with the board
		game.sendAction(spa);
	}

	/** 
	 * sets the quarto value in the text view
	 *  */
	private void updateDisplay() {
		// if the guiHandler is available, set the new quarto value
		// in the quarto-display widget, doing it in the Activity's
		// thread.
		if (guiHandler != null) {
			guiHandler.post(
					new Runnable() {
						public void run() {
						if (quartoValueTextView != null && currentGameState != null) {
							//TODO: edit this area
						}
					}});
		}
	}
	
	/**
	 * Tells whether we support a GUI
	 * 
	 * @return
	 * 		true because we support a GUI
	 */
	public boolean supportsGui() {
		return true;
	}
	
	/**
	 * callback method--our player has been chosen/rechosen to be the GUI,
	 * called from the GUI thread.
	 * 
	 * @param a
	 * 		the activity under which we are running
	 */
	@Override
	public void setAsGui(GameMainActivity a) {
		
		// remember who our activity is
		this.activityForGui = a;
		
		// remember the handler for the GUI thread
		this.guiHandler = new Handler();
		
		// Load the layout resource for the our GUI's configuration
		activityForGui.setContentView(R.layout.activity_main);
		
		// if the state is non=null, update the display
		if (currentGameState != null) {
			updateDisplay();
		}
	}

}
