package com.example.shootinggame;

import com.example.mygame.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Beam {
	public Bitmap bitmap;
	private Canvas canvas;
	private int base;
	private int mass;
	public float x = -1;
	public float y = -1;
	public float speed = (float) 0.5;
	public boolean attackFlag = false;
	public boolean unAttackFlag = true;

	public Beam(Resources res, Canvas canvas, int base, int mass) {
		bitmap = BitmapFactory.decodeResource(res, R.drawable.fire3);
		bitmap = Bitmap.createScaledBitmap(bitmap, mass / 3, mass / 3, false);
		this.canvas = canvas;
		this.base = base;
		this.mass = mass;
	}

	public void draw() {
		if (attackFlag) {
			this.x += speed;
		}
		if (this.x > (canvas.getHeight() / mass) + 5 || this.x < -2 || attackFlag == false) {
			x = -1;
			y = -1;
			attackFlag = false;
			unAttackFlag = true;
		}
		canvas.drawBitmap(bitmap, mass * x, mass * (base - y), null);
	}

	public void attack(Player player) {
		if (unAttackFlag) {
			this.x = (float) (player.x + 0.5);
			this.y = (float) (player.y - 0.2);
			if (player.direction) {
				speed = (float) 0.5;
			} else {
				speed = (float) -0.5;
			}
			attackFlag = true;
			unAttackFlag = false;
		}
	}

	public void recycle() {
		bitmap.recycle();
	}

}
