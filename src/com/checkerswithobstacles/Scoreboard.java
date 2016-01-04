package com.checkerswithobstacles;

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
import android.widget.TextView;

public class Scoreboard extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scoreboard);
        TextView tv = (TextView) findViewById(R.id.textView2);
        Bundle b = getIntent().getExtras();
        int value = b.getInt("score");
        String string = String.valueOf(value);
		tv.setText(string);
		Button button9 = (Button) findViewById(R.id.button9);
		button9.setOnClickListener(new View.OnClickListener() {
	    public void onClick(View v) {

	    	Intent intent = new Intent(getApplicationContext(), MainActivity.class);
	    	startActivity(intent);
	    	setContentView(R.layout.activity_main);
			 }

		});
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scoreboard, menu);
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
