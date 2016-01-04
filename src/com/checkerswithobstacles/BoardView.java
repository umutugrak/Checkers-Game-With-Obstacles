package com.checkerswithobstacles;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import model.CheckerMove;
import model.CheckersBoard;

import com.example.checkerswithobstacles.R;
import com.example.checkerswithobstacles.R.drawable;

import control.CheckersPredictor;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BoardView extends ImageView
{
	private final int EMPTY = 1001;
	private final int BLACK = 1000;
	private final int BLOCK = 999;
	private int[][] board;
	private Resources res;
	private Bitmap bitmap; 
    private int width;
    private int height;
    private int prevRow;
    private int prevCol;
    private int totalmoves = 0;
    private int leveltotal = 15;
    private float moveX;
    private float moveY;
    private boolean picked;
    private boolean gameEnd = false;
    private boolean cpustart = false;
    private Point from;
    private Point to;
    
    public BoardView(Context context, Intent intent) {
        super(context);

    }
    
    public BoardView(Context context, AttributeSet attrs) {
	   super(context, attrs); 

    }


            @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //get width and height      
        width = getWidth() / 5 ;
        height = getHeight() / 5;
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        //
        if (gameEnd) {
        	paint.setTextSize(40);
        	canvas.drawText("your moves: ", 50.0f, 100.0f, paint);
            canvas.drawText(String.valueOf(totalmoves), 110.0f, 160.0f, paint);
            if (totalmoves <= leveltotal)
            	canvas.drawText("well done", 90.0f, 200.0f, paint);
            else if (totalmoves <= leveltotal + 3)
            	canvas.drawText("good.not bad", 90.0f, 200.0f, paint);
            else if (totalmoves <= leveltotal + 6)
            	canvas.drawText("normal.but not so bad", 90.0f, 200.0f, paint);
            else
            	canvas.drawText("you should improve yourself", 90.0f, 200.0f, paint);
            
        } 
        
        else {
    		
            
    		
        for( int i = 0; i != 5; ++i) {
			for(int j = 0; j != 5; ++j) {
				if(board[i][j] == BLACK) { 
					Paint p = new Paint();
				    p.setColor(Color.BLACK);
				    p.setStrokeWidth(30);
				    canvas.drawCircle((float)(j*width + 0.5*width), (float)((i + 0.5)*height), (float)(0.4 * width), p);
				}
			
        
				if(board[i][j] == BLOCK) {
					canvas.drawBitmap(bitmap, new Rect(0,0,50,50), new Rect(1+j*width,1+i*width,j*width + width,i*width + width) , null);
				}
			}
		}
        if(moveX != -1) {
        	Paint p = new Paint();
		    p.setColor(Color.BLACK);
		    p.setStrokeWidth(2); 
        	canvas.drawCircle((float)(moveX*width + 0.5*width), (float)((moveY + 0.5)*height), (float)(0.4 * width), p);
        	invalidate();
        }
        }
     }
	public void setBoard(int[][] _board) {
		picked = false;
		moveX = -1;
        width = getWidth() / 5;
        height = getHeight() / 5;
		res = getResources();
		bitmap = BitmapFactory.decodeResource(res, R.drawable.wall);
		board = _board;
		invalidate();
	}  
	public int getTotalMoves () {
		return this.totalmoves;
	}
	 public boolean onTouchEvent(MotionEvent event) {
		    int action = event.getAction();
		    float X = event.getX();
		    float Y = event.getY();
		    switch (action) {
		    	case MotionEvent.ACTION_DOWN:
		    		if(board[(int) (Y/height)][(int) (X/width)] == BLACK) {
		    			prevRow = (int) (Y/height);
		    			prevCol = (int) (X/width);
		    			picked = true;
		    			board[prevRow][prevCol] = EMPTY;
		    			invalidate();
		    		}
		    		break;
			    case MotionEvent.ACTION_MOVE:
			    	if(picked){
			    		moveX =  X;
	    				moveY =  Y;
	    				invalidate();
			    	}
			    	break;
			    case MotionEvent.ACTION_UP:
			    	if(moveX == -1 && !picked)
			    		return true;
			    	if(isValidMove()) {
			    		board[(int) (moveY/height)][(int) (moveX/width)] = BLACK;
			    		totalmoves++;
			    		gameEnd();
			    	}
			    	else {
			    		board[prevRow][prevCol] = BLACK;
			    	}
			    	moveX = -1;
			    	picked = false;
			    	invalidate();
			    	break;
			    case MotionEvent.ACTION_CANCEL:
			    	break;
		    }
		    return (true);
		  }
	private boolean isValidMove() {
		try {
		int coordX = (int) (moveY/height);
		int coordY = (int) (moveX/width);
		return picked && board[coordX][coordY] == EMPTY && 
				((coordX == prevRow - 1 && coordY == prevCol)||//up
				(coordX == prevRow && coordY == prevCol - 1)||//left
				(coordX == prevRow+1 && coordY == prevCol)||//down
				(coordX == prevRow && coordY == prevCol + 1)||//right
				(coordX == prevRow - 2 && coordY == prevCol && board[prevRow - 1][prevCol] == BLACK)||//up jump
				(coordX == prevRow && coordY == prevCol - 2 && board[prevRow][prevCol - 1] == BLACK)||//left jump
				(coordX == prevRow + 2 && coordY == prevCol && board[prevRow + 1][prevCol] == BLACK)||//down jump
				(coordX == prevRow && coordY == prevCol + 2 && board[prevRow][prevCol + 1] == BLACK)//right jump
				);
		} catch(Exception e) {
			return false;
		}
	}
	    
	public void setLevelTotal(int total) {
		this.leveltotal = total;
	}
	
	public void gameEnd() {
		if (board[0][0] == BLACK && board[0][1] == BLACK && board[1][0] == BLACK 
				&& board[1][1] == BLACK) 
			gameEnd = true;	
	}	
	
	public boolean ifEndGame () {
		return this.gameEnd;
	}
	
	public void restartGame () {
		this.gameEnd = false;
		this.totalmoves = 0;
	}
	
	public void movecount() {
		this.totalmoves++;
	}
		   
		    
	}

