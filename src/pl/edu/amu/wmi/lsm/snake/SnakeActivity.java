package pl.edu.amu.wmi.lsm.snake;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class SnakeActivity extends Activity {
    private SharedPreferences sp;
    private final String KEY_COL = "color";
    private final String KEY_LANG = "lang";
    private final String KEY_SOUND = "sound";
    private final String KEY_RESTART = "restart";
    private String lang;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
                sp  = PreferenceManager.getDefaultSharedPreferences(this);

                try { lang = loadPreferences(KEY_LANG); }
                catch(Exception e) { lang = "EN"; }

                if(lang.equals("PL")) setContentView(R.layout.mainpl);
                else setContentView(R.layout.main);
    // Toast.makeText(getApplicationContext(), lang ,Toast.LENGTH_SHORT).show();
		ImageView image = (ImageView) findViewById(R.id.main_image);
	}

	public void onClick(View view) {
            Intent i = new Intent();
            i.setClass(view.getContext(), ActivityGameBoard.class);
            try
            {
            Intent j = getIntent();
            Bundle bundle = j.getExtras();
            String valueCol = bundle.getString(KEY_COL);
            String valueLang = bundle.getString(KEY_LANG);
            String valueSound = bundle.getString(KEY_SOUND);
            i.putExtra(KEY_COL, valueCol);
            i.putExtra(KEY_LANG, valueLang);
            i.putExtra(KEY_RESTART, true);
            i.putExtra(KEY_SOUND, valueSound);
            }
            catch(Exception e)
            {
            }          
            startActivity(i);
	}

	public void onClick2(View view) {

		Intent i = getIntent();
		i.setClass(view.getContext(), ActivityGameBoard.class);
		i.putExtra(KEY_RESTART, false);
		startActivity(i);
	}

	public void onClick3(View view) {

		Intent i = getIntent();
		i.setClass(view.getContext(), ActivityViewHighScores.class);
		startActivity(i);
	}

	public void onClick4(View view) {

		Intent i = new Intent();
		i.setClass(view.getContext(), ActivityViewSettings.class);
		startActivity(i);
	}

	public void onClick5(View view) {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

        private String loadPreferences(String key) {
            //sp = PreferenceManager.getDefaultSharedPreferences(this);
            String loadedString = sp.getString(key, "");
            return loadedString;
        }
}
