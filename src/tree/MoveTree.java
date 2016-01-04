package tree;


import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

public class MoveTree
{
    private final MoveTreeNode root;
    private MoveTreeNode stopNode;
    
    public MoveTree(MoveTreeNode root)
    {
        this.root = root;
    }

    public MoveTreeNode getRoot()
    {
        return root;
    }
    
    public MoveTreeNode getStopNode()
    {
        return stopNode;
    }



    public void setStopNode(MoveTreeNode stopNode)
    {
        this.stopNode = stopNode;
    }
}