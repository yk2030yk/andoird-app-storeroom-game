package com.example.scrollgame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable, SurfaceHolder.Callback {
	private boolean isLoop = true;
	Canvas canvas;
	Stage stage;
	Player player;
	int base = 10;
	int stageID;

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		getHolder().addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		isLoop = true;
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		isLoop = false;
	}

	ScoreView n;
	int cnt = 0;

	@Override
	public void run() {
		canvas = getHolder().lockCanvas();
		float mass = canvas.getWidth() / 9;

		if (canvas != null) {
			stage = new Stage(getResources(), canvas, mass, base);
			player = new Player(getResources(), canvas, mass, base, stageID);
			getHolder().unlockCanvasAndPost(canvas);
		}

		while (isLoop) {
			canvas = getHolder().lockCanvas();
			cnt++;
			if (cnt >= 300) {
				cnt = 0;
			}
			if (canvas != null) {
				stage.collisionCheck(player);
				stage.draw();
				player.draw();
				getHolder().unlockCanvasAndPost(canvas);
				if (player.gameCheck()) {
					isLoop = false;
					Activity activity = (Activity) this.getContext();
					Intent intent = new Intent(activity, ResultActivity.class);
					intent.putExtra("result", (int) (player.score * 100));
					activity.startActivity(intent);
				}
			}
		}
	}

	int h;

	@SuppressLint("ClickableViewAccessibility")
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (player.land_flag == false) {
				player.land_flag = true;
				player.jump_flag = true;
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
