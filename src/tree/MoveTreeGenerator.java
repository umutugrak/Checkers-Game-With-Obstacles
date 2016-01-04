package tree;


import java.io.IOException;

import java.util.*;

import tree.MoveTreeNode;
import control.EvaluationFunction;
import control.MoveController;
import checkers.CheckersTool;
import checkers.ICheckersConstants;
import tree.IMoveController;
import tree.MoveTree;
import model.CheckerMove;
import model.CheckersBoard;





public class MoveTreeGenerator implements ICheckersConstants 
{
    private final IMoveController controller;
    private int nodeCount;
    private int depth;
    private int id = 0;
    private int cur_eval = 0;
    private int[][] subcells;
    private int tempvalue;
    private Random random;
    private MoveTree ret;
    private MoveTreeNode root;
    public MoveTreeGenerator(IMoveController instance)
    {
        this.controller = instance;
    }
    
    public MoveTree generateTree(CheckersBoard board, int depth) throws IOException 
    {
    	root = new MoveTreeNode(null, board, null);
        root.setLevel(0);
        MoveTree tree = new MoveTree(root);
        this.depth = depth;
        System.out.print(this.depth + "\n" +"\n");
        nodeCount = 0;


        //This is the linkedlist to do A* sorting
        LinkedList<MoveTreeNode> arr = new LinkedList<MoveTreeNode>();
        List<CheckersBoard> fix = new ArrayList<CheckersBoard>();
        EvaluationFunction eval = new EvaluationFunction(root.getBoard().getCells());
        //The very first node, our root is added as first element
        arr.add(0, root);
        MoveTreeNode node;
        CheckersBoard last;
        while (true) {
        	Collections.sort(arr,new Comparator<MoveTreeNode>() {
                @Override
                public int compare(MoveTreeNode o1, MoveTreeNode o2) {
                	if(o1.getTotalValue() < o2.getTotalValue())  
                		return -1;
                	else if (o1.getTotalValue() == o2.getTotalValue())
                		return 0;
                	return 1;
                }
            });
            if (1000 < arr.size())
        	   arr.remove( 1000 );
        	node = arr.get(0);
        	if(fix.size()!=0) 
        		last = fix.get(fix.size()-1);
        	arr.remove(0);
            node.setId(id);
            boolean f = false;
            for (int z=0; z<fix.size(); z++)
            	if(node.getBoard().iden(fix.get(z))) 
            	{
            		f = true; 
            	    break;
            	}
            if(f) {
            	continue;
            }
            fix.add(node.getBoard());
            if(controller.shouldStop(node.getBoard())) {
               ret = new MoveTree(root);
               dfs(node,node.getParent());
               ret.setStopNode(node);
               return ret;
            }
            //change cells and calculate total value
//            eval.changeCells(node.getBoard().getCells());
//            tempvalue = eval.calculateTotal();
//            //set total value(eval value and the current level of node!)
//            node.setTotalValue(tempvalue + node.getLevel());
            //System.out.print("Level: " + node.getLevel() + "\n");
            //System.out.print("node id: " + node.getId() + "\n");
            if(node.getTotalValue() != cur_eval) {
            	cur_eval = node.getTotalValue();
                System.out.print("current eval function: " + node.getTotalValue() + "\n"); 
            }
            depth = node.getLevel();
//            subcells = node.getBoard().getCells();
            id++;
            Collection<CheckerMove> moves = controller.getPossibleMoves(node.getBoard());
            // For each move
            for (CheckerMove m: moves) {
            	//evaluate only this isn't a root node
                if (node.getId() > 1)
                  if (!evaluate(node, m)) continue;
                //for every board configuration, assign the total value
                CheckersBoard childBoard = CheckersTool.copy(node.getBoard());
                childBoard.move(m.getFrom(), m.getDirection());
                MoveTreeNode childNode = new MoveTreeNode(node, childBoard, m);
                eval.changeCells(childBoard.getCells());
                tempvalue = eval.calculateTotal();
                boolean ff = false;
                for(int z=0; z<fix.size(); z++)
                	if(childBoard.iden(fix.get(z))) {
                		ff = true; 
                		break;
                	}
                if(ff)
                	continue;
                childNode.setTotalValue(tempvalue + childNode.getLevel());
                arr.add(childNode);
                nodeCount++;
            } 
        } 
       
    }
    private void dfs(MoveTreeNode node,MoveTreeNode Parent) {
        if(node == root) {
        	return;    	
        }
         dfs(Parent,Parent.getParent());	
         Parent.setChild(node);
        }
    public boolean evaluate(MoveTreeNode b, CheckerMove m) {
    	char c = b.getLastMove().getDirection();
        if (c == 'l' && m.getDirection() == 'r')
        	if ( ( b.getLastMove().getFrom().x == m.getTo().x )
        			&& ( b.getLastMove().getFrom().y == m.getTo().y ) ) 
        		return false;
        if (c == 'r' && m.getDirection() == 'l')
        	if ( ( b.getLastMove().getFrom().x == m.getTo().x )
        			&& ( b.getLastMove().getFrom().y == m.getTo().y ) ) 
        		return false;
        if (c == 'u' && m.getDirection() == 'd')
        	if ( ( b.getLastMove().getFrom().x == m.getTo().x )
        			&& ( b.getLastMove().getFrom().y == m.getTo().y ) ) 
        		return false;
        if (c == 'd' && m.getDirection() == 'u')
        	if ( ( b.getLastMove().getFrom().x == m.getTo().x )
        			&& ( b.getLastMove().getFrom().y == m.getTo().y ) ) 
        		return false;
        if (c == 'a' && m.getDirection() == 'b')
        	if ( ( b.getLastMove().getFrom().x == m.getTo().x )
        			&& ( b.getLastMove().getFrom().y == m.getTo().y ) ) 
        		return false;
        if (c == 'b' && m.getDirection() == 'a')
        	if ( ( b.getLastMove().getFrom().x == m.getTo().x )
        			&& ( b.getLastMove().getFrom().y == m.getTo().y ) ) 
        		return false;
        if (c == 'c' && m.getDirection() == 'e')
        	if ( ( b.getLastMove().getFrom().x == m.getTo().x )
        			&& ( b.getLastMove().getFrom().y == m.getTo().y ) ) 
        		return false;
        if (c == 'e' && m.getDirection() == 'c')
        	if ( ( b.getLastMove().getFrom().x == m.getTo().x )
        			&& ( b.getLastMove().getFrom().y == m.getTo().y ) ) 
        		return false;

       
        return true;  
    }

}