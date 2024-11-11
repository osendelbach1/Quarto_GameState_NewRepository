package edu.up.cs301.quarto;

import edu.up.cs301.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.ArrayList;

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

	// The TextView the displays the current quarto value
	private TextView testResultsTextView;

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
		// if we are not yet connected to a game, ignore
		if (game == null) return;
		testResultsTextView.invalidate();
		QuartoState firstInstance = new QuartoState();
		QuartoState firstCopy = new QuartoState(firstInstance);
		//place 4 light pieces in a row
		//check for victory
		//quit the game

		ArrayList<String> output = new ArrayList<>();


		firstInstance.selectPieceAction(true, false, false, false);
		output.add("Player 1 selects Tall, no hole, light color, circle.");
		firstInstance.endTurnAction();

		firstInstance.placePieceAction(0,0);
		output.add("Player 2 places the piece on row 0 column 0.");
		firstInstance.selectPieceAction(true, true, false, true);
		output.add("Player 2 selects Tall, hole, light color, square.");

		firstInstance.endTurnAction();

		firstInstance.placePieceAction(0, 1);
		output.add("Player 1 places the piece on row 0 column 1.");

		firstInstance.selectPieceAction(true, true, true, true);
		output.add("Player 1 selects Tall, hole, dark color, square.");

		firstInstance.placePieceAction(0, 2);
		output.add("Player 2 places the piece on row 0 column 2.");

		firstInstance.selectPieceAction(true, false, false, true);
		output.add("Player 2 selects Tall, no hole, light color, square.");

		firstInstance.placePieceAction(0, 3);
		output.add("Player 1 places the piece on row 0 column 3.");


		firstInstance.declareVictoryAction();
		output.add("Player 1 declares victory.");


		firstInstance.quitAction();
		output.add("Player 1 quits the game.");



		QuartoState secondInstance = new QuartoState();
		QuartoState secondCopy = new QuartoState(secondInstance);

		testResultsTextView.getText();

		String existingText = "";
		for(String s : output) {
			existingText += s + "\n";
		}

		existingText += testResultsTextView.getText().toString();

		testResultsTextView.setText(existingText + firstCopy.toString() + secondCopy.toString());






	}// onClick

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
		activity.setContentView(R.layout.game_unit_tests);

		// initialize textView variable with reference to tv in new xml file
		testResultsTextView = activity.findViewById(R.id.editTextTextMultiLine);

		//register human player as listener for Run Test button
		Button runTest = activity.findViewById(R.id.runTestButt);
		runTest.setOnClickListener(this);
	}

	@Override
	public boolean onTouch(View view, MotionEvent motionEvent) {
		return false;
	}
}// class quartoHumanPlayer

