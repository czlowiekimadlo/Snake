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
import android.util.Log;
import android.view.View;
import android.widget.Toast;
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

    @Override
	public void onCreate(Bundle savedInstanceState) {
                View view = this.findViewById(android.R.id.content);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameboard);
               //sp = this.getApplicationContext().getSharedPreferences(SHARED_PREFERENCE, Activity.MODE_PRIVATE);
		Log.v("INFO", "game board create");
                try 
                {
                Intent j = getIntent();
                Bundle bundle = j.getExtras();
                String valueC = bundle.getString(KEY_COL);               
                Boolean valueR = bundle.getBoolean(KEY_RESTART);

                String[] colors = valueC.split(";");
                int[] kolory = null;
                try {
                    kolory = convertStringArraytoIntArray(colors);
                } catch (Exception ex) {
                    Logger.getLogger(ActivityGameBoard.class.getName()).log(Level.SEVERE, null, ex);
                }
                view.setBackgroundColor(Color.rgb(kolory[0],kolory[1],kolory[2]));
                if (valueR) {
                	this.snakeView.resetGame();
                }
                }
                catch(Exception e) {
                    //view.setBackgroundColor(Color.rgb(0,255,0));
                    Log.v("ERROR", e.toString());
                }

                try {
                    Intent j = getIntent();
                    Bundle bundle = j.getExtras();
                    String valueL = bundle.getString(KEY_LANG);
                } catch (Exception e){

                }
                try {
                    Intent j = getIntent();
                    Bundle bundle = j.getExtras();
                    String valueS = bundle.getString(KEY_SOUND);
                    if(valueS.equals("1")) //dzwiek
                    {
                        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.maintheme);
                        mediaPlayer.start(); // no need to call prepare(); create() does that for you
                    }
                } catch(Exception e) {
                        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.maintheme);
                        mediaPlayer.start(); // no need to call prepare(); create() does that for you
                }
                
                snakeView = (UberSnakeView) findViewById(R.id.snake);
                if (savedInstanceState == null) {
                    // We were just launched -- set up a new game
                	snakeView.setMode(SnakeView.READY);
                } else {
                    // We are being restored
                    Bundle map = savedInstanceState.getBundle(ICICLE_KEY);
                    if (map != null) {
                    	//snakeView.restoreState(map);
                    } else {
                    	snakeView.setMode(SnakeView.READY);
                    }
                }
	}
	
//        @Override
//        public void onPause() {
//            super.onPause();  // Always call the superclass method first
//            Editor ed = sp.edit();
//            ed.putBoolean(KEY_IS_PAUSE, true);
//            ed.commit();
//            mediaPlayer.pause();
//        }
//
//        @Override
//        public void onResume() {
//            super.onResume();  // Always call the superclass method first
//            Editor ed = sp.edit();
//            ed.putBoolean(KEY_IS_PAUSE, false);
//            ed.commit();
//            mediaPlayer.start();
//        }

    public void onClickPause(View view) {
        if (!sp.getBoolean(KEY_IS_PAUSE, false)) onPause();
        else onResume();
    }

	public void onClickExit(View view) { //exit
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to exit?")
                   .setCancelable(false)
                   .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {
                           try {
                           mediaPlayer.stop();
                           } catch(Exception e) { }
                            Intent i = new Intent(ActivityGameBoard.this, SnakeActivity.class);
                            //i.setClass(view.getContext(), SnakeActivity.class);
                            startActivity(i); 
                            ActivityGameBoard.this.finish();
                       }
                   })
                   .setNegativeButton("No", new DialogInterface.OnClickListener() {
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
            //SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            String loadedString = sp.getString(key, "");
            return loadedString;
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