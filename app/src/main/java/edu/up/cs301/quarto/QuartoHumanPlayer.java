package edu.up.cs301.quarto;

import edu.up.cs301.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * A GUI of a quarto-player. The GUI displays the current value of the quarto,
 * and allows the human player to press the '+' and '-' buttons in order to
 * send moves to the game.
 *
 * Just for fun, the GUI is implemented so that if the player presses either button
 * when the quarto-value is zero, the screen flashes briefly, with the flash-color
 * being dependent on whether the player is player 0 or player 1.
 *
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version July 2013
 */
public class QuartoHumanPlayer extends GameHumanPlayer implements OnClickListener, View.OnTouchListener {

	/* instance variables */

	// the most recent game state, as given to us by the quartoLocalGame
	private QuartoState state;

	// the android activity that we are running
	private GameMainActivity myActivity;

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
		Piece p = state.getUnPlaced().get(0);
		selectPieceAction spa = new selectPieceAction(this, p);
		game.sendAction(spa);

	}// onClick

	public boolean onTouch(View view, MotionEvent motionEvent) {
		Piece q = state.getUnPlaced().get(0);
		float x = motionEvent.getX();
		float y = motionEvent.getY();
		placePieceAction ppa = new placePieceAction(this, x, y, q);
		game.sendAction(ppa);
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
	}
}// class quartoHumanPlayer

