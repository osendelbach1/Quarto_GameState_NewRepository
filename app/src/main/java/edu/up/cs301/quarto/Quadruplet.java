package edu.up.cs301.quarto;
import java.io.Serializable;
/*

Quadruplet helper class for ComputerPlayer2 created to keep track
of characteristics of rows, columns, and diagonals. Each Quadruplet
instance contains the total of each characteristic per row/col/diagonal.

@Author Magnus Graham
@Author Olivia Sendelbach
@Author Becca Biukoto
 */

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
    private String name;

    // constructor
    public Quadruplet(String name) {
        this.consecDark = 0;
        this.consecLight = 0;
        this.consecShort = 0;
        this.consecTall = 0;
        this.consecHole = 0;
        this.consecSolid = 0;
        this.consecCircle = 0;
        this.consecSquare = 0;
        this.placeable = false;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // gets the total of similar characteristics in a row, column, or diagonal
    //total of each characteristic
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

    public void addConsecDark() {
        this.consecDark++;
    }

    public void addConsecLight() {
        this.consecLight++;
    }

    public void addConsecShort() {
        this.consecShort++;
    }

    public void addConsecTall() {
        this.consecTall++;
    }

    public void addConsecHole() {
        this.consecHole++;
    }

    public void addConsecCircle() {
        this.consecCircle++;
    }

    public void addConsecSquare() {
        this.consecSquare++;
    }



    //The following methods cancel out opposing characteristics so the AI does not try to
    //place where the pattern is already disrupted.
    public void checkHeight() {
        if (this.consecShort > 0 && this.consecTall > 0) {
                this.consecShort = 0;
                this.consecTall = 0;
            }
    }

    public void checkShape() {
        if (this.consecSquare > 0 && this.consecCircle > 0) {
                this.consecSquare = 0;
                this.consecCircle = 0;
        }
    }

    public void checkColor() {
        if (this.consecLight > 0 && this.consecDark > 0) {

                this.consecCircle = 0;
                this.consecSquare = 0;
        }
    }

    public void checkHole() {
        if (this.consecHole > 0 && this.consecSolid > 0) {
                this.consecHole = 0;
                this.consecSolid = 0;
        }

    }


} //Quadruplet class
