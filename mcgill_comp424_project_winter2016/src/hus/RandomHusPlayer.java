package hus;

import hus.HusBoardState;
import hus.HusPlayer;
import student_player.mytools.MyTools;
import hus.HusMove;

import java.util.ArrayList;
import java.util.Random;

/** A random Hus player. */
public class RandomHusPlayer extends HusPlayer {
    Random rand = new Random();

    public RandomHusPlayer() { super("RandomHusPlayer"); }

    /** Choose moves randomly. */
    public HusMove chooseMove(HusBoardState board_state)
    {
             // Get the contents of the pits so we can use it to make decisions.
        int[][] pits = board_state.getPits();

        // Use ``player_id`` and ``opponent_id`` to get my pits and opponent pits.
        int[] my_pits = pits[player_id];
        int[] op_pits = pits[opponent_id];
      
        // Use code stored in ``mytools`` package.
        

        // Get the legal moves for the current board state.
    	ArrayList<HusMove> moves = board_state.getLegalMoves();
    	int[] scores = new int[moves.size()];
    	HusBoardState Clone = (HusBoardState) board_state.clone();
    	int MaxIndex = 0;
    	Clone.move(moves.get(MaxIndex));
    	int MaxScore = 0;
    	for(int i = 1;i <scores.length ; i++){
    		Clone = (HusBoardState)board_state.clone();
    		Clone.move(moves.get(i));
    		scores[i]= MyTools.NaiveAlphaBeta(Clone,player_id,5,1,Integer.MIN_VALUE,Integer.MAX_VALUE,false);
    		if(scores[i]>=MaxScore){
    			MaxScore = scores[i];
    			MaxIndex = i;
    		}
    	}
    	
    	return moves.get(MaxIndex);
    }


    




}
