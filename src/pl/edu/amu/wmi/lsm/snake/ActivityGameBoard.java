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
import android.view.View;
import android.widget.Toast;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AnitkA
 */
public class ActivityGameBoard extends Activity {
   private SharedPreferences sp;
   private final String SHARED_PREFERENCE = "Settings";
   private final String KEY_LANG = "lang";
   private final String KEY_COL = "color";
   private final String KEY_SOUND = "sound";
   private final String KEY_IS_PAUSE = "isPause";
   private MediaPlayer mediaPlayer;

    @Override
	public void onCreate(Bundle savedInstanceState) {
               // View view = this.findViewById(android.R.id.content);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameboard);
               //sp = this.getApplicationContext().getSharedPreferences(SHARED_PREFERENCE, Activity.MODE_PRIVATE);

                try
                {
                Intent j = getIntent();
                Bundle bundle = j.getExtras();
                String valueC = bundle.getString(KEY_COL);
                String valueL = bundle.getString(KEY_LANG);
                //Boolean valueS = bundle.getBoolean(KEY_SOUND);
                }
                catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "blablA" ,Toast.LENGTH_SHORT).show();
                }
                
//                String[] colors = valueC.split(";");
//                int[] kolory = null;
//                try {
//                    kolory = convertStringArraytoIntArray(colors);
//                } catch (Exception ex) {
//                    Logger.getLogger(ActivityGameBoard.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                view.setBackgroundColor(Color.rgb(kolory[0],kolory[1],kolory[2]));
//
//                if(valueS) //dzwiek
//                {
//                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.maintheme);
//                    mediaPlayer.start(); // no need to call prepare(); create() does that for you
//                }
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
                            Intent i = new Intent(ActivityGameBoard.this, SnakeActivity.class);
                            //i.setClass(view.getContext(), SnakeActivity.class);
                            startActivity(i); 
                            //ActivityGameBoard.this.finish();
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

	}

	public void onClickRight(View view) {

	}

        private void alertExit() {

        }

        private String loadPreferences(String key) {
            SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            String loadedString = sharedPreferences.getString(key, "");
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