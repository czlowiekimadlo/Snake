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
	//	db.addScore(new Score("Monika", 100));

	//	Log.d("Reading: ", "Reading all scores..");
	//	List<Score> scores = db.getAllScores();

	//	for (Score cn : scores) {
	//		String log = "Id: " + cn.getID() + " ,Name: " + cn.getName()
	//				+ " ,score: " + cn.getScore();
			// Writing scores to log
	//		Log.d("Name: ", log);
	//	}
		Log.d("Reading: ", "Reading 3 best scores..");
		List<Score> sc = db.get3BestScores();

		for (Score cn : sc) {
			String log = "Id: " + cn.getID() + " ,Name: " + cn.getName()
					+ " ,score: " + cn.getScore();
			// Writing scores to log
			Log.d("Name: ", log);
		}

	}
}
