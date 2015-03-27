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

	// �����ʒu�w��Ő���
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

	// �x�[�X�̉摜�ɕύX
	public void changeImage(int id) {
		bitmap = BitmapFactory.decodeResource(res, id);
		bitmap = Bitmap.createScaledBitmap(bitmap, mass, mass, false);
	}

	public void draw() {
		canvas.drawBitmap(bitmap, mass * x, mass * (base - y), null);
	}

	// �ʒu�w�ŕ`��
	public void draw(int x, int y) {
		this.x = x;
		this.y = y;
		draw();
	}

}
