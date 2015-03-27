package com.example.shootinggame;

import com.example.mygame.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.startText);
		AnimationSet as = new AnimationSet(false);
		setAnimation(as);
		tv.setVisibility(View.VISIBLE);
		tv.startAnimation(as);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Intent i = new Intent(MainActivity.this, GameActivity.class);
			startActivity(i);
		}
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.gc();
	}

	private void setAnimation(AnimationSet as) {
		AlphaAnimation animation_alpha = new AlphaAnimation(0f, 1f);
		animation_alpha.setDuration(700);
		animation_alpha.setRepeatCount(Animation.INFINITE);
		as.addAnimation(animation_alpha);

	}
}
