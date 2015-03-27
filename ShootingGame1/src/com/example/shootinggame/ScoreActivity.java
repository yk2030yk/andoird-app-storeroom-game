package com.example.shootinggame;

import com.example.mygame.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends Activity {
	private SQLiteDatabase db;
	private TextView tv, tv2;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_score);
		context = this;
		
		SQLiteOpenHelper helper = new ScoreSQLiteHelper(getApplicationContext(), "my.db", null, 1);
		db = helper.getWritableDatabase();
		
		Intent intent = getIntent();
		int score = intent.getExtras().getInt("SCORE", 0);
		tv = (TextView) findViewById(R.id.score);
		tv2 = (TextView) findViewById(R.id.nowScore);
		
		tv2.setText(Integer.toString(score) + "点!");
		addScoreDB(score);
		setTextDB();

		Button btn = (Button) findViewById(R.id.start);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});

		Button btn_delete = (Button) findViewById(R.id.delete);
		btn_delete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder.setTitle("得点の初期化");
				alertDialogBuilder.setMessage("今までの得点を初期化してよろしいですか?");
				alertDialogBuilder.setPositiveButton("はい", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						deleteScoreDB();
						setTextDB();
					}
				});
				alertDialogBuilder.setNegativeButton("いいえ", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {}
				});
				alertDialogBuilder.setCancelable(true);
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is
		// present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void addScoreDB(int score) {
		ContentValues values = new ContentValues();
		values.put("SCORE", score);
		db.insert("SCORE_TABLE", null, values);
	}

	private void setTextDB() {
		tv.setText("");
		int rank = 1;
		String[] columns = { "ID", "SCORE" };
		String where = "";
		Cursor cursor = db.query("SCORE_TABLE", columns, where, null, null, null, "SCORE DESC", "5");
		StringBuilder text = new StringBuilder();
		while (cursor.moveToNext()) {
			text.append(rank + "位: " + cursor.getInt(1) + "点");
			text.append("\n");
			rank++;
		}
		tv.setText(text);
	}

	private void deleteScoreDB() {
		db.delete("SCORE_TABLE", null, null);
	}

}
