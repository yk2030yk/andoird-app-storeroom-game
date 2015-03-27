package com.example.scrollgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class SelectActivity extends FragmentActivity {
	Button btn_right;
	Button btn_left;
	Button btn_start;
	ImageView iv;
	int[] bitmapID = { R.drawable.player1, R.drawable.player3, R.drawable.player2, R.drawable.player4 };
	int stageID = 0;
	AnimationSet as, as2, as3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_select);
		iv = (ImageView) findViewById(R.id.chara);
		btn_right = (Button) findViewById(R.id.button_right);
		btn_left = (Button) findViewById(R.id.button_left);
		btn_start = (Button) findViewById(R.id.start_btn2);

		as = new AnimationSet(false);
		as2 = new AnimationSet(false);
		setAnimation(as);
		setAnimation2(as2);

		btn_right.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				iv.startAnimation(as);
				stageID++;
				if (stageID >= bitmapID.length) {
					stageID = 0;
				}
				iv.setImageResource(bitmapID[stageID]);
				iv.startAnimation(as);
			}
		});
		btn_left.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				stageID--;
				if (stageID < 0) {
					stageID = bitmapID.length - 1;
				}
				iv.setImageResource(bitmapID[stageID]);
				iv.startAnimation(as2);
			}
		});
		btn_start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SelectActivity.this, GameActivity.class);
				intent.putExtra("ID", stageID);
				startActivity(intent);
			}
		});
	}

	void setAnimation(AnimationSet as) {
		AlphaAnimation animation_alpha = new AlphaAnimation(0f, 1f);
		animation_alpha.setDuration(200);
		TranslateAnimation animation_translate = new TranslateAnimation(300, 0, 0, 0);
		animation_translate.setDuration(200);
		as.addAnimation(animation_alpha);
		as.addAnimation(animation_translate);
		as.setFillAfter(true);
	}

	void setAnimation2(AnimationSet as) {
		AlphaAnimation animation_alpha = new AlphaAnimation(0f, 1f);
		animation_alpha.setDuration(200);
		TranslateAnimation animation_translate = new TranslateAnimation(-300, 0, 0, 0);
		animation_translate.setDuration(200);
		as.addAnimation(animation_alpha);
		as.addAnimation(animation_translate);
	}

}