package com.example.scrollgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class TopActivity extends Activity {
	Button btn_start;
	Button btn_select;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_top);
		btn_start = (Button) findViewById(R.id.button_start);
		btn_select = (Button) findViewById(R.id.button_select);
		
		btn_start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TopActivity.this, GameActivity.class);
				startActivity(intent);
			}
		});
		
		btn_select.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TopActivity.this, SelectActivity.class);
				startActivity(intent);
			}
		});
	}

}
