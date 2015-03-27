package com.example.shootinggame;

import com.example.mygame.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Obstacle {
	private Resources res;
	private Canvas canvas;
	public Bitmap bitmap;
	public int base;
	public int mass;
	public float x = 0;
	public float y = 1;

	// 初期位置指定で生成
	public Obstacle(Resources res, Canvas canvas, int base, int mass, int x, int y) {
		this.res = res;
		bitmap = BitmapFactory.decodeResource(res, R.drawable.block_nomal4);
		bitmap = Bitmap.createScaledBitmap(bitmap, mass, mass, false);
		this.canvas = canvas;
		this.base = base;
		this.mass = mass;
		this.x = x;
		this.y = y;
	}

	// ベースの画像に変更
	public void changeImage(int id) {
		bitmap = BitmapFactory.decodeResource(res, id);
		bitmap = Bitmap.createScaledBitmap(bitmap, mass, mass, false);
	}

	public void draw() {
		canvas.drawBitmap(bitmap, mass * x, mass * (base - y), null);
	}

	// 位置指で描く
	public void draw(int x, int y) {
		this.x = x;
		this.y = y;
		draw();
	}

}
