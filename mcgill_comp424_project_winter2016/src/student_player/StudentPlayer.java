package student_player;

import student_player.mytools.MyTools; 
import hus.HusBoardState;
import hus.HusPlayer;
import hus.HusMove;

import java.util.ArrayList;

import student_player.mytools.MyTools;
import java.math.*;
/** A Hus player submitted by a student. */
public class StudentPlayer extends HusPlayer {

    /** You must modify this constructor to return your student number.
     * This is important, because this is what the code that runs the
     * competition uses to associate you with your agent.
     * The constructor should do nothing else. */
    public StudentPlayer() { super("260520591"); }
    
    /** This is the primary method that you need to implement.
     * The ``board_state`` object contains the current state of the game,
     * which your agent can use to make decisions. See the class hus.RandomHusPlayer
     * for another example agent. */
    public HusMove chooseMove(HusBoardState board_state)
    {
    	
    	ArrayList<HusMove> moves = board_state.getLegalMoves();
    	double[] scores = new double[moves.size()];
    	HusBoardState Clone = (HusBoardState) board_state.clone();
    	int MaxIndex = 0;
    	Clone.move(moves.get(MaxIndex));
    	double MaxScore = 0;//MiniMax(Clone,5,1,false);
    	for(int i = 1;i <scores.length ; i++){
    		Clone = (HusBoardState)board_state.clone();
    		Clone.move(moves.get(i));
    		scores[i]= MiniMax(Clone,5,1,false);
    		if(scores[i]>=MaxScore){
    			MaxScore = scores[i];
    			MaxIndex = i;
    		}
    	}
    	
    	return moves.get(MaxIndex);
    	
    	
    	
    	
    	
        // Get the contents of the pits so we can use it to make decisions.
       /* int[][] pits = board_state.getPits();

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
    	MyTools Move = MyTools.MiniMax(Depth,cloned_board_state,player_id,opponent_id);
    	BestMove = Move.Move;
    	
    	
    	//BestMove=MiniMax();
    	
       
      
    	
    	
    	/*  for(HusMove move: moves){
          
        
        	cloned_board_state.move(move);
        	pits= cloned_board_state.getPits();
        	my_pits=pits[player_id];
        	int NewSeedCount=0;
            for(int i : my_pits){
            	NewSeedCount += i;
            }
            if(NewSeedCount>CurrentSeedCount){
            CurrentSeedCount=NewSeedCount;
            BestMove=move;
            }
        
        
        } 
        
        
        // We can see the effects of a move like this...
        //HusBoardState cloned_board_state = (HusBoardState) board_state.clone();
        //cloned_board_state.move(move);

        // But since this is a placeholder algorithm, we won't act on that information.
        return BestMove; 
        
        */
    }
    
   
    
    
    private double MiniMax(HusBoardState State, int MaxLevel, int CurrentLevel, boolean CurrentPlayer){
    	
    	if(CurrentLevel == MaxLevel){
    		
    		return BestHeruistic(State); // chnage it to new 
    	}
    	
    	if(State.gameOver()){
    		if(State.getWinner()==player_id){
    			return Integer.MAX_VALUE;
    		}
    		else{
    			return Integer.MIN_VALUE;
    			
    		}
    	}
    	ArrayList<HusMove> moves = State.getLegalMoves();
    	
    	double[] score = new double[moves.size()];
        double Min = Double.MAX_VALUE + 1d;
        double Maxi = Double.MIN_VALUE - 1d;
        for(int i =0; i<score.length;i++){
        	HusBoardState Clone = (HusBoardState) State.clone();
        	Clone.move(moves.get(i));
        	score[i] = MiniMax(Clone,MaxLevel,CurrentLevel+1, !CurrentPlayer);
            if(score[i]<Min){
            	
            	Min = score[i];
            	
            }
            else if(score[i]> Maxi){	
            
            	Maxi = score[i];
            	
            }    
        }
        if(CurrentPlayer){
        	
        	return Maxi;  	
        
        }
        else{
        
        	return Min;
        	
        }
    }
    

    	
    
    
    private double SecondHeruistic(HusBoardState State){
    	
    	double finalscore = 0; 
    	double myseeds = 0;
    	double myempty =0;
    	double opponentempty = 0;
    	
    	
    	int[][] pits = State.getPits();
    	int [] opponent_pits = pits[opponent_id];
    	int [] player_pits = pits[player_id];
    	
    	for(double pit : player_pits){ 
    		
    		myseeds+=pit;
    	}
    	for(int i = 0; i<opponent_pits.length ; i++){
    		if(opponent_pits[i]==0){
    			opponentempty++;
    		}
    	}
    	for(int i = 0; i<player_pits.length ; i++){
    		if(player_pits[i]==0){
    			myempty++;
    		}
    	}  	
    	
    	return (0.45*myseeds+0.35*opponentempty-0.25*myempty);
    	
    }
    
    
 private int NaiveSeedHeruistic(HusBoardState CurrentState){
    	
    	
    	int[][] pits = CurrentState.getPits();
        int[] player_pits = pits[player_id];
        int[] opponent_pits = pits[opponent_id];    		
    	int PlayerTotal =0;
    	
    	for(int i=0; i <player_pits.length; i++ ){
    		PlayerTotal+=player_pits[i];
    		
    	}
    	
    	return PlayerTotal; 	
    	   	
    }
    
    
    private double BestHeruistic(HusBoardState State){
    	
    	int Vulnerable = 0;
    	int TotalVulnerable = 0;
    	int MaxVulnerable = 0;
    	
    	int PlayerVulnerable = 0;
    	int PlayerTotalVulnerable = 0;
    	int PlayerMaxVulnerable = 0;
    	
    	int[][] pits = State.getPits();
    	int [] opponent_pits = pits[opponent_id];
    	int [] player_pits = pits[player_id];
    	
    	for(int i = 16; i<=29; i++){
    		
    		if(i+player_pits[i]<=29 && player_pits[i+player_pits[i]]>=1 ){
    		   Vulnerable++;
    		   int Grabable =opponent_pits[32-(i+player_pits[i]-16)];
    		   TotalVulnerable += Grabable;
    		   if(Grabable > MaxVulnerable){
    			  MaxVulnerable = Grabable;
    		   }
    		}
    		
    		if(i+opponent_pits[i]<=29 && opponent_pits[i+opponent_pits[i]]>=1 && (32-(i+opponent_pits[i-16]))>-1){
    			PlayerVulnerable++;
    			
    			int Grabable = player_pits[32-(i+opponent_pits[i-16])];// change to 32
    			PlayerTotalVulnerable += Grabable;
    			if(Grabable > PlayerMaxVulnerable){
    				PlayerMaxVulnerable = Grabable;
    			}

    		}
    		
    	
    	}
		
    	double FinalScore =((0.45*NaiveSeedHeruistic(State))+ (0.25*((float)TotalVulnerable/Vulnerable))+(0.1*MaxVulnerable))	
        		-(0.1*((float)PlayerTotalVulnerable/PlayerVulnerable)-(0.1*PlayerMaxVulnerable));	
    	
    	return FinalScore;
    	
    	
    }
    
    
    
    
    
    
    
    
    
    
    
}
