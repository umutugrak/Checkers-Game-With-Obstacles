package control;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;




import java.util.Random;

import android.graphics.Point;
import checkers.CheckersTool;
import checkers.ICheckersConstants;
import control.MoveController;
import model.CheckerMove;
import model.CheckersBoard;
import tree.IMoveController;
import tree.MoveTree;
import tree.MoveTreeGenerator;
import tree.MoveTreeNode;

public class CheckersPredictor implements ICheckersConstants 
{
	int[][] cells;
	private MoveTreeNode root;
	private MoveTreeNode stopNode;

    public CheckersPredictor (int[][] array) {
    	this.cells = array;
    }
    public void start() throws IOException
    {
       
    	CheckersBoard board = new CheckersBoard(cells);
        MoveTree moveTree = new MoveTreeGenerator(MoveController.INSTANCE).generateTree(board, 0);
        stopNode = moveTree.getStopNode();
        root = moveTree.getRoot();     
      }
     public int[][] getArray() {

		return root.getBoard().getCells(); 
        }
     
     public void nextChild() {
       if (root != stopNode) {
             root = root.getChild();
      }
     }
    }



    

        
