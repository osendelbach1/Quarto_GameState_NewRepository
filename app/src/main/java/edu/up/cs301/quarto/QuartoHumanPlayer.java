package edu.up.cs301.quarto;
import edu.up.cs301.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;

import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import com.google.android.material.snackbar.Snackbar;
import android.os.Handler;

/**
 * A GUI of a quarto-player. The GUI displays the current value of the quarto,
 * and allows the human player to press the '+' and '-' buttons in order to
 * send moves to the game.
 *
 * Just for fun, the GUI is implemented so that if the player presses either button
 * when the quarto-value is zero, the screen flashes briefly, with the flash-color
 * being dependent on whether the player is player 0 or player 1.
 *
 * @author Olivia Sendelbach, Magnus Graham, Becca Biukoto, Aaron Chu,Steven R. Vegdahl, Andrew M. Nuxoll
 *
 * @Author: Another reference from Shogi group ShogiHumanPlayer.Java
 * @version July 2013
 */
public class QuartoHumanPlayer extends GameHumanPlayer implements OnClickListener, View.OnTouchListener {

	/* instance variables */

	// the most recent game state, as given to us by the quartoLocalGame
	private QuartoState state;

	// the android activity that we are running
	private GameMainActivity myActivity;

	//Refernce variables
	//Refernce of the surface view of the board
	private QuartoBoardView quartoBoardView;

	/**
	 * constructor
	 * @param name
	 * 		the player's name
	 */
	public QuartoHumanPlayer(String name) {
		super(name);
	}

	/**
	 * Returns the GUI's top view object
	 *
	 * @return
	 * 		the top object in the GUI's view heirarchy
	 */
	public View getTopView() {
		return myActivity.findViewById(R.id.topLevel);
	}

	/**
	 * sets the quarto value in the text view
	 */
	protected void updateDisplay() {
		// set the text in the appropriate widget
		//quartoValueTextView.setText("" + state.getquarto());
	}

	/**
	 * this method gets called when the user clicks the '+' or '-' button. It
	 * creates a new quartoMoveAction to return to the parent activity.
	 *
	 * @param button
	 * 		the button that was clicked
	 */

	public void onClick(View button) {

		int index = -1; // Default invalid index

		// Map button IDs to indices
		if (button.getId() == R.id.button1) {
			index = 0;
		} else if (button.getId() == R.id.button2) {
			index = 1;
		} else if (button.getId() == R.id.button3) {
			index = 2;
		} else if (button.getId() == R.id.button4) {
			index = 3;
		} else if (button.getId() == R.id.button5) {
			index = 4;
		} else if (button.getId() == R.id.button6) {
			index = 5;
		} else if (button.getId() == R.id.button7) {
			index = 6;
		} else if (button.getId() == R.id.button8) {
			index = 7;
		} else if (button.getId() == R.id.button9) {
			index = 8;
		} else if (button.getId() == R.id.button10) {
			index = 9;
		} else if (button.getId() == R.id.button11) {
			index = 10;
		} else if (button.getId() == R.id.button12) {
			index = 11;
		} else if (button.getId() == R.id.button13) {
			index = 12;
		} else if (button.getId() == R.id.button14) {
			index = 13;
		} else if (button.getId() == R.id.button15) {
			index = 14;
		} else {
			index = 15; // Default case
		}

		// Get the Piece
		Piece p = state.getPieces()[index];
		boolean found = false;
		for (Piece p1 : state.getUnPlaced()) {
			if (state.PieceEquals(p1, p)) {
				found = true;
				selectPieceAction spa = new selectPieceAction(this, p1);
				game.sendAction(spa);
				break;
			}
		}
		if(!found) {
			Log.d("Error","Piece already placed!");
		}


	}// onClick

	public boolean onTouch(View view, MotionEvent motionEvent) {
		Piece q = state.getCurrentPiece();
		float x = motionEvent.getX();
		float y = motionEvent.getY();
		Log.d("Touch","" + x + " " + y);
		placePieceAction ppa = new placePieceAction(this, x, y, q);
		game.sendAction(ppa);
		view.invalidate();
		return true;
	}

	/**
	 * callback method when we get a message (e.g., from the game)
	 *
	 * @param info
	 * 		the message
	 */
	@Override
	public void receiveInfo(GameInfo info) {
		// ignore the message if it's not a quartoState message
		if (!(info instanceof QuartoState)) return;

		// update our state; then update the display
		this.state = (QuartoState)info;
		updateDisplay();
	}

	/**
	 * callback method--our game has been chosen/rechosen to be the GUI,
	 * called from the GUI thread
	 *
	 * @param activity
	 * 		the activity under which we are running
	 */
	public void setAsGui(GameMainActivity activity) {

		// remember the activity
		this.myActivity = activity;
		
		// Load the layout resource for our GUI
		activity.setContentView(R.layout.activity_main);

		Button button1 = activity.findViewById(R.id.button1);
		button1.setOnClickListener(this);
		Button button2 = activity.findViewById(R.id.button2);
		button2.setOnClickListener(this);
		Button button3 = activity.findViewById(R.id.button3);
		button3.setOnClickListener(this);
		Button button4 = activity.findViewById(R.id.button4);
		button4.setOnClickListener(this);
		Button button5 = activity.findViewById(R.id.button5);
		button5.setOnClickListener(this);
		Button button6 = activity.findViewById(R.id.button6);
		button6.setOnClickListener(this);
		Button button7 = activity.findViewById(R.id.button7);
		button7.setOnClickListener(this);
		Button button8 = activity.findViewById(R.id.button8);
		button8.setOnClickListener(this);
		Button button9 = activity.findViewById(R.id.button9);
		button9.setOnClickListener(this);
		Button button10 = activity.findViewById(R.id.button10);
		button10.setOnClickListener(this);
		Button button11 = activity.findViewById(R.id.button11);
		button11.setOnClickListener(this);
		Button button12 = activity.findViewById(R.id.button12);
		button12.setOnClickListener(this);
		Button button13 = activity.findViewById(R.id.button13);
		button13.setOnClickListener(this);
		Button button14 = activity.findViewById(R.id.button14);
		button14.setOnClickListener(this);
		Button button15 = activity.findViewById(R.id.button15);
		button15.setOnClickListener(this);
		Button button16 = activity.findViewById(R.id.button16);
		button16.setOnClickListener(this);

		QuartoBoardView QBV = activity.findViewById(R.id.QuartoBoardView);
		QBV.setOnTouchListener(this);

	}
}// class quartoHumanPlayer



