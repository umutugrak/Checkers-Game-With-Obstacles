package com.checkerswithobstacles;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import model.CheckerMove;
import model.CheckersBoard;

import com.example.checkerswithobstacles.R;
import com.example.checkerswithobstacles.R.drawable;

import control.CheckersPredictor;
import control.EvaluationFunction;
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

public class BoardGenerator extends ImageView
{
	private final int EMPTY = 1001;
	private final int BLACK = 1000;
	private final int BLOCK = 999;
	private int[][] board;
	private Resources res;
	private Bitmap bitmap; 
    private int width;
    private int height;
    private float moveX;
    private float moveY;
    private boolean blocked = false;
    private EvaluationFunction eval;
    Random rand = new Random();
    
    EvaluationFunction evalfunc;
    
    public BoardGenerator(Context context, Intent intent) {
        super(context);

    }
    
    public BoardGenerator(Context context, AttributeSet attrs) {
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
        
        if (blocked) {
        	paint.setTextSize(20);
        	canvas.drawText("this board is blocked.please", 50.0f, 100.0f, paint);
        	canvas.drawText("use another board ", 50.0f, 140.0f, paint);                    
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
				invalidate();
				if(board[i][j] == BLOCK) {
					canvas.drawBitmap(bitmap, new Rect(0,0,50,50), new Rect(1+j*width,1+i*width,j*width + width,i*width + width) , null);
					}
				}
			}
	     }
    }
            
	public void setBoard(int[][] _board) {

		moveX = -1;
        width = getWidth() / 5;
        height = getHeight() / 5;
		res = getResources();
		bitmap = BitmapFactory.decodeResource(res, R.drawable.wall);
		board = _board;			
		evalfunc = new EvaluationFunction(board);
	}  
	public boolean onTouchEvent(MotionEvent event) {
		    int action = event.getAction();
		    float X = event.getX();
		    float Y = event.getY();
            int y = (int) (Y/height);
            int x = (int) (X/width);
		    switch (action) {
		    	case MotionEvent.ACTION_DOWN:
			        if ( x == 4 && y == 4 || x == 3 && y == 4 || x == 4 && y == 3 || x == 3 && y == 3 || x == 0 && y == 0 
			        || x == 0 && y == 1 ||x == 1 && y == 0 || x == 1 && y == 1)
			        	break;
			        else {
			        board[(int) (Y/height)][(int) (X/width)] = BLOCK;
			        if (!isBlocked()) {
			        	invalidate();
			        }
			        
			        else
			        	board[(int) (Y/height)][(int) (X/width)] = EMPTY;
			        }
			        	/*else
			        {	
			           board[(int) (Y/height)][(int) (X/width)] = BLOCK;
			    	if (isValid()) {
                            invalidate();
			        }
			    	else
			    		board[(int) (Y/height)][(int) (X/width)] = EMPTY; 	
			    	invalidate();
			        }*/
			        break;
		    	case MotionEvent.BUTTON_SECONDARY:
			        if ( x == 4 && y == 4 || x == 3 && y == 4 || x == 4 && y == 3 || x == 3 && y == 3)
			        	break;
			        else
			           board[(int) (Y/height)][(int) (X/width)] = EMPTY;
			    	invalidate();
			        break;
		        
		    }
		    return (true);
	 }
	  public int[][] returnBoard() {
		  return this.board;
	  }
	  
	  public void setRandom() {
		  int count = 0;
		  while (count != board.length) { 
		  int row = randInt(0,board[0].length -1);
		  int col = randInt(0,board.length -1);
		  if(board[row][col] == EMPTY) {
			  if ( row == 0 && col == 0 
				|| row == 0 && col == 1 || row == 1 && col == 0 || row == 1 && col == 1)
				        	break;
		  count++;
		  if (!isBlocked())
    		 board[row][col] = BLOCK;
		  }
	      
		  invalidate();
	   }
	  }

	  public int randInt(int min, int max) {
		    int randomNum = rand.nextInt((max - min) + 1) + min;
		    return randomNum;
		}

	  public void setblock(boolean value) {
		  this.blocked = value;
	  }
	  
	  public boolean isBlocked() {
      eval = new EvaluationFunction(board);
      eval.calculateTotal();
      if (eval.ifBlocked()) {
    	  this.blocked = true;
    	  return true;
	  }
      return false;
}
}
