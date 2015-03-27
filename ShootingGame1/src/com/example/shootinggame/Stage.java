package com.example.shootinggame;

import java.util.ArrayList;
import java.util.Random;
import com.example.mygame.R;
import android.content.res.Resources;
import android.graphics.Canvas;

public class Stage {
	public Resources res;
	public Canvas canvas;
	public int base;
	public int mass;
	public Player player;
	public ArrayList<Obstacle> basesOb = new ArrayList<Obstacle>();
	public ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();

	public Stage(Resources res, Canvas canvas, int base, int mass, Player player) {
		this.res = res;
		this.canvas = canvas;
		this.base = base;
		this.mass = mass;
		this.player = player;
		// ベース生成
		for (int i = 0; i < 18; i++) {
			Obstacle ob = new Obstacle(res, canvas, base, mass, i - 3, -1);
			ob.changeImage(R.drawable.block_base2);
			basesOb.add(ob);
		}
		// 障害物生成
		for (int i = 0; i < 18; i++) {
			Random ram = new Random();
			int oby = ram.nextInt(7);
			Obstacle ob = new Obstacle(res, canvas, base, mass, i - 3, oby);
			obstacles.add(ob);
		}
		// 敵生成
		for (int i = 0; i < 2; i++) {
			Random ram = new Random();
			int eny = ram.nextInt(7) + 1;
			Enemy en = new Enemy(res, canvas, base, mass, i + 2, eny);
			enemies.add(en);
		}
	}

	public void draw() {
		for (int i = 0; i < obstacles.size(); i++) {
			Obstacle dummyob = obstacles.get(i);
			dummyob.draw();
		}
		for (int i = 0; i < basesOb.size(); i++) {
			Obstacle dummyOb = basesOb.get(i);
			dummyOb.draw();
		}
		for (int i = 0; i < enemies.size(); i++) {
			Enemy dummyEn = enemies.get(i);
			dummyEn.draw();
			dummyEn.damageCheck(player.beam, player);
			collisionCheckAll(dummyEn);
		}
		collisionCheckAll(player);
	}

	// すべてのブロックとのあたり判定をチェック
	public void collisionCheckAll(Player p) {
		for (int i = 0; i < obstacles.size(); i++) {
			Obstacle dummyob = obstacles.get(i);
			p.collisionCheck(dummyob);
		}
		for (int i = 0; i < basesOb.size(); i++) {
			Obstacle dummyOb = basesOb.get(i);
			p.collisionCheck(dummyOb);
		}
	}

	public void recycle() {
		for (int i = 0; i < obstacles.size(); i++) {
			Obstacle dummyob = obstacles.get(i);
			dummyob.bitmap.recycle();
		}
		for (int i = 0; i < basesOb.size(); i++) {
			Obstacle dummyOb = basesOb.get(i);
			dummyOb.bitmap.recycle();
		}
		for (int i = 0; i < enemies.size(); i++) {
			Enemy dummyEn = enemies.get(i);
			dummyEn.recycle();
		}
	}
}
