package edu.up.cs301.quarto;

import java.io.Serializable;

/**
 * Assists with Smart AI functionality in placing the piece
 * in the most optimal grid on the board. Also takes into
 * account how many pieces with the same characteristic are in a row,
 * column, and/or diagonal.
 *
 * @author Magnus Graham
 * @author Olivia Sendelbach
 * @author Becca Biukoto
 * @version 11/22/2024
 */

public class Quadruplet implements Serializable {
    // instance variables keeping count of consecutive characteristics
    private int consecDark;
    private int consecLight;
    private int consecShort;
    private int consecTall;
    private int consecHole;
    private int consecSolid;
    private int consecCircle;
    private int consecSquare;
    private boolean placeable;
    private boolean consecutive;

    // constructor
    public Quadruplet() {
        this.consecDark = 0;
        this.consecLight = 0;
        this.consecShort = 0;
        this.consecTall = 0;
        this.consecHole = 0;
        this.consecSolid = 0;
        this.consecCircle = 0;
        this.consecSquare = 0;
        this.placeable = false;
    }

    // gets the total of similar characteristics in a row, column, or diagonal
    public int getTotal() {
        checkHeight();
        checkColor();
        checkShape();
        checkHole();

        return consecDark + consecLight + consecShort + consecTall + consecHole + consecSolid + consecCircle + consecSquare;
    }

    public boolean getPlaceable() {
        return placeable;
    }

    public void setPlaceable(boolean placeable) {
        this.placeable = placeable;
    }

    // Getter and Setter for consecDark
    public int getConsecDark() {
        return consecDark;
    }

    public void addConsecDark() {
        this.consecDark++;
    }

    // Getter and Setter for consecLight
    public int gettConsecLight() {
        return consecLight;
    }

    public void addConsecLight() {
        this.consecLight++;
    }

    // Getter and Setter for consecShort
    public int getConsecShort() {
        return consecShort;
    }

    public void addConsecShort() {
        this.consecShort++;
    }

    // Getter and Setter for consecTall
    public int getConsecTall() {
        return consecTall;
    }

    public void addConsecTall() {
        this.consecTall++;
    }

    // Getter and Setter for consecHole
    public int getConsecHole() {
        return consecHole;
    }

    public void addConsecHole() {
        this.consecHole++;
    }

    // Getter and Setter for consecSolid
    public int getConsecSolid() {
        return consecSolid;
    }

    public void addConsecSolid() {
        this.consecSolid++;
    }

    // Getter and Setter for consecCircle
    public int getConsecCircle() {
        return consecCircle;
    }

    public void addConsecCircle() {
        this.consecCircle++;
    }

    // Getter and Setter for consecSquare
    public int getConsecSquare() {
        return consecSquare;
    }

    public void addConsecSquare() {
        this.consecSquare++;
    }

    public void checkHeight() {
        if (this.consecShort > 0 && this.consecTall > 0) {
                this.consecShort = 0;
                this.consecTall = 0;
            }
    }//checkIfShort()

    public void checkShape() {
        if (this.consecSquare > 0 && this.consecCircle > 0) {
                this.consecSquare = 0;
                this.consecCircle = 0;
        }
    } //checkIfSquare()

    public void checkColor() {
        if (this.consecLight > 0 && this.consecDark > 0) {

                this.consecCircle = 0;
                this.consecSquare = 0;
        }
    } //checkIfCircle()

    public void checkHole() {
        if (this.consecHole > 0 && this.consecSolid > 0) {
                this.consecHole = 0;
                this.consecSolid = 0;
        }

    } //checkIfDark()


} //Quadruplet class
