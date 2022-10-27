package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.GameState;

public class PigGameState extends GameState {
    private int id = 0;
    private int player0Score = 0;
    private int player1Score = 0;
    private int runningTotal = 0;
    private int dieVal;

    // constructor
    public PigGameState() {
        id = 0;
        player0Score = 0;
        player1Score = 0;
        runningTotal = 1;
        dieVal = 1;
    }

    // copy constructor
    public PigGameState(PigGameState pig) {
        this.id = pig.id;
        this.player0Score = pig.player0Score;
        this.player1Score = pig.player1Score;
        this.runningTotal = pig.runningTotal;
    }

    public int getId() {
        return this.id;
    }

    public int getPlayer0Score() {
        return this.player0Score;
    }

    public int getPlayer1Score() {
        return this.player1Score;
    }

    public int getRunningTotal() {
        return this.runningTotal;
    }

    public int getDieVal() {
        return this.dieVal;
    }

    public void switchTurn() {
        if (id == 0) {
            id = 1;
        }
        else {
            id = 0;
        }
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setPlayer0Score(int player0Score) {
        this.player0Score = player0Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public void setRunningTotal(int runningTotal) {
        this.runningTotal = runningTotal;
    }

    public void setDieVal(int dieVal) {
        this.dieVal = dieVal;
    }
}
