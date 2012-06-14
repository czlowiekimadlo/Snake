/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.amu.wmi.lsm.snake;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AnitkA
 */
public class ActivityGameBoard extends Activity {
   private SharedPreferences mPrefs;
   private final String SHARED_PREFERENCE = "Settings";
   private final String KEY_LANG = "lang";
   private final String KEY_COL = "color";
   private final String KEY_SOUND = "sound";

	public void onCreate(Bundle savedInstanceState) {
                View view = this.findViewById(android.R.id.content);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameboard);
                SharedPreferences sp = this.getApplicationContext().getSharedPreferences(SHARED_PREFERENCE, Activity.MODE_PRIVATE);
                String[] colors = sp.getString(KEY_COL, "0;255;0").split(";");
                int[] kolory = null;
                try {
                    kolory = convertStringArraytoIntArray(colors);
                } catch (Exception ex) {
                    Logger.getLogger(ActivityGameBoard.class.getName()).log(Level.SEVERE, null, ex);
                }
                view.setBackgroundColor(Color.rgb(kolory[0],kolory[1],kolory[2]));
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