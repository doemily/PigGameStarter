package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

// dummy comment, to see if commit and push work from srvegdahl account

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {
    PigGameState pigGameState;

    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        pigGameState = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if (playerIdx == pigGameState.getId()) {
            return true;
        }
        return false;
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        if (action instanceof PigHoldAction) {
            if (pigGameState.getId() == 0) {
                pigGameState.setPlayer0Score(pigGameState.getPlayer0Score() + pigGameState.getRunningTotal());
                if (players.length > 1) {
                    pigGameState.setId(1);
                }
            }
            else if (pigGameState.getId() == 1) {
                pigGameState.setPlayer1Score(pigGameState.getPlayer1Score() + pigGameState.getRunningTotal());
                if (players.length > 1) {
                    pigGameState.setId(0);
                }
            }
            pigGameState.setRunningTotal(0);
            return true;
        }
        else if (action instanceof PigRollAction) {
            pigGameState.setDieVal((int) (Math.random() * 6) +1);
            if (pigGameState.getDieVal() != 1) {
                pigGameState.setRunningTotal(pigGameState.getRunningTotal() + pigGameState.getDieVal());
            }
            else {
                pigGameState.setRunningTotal(0);
                if (players.length == 2) {
                    if (pigGameState.getId() == 0) {
                        pigGameState.setId(1);
                    }
                    else if (pigGameState.getId() == 1) {
                        pigGameState.setId(0);
                    }
                }
            }
            return true;
        }
        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        PigGameState temp = new PigGameState(this.pigGameState);
        p.sendInfo(temp);
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
        //TODO  You will implement this method
        if (pigGameState.getPlayer0Score() >= 50) {
            return "Player 0 won: " + pigGameState.getPlayer0Score() + " points";
        }
        else if (pigGameState.getPlayer1Score() >= 50) {
            return "Player 1 won: " + pigGameState.getPlayer1Score() + " points";
        }
        return null;
    }

}// class PigLocalGame
