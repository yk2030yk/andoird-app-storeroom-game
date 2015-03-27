package com.example.shootinggame;

import com.example.mygame.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Background {
	public Bitmap bitmap;
	public Bitmap bitmap2;
	public Canvas canvas;
	public int base;
	public int mass;
	public Player player;
	public Beam beam;
	public Paint paint;
	public Paint textPaint;
	public int score = 0;
	public float width;
	public float x1;
	public float x2;

	public Background(Resources res, Canvas canvas, int base, int mass, Player player) {
		bitmap = BitmapFactory.decodeResource(res, R.drawable.back);
		bitmap = Bitmap.createScaledBitmap(bitmap, canvas.getWidth(), canvas.getHeight(), false);
		bitmap2 = BitmapFactory.decodeResource(res, R.drawable.back);
		bitmap2 = Bitmap.createScaledBitmap(bitmap2, canvas.getWidth(), canvas.getHeight(), false);
		this.canvas = canvas;
		this.base = base;
		this.mass = mass;
		this.player = player;
		width = canvas.getWidth();
		x1 = 0;
		x2 = width;
		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setTextSize(35);
		textPaint.setColor(Color.BLACK);
	}

	public void draw() {
		x1 -= 1;
		x2 -= 1;
		canvas.drawBitmap(bitmap, x1, 0, null);
		canvas.drawBitmap(bitmap2, x2, 0, null);
		if (x1 < width * -1) {
			x1 = width;
		}
		if (x2 < width * -1) {
			x2 = width;
		}
	}

	public void recycle() {
		bitmap.recycle();
		bitmap2.recycle();
	}
}
