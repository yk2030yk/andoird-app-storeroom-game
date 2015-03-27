package com.example.shootinggame;

import com.example.mygame.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Player {
	protected Resources res;
	protected Canvas canvas;
	public Beam beam;
	public int base;
	public int mass;
	public Bitmap bitmap;
	public Bitmap bitmap_nomal;
	public Bitmap bitmap_nomal2;
	public Bitmap bitmap_d1;
	public Bitmap bitmap_d2;
	public Bitmap bitmap_jump;
	public float x = 0;
	public float y = 0;
	public double score = 0;
	public double bonus = 1;
	public float speed = (float) 0.05;
	public boolean jumpflag = false;// ジャンプ中かどうか
	public boolean damageFlag = false;// ダメージ中を喰らったかどうか
	public boolean direction = true;// 右か左か
	public int jump_cnt = 0;
	public int damage_cnt = 0;
	public int walk_cnt = 0;
	private static int[][] bitmapID = { { R.drawable.p_n, R.drawable.p_n2, R.drawable.p_j, R.drawable.p_d } };

	public Player(Resources res, Canvas canvas, int base, int mass) {
		this.res = res;
		this.canvas = canvas;
		this.base = base;
		this.mass = mass;
		setBitmap(bitmapID, 0);
		beam = new Beam(res, canvas, base, mass);
	}

	public void setBitmap(int[][] bitmapID, int stageID) {
		bitmap_nomal = BitmapFactory.decodeResource(res, bitmapID[stageID][0]);
		bitmap_nomal = Bitmap.createScaledBitmap(bitmap_nomal, mass, mass, false);
		bitmap_nomal2 = BitmapFactory.decodeResource(res, bitmapID[stageID][1]);
		bitmap_nomal2 = Bitmap.createScaledBitmap(bitmap_nomal2, mass, mass, false);
		bitmap_jump = BitmapFactory.decodeResource(res, bitmapID[stageID][2]);
		bitmap_jump = Bitmap.createScaledBitmap(bitmap_jump, mass, mass, false);
		bitmap_d1 = BitmapFactory.decodeResource(res, bitmapID[stageID][3]);
		bitmap_d1 = Bitmap.createScaledBitmap(bitmap_d1, mass, mass, false);
		bitmap_d2 = BitmapFactory.decodeResource(res, R.drawable.damage2);
		bitmap_d2 = Bitmap.createScaledBitmap(bitmap_d2, mass, mass, false);
		bitmap = bitmap_nomal;
	}

	public void draw() {
		this.bonus -= 0.001;
		walkEffect();
		x += speed;
		areaCheck();
		if (jumpflag) {
			jump();
		} else {
			y -= 0.125;
		}
		beam.draw();
		canvas.drawBitmap(bitmap, mass * x, mass * (base - y), null);
	}

	public void draw(int x, int y) {
		this.x = x;
		this.y = y;
		canvas.drawBitmap(bitmap, mass * x, mass * (base - y), null);
	}

	// 方向変換
	public void direction() {
		speed *= -1;
		if (direction) {
			direction = false;
		} else {
			direction = true;
		}
	}

	// ジャンプアクション
	public void jump() {
		if (jump_cnt < 5) {
			y += 0.25;
		} else if (jump_cnt < 10) {
			y += 0.1;
		} else if (jump_cnt < 15) {
			y += 0.15;
		} else if (jump_cnt < 20) {
			y += 0.05;
		}
		jump_cnt++;
		jumpEffect();
		if (jump_cnt == 20) {
			jumpflag = false;
			jump_cnt = 0;
		}
	}

	public void attack() {
		beam.attack(this);
	}

	// ブロックとの当たり判定(上)
	public void collisionCheck(Obstacle ob) {
		if (jumpflag == false) {
			if ((ob.x < this.x + 0.5) && (this.x + 0.5 < ob.x + 1)) {
				if ((ob.y - 1 < this.y - 1) && (this.y - 1 < ob.y + 0.5)) {
					this.y = (float) (ob.y + 1 + 0.125);
				}
			}
		}
	}

	// 範囲内にいるかチェック
	public void areaCheck() {
		if (x > (canvas.getWidth() / mass)) {
			x = -1;
		} else if (x < -1) {
			x = (canvas.getWidth() / mass);
		}
	}

	// ジャンプ中の画像を変える
	public void jumpEffect() {
		if (jump_cnt < 20) {
			bitmap = bitmap_jump;
		} else if (jump_cnt == 20) {
			bitmap = bitmap_nomal;
		}
	}

	// 歩き中の画像を変える
	public void walkEffect() {
		if (walk_cnt % 20 < 10) {
			bitmap = bitmap_nomal;
		} else {
			bitmap = bitmap_nomal2;
		}
		walk_cnt++;
		if (walk_cnt == 100) {
			walk_cnt = 0;
		}
	}

	// ダメージ中の画像を変える
	public void damageEffect() {
		if (damage_cnt < 20 && (damage_cnt % 2 == 0)) {
			bitmap = bitmap_d1;
		} else if (damage_cnt < 20 && damage_cnt % 2 == 1) {
			bitmap = bitmap_d2;
		} else if (damage_cnt == 20) {
			bitmap = bitmap_nomal;
			this.x = 5;
			this.y = 15;
			damage_cnt = 0;
			damageFlag = false;
		}
		damage_cnt++;
	}

	public void scored() {
		score += this.bonus;
	}

	public int getResult() {
		return (int) (score * 100);
	}

	public void recycle() {
		bitmap.recycle();
		bitmap_nomal.recycle();
		bitmap_nomal2.recycle();
		bitmap_jump.recycle();
		bitmap_d1.recycle();
		bitmap_d2.recycle();
		beam.bitmap.recycle();
	}
}
