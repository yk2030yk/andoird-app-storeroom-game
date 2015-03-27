package com.example.scrollgame;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity {
	private SQLiteDatabase db;
	TextView t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_result);

		SQLiteOpenHelper helper = new ScoreSQLiteHelper(getApplicationContext(), "my.db", null, 1);
		db = helper.getWritableDatabase();

		Intent intent = getIntent();
		int score = intent.getExtras().getInt("result", 0);

		addScoreDB(score);

		t = (TextView) findViewById(R.id.textView1);
		ScoreView n = (ScoreView) findViewById(R.id.number1);
		n.setNumber(score);

		AnimationSet as = new AnimationSet(false);
		setAnimation(as);
		n.setVisibility(View.VISIBLE);
		n.startAnimation(as);

		Button btn = (Button) findViewById(R.id.backtop);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ResultActivity.this, TopActivity.class);
				startActivity(intent);
			}
		});

		setTextDB();
	}

	void setAnimation(AnimationSet as) {
		AlphaAnimation animation_alpha = new AlphaAnimation(0f, 1f);
		animation_alpha.setDuration(200);
		TranslateAnimation animation_translate = new TranslateAnimation(300, 0, 0, 0);
		animation_translate.setDuration(200);
		as.addAnimation(animation_alpha);
		as.addAnimation(animation_translate);
	}

	private void addScoreDB(int score) {
		ContentValues values = new ContentValues();
		values.put("SCORE", score);
		db.insert("SCORE_TABLE", null, values);
	}

	private void setTextDB() {
		t.setText("");
		int rank = 1;
		String[] columns = { "ID", "SCORE" };
		String where = "";
		Cursor cursor = db.query("SCORE_TABLE", columns, where, null, null, null, "SCORE DESC", "5");
		StringBuilder text = new StringBuilder();
		while (cursor.moveToNext()) {
			text.append(rank + "ˆÊ:" + cursor.getInt(1) + "“_/");
			rank++;
		}
		t.setText(text);
	}

}
