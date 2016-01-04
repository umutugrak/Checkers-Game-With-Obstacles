package control;


import java.util.Arrays;

import checkers.CheckersTool;
import checkers.ICheckersConstants;

public class EvaluationFunction implements ICheckersConstants{
   
	private int[][] cells;
	private int[][] subcells;
	private int totalvalue = 0;
	private int temp = 0;
	private int counter = 0;
	private int value = 0;
	private int totalcounter = 0;
	private boolean blocked = false;
	public EvaluationFunction(int[][] cells) {
		this.cells = cells;
		subcells = new int[cells.length][cells[0].length];
	}

	public void changeCells(int[][] cells) {
		this.cells = cells;		
		
        for (int i = 0; i < cells.length;i++) {
            subcells[i] = Arrays.copyOf(cells[i], cells[i].length);
        }
		totalvalue = 0;
		counter = 0;
		value = 0;
	}
	
	public int calculateTotal() {
		totalvalue = 0;
		int i1 = -1; int i2 = -1; int i3 = -1; int i4 = -1; 
		int j1 = -1; int j2 = -1; int j3 = -1; int j4 = -1; 
		for (int i = 0; i < cells.length; i++) 
			for (int j = 0; j < cells[0].length; j++)
		      if (cells[i][j] == BLACK) {
		    	 if ( i1 == -1 ) { i1 = i; j1 = j; }
		    	 else if ( i2 == -1 ) { i2 = i; j2 = j; }
		    	 else if ( i3 == -1 ) { i3 = i; j3 = j; }
		    	 else if ( i4 == -1 ) { i4 = i; j4 = j; }
		    	totalvalue = totalvalue + boardvalue(i, j);
		        for (int k = 0; k < cells.length;k++) {
		            subcells[k] = Arrays.copyOf(cells[k], cells[k].length);
		        }
		      }
		
		int dif = Math.abs( i1-i2 ) + Math.abs( i1-i3 ) + Math.abs( i1-i4 ) + Math.abs( i2-i3 ) + Math.abs( i2-i4 )
				+ Math.abs( i3-i4 ) + Math.abs( j1-j2 ) + Math.abs( j1-j2 ) + Math.abs( j1-j4 ) + Math.abs( j2-j3 )
				+ Math.abs( j2-j4 ) + Math.abs( j3-j4 );
		totalvalue += dif;
		return totalvalue;
	}
	
	public int boardvalue(int posX, int posY) {
		if ( (posX == 0) && (posY == 0) ) return 0;
		if ( (posX == 0) && (posY == 1) ) return 1;
		if ( (posX == 1) && (posY == 0) ) return 1;
		if ( (posX == 1) && (posY == 1) ) return 2;
		value = -1;
		int temporary;
		/*		if (posX >= 1) 
  		  if (subcells[posX][posY] == EMPTY)
  		         subcells[posX][posY] = 1;
  		  if (posX < subcells.length-1)
  			  if (subcells[posX+1][posY] == EMPTY)
  		         subcells[posX+1][posY] = 1;
  		  if (posY >= 1) 
  			  if (subcells[posX][posY-1] == EMPTY)
  		         subcells[posX][posY-1] = 1;
  		  if (posY+1 < subcells[0].length) 
  			  if (subcells[posX][posY+1] == EMPTY)
  		         subcells[posX][posY+1] = 1;*/
  		subcells[posX][posY] = 0;
		while (subcells[0][0] == EMPTY) {
			
		   value++;
		   
		   if ( value > EMPTY ) System.out.println( "YYYYYYYYYYYYYYYYYYYYYYYYYYYYYY" );
		   
	       for (int i = 0; i < subcells.length; i++) {
	         for (int j = 0; j < subcells[0].length; j++) {
	    	  if(subcells[i][j] == value) {		  
	    		  temporary = value + 1;
	    		  
	    		  // check up
	    		  if (i >= 1) 
		    		  if (subcells[i-1][j] == EMPTY) {
		    		         subcells[i-1][j] = temporary;
		    		         counter++;
		    		  }
	    		  
	    		  // check up - jump
	    		  if (i >= 2)
		    		  if (subcells[i-1][j] == BLACK) {
		    			  if (subcells[i-2][j] == EMPTY) {
		    				  subcells[i-2][j] = temporary;
		    				  counter++;
		    			  }	    		         
	    		  }
	    		  
	    		// check down
	    		  if (i < subcells.length - 1) 		  
		    		  if (subcells[i+1][j] == EMPTY) {
		    		         subcells[i+1][j] = temporary;
		    		         counter++;
		    		  }
	    		  
	    		// check down - jump
	    		  if (i < subcells.length - 2)
		    		  if (subcells[i+1][j] == BLACK) {
		    			  if (subcells[i+2][j] == EMPTY) {
		    				  subcells[i+2][j] = temporary;
		    				  counter++;
		    			  }	
			    	  }
	    		  
	    		// check left
	    		  if (j >= 1) 
		    		  if (subcells[i][j-1] == EMPTY) {
		    		         subcells[i][j-1] = temporary;
		    		         counter++;
		    		  }
	    		  
	    		// check left - jump
	    		  if (j >= 2) 
	    		  if (subcells[i][j-1] == BLACK) {
	    			  if (subcells[i][j-2] == EMPTY) {
	    				  subcells[i][j-2] = temporary;
	    				  counter++;
	    			  }	
		    	  }
	    		  
	    		  // check right
	    		  if (j < subcells[0].length-1) 
		    		  if (subcells[i][j+1] == EMPTY) {
		    		         subcells[i][j+1] = temporary;
		    		         counter++;
		    		  }
	    		  
	    		// check right - jump
	    		  if (j < subcells[0].length - 2)
		    		  if (subcells[i][j+1] == BLACK) {
		    			  if (subcells[i][j+2] == EMPTY) {
		    				  subcells[i][j+2] = temporary;
		    				  counter++;
		    			  }	
		    	      }
	    	  }
	    	     
	       } //inner for
		}//outer for

       if (counter == 0) {
    	   totalcounter++;
    	   if (totalcounter == 4)
    		   blocked = true;
    	   System.out.println( "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" );    	   
    	   return value+5;
       }
       counter = 0;
	  }//while
		
	  return subcells[0][0];
	 }
	 public boolean ifBlocked() {
		 return this.blocked;
	 }


	}