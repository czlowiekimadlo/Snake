package pl.edu.amu.wmi.lsm.snake;

import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ActivityViewHighScores extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscores);

		DataBaseHelper db = new DataBaseHelper(this);

		Log.d("Reading: ", "Reading 3 best scores..");
		List<Score> sc = db.get3BestScores();

		for (Score cn : sc) {
			String log = "Id: " + cn.getID() + " ,Name: " + cn.getName()
					+ " ,score: " + cn.getScore();
			Log.d("Name: ", log);
		}

	}
}
