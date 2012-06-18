package pl.edu.amu.wmi.lsm.snake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class SnakeActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ImageView image = (ImageView) findViewById(R.id.main_image);
	}

	public void onClick(View view) {

		Intent i = new Intent(this, ActivityNewGame.class);
		startActivity(i);
	}

	public void onClick2(View view) {

		Intent i = getIntent();
		i.setClass(view.getContext(), ActivityContinueGame.class);
		startActivity(i);
	}

	public void onClick3(View view) {

		Intent i = getIntent();
		i.setClass(view.getContext(), ActivityViewHighScores.class);
		startActivity(i);
	}

	public void onClick4(View view) {

		Intent i = getIntent();
		i.setClass(view.getContext(), GameSettings.class);
		startActivity(i);
	}

	public void onClick5(View view) {

		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
}
