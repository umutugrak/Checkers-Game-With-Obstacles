package tree;


import java.util.LinkedList;

import java.util.List;

import checkers.ICheckersConstants;
import model.CheckerMove;
import model.CheckersBoard;

public class MoveTreeNode implements ICheckersConstants
{
    private final CheckersBoard board;
    private final CheckerMove lastMove;
    private final MoveTreeNode parent;
    private int level;
    private int NodeId;
    private int totalvalue = 0;
    private MoveTreeNode child;
    public MoveTreeNode(MoveTreeNode parent, CheckersBoard board, CheckerMove lastMove)
    {
        this.parent = parent;
        this.board = board;
        this.lastMove = lastMove;
        if (parent != null)
          this.level = parent.level + 1;
    }
    public void setChild(MoveTreeNode Child)
    {
    	child = Child;
    }
    public MoveTreeNode getChild()
    {
    	return child;
    }
    public void printBoard(){
    	int[][] cells = board.getCells();
        String string = new String();
        for(int y = 0; y < cells[0].length;y++)
        {
            for (int x = 0; x < cells.length; x++)
            {
                switch(cells[x][y])
                {
                case EMPTY: string += '_'; break;
                case BLOCK: string += 'X'; break;
                case BLACK: string += 'B'; break;
                }
            }
            string += '\n';
        }
        System.out.println(string);
    }

    public CheckersBoard getBoard()
    {
        return board;
    }

    public CheckerMove getLastMove()
    {
        return lastMove;
    }

    public MoveTreeNode getParent()
    {
        return parent;
    }
    
    public int getLevel()
    {
        return level;
    }
    
    public int getTotalValue()
    {
        return totalvalue;
    }
    
    public void setTotalValue(int value)
    {
        this.totalvalue = value;
    }
    
    public void setLevel(int level)
    {
        this.level = level;
    }
    
    public void setId(int id) {
    	this.NodeId = id;
    }
    
    public int getId() {
    	return NodeId;
    }
    

}