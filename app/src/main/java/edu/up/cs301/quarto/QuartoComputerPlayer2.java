package edu.up.cs301.quarto;

import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;

import edu.up.cs301.quarto.Quadruplet;



/**
* A computer-version of a quarto-player.  Since this is such a simple game,
* it just sends "+" and "-" commands with equal probability, at an average
* rate of one per second. This computer player does, however, have an option to
* display the game as it is progressing, so if there is no human player on the
* device, this player will display a GUI that shows the value of the quarto
* as the game is being played.
* 
* @author Steven R. Vegdahl
* @author Andrew M. Nuxoll
* @version September 2013
*/
public class QuartoComputerPlayer2 extends QuartoComputerPlayer1 {
	
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
	}

	Quadruplet Row0;
	Quadruplet Row1;
	Quadruplet Row2;
	Quadruplet Row3;

	Quadruplet Col0;
	Quadruplet Col1;
	Quadruplet Col2;
	Quadruplet Col3;

	Quadruplet Diag0;
	Quadruplet Diag1;

	private void smartPlace() {
		ArrayList<Quadruplet> rankedArray = new ArrayList<>();
		//declare arraylist

		//for loop through board (vertical, horizontal, diagonal)
			//if null found in row, make placeable true
				//if pattern disrupted (helper method), make consecutive false

				//increment found characteristics ConsecShort, ConsecTall by 1

		//end of row iteration loop
			//if placeable true, add row/col/diag to ranked array list (helper method)







		//each pass through should contain a number of consecutive shape, height, color, hole

		//note consecutive patterns

	}




	public void addRanked(ArrayList<Quadruplet> rankedList, Quadruplet q) {
		for (int i = 0; i < rankedList.size(); i++) {
			if (q.getTotal() > rankedList.get(i).getTotal()) {
				rankedList.add(i, q);
				return; // Exit after inserting
			}
		}
		rankedList.add(q); // Add at the end if no larger total found
	}

	public boolean checkRow(Quadruplet q, int row) {
		// Check if all values in the row are true


		for (int j = 0; j < 4; j++) {
			if (q.getPlaceable() == false) {
				if (currentGameState.getBoard()[row][j] == null) {
					q.setPlaceable(true);
				}
			}


		}
		return false;
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
							//quartoValueTextView.setText("" + currentGameState.getquarto());
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
		activityForGui.setContentView(R.layout.quarto_human_player);

		// remember who our text view is, for updating the quarto value
		this.quartoValueTextView =
				(TextView) activityForGui.findViewById(R.id.quartoValueTextView);
		
		// disable the buttons, since they will have no effect anyway
		Button plusButton = (Button)activityForGui.findViewById(R.id.plusButton);
		plusButton.setEnabled(false);
		Button minusButton = (Button)activityForGui.findViewById(R.id.minusButton);
		minusButton.setEnabled(false);
		
		// if the state is non=null, update the display
		if (currentGameState != null) {
			updateDisplay();
		}
	}

}
