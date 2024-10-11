package edu.up.cs301.quarto;

import edu.up.cs301.GameFramework.infoMessage.GameState;


/**
 * This contains the state for the quarto game. The state consist of simply
 * the value of the quarto.
 * 
 * @author Steven R. Vegdahl
 * @version July 2013
 */
public class QuartoState extends GameState {
	
	// to satisfy Serializable interface
	private static final long serialVersionUID = 7737393762469851826L;
	
	// the value of the quarto
	private int quarto;
	
	/**
	 * constructor, initializing the quarto value from the parameter
	 * 
	 * @param quartoVal
	 * 		the value to which the quarto's value should be initialized
	 */
	public QuartoState(int quartoVal) {
		quarto = quartoVal;
	}
	
	/**
	 * copy constructor; makes a copy of the original object
	 * 
	 * @param orig
	 * 		the object from which the copy should be made
	 */
	public QuartoState(QuartoState orig) {
		// set the quarto to that of the original
		this.quarto = orig.quarto;
	}

	/**
	 * getter method for the quarto
	 * 
	 * @return
	 * 		the value of the quarto
	 */
	public int getquarto() {
		return quarto;
	}
	
	/**
	 * setter method for the quarto
	 * 
	 * @param quarto
	 * 		the value to which the quarto should be set
	 */
	public void setquarto(int quarto) {
		this.quarto = quarto;
	}
}
