package pl.edu.amu.wmi.lsm.snake;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityViewHighScores extends Activity {

	private SharedPreferences sp;
	private String lang;
	private final String KEY_LANG = "lang";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			lang = loadPreferences(KEY_LANG);
		} catch (Exception e) {
			lang = "EN";
		}
		if (lang.equals("PL"))
			setContentView(R.layout.highscorespl);
		else
			setContentView(R.layout.highscores);

		DataBaseHelper db = new DataBaseHelper(this);

		// db.addScore(new Score(10));

		List<Score> list = db.get3BestScores();
		db.close();
		List<Integer> listScores = new ArrayList<Integer>();
		int i = 0;
		for (Score s : list) {

			listScores.add(s.getScore());
			i = i + 1;
		}

		ListView myList = (ListView) findViewById(R.id.list);

		ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
				android.R.layout.simple_list_item_1, listScores);

		myList.setAdapter(adapter);

		if (i == 0) {
			Toast.makeText(getApplicationContext(), "The Database is empty",
					Toast.LENGTH_SHORT).show();
		}

	}

	public void onClick(View view) {
		Intent i = new Intent(this, SnakeActivity.class);
		startActivity(i);
	}

	public void onClickDelete(View view) {
		DataBaseHelper db = new DataBaseHelper(this);
		db.getWritableDatabase();
		db.deleteALL();
		db.close();
		Intent i = new Intent(this, ActivityViewHighScores.class);
		startActivity(i);
	}

	private String loadPreferences(String key) {
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		String loadedString = sp.getString(key, "");
		return loadedString;
	}
}
