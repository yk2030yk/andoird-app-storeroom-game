package com.example.scrollgame;

import java.util.Random;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Obstacle{
	Bitmap bitmap_block;
	Canvas canvas;
	float mass;
	int base;
	float x, y;
	float speed=(float)0.04;
	
	Obstacle(Resources res, Canvas canvas, float mass, int base, int x, int y){
		this.canvas=canvas;
		this.mass=mass;
		this.base=base;
		this.x=x;
		this.y=y;
		bitmap_block=BitmapFactory.decodeResource(res, R.drawable.block2);
		bitmap_block=Bitmap.createScaledBitmap(bitmap_block, (int)mass, (int)mass, false);
	}
	
	void draw(){
		x-=speed;
		if(x<=-2){
			x=(float)(11-0.05);
			Random ran=new Random();
			y=ran.nextInt(7);
			
		}
		canvas.drawBitmap(bitmap_block, mass*x , mass*(base-y), null);
	}
	
}
