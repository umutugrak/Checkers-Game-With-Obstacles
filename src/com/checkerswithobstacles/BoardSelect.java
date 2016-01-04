package com.checkerswithobstacles;

import checkers.ICheckersConstants;

import com.example.checkerswithobstacles.R;
import com.example.checkerswithobstacles.R.id;
import com.example.checkerswithobstacles.R.layout;
import com.example.checkerswithobstacles.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class BoardSelect extends Activity implements ICheckersConstants {
	
	private int[][] board1 = 
		{	{EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
			{EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
			{EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
			{EMPTY, EMPTY, EMPTY, BLACK, BLACK},
			{EMPTY, EMPTY, EMPTY, BLACK, BLACK},
	};
	
	private  int[][] board2 = 
		{	{EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
			{EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
			{EMPTY, BLOCK, BLOCK, EMPTY, EMPTY},
			{EMPTY, BLOCK, EMPTY, BLACK, BLACK},
			{EMPTY, EMPTY, EMPTY, BLACK, BLACK},
	};
	
	private int[][] board3 = 
		{	{EMPTY, EMPTY, BLOCK, EMPTY, EMPTY},
			{EMPTY, EMPTY, BLOCK, EMPTY, EMPTY},
			{EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
			{EMPTY, EMPTY, BLOCK, BLACK, BLACK},
			{EMPTY, EMPTY, EMPTY, BLACK, BLACK},
	};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.board_select);
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), Board_Activity.class);
				Bundle mBundle = new Bundle();
				Bundle mBundle2 = new Bundle();
				mBundle.putSerializable("array", board1);
                intent.putExtras(mBundle);
				startActivity(intent);			
				 }

			});
		Button button02 = (Button) findViewById(R.id.Button02);
		button02.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), Board_Activity.class);
				Bundle mBundle = new Bundle();
				mBundle.putSerializable("array", board2);
                intent.putExtras(mBundle);
				startActivity(intent);			
				 }

			});
		Button button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), Board_Activity.class);
				Bundle mBundle = new Bundle();
				mBundle.putSerializable("array", board3);
                intent.putExtras(mBundle);
				startActivity(intent);			
				 }

			});
		Button button6 = (Button) findViewById(R.id.button6);
		button6.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), Generator_Activity.class);
				startActivity(intent);			
				 }

			}); 	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.board_select, menu);
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
