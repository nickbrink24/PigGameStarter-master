package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.GameState;

public class PigGameState extends GameState {

    private int turnID;
    private int player0_score;
    private int player1_score;
    private int runningTotal;
    private int dieValue;
    private String action;

    public PigGameState() {
        this.turnID = 0;
        this.player0_score = 0;
        this.player1_score = 0;
        this.runningTotal = 0;
        this.dieValue = 1;
        action = "";
    }

    public PigGameState(PigGameState PGS) {
        this.turnID = PGS.getTurnID();
        this.player0_score = PGS.getPlayer0_score();
        this.player1_score = PGS.getPlayer1_score();
        this.runningTotal = PGS.getRunningTotal();
        this.dieValue = PGS.getDieValue();
        this.action = PGS.getAction();
    }

    public int getTurnID() {
        return turnID;
    }

    public void setTurnID(int turnID) {
        this.turnID = turnID;
    }

    public int getPlayer0_score() {
        return player0_score;
    }

    public void setPlayer0_score(int player0_score) {
        this.player0_score = player0_score;
    }

    public int getPlayer1_score() {
        return player1_score;
    }

    public void setPlayer1_score(int player1_score) {
        this.player1_score = player1_score;
    }

    public int getRunningTotal() {
        return runningTotal;
    }

    public void setRunningTotal(int runningTotal) {
        this.runningTotal = runningTotal;
    }

    public int getDieValue() {
        return dieValue;
    }

    public void setDieValue(int dieValue) {
        this.dieValue = dieValue;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
