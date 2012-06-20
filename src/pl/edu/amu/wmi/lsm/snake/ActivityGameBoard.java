/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.amu.wmi.lsm.snake;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;
import java.util.logging.Level;
import java.util.logging.Logger;

import pl.edu.amu.wmi.lsm.snake.R;
import pl.edu.amu.wmi.lsm.snake.UberSnakeView;

/**
 * 
 * @author AnitkA
 */
public class ActivityGameBoard extends Activity {
	private SharedPreferences sp;
	private UberSnakeView snakeView;
	private final String SHARED_PREFERENCE = "Settings";
	private final String KEY_LANG = "lang";
	private final String KEY_COL = "color";
	private final String KEY_SOUND = "sound";
	private final String KEY_RESTART = "restart";
	private final String KEY_IS_PAUSE = "isPause";
	private static String ICICLE_KEY = "snake-view";
	private MediaPlayer mediaPlayer;
        private String lang;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		View view = this.findViewById(android.R.id.content);
		super.onCreate(savedInstanceState);
                sp  = PreferenceManager.getDefaultSharedPreferences(this);
		setContentView(R.layout.gameboard);

                try { lang = loadPreferences(KEY_LANG); } catch(Exception e) {lang = "EN"; }

		Log.v("INFO", "game board create");
		try {
			Intent j = getIntent();
			Bundle bundle = j.getExtras();
			String valueC = bundle.getString(KEY_COL);
			Boolean valueR = bundle.getBoolean(KEY_RESTART);
			String[] colors = valueC.split(";");
			int[] kolory = null;
			try {
				kolory = convertStringArraytoIntArray(colors);
			} catch (Exception ex) {
				Logger.getLogger(ActivityGameBoard.class.getName()).log(
						Level.SEVERE, null, ex);
			}
			view.setBackgroundColor(Color.rgb(kolory[0], kolory[1], kolory[2]));
			if (valueR) {
				this.snakeView.resetGame();
			}
		} catch (Exception e) {
			// view.setBackgroundColor(Color.rgb(0,255,0));
			Log.v("ERROR", e.toString());
			try {
				String valueC = loadPreferences(KEY_COL);
				String[] colors = valueC.split(";");
				int[] kolory = null;
				try {
					kolory = convertStringArraytoIntArray(colors);
				} catch (Exception ex) {
					Logger.getLogger(ActivityGameBoard.class.getName()).log(
							Level.SEVERE, null, ex);
				}
				view.setBackgroundColor(Color.rgb(kolory[0], kolory[1],
						kolory[2]));
			} catch (Exception f) {
				Logger.getLogger(ActivityGameBoard.class.getName()).log(
						Level.SEVERE, null, f);
			}
		}

		try {
			Intent j = getIntent();
			Bundle bundle = j.getExtras();
			String valueL = bundle.getString(KEY_LANG);
		} catch (Exception e) {

		}
		try {
			Intent j = getIntent();
			Bundle bundle = j.getExtras();
			String valueS = bundle.getString(KEY_SOUND);
			if (valueS.equals("1")) // dzwiek
			{
				mediaPlayer = MediaPlayer.create(getApplicationContext(),
						R.raw.maintheme);
				mediaPlayer.setVolume(0.1f, 0.1f);
				mediaPlayer.setLooping(true);
				mediaPlayer.start(); // no need to call prepare(); create() does
										// that for you
			}
		} catch (Exception e) {
			// savePreferences(KEY_SOUND, "1");
			mediaPlayer = MediaPlayer.create(getApplicationContext(),
					R.raw.maintheme);
			mediaPlayer.start(); // no need to call prepare(); create() does
									// that for you
		}

		snakeView = (UberSnakeView) findViewById(R.id.snake);
		snakeView.scoreNumber = (TextView) findViewById(R.id.scoreNumber);
		snakeView.statusText = (TextView) findViewById(R.id.statusText);
		snakeView.bgMusic = this.mediaPlayer;
		if (savedInstanceState == null) {
			// We were just launched -- set up a new game
			snakeView.setMode(SnakeView.READY);
		} else {
			// We are being restored
			Bundle map = savedInstanceState.getBundle(ICICLE_KEY);
			if (map != null) {
				// snakeView.restoreState(map);
			} else {
				snakeView.setMode(SnakeView.READY);
			}
		}
	}

	// @Override
	// public void onPause() {
	// super.onPause(); // Always call the superclass method first
	// Editor ed = sp.edit();
	// ed.putBoolean(KEY_IS_PAUSE, true);
	// ed.commit();
	// mediaPlayer.pause();
	// }
	//
	// @Override
	// public void onResume() {
	// super.onResume(); // Always call the superclass method first
	// Editor ed = sp.edit();
	// ed.putBoolean(KEY_IS_PAUSE, false);
	// ed.commit();
	// mediaPlayer.start();
	// }
	public void onClickPause(View view) { // zmienilam

		if (snakeView.mode == UberSnakeView.RUNNING) {
			snakeView.setMode(UberSnakeView.PAUSE);
			mediaPlayer.pause();
		} else if (snakeView.mode == UberSnakeView.PAUSE) {
			snakeView.setMode(UberSnakeView.RUNNING);
			mediaPlayer.start();
		}
		snakeView.update();

	}

	public void onClickExit(View view) { // exit
            String msg = null, yes = null, no = null;
            if(lang.equals("PL")) { msg = "Chcesz zakończyć grę?"; yes = "Tak"; no = "Nie"; }
            else { msg = "Are you sure you want to exit?"; yes = "Yes"; no = "No"; }
            if (snakeView.mode == UberSnakeView.RUNNING) snakeView.setMode(UberSnakeView.PAUSE);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg)
				.setCancelable(false)
				.setPositiveButton(yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								try {
									mediaPlayer.stop();
								} catch (Exception e) {
								}
								Intent i = new Intent(ActivityGameBoard.this,
										SnakeActivity.class);
								// i.setClass(view.getContext(),
								// SnakeActivity.class);
								ActivityGameBoard.this.finish();
								startActivity(i);

							}
						})
				.setNegativeButton(no, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}

	public void onClickLeft(View view) {
		this.snakeView.goLeft();
	}

	public void onClickRight(View view) {
		this.snakeView.goRight();
	}

	private void alertExit() {

	}

	private String loadPreferences(String key) {
		//sp = getPreferences(MODE_PRIVATE);
		String loadedString = sp.getString(key, "");
		return loadedString;
	}

	private void savePreferences(String key, String value) {
		//sp = getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public int[] convertStringArraytoIntArray(String[] sarray) throws Exception {
		if (sarray != null) {
			int intarray[] = new int[sarray.length];
			for (int i = 0; i < sarray.length; i++) {
				intarray[i] = Integer.parseInt(sarray[i]);
			}
			return intarray;
		}
		return null;
	}
}