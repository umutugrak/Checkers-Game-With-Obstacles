package model;








import java.io.File;

import android.graphics.Point;
import checkers.CheckersTool;
import checkers.ICheckersConstants;

public class CheckersBoard implements ICheckersConstants
{
    private final int[][] cells;
    private char dir;
    private int temp = 0;
    private int temp2 = 0;
    
    public CheckersBoard(int[][] cells)
    {
        this.cells = cells;
    }
    
    public int[][] getCells()
    {
        return cells;
    }
    public boolean iden(CheckersBoard a){
     for(int i=0;i<a.cells.length;i++)
      for(int j=0;j<a.cells[0].length;j++)
    	  if(a.cells[i][j] != cells[i][j])
    		  return false;
     return true;
    }
    public Point tryMove(Point from, char dir)
    {
        this.dir = dir;
        
    	int fromCell = cells[from.x][from.y];
        if(fromCell == EMPTY|| fromCell == BLOCK)
        {
            return null;
        }
        
        Point to = null;
        if (dir == 'l') {
        	temp = from.x -1;
        	if(temp < 0)
        		return null;
            if (cells[temp][from.y] == EMPTY) {   	
            	to = new Point(temp, from.y);
            	
            }
            else if (cells[temp][from.y] == BLOCK)
                return null;
        }   
        if (dir == 'r') {
        	temp = from.x +1;
        	if (temp >= cells.length)
        		return null;
            if (cells[temp][from.y] == EMPTY) { 	
            	to = new Point(temp, from.y);	
            }
            else if (cells[temp][from.y] == BLOCK)
                return null;
        }   
        if (dir == 'u') {
        	 temp = from.y -1;
        	 if(temp < 0)
        	    return null;
        	 if (cells[from.x][temp] == EMPTY) {
            	to = new Point(from.x , temp);      	
        	} 	
            else if (cells[from.x][temp] == BLOCK)
                return null;           	 
        }
        if (dir == 'd') {
        	temp = from.y +1;
        	if(temp >= cells.length)
        	    return null;        	 
            if (cells[from.x][temp] == EMPTY)
            	to = new Point(from.x , temp);
            else if (cells[from.x][temp] == BLOCK)
                return null;
        }
        if (dir == 'a') {
        	temp = from.x -2;
        	temp2 = from.x -1;
        	if(temp < 0)
        		return null;
        	 if (cells[temp2][from.y] == BLACK) { 
        		 if (cells[temp][from.y] == EMPTY) 
            	   to = new Point(temp , from.y);
                 else
                   return null;
            } 
            else
              return null;
        }        
        if (dir == 'b') {
        	temp = from.x +2;
        	temp2 = from.x +1;
        	if (temp >= cells.length)
        		return null;
            if (cells[temp2][from.y] == BLACK) {
              if (cells[temp][from.y] == EMPTY) 
            	  to = new Point(temp , from.y);
              else
                 return null;
            } 
            else
              return null;
        }
        if (dir == 'c') {
        	temp = from.y -2;
        	temp2 = from.y -1;
       	    if(temp < 0)
       	       return null;
            if (cells[from.x][temp2] == BLACK) {
              if (cells[from.x][temp] == EMPTY) 
            	  to = new Point(from.x , temp);
              else
                 return null;
            } 
            else
              return null;
        }
        if (dir == 'e') {
        	temp = from.y +2;
        	temp2 = from.y +1;
        	if(temp >= cells.length)
        	    return null;  	
            if (cells[from.x][temp2] == BLACK) {
              if (cells[from.x][temp] == EMPTY) 
            	  to = new Point(from.x , temp);
              else
                 return null;
            } 
            else
              return null;
        }
        return to;
    }
    
    public Point move(Point from, char dir)
    {
        Point to = this.tryMove(from, dir);
        if(to != null)
        {
            cells[to.x][to.y] = cells[from.x][from.y];
            cells[from.x][from.y] = EMPTY;
        }
        
        return to;
    }
}