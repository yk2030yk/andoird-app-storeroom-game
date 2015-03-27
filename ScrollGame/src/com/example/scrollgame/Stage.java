package com.example.scrollgame;

import java.util.ArrayList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Stage {
	Resources res;
	Bitmap bitmap_back;
	Canvas canvas;
	float mass;
	int base;
	ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	ArrayList<Obstacle> obstacles2 = new ArrayList<Obstacle>();
	ArrayList<Obstacle> obstacles_base = new ArrayList<Obstacle>();

	Stage(Resources res, Canvas canvas, float mass, int base) {
		this.res = res;
		this.canvas = canvas;
		this.mass = mass;
		this.base = base;
		bitmap_back = BitmapFactory.decodeResource(res, R.drawable.backgame);
		bitmap_back = Bitmap.createScaledBitmap(bitmap_back, canvas.getWidth(), canvas.getHeight(), false);
		createObstacles(obstacles, false);
		createObstacles(obstacles2, false);

	}

	void draw() {
		canvas.drawBitmap(bitmap_back, 0, 0, null);
		drawObstacles(obstacles);
		drawObstacles(obstacles2);

	}

	void createObstacles(ArrayList<Obstacle> o, boolean f) {
		for (int i = 0; i < 13; i++) {
			Obstacle obstacle = new Obstacle(res, canvas, mass, base, i, 0);
			o.add(obstacle);
		}
	}

	void drawObstacles(ArrayList<Obstacle> o) {
		for (int i = 0; i < o.size(); i++) {
			o.get(i).draw();
		}
	}

	void collisionCheck(Player p) {
		for (int i = 0; i < obstacles.size(); i++) {
			p.topCollision(obstacles.get(i));
			p.bottomCollision(obstacles.get(i));
			p.sideCollision(obstacles.get(i));
		}
		for (int i = 0; i < obstacles2.size(); i++) {
			p.topCollision(obstacles2.get(i));
			p.bottomCollision(obstacles2.get(i));
			p.sideCollision(obstacles2.get(i));
		}
	}

}
