package pl.edu.amu.wmi.lsm.snake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SnakeActivity extends Activity {
    private final String KEY_COL = "color";
    private final String KEY_LANG = "lang";
    private final String KEY_SOUND = "sound";
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// mojsaidas
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

//                            Intent j = getIntent();
//            Bundle bundle = j.getExtras();
//            Object valueSound = bundle.get(KEY_SOUND);
            //Toast.makeText(getApplicationContext(), valueSound.toString() ,Toast.LENGTH_SHORT).show();

	}

	public void onClick(View view) {
            Intent j = getIntent();
            Bundle bundle = j.getExtras();
            String valueCol = bundle.getString(KEY_COL);
            String valueLang = bundle.getString(KEY_LANG);
            Boolean valueSound = bundle.getBoolean(KEY_SOUND);

            Intent i = new Intent(this, ActivityGameBoard.class);
            i.putExtra(KEY_COL, valueCol);
            i.putExtra(KEY_LANG, valueLang);
            i.putExtra(KEY_SOUND, valueSound);
            Toast.makeText(getApplicationContext(), valueSound.toString(), Toast.LENGTH_SHORT).show();

            //Intent i = new Intent(this, ActivityNewGame.class);
		//i.setClass(view.getContext(), ActivityNewGame.class);
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
		i.setClass(view.getContext(), ActivityViewSettings.class);
		startActivity(i);
	}

	public void onClick5(View view) {

		Intent i = getIntent();
		i.setClass(view.getContext(), ActivityViewHighScores.class);
		startActivity(i);
	}
}
