package com.example.scrollgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Player {
	Bitmap bitmap, bitmap_w;
	Canvas canvas;
	float mass;
	int base;
	float x = 1, y = 4;
	float speed = (float) 0.01;
	float score = 0;
	int stageID;
	boolean gameOver = false;
	int[] bitmapID = { R.drawable.player1, R.drawable.player3, R.drawable.player2, R.drawable.player4 };
	int[] bitmapID_w = { R.drawable.player1_w, R.drawable.player3_w, R.drawable.player2_w, R.drawable.player4_w };

	Player(Resources res, Canvas canvas, float mass, int base, int stageID) {
		this.canvas = canvas;
		this.mass = mass;
		this.base = base;
		this.stageID = stageID;
		this.score = 0;
		bitmap = BitmapFactory.decodeResource(res, bitmapID[stageID]);
		bitmap = Bitmap.createScaledBitmap(bitmap, (int) mass, (int) mass, false);
		bitmap_w = BitmapFactory.decodeResource(res, bitmapID_w[stageID]);
		bitmap_w = Bitmap.createScaledBitmap(bitmap_w, (int) mass, (int) mass, false);
	}

	int cnt_walk = 0;

	void draw() {
		if (jump_flag) {
			if (jump_cnt < 5) {
				y += 0.1;
			} else if (jump_cnt < 10) {
				y += 0.2;
			} else if (jump_cnt < 15) {
				y += 0.15;
			} else if (jump_cnt < 20) {
				y += 0.1;
			} else {
				y += 0.05;
			}
			jump();
		} else {
			y -= 0.2;
		}

		x += speed;
		if (x > 4) {
			speed = 0;
		} else if (x >= 0) {
			speed = (float) 0.01;
		} else {
			gameOver = true;
		}
		if (y < -5) {
			gameOver = true;
		}
		if (cnt_walk % 20 < 10) {
			canvas.drawBitmap(bitmap, mass * x, mass * (base - y), null);
		} else {
			canvas.drawBitmap(bitmap_w, mass * x, mass * (base - y), null);
		}
		cnt_walk++;
		if (cnt_walk == 100) {
			cnt_walk = 0;
		}
		score += speed;
	}

	int jump_cnt = 0;
	boolean jump_flag = false;
	boolean land_flag = false;

	void jump() {
		if (jump_cnt == 25) {
			jump_cnt = 0;
			jump_flag = false;
		}
		jump_cnt++;
	}

	boolean gameCheck() {
		return gameOver;
	}

	void topCollision(Obstacle o) {
		float cx = (float) (x + 0.5);
		float cy = (float) (y - 1);
		if (o.x <= cx && cx <= o.x + 1) {
			if (o.y - 0.5 <= cy && cy <= o.y + 0.05) {
				y = (float) (o.y + 1.1);
				land_flag = false;
			}
		}
	}

	void bottomCollision(Obstacle o) {
		float cx = (float) (x + 0.5);
		float cy = y;
		if (o.x <= cx && cx <= o.x + 1) {
			if (o.y - 1 <= cy && cy <= o.y - 0.5) {
				y = o.y - 1;
			}
		}

	}

	void sideCollision(Obstacle o) {
		float cx = (float) (x + 1);
		float cy = (float) (y - 0.5);
		if (o.y - 1 <= cy && cy <= o.y) {
			if (o.x <= cx && cx <= o.x + 0.5) {
				x = o.x - 1;
				speed = (float) -0.05;
			}
		}
	}

}
