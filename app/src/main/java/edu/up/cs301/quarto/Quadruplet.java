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

    public int getTotal() {
        return consecDark + consecLight + consecShort + consecTall + consecHole + consecSolid + consecCircle + consecSquare;
    }

    public boolean getConsecutive() {
        return consecutive;
    }

    public void setConsecutive(boolean consecutive) {
        this.consecutive = consecutive;
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

    public void setConsecDark(int consecDark) {
        this.consecDark = consecDark;
    }

    // Getter and Setter for consecLight
    public int getConsecLight() {
        return consecLight;
    }

    public void setConsecLight(int consecLight) {
        this.consecLight = consecLight;
    }

    // Getter and Setter for consecShort
    public int getConsecShort() {
        return consecShort;
    }

    public void setConsecShort(int consecShort) {
        this.consecShort = consecShort;
    }

    // Getter and Setter for consecTall
    public int getConsecTall() {
        return consecTall;
    }

    public void setConsecTall(int consecTall) {
        this.consecTall = consecTall;
    }

    // Getter and Setter for consecHole
    public int getConsecHole() {
        return consecHole;
    }

    public void setConsecHole(int consecHole) {
        this.consecHole = consecHole;
    }

    // Getter and Setter for consecSolid
    public int getConsecSolid() {
        return consecSolid;
    }

    public void setConsecSolid(int consecSolid) {
        this.consecSolid = consecSolid;
    }

    // Getter and Setter for consecCircle
    public int getConsecCircle() {
        return consecCircle;
    }

    public void setConsecCircle(int consecCircle) {
        this.consecCircle = consecCircle;
    }

    // Getter and Setter for consecSquare
    public int getConsecSquare() {
        return consecSquare;
    }

    public void setConsecSquare(int consecSquare) {
        this.consecSquare = consecSquare;
    }

   
    
    
    public boolean checkIfShort() {
        if (this.consecShort > 1) {
            if (this.consecTall > 1) {
                this.consecShort = -1;
                this.consecTall = -1;
            }
        }
        return true;
    } //checkIfShort()

    public boolean checkIfTall() {
        if (this.consecTall > 1) {
            if (this.consecShort > 1) {
                this.consecTall = -1;
                this.consecShort = -1;
            }
        }
        return true;
    } //checkIfTall()

    public boolean checkIfSquare() {
        if (this.consecSquare > 1) {
            if (this.consecCircle > 1) {
                this.consecSquare = -1;
                this.consecCircle = -1;
            }
        }
        return true;
    } //checkIfSquare()

    public boolean checkIfCircle() {
        if (this.consecCircle > 1) {
            if (this.consecSquare > 1) {
                this.consecCircle = -1;
                this.consecSquare = -1;
            }
        }
        return true;
    } //checkIfCircle()

    public boolean checkIfDark() {
        if (this.consecDark > 1) {
            if (this.consecLight > 1) {
                this.consecDark = -1;
                this.consecLight = -1;
            }
        }
        return true;
    } //checkIfDark()


    public boolean checkIfLight() {
        if (this.consecLight > 1) {
            if (this.consecDark > 1) {
                this.consecLight = -1;
                this.consecDark = -1;
            }
        }
        return true;
    } //checkIfLight()


    public boolean checkIfHole() {
        if (this.consecHole > 1) {
            if (this.consecSolid > 1) {
                this.consecHole = -1;
                this.consecSolid = -1;
            }
        }
        return true;
    } //checkIfHole()

    public boolean checkIfSolid() {
        if (this.consecSolid > 1) {
            if (this.consecHole > 1) {
                this.consecSolid = -1;
                this.consecHole = -1;
            }
        }
        return true;
    } //checkIfSolid()
} //Quadruplet class
