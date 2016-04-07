package student_player.mytools;
import java.util.ArrayList;

import hus.*;
import student_player.StudentPlayer;  

/*
Can we create new classes or Not??
*/

public class MyTools {

	
	public HusMove Move;
	public int CalculatedScore;
	
	public MyTools(HusMove Move, int CalculatedScore){
		Move=this.Move;
		CalculatedScore=this.CalculatedScore;
	}
	
	
    public static double getSomething(){
        return Math.random();
    }
    
    
    	
    public static int heruistic1_NumberSeed(HusBoardState Board,int Player_id){
    	int Score =0;
    	int [][] pits= Board.getPits();
    	int TurnPlayer_id=Player_id;
    	int [] turn_pits= pits[TurnPlayer_id];
    	
    	for(int i : turn_pits){
    		
    		Score+=i;
    		
    	}
    	
    	
    	return Score;
    }
    
    public static int heruistic2_Empty(HusBoardState Board,int Player_id){
    	
    int Empty =0;
    int [][] pits = Board.getPits();
    int Player=Player_id;
    int [] turn_pits= pits[Player];
    
    for(int i : turn_pits){
    	
    	if(i == 0){
    		
    		Empty++;
    	}
    	
    }
    
    
    return Empty;
    	
    }
    
    public static double SecondHeruistic(HusBoardState State,int player_id, int opponent_id){
    	
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
    
    
//Make Best Evaluate later 
   

    private double NaiveMiniMax(HusBoardState State, int MaxLevel, int CurrentLevel, boolean CurrentPlayer,int player_id,int opponent_id){
    	//Naive MiniMax gave an error before so I had to fix that, there are no errors when you run it now
    	// Previously only StudentPlayer had the correct version of minimax
    	if(CurrentLevel == MaxLevel){
    		
    		return heruistic1_NumberSeed(State,player_id); // chnage it to new 
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
        	score[i] = NaiveMiniMax(Clone,MaxLevel,CurrentLevel+1, !CurrentPlayer,player_id,opponent_id);
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
    

    
    
    
    
  public static int NaiveAlphaBeta(HusBoardState Board, int Player_id, int depth, int currentdepth, int alpha, int beta, boolean CurrentPlayer ){
  	
	 
     if(currentdepth == depth){
	    return heruistic1_NumberSeed(Board,Player_id);
     }
     
     if(Board.gameOver()){
    	 if(Board.getWinner()==Player_id){
    		 return Integer.MAX_VALUE;
    	 }
    	 else{
    		 return Integer.MIN_VALUE;
    	 }
    	 
     }
	  
     ArrayList<HusMove> moves = Board.getLegalMoves();
 	 int[] Scores = new int[moves.size()];
     
     for(int i = 0;i<Scores.length ; i++ ){
    	 HusBoardState Clone = (HusBoardState) Board.clone();
    	 Clone.move(moves.get(i));
    	 Scores[i]= NaiveAlphaBeta(Clone,Player_id,depth,currentdepth+1,alpha,beta,!CurrentPlayer);
    	 if(Scores[i]>alpha){
    		 alpha=Scores[i];
    	 }
    	 if(Scores[i]<beta){
    		 beta=Scores[i];
    	 }
    	 if(alpha >= beta){
    		 break;
    	 }	 
     }
     
     if(CurrentPlayer){
    	 return alpha;
     }
     else{
    	 return beta;
     }
     

  
		  
			  
		  
  

		  
		  
		  
		  
		  
		  
	  
	 /* 
	  if(Board.getTurnPlayer()==player_id){
          ArrayList<HusMove> moves = Board.getLegalMoves();
          for(HusMove move : moves){
        	  cloned_board_state.move(move);
        	  Score=AlphaBeta(depth-1,Board,Alpha,Beta).CalculatedScore;
        	  if(Score>Alpha){
        		  Alpha=Score;
        		  PlaceHolderMove.Move=move;
        	  }	  
          }
         		  
	  }
	  
	  else{
          ArrayList<HusMove> moves = Board.getLegalMoves();
          for(HusMove move : moves){
        	  cloned_board_state.move(move);
        	  Score=AlphaBeta(depth-1,Board,Alpha,Beta).CalculatedScore;
        	  if(Score < Beta){
        		  Beta=Score;
        		  PlaceHolderMove.Move=move;
        	  }
        	  
          }
		  
		  
	  }
	  */
	//  if(Alpha >= Beta){
		  
		  
	 // }
	  
	  
	  //return PlaceHolderMove;
	  
	  
	  
  }    
    
    

}
