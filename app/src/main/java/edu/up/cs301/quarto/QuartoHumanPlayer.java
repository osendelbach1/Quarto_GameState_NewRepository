package edu.up.cs301.quarto;

import edu.up.cs301.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
	private QuartoState state = new QuartoState();

	// the android activity that we are running
	private GameMainActivity myActivity;

	//Refernce variables
	//Reference of the surface view of the board
	private QuartoBoardView QBV;

	private TextView textView;
	private TextViewModel tvm;
	private ImageButton imgButton;
	private boolean isPieceSelected = false;
	/**
	 * constructor
	 * @param name
	 * 		the player's name
	 */
	public QuartoHumanPlayer(String name) {
		super(name);
		tvm = new TextViewModel();
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
	protected void updateDisplay(QuartoState qs) {
		if (qs != null) {
			QBV.setQuartoState(qs);
		}
	}

	/**
	 * this method gets called when the user clicks the '+' or '-' button. It
	 * creates a new quartoMoveAction to return to the parent activity.
	 *
	 * @param button
	 * 		the button that was clicked
	 */

	public void onClick(View button) {
		if (isPieceSelected) {
			return;
		}
		int index = -1; // Default invalid index
		boolean selection = false;

		// Map button IDs to indices
		if (button.getId() == R.id.imageButton1) {
			index = 0;
			selection = true;
			imgButton = myActivity.findViewById(R.id.imageButton1);
		} else if (button.getId() == R.id.imageButton2) {
			index = 1;
			selection = true;
			imgButton = myActivity.findViewById(R.id.imageButton2);
		} else if (button.getId() == R.id.imageButton3) {
			index = 2;
			selection = true;
			imgButton = myActivity.findViewById(R.id.imageButton3);
		} else if (button.getId() == R.id.imageButton4) {
			index = 3;
			selection = true;
			imgButton = myActivity.findViewById(R.id.imageButton4);
		} else if (button.getId() == R.id.imageButton5) {
			index = 4;
			selection = true;
			imgButton = myActivity.findViewById(R.id.imageButton5);
		} else if (button.getId() == R.id.imageButton6) {
			index = 5;
			selection = true;
			imgButton = myActivity.findViewById(R.id.imageButton6);
		} else if (button.getId() == R.id.imageButton7) {
			index = 6;
			selection = true;
			imgButton = myActivity.findViewById(R.id.imageButton7);
		} else if (button.getId() == R.id.imageButton8) {
			index = 7;
			selection = true;
			imgButton = myActivity.findViewById(R.id.imageButton8);
		} else if (button.getId() == R.id.imageButton9) {
			index = 8;
			selection = true;
			imgButton = myActivity.findViewById(R.id.imageButton9);
		} else if (button.getId() == R.id.imageButton10) {
			index = 9;
			selection = true;
			imgButton = myActivity.findViewById(R.id.imageButton10);
		} else if (button.getId() == R.id.imageButton11) {
			index = 10;
			selection = true;
			imgButton = myActivity.findViewById(R.id.imageButton11);
		} else if (button.getId() == R.id.imageButton12) {
			index = 11;
			selection = true;
			imgButton = myActivity.findViewById(R.id.imageButton12);
		} else if (button.getId() == R.id.imageButton13) {
			index = 12;
			selection = true;
			imgButton = myActivity.findViewById(R.id.imageButton13);
		} else if (button.getId() == R.id.imageButton14) {
			index = 13;
			selection = true;
			imgButton = myActivity.findViewById(R.id.imageButton14);
		} else if (button.getId() == R.id.imageButton15) {
			index = 14;
			selection = true;
			imgButton = myActivity.findViewById(R.id.imageButton15);
		} else if (button.getId()== R.id.imageButton16) {
			index = 15;
			selection = true;
			imgButton = myActivity.findViewById(R.id.imageButton16);
		}

		if (button.getId() == R.id.quartoButton) {
			declareVictoryAction dva = new declareVictoryAction((this));
			game.sendAction(dva);
		}

		if(selection) {
			// Get the Piece
			Piece p = state.getPieces()[index];
			boolean found = false;
			for (Piece p1 : state.getUnPlaced()) {
				if (state.PieceEquals(p1, p)) {
					found = true;
					selectPieceAction spa = new selectPieceAction(this, p1);
					game.sendAction(spa);
					textView.setText(tvm.cpSelect);
					indicateSelection(imgButton);
					isPieceSelected = true;
					break;
				}
			}
			if (!found) {
				Log.d("Error", "Piece already placed!");
				this.flash(0xFFFF0000, 100);
				isPieceSelected = false;
			}
		}


	}// onClick

	public void indicateSelection(ImageButton button) {
		button.setImageAlpha(128);
	}


	private CharSequence getTurnAndPhase (boolean isHumanTurn, boolean isSelectionPhase) {
		if (isHumanTurn) {
			return isSelectionPhase ? "Your Turn: Select a Piece" : "Your Turn: Place the Piece";
		} else {
			return isSelectionPhase ? "Opponent's Turn: Select a Piece" : "Opponent's Turn: Place the Piece";
		}
	}

	public boolean onTouch(View view, MotionEvent motionEvent) {
		Piece q = state.getCurrentPiece();
		float x = motionEvent.getX();
		float y = motionEvent.getY();
		Log.d("Touch","" + x + " " + y);
		placePieceAction ppa = new placePieceAction(this, x, y, q, view.getHeight(), view.getWidth());
		game.sendAction(ppa);
		isPieceSelected = false;
		textView.setText(tvm.cpPlace);
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
		if (QBV == null) return;

		if (!(info instanceof QuartoState))
			return;
		else {
			this.state = (QuartoState) info;
			updateDisplay(state);
		}
		// Determine if it's the human's turn
		boolean isHumanTurn = state.getTurn() == 0; // Assuming 0 = human, 1 = computer
		boolean isSelectionPhase = state.getPhase(); // Assuming true = selection, false = placement
		Log.d("GameState", "Turn: " + state.getTurn() + ", Phase: " + state.getPhase());


		// Get the appropriate message
		CharSequence turnMessage = getTurnAndPhase(isHumanTurn, isSelectionPhase);

		// Update the TextView
		textView.setText(turnMessage);

		if (!isHumanTurn && isSelectionPhase) {
			indicateSelection(imgButton);
		}
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
		textView = myActivity.findViewById(R.id.PlayerTurn);


		ImageButton imageButton1 = activity.findViewById(R.id.imageButton1);
		imageButton1.setOnClickListener(this);
		ImageButton imageButton2 = activity.findViewById(R.id.imageButton2);
		imageButton2.setOnClickListener(this);
		ImageButton imageButton3 = activity.findViewById(R.id.imageButton3);
		imageButton3.setOnClickListener(this);
		ImageButton imageButton4 = activity.findViewById(R.id.imageButton4);
		imageButton4.setOnClickListener(this);
		ImageButton imageButton5 = activity.findViewById(R.id.imageButton5);
		imageButton5.setOnClickListener(this);
		ImageButton imageButton6 = activity.findViewById(R.id.imageButton6);
		imageButton6.setOnClickListener(this);
		ImageButton imageButton7 = activity.findViewById(R.id.imageButton7);
		imageButton7.setOnClickListener(this);
		ImageButton imageButton8 = activity.findViewById(R.id.imageButton8);
		imageButton8.setOnClickListener(this);
		ImageButton imageButton9 = activity.findViewById(R.id.imageButton9);
		imageButton9.setOnClickListener(this);
		ImageButton imageButton10 = activity.findViewById(R.id.imageButton10);
		imageButton10.setOnClickListener(this);
		ImageButton imageButton11 = activity.findViewById(R.id.imageButton11);
		imageButton11.setOnClickListener(this);
		ImageButton imageButton12 = activity.findViewById(R.id.imageButton12);
		imageButton12.setOnClickListener(this);
		ImageButton imageButton13 = activity.findViewById(R.id.imageButton13);
		imageButton13.setOnClickListener(this);
		ImageButton imageButton14 = activity.findViewById(R.id.imageButton14);
		imageButton14.setOnClickListener(this);
		ImageButton imageButton15 = activity.findViewById(R.id.imageButton15);
		imageButton15.setOnClickListener(this);
		ImageButton imageButton16 = activity.findViewById(R.id.imageButton16);
		imageButton16.setOnClickListener(this);

		Button QuartoButton = activity.findViewById(R.id.quartoButton);
		QuartoButton.setOnClickListener(this);

		QBV = activity.findViewById(R.id.QuartoBoardView);
		QBV.setOnTouchListener(this);
		QBV.setQuartoState(state);

	}
}// class quartoHumanPlayer

