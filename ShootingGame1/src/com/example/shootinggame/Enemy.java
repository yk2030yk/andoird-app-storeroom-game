package com.example.shootinggame;

import java.util.Random;
import com.example.mygame.R;
import android.content.res.Resources;
import android.graphics.Canvas;

public class Enemy extends Player {
	private Random ran;
	private int direction_mass;
	private int draw_cnt = 0;
	private static int[][] bitmapID = { { R.drawable.enemy1_nomal, R.drawable.enemy10_nomal2, R.drawable.enemy1_jump, R.drawable.enemy10_damage1 } };

	public Enemy(Resources res, Canvas canvas, int base, int mass, int x, int y) {
		super(res, canvas, base, mass);
		setBitmap(bitmapID, 0);
		this.y = base;
		this.x = x;
		this.y = y;
		ran = new Random();
		direction_mass = ran.nextInt(6) + 1;
	}

	// 敵が左右に動き続ける
	public void draw() {
		if (damageFlag) {
			damageEffect();
		} else {
			walkEffect();
		}
		this.x += speed;
		areaCheck();
		if (jumpflag) {
			jump();
		} else {
			this.y -= 0.125;
		}
		draw_cnt++;
		if (draw_cnt == 20 * (direction_mass)) {
			draw_cnt = 0;
			this.speed *= -1;
			ran = new Random();
			direction_mass = ran.nextInt(6) + 1;
			jumpCheck();
		}
		canvas.drawBitmap(bitmap, mass * x, mass * (base - y), null);
	}

	// ジャンプするか決める
	public void jumpCheck() {
		if ((new Random().nextInt(60)) < 30) {
			jumpflag = true;
		}
	}

	// ダメージを受けたかチェック
	public void damageCheck(Beam beam, Player p) {
		if (this.x < beam.x && beam.x < this.x + 1) {
			if (this.y - 1 < beam.y && beam.y < this.y) {
				if (!damageFlag) {
					p.scored();
				}
				
				damageFlag = true;
				beam.attackFlag = false;
			}
		}
	}
}
