package edu.up.cs301.quarto;
import java.io.Serializable;

/**
 * This class defines what a piece is by their characteristics.
 * It assists the QuartoState with identifying different pieces.
 *
 *
 * @author Olivia Sendelbach
 * @author Magnus Graham
 * @author Aaron Chu
 * @author Becca Biukoto
 * @version October 20, 2024
 */
public class Piece implements Serializable {

    private static final long serialVersionUID = 1126202474;

    //instance variables
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

    // constructor to create a new piece if needed
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
}

