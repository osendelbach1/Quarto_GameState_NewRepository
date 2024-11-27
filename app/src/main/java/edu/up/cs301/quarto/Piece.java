package edu.up.cs301.quarto;
/*
 * @author Olivia Sendelbach
 * @author Magnus Graham
 * @author Aaron Chu
 * @author Becca Biukoto
 * @version October 20, 2024
*/


import java.io.Serializable;

public class Piece implements Serializable {

    private static final long serialVersionUID = 1126202471;

    //instance variables
    private int pieceID;
    private Boolean shortOrTall;
    private Boolean holeOrSolid;
    private Boolean darkOrLight;
    private Boolean squareOrCircle;

    //constructor
    public Piece(Boolean height, Boolean hole, Boolean color, Boolean shape) {
        this.shortOrTall = height;
        this.holeOrSolid = hole;
        this.darkOrLight = color;
        this.squareOrCircle = shape;
    }

    public Piece(Piece p) {
        this.shortOrTall = p.getHeight();
        this.holeOrSolid = p.getHole();
        this.darkOrLight = p.getColor();
        this.squareOrCircle = p.getShape();
    }

    //getters
    public Boolean getHeight() {
        return this.shortOrTall;
    }

    public Boolean getHole() {
        return this.holeOrSolid;
    }

    public Boolean getColor() {
        return this.darkOrLight;
    }

    public Boolean getShape() {
        return this.squareOrCircle;
    }

    //setters
    public void setHeight(Boolean b) {
        this.shortOrTall = b;
    }

    public void setHole(Boolean b) {
        this.holeOrSolid = b;
    }

    public void setColor(Boolean b) {
        this.darkOrLight = b;
    }

    public void setShape(Boolean b) {
        this.squareOrCircle = b;
    }

}

