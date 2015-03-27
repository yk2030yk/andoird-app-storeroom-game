package com.example.scrollgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class ScoreView extends View {
	Bitmap base;
	Bitmap bitmap1;
	Bitmap bitmap10;
	Bitmap bitmap100;
	Bitmap bitmap1000;
	Bitmap bitmap10000;
	Resources res;
	int[] bID = { R.drawable.num0, R.drawable.num1, R.drawable.num2, R.drawable.num3, R.drawable.num4, R.drawable.num5, R.drawable.num6, R.drawable.num7, R.drawable.num8, R.drawable.num9, R.drawable.clear };
	int bw = 10, bh = 10;
	int w, h;
	int x, y;
	int num = 0;

	public ScoreView(Context context, AttributeSet attrs) {
		super(context, attrs);
		res = context.getResources();
		bitmap1 = BitmapFactory.decodeResource(res, bID[0]);
		bitmap10 = BitmapFactory.decodeResource(res, bID[0]);
		bitmap100 = BitmapFactory.decodeResource(res, bID[0]);
		bitmap1000 = BitmapFactory.decodeResource(res, bID[0]);
		bitmap10000 = BitmapFactory.decodeResource(res, bID[0]);
		base = BitmapFactory.decodeResource(res, R.drawable.score);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		w = this.getWidth();
		h = this.getHeight();
		bw = w / 8;
		bh = h / 2;
		x = (w - (5 * bw + 20)) / 2;
		y = (h - bh) / 2;
		base = BitmapFactory.decodeResource(res, R.drawable.score);
		base = Bitmap.createScaledBitmap(base, w, h, false);
		bitmap1 = Bitmap.createScaledBitmap(bitmap1, bw, bh, false);
		bitmap10 = Bitmap.createScaledBitmap(bitmap10, bw, bh, false);
		bitmap100 = Bitmap.createScaledBitmap(bitmap100, bw, bh, false);
		bitmap1000 = Bitmap.createScaledBitmap(bitmap1000, bw, bh, false);
		bitmap10000 = Bitmap.createScaledBitmap(bitmap10000, bw, bh, false);
	}

	void setNumber(int num) {
		this.num = num;
		bitmap1 = BitmapFactory.decodeResource(res, bID[num % 10]);

		if (num >= 10) {
			bitmap10 = BitmapFactory.decodeResource(res, bID[((int) (num / 10)) % 10]);
		} else {
			bitmap10 = BitmapFactory.decodeResource(res, bID[10]);
		}

		if (num >= 100) {
			bitmap100 = BitmapFactory.decodeResource(res, bID[((int) (num / 100)) % 10]);
		} else {
			bitmap100 = BitmapFactory.decodeResource(res, bID[10]);
		}
		
		if (num >= 1000) {
			bitmap1000 = BitmapFactory.decodeResource(res, bID[((int) (num / 1000)) % 10]);
		} else {
			bitmap1000 = BitmapFactory.decodeResource(res, bID[10]);
		}
		
		if (num >= 10000) {
			bitmap10000 = BitmapFactory.decodeResource(res, bID[((int) (num / 10000)) % 10]);
		} else {
			bitmap10000 = BitmapFactory.decodeResource(res, bID[10]);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(base, 0, 0, null);
		canvas.drawBitmap(bitmap10000, x, y, null);
		canvas.drawBitmap(bitmap1000, x + bw, y, null);
		canvas.drawBitmap(bitmap100, x + (bw + 10) * 2, y, null);
		canvas.drawBitmap(bitmap10, x + (bw + 10) * 3, y, null);
		canvas.drawBitmap(bitmap1, x + (bw + 10) * 4, y, null);
	}

}
