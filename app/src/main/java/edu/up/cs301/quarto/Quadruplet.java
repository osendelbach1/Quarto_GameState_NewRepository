package edu.up.cs301.quarto;

public class Quadruplet {
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
