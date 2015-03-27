package com.example.shootinggame;

import com.example.mygame.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class GameActivity extends Activity {
	private GameView gameView;
	private Button btn_left;
	private Button btn_up;
	private Button btn_right;
	private Button btn_beam;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_game);
		btn_left = (Button) findViewById(R.id.left);
		btn_up = (Button) findViewById(R.id.up);
		btn_right = (Button) findViewById(R.id.right);
		btn_beam = (Button) findViewById(R.id.beam);
		gameView = (GameView) findViewById(R.id.myView1);
		
		WindowManager wm = getWindowManager();
		Display disp = wm.getDefaultDisplay();
		gameView.getLayoutParams().height = disp.getHeight() / 5 * 3;
		

		btn_left.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (gameView.player.direction) {
					gameView.player.direction();
				}
			}
		});

		btn_up.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				gameView.player.jumpflag = true;
			}
		});

		btn_right.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (gameView.player.direction == false) {
					gameView.player.direction();
				}
			}
		});

		btn_beam.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				gameView.player.attack();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.gc();
	}

}
