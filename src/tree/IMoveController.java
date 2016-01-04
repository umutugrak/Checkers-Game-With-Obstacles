package tree;

import java.util.Collection;
import java.util.LinkedList;

import model.CheckerMove;
import model.CheckersBoard;

public interface IMoveController
{
    
    boolean shouldSkip(int depth);
    
    boolean shouldStop(CheckersBoard board);

	Collection<CheckerMove> getPossibleMoves(CheckersBoard board);


}
