package control;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import android.graphics.Point;
import model.CheckerMove;
import model.CheckersBoard;
import tree.IMoveController;
import checkers.ICheckersConstants;

public enum MoveController implements IMoveController, ICheckersConstants
{
    INSTANCE;

    @Override
    public Collection<CheckerMove> getPossibleMoves(CheckersBoard board)
    {
        List<CheckerMove> moves = new LinkedList<>();
        int[][] cells = board.getCells();
        
        for (int x = 0; x < cells.length; x++)
        {
            for (int y = 0; y < cells[0].length; y++)
            {
                if (cells[x][y] == BLACK) {
            	  Point from = new Point(x, y);
                  this.tryMove(from, 'l', board, moves);
                  this.tryMove(from, 'r', board, moves);
                  this.tryMove(from, 'd', board, moves);
                  this.tryMove(from, 'u', board, moves);
                  this.tryMove(from, 'a', board, moves);
                  this.tryMove(from, 'b', board, moves);
                  this.tryMove(from, 'c', board, moves);
                  this.tryMove(from, 'e', board, moves);
                }
            }
        }
        
        return moves;
    }
    
    private void tryMove(Point from, char dir, CheckersBoard board, Collection<CheckerMove> moves)
    {
        Point to = board.tryMove(from, dir);
        if(to == null)
        {
            return;
        }
        //System.out.println("tryMove");
        CheckerMove move = new CheckerMove(from, dir, to);
        moves.add(move);
    }


    @Override
    public boolean shouldSkip(int depth)
    {
        return depth > 2;
    }




	@Override
	public boolean shouldStop(CheckersBoard board) {
		 int[][] cells = board.getCells();
	     return cells[0][0] == BLACK && cells[0][1] == BLACK && cells[1][0] == BLACK && cells[1][1] == BLACK;
	}


}
