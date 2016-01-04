package com.checkerswithobstacles;


import com.example.checkerswithobstacles.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button1 = (Button) findViewById(R.id.button1);
		
		button1.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(v.getContext(), BoardSelect.class);
			//intent.putExtra("score", 30);
			startActivity(intent);			
			 }

		});
		Button button2 = (Button) findViewById(R.id.button2);
	    button2.setOnClickListener(new View.OnClickListener() {
			 public void onClick(View v) {
				 Intent intent = new Intent(v.getContext(), Help.class);
				 startActivity(intent);
				 setContentView(R.layout.about);
				 
		    }
		});
		Button button3 = (Button) findViewById(R.id.button3);
	    button3.setOnClickListener(new View.OnClickListener() {
			 public void onClick(View v) {
				 Intent intent = new Intent(v.getContext(), About.class);
				 startActivity(intent);
				 setContentView(R.layout.about);
				 
		    }
		});
	    Button button4 = (Button) findViewById(R.id.button4);
	    button4.setOnClickListener(new View.OnClickListener() {
			 public void onClick(View v) {
				 System.exit(0);
		    }
		});
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
