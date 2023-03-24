package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

import java.util.Random;

// dummy comment, to see if commit and push work from srvegdahl account

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    private PigGameState PGS;

    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        PGS = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        return playerIdx == PGS.getTurnID();
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        if (action instanceof PigRollAction || action instanceof PigHoldAction) {

            if (action instanceof PigHoldAction) {
                if (PGS.getTurnID() == 0) {
                    PGS.setPlayer0_score(PGS.getPlayer0_score() + PGS.getRunningTotal());
                    if(playerNames.length == 2) {
                        PGS.setTurnID(1);
                    }
                } else if (PGS.getTurnID() == 1) {
                    PGS.setPlayer1_score(PGS.getPlayer1_score() + PGS.getRunningTotal());
                    if(playerNames.length == 2) {
                        PGS.setTurnID(0);
                    }
                }

                PGS.setRunningTotal(0);
                return true;
            } else {
                Random rand = new Random();
                PGS.setDieValue(rand.nextInt(6) + 1);

                if (PGS.getDieValue() != 1) {
                    PGS.setRunningTotal(PGS.getRunningTotal() + PGS.getDieValue());
                } else {
                    PGS.setRunningTotal(0);
                    if (PGS.getTurnID() == 0 && playerNames.length == 2) {
                        PGS.setTurnID(1);
                    } else if (PGS.getTurnID() == 1 && playerNames.length == 2){
                        PGS.setTurnID(0);
                    }
                }

                return true;
            }
        } else {
            return false;
        }
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        PigGameState copy = new PigGameState(PGS);
        p.sendInfo(copy);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        if(PGS.getPlayer0_score() >= 50) {
            return "Congrats, " + playerNames[0] + ". You won with a score of" +
                    PGS.getPlayer0_score();
        } else if (PGS.getPlayer1_score() >= 50) {
            return "Congrats, " + playerNames[1] + ". You won with a score of" +
                    PGS.getPlayer1_score();
        }

        return null;
    }

}// class PigLocalGame
