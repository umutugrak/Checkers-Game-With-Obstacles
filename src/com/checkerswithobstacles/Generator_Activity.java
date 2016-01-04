package com.checkerswithobstacles;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import model.CheckersBoard;

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

public class Generator_Activity extends Activity {

	//constants
		private final int EMPTY = 1001;
		private final int BLACK = 1000;
		private final int BLOCK = 999;
		//game board
		private int[][] board = {	{EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
									{EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
									{EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
									{EMPTY, EMPTY, EMPTY, BLACK, BLACK},
									{EMPTY, EMPTY, EMPTY, BLACK, BLACK},
									
								};
		private int[][] subboard = null;
		private BoardGenerator boardGenerator;
		
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.generator_gui);
			boardGenerator = (BoardGenerator)findViewById(R.id.boardgeneratorgui);
		    boardGenerator.setBoard(board);
		    subboard = board;
		    Button button09 = (Button) findViewById(R.id.Button09);
			button09.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					boardGenerator.setRandom();			
					 }

				});
			Button button10 = (Button) findViewById(R.id.Button10);			
			button10.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent(v.getContext(), Board_Activity.class);
					board = boardGenerator.returnBoard();
					Bundle mBundle = new Bundle();
					mBundle.putSerializable("array", board);
	                intent.putExtras(mBundle);
					startActivity(intent);			
					 }

				});
			Button button11 = (Button) findViewById(R.id.Button11);
			button11.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					board = subboard;
					boardGenerator.setBoard(board);
					boardGenerator.setblock(false);
					 }

				});
		
		}

		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.generator_, menu);
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
