package edu.up.cs301.quarto;

public class QuartoBoardModel {
    public boolean isSquareOneTouched (float x, float y) {
        if (x >= 100 && x <= 300 && y >= 50 && y <= 250) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSquareTwoTouched (float x, float y) {
        if (x >= 300 && x <= 500 && y >= 50 && y <= 250) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isSquareThreeTouched (float x, float y) {
        if (x >= 500 && x <= 700 && y >= 50 && y <= 250) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSquareFourTouched (float x, float y) {
        if (x >= 700 && x <= 900 && y >= 50 && y <= 250) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSquareFiveTouched (float x, float y) {
        if (x >= 100 && x <= 300 && y >= 250 && y <= 450) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isSquareSixTouched (float x, float y) {
        if (x >= 300 && x <= 500 && y >= 250 && y <= 450) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isSquareSevenTouched (float x, float y) {
        if (x >= 500 && x <= 700 && y >= 250 && y <= 450) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isSquareEightTouched (float x, float y) {
        if (x >= 700 && x <= 900 && y >= 250 && y <= 450) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isSquareNineTouched (float x, float y) {
        if (x >= 100 && x <= 300 && y >= 450 && y <= 650) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isSquareTenTouched (float x, float y) {
        if (x >= 300 && x <= 500 && y >= 450 && y <= 650) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isSquareElevenTouched (float x, float y) {
        if (x >= 500 && x <= 700 && y >= 450 && y <= 650) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isSquareTwelveTouched (float x, float y) {
        if (x >= 700 && x <= 900 && y >= 450 && y <= 650) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isSquareThirteenTouched (float x, float y) {
        if (x >= 100 && x <= 300 && y >= 650 && y <= 850) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isSquareFourteenTouched (float x, float y) {
        if (x >= 300 && x <= 500 && y >= 650 && y <= 850) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isSquareFifteenTouched (float x, float y) {
        if (x >= 500 && x <= 700 && y >= 650 && y <= 850) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isSquareSixteenTouched (float x, float y) {
        if (x >= 700 && x <= 900 && y >= 650 && y <= 850) {
            return true;
        } else {
            return false;
        }
    }
}
