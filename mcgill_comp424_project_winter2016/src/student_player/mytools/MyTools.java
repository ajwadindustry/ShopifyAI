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
    
    public static int heruistic3_Capturable(HusBoardState Board,int Player_id,int opponent_id){
    	
    int Capturable =0;
    int [][] pits = Board.getPits();
    int Player=Player_id;
    int [] opponent_pits= pits[opponent_id];
    int [] turn_pits= pits[Player];
    
   for(int i = 16 ; i<28 ; i++  ){
    	if((i+turn_pits[i])<31 || (31-(i+turn_pits[i]-16))<31 || (31-(i+turn_pits[i]-16)>0)){
    	if(turn_pits[i+turn_pits[i]]>=1 && opponent_pits[31-(i+turn_pits[i]-16)]>=1 ){
    		Capturable++;
    		
    	}
    	}
    }
    
    
    return Capturable;
    	
    	
    	
    	
    }
    
   

    public static MyTools MiniMax(int depth, HusBoardState Board,int Player_id, int Opponent_id){
    	HusMove FinalMove=Board.getLegalMoves().get(0) ;
    	HusMove WorstMove=Board.getLegalMoves().get(0);
    	
    	int MethodDepth= depth;
    	int NewScore=0;
    	int Score=0;
    	HusBoardState cloned_board_state = (HusBoardState) Board.clone();
        int [][] pits= cloned_board_state.getPits();
        int [] my_pits = pits[Player_id];
        MyTools BestMove=new MyTools(FinalMove,0);
        MyTools MiniMove=new MyTools(WorstMove,0);
        
    	
    	
        if(MethodDepth==0 || cloned_board_state.gameOver()){
        	BestMove.CalculatedScore=(3*heruistic1_NumberSeed(cloned_board_state,Player_id)+2*heruistic2_Empty(cloned_board_state,Player_id)+4*heruistic3_Capturable(cloned_board_state,Player_id,Opponent_id))-(2*heruistic1_NumberSeed(cloned_board_state,Opponent_id)+heruistic2_Empty(cloned_board_state,Opponent_id)+heruistic3_Capturable(cloned_board_state,Opponent_id,Player_id));
      // Remember to change this This doesn't work 
        }
    	
        if(Board.getTurnPlayer() == 260520591){
        	ArrayList<HusMove> moves = Board.getLegalMoves();
            int BestScore = -999;
            for (HusMove move : moves){
            	cloned_board_state.move(move);
            	NewScore=MiniMax(depth-1,cloned_board_state,Player_id,Opponent_id).CalculatedScore;
            	if(NewScore>BestScore){
            		BestScore=NewScore;
            		BestMove.CalculatedScore=NewScore;
            		BestMove.Move=move;
            	}
            }
        	
          //  return BestMove;
        }
    		
        else{
          ArrayList<HusMove> moves = Board.getLegalMoves();
          int WorstScore = 999;
          for(HusMove move : moves){
        	  cloned_board_state.move(move);
        	  NewScore=MiniMax(depth-1,cloned_board_state,Player_id,Opponent_id).CalculatedScore;
        	  if(NewScore<WorstScore){
        		  WorstScore=NewScore;
        		  MiniMove.CalculatedScore=NewScore;
        		  MiniMove.Move=move;
        	  }
        	  
          }
        
        return MiniMove;
        
        }    	
    return BestMove;
    }
    
  public static MyTools NaiveAlphaBeta(int depth,HusBoardState Board,int alpha,int beta){
  	
	 
	  
	  
	HusMove FinalMove=Board.getLegalMoves().get(0) ;
  	HusMove WorstMove=Board.getLegalMoves().get(0);
  	int player_id = 260520591;
  	int MethodDepth= depth;
  	int Alpha=alpha;
  	int Beta=beta;
  	int Score=0;
  	HusBoardState cloned_board_state = (HusBoardState) Board.clone();
  	MyTools PlaceHolderMove=new MyTools(FinalMove,0);
    MyTools AlphaMove=new MyTools(FinalMove,0);
    MyTools BetaMove=new MyTools(WorstMove,0);
    
    
    
	  if(depth==0 || Board.gameOver()){
      	PlaceHolderMove.CalculatedScore=heruistic1_NumberSeed(Board,Board.getTurnPlayer());
      }
	  else{
		  ArrayList<HusMove> moves = Board.getLegalMoves();
		  for(HusMove move : moves){
			  Board.move(move);
			  if(player_id == Board.getTurnPlayer()){
				  Score=NaiveAlphaBeta(depth-1,Board,Alpha,Beta).CalculatedScore;
				  if(Score>Alpha){
	        		  Alpha=Score;
	        		  PlaceHolderMove.Move=move;
	        	  }	
				  
			    }
			  else{
				  Score=NaiveAlphaBeta(depth-1,Board,Alpha,Beta).CalculatedScore;
				  if(Score< Beta){
					  Beta=Score;
					  PlaceHolderMove.Move=move;	  
				  }
			  }
			  
			  if(Alpha >= Beta){
				  break;
			  }
			  
		  }
			  
		  return PlaceHolderMove;
		  
			  
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
	  if(Alpha >= Beta){
		  
		  
	  }
	  
	  
	  return PlaceHolderMove;
	  
	  
	  
  }    
    
    

}
