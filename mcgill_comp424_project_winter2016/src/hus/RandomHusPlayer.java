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
        MyTools.getSomething();

        // Get the legal moves for the current board state.
        ArrayList<HusMove> moves = board_state.getLegalMoves();
        HusMove finalmove;
        HusBoardState cloned_board_state = (HusBoardState) board_state.clone();
        int CurrentSeedCount=0;
    	HusMove BestMove=moves.get(0);
    	int Depth = 7;
    	MyTools Tool = MyTools.NaiveAlphaBeta(Depth,cloned_board_state,Integer.MIN_VALUE,Integer.MAX_VALUE);
    	BestMove = Tool.Move;
    	
    	return BestMove;
    }


    




}
