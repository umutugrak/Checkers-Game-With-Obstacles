package com.checkerswithobstacles;

import java.io.IOException;

import java.lang.reflect.Array;
import java.util.Random;


import java.util.concurrent.TimeUnit;

import model.CheckerMove;
import model.CheckersBoard;
import control.CheckersPredictor;


import com.example.checkerswithobstacles.R.drawable;
import com.example.checkerswithobstacles.R;
import com.example.checkerswithobstacles.R.id;
import com.example.checkerswithobstacles.R.layout;
import com.example.checkerswithobstacles.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Board_Activity extends Activity {
	//constants
	private final int EMPTY = 1001;
	private final int BLACK = 1000;
	private final int BLOCK = 999;
	private boolean predictor_start = false;
	private Point from;
	private Point to;
	//random generator
	private Random rand;
	private int leveltotal = 0;
	private CheckersPredictor cp;
	//game board
	private int[][] board /*= {	{EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
								{EMPTY, BLOCK, EMPTY, EMPTY, EMPTY},
								{EMPTY, BLOCK, EMPTY, EMPTY, EMPTY},
								{EMPTY, EMPTY, EMPTY, BLACK, BLACK},
								{EMPTY, EMPTY, EMPTY, BLACK, BLACK},
								
							};*/ = null;
	private int[][] subboard = null;
	private BoardView boardView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.boardgui);
		Bundle b = getIntent().getExtras();
		board = (int[][]) b.getSerializable("array");
		boardView = (BoardView)findViewById(R.id.board);
		boardView.setBoard(board);
		subboard = board;
		Button button08 = (Button) findViewById(R.id.Button08);
		cp = new CheckersPredictor(board);
		button08.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), MainActivity.class);
				startActivity(intent);			
				 }

			});
		Button button11 = (Button) findViewById(R.id.Button11);
		button11.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			board = subboard;
		    boardView.setBoard(board);
		    boardView.restartGame();
		    predictor_start = false;
		}

			});

		Button button09 = (Button) findViewById(R.id.Button09);
		button09.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!predictor_start) {
				  try {
					cp.start();
				  } catch (IOException e) {
					e.printStackTrace();
				  }
				  predictor_start = true;
				}
				if (predictor_start)
                if(!shouldStop(board)) {
				    cp.nextChild();
                    board = cp.getArray();
					boardView.setBoard(board);					
					if (!boardView.ifEndGame())
					    boardView.movecount(); 
					boardView.gameEnd();
                }
             }
                
		});   
	}


	
	public boolean shouldStop(int[][] control) {
	    return control[0][0] == BLACK && control[0][1] == BLACK && control[1][0] == BLACK && control[1][1] == BLACK;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.board_, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
