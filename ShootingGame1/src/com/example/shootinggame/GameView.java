package com.example.shootinggame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable, SurfaceHolder.Callback {
	public Canvas canvas;
	public Background background;
	public Player player;
	public Stage stage;
	public boolean isloop = true;
	private int base = 9;
	private int mass;

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		getHolder().addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		isloop = true;
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		isloop = false;
		background.recycle();
		player.recycle();
		stage.recycle();
	}

	@Override
	public void run() {
		canvas = getHolder().lockCanvas();
		if (canvas != null) {
			mass = canvas.getWidth() / (base + 3);
			player = new Player(getResources(), canvas, base, mass);
			background = new Background(getResources(), canvas, base, mass, player);
			stage = new Stage(getResources(), canvas, base, mass, player);
			getHolder().unlockCanvasAndPost(canvas);
		}
		
		int cnt = 0;
		while (isloop) {
			canvas = getHolder().lockCanvas();
			if (canvas != null) {
				cnt++;
				background.draw();
				stage.draw();
				player.draw();
				getHolder().unlockCanvasAndPost(canvas);
				if (cnt > 1000) {
					isloop = false;
					Activity activity = (Activity) this.getContext();
					Intent intent = new Intent(activity, ScoreActivity.class);
					intent.putExtra("SCORE", (int) (player.getResult()));
					activity.startActivity(intent);
				}
			}
		}
	}

	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getRawX();
		int y = (int) event.getRawY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (x > canvas.getWidth() / 2) {
				if (player.direction == false) {
					player.direction();
				}
			} else {
				if (player.direction) {
					player.direction();
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
		return true;
	}

}
