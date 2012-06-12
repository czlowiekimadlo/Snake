/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.wmi.lsm.snake;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//import android.widget.Toast;

/**
 *
 * @author AnitkA
 */
public class GameSettings extends ListActivity {
   private SharedPreferences mPrefs;
   private final String SHARED_PREFERENCE = "Settings";
   private final String KEY_LANG = "lang";
   private final String KEY_COL = "color";
   private final String KEY_SOUND = "sound";
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] settings = getResources().getStringArray(R.array.settings_array);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.gamesettings, settings));
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

      ListView lv = getListView();
      lv.setTextFilterEnabled(true);

      lv.setOnItemClickListener(new OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view,
            int position, long id) {
            int wybor = safeLongToInt(id);
            switch (wybor) {
                case 0: // change color
                    alertColors();
                    break;
                case 1: // language change
                    alertLang();
                    break;
                case 2: // sounds
                    alertSounds();
                    break;
            }
//          Toast.makeText(getApplicationContext(), ((TextView) view).getText() ,
//              Toast.LENGTH_SHORT).show();
           
        }
      });
    }

//    @Override
//public void onResume()
//{
//    if(! SoundManager.getInstance().isPlaying("thisStageSound") ) //if it's already playing, do not stop it!
//    {
//        SoundManager.getInstance().stopAllSounds();
//        SoundManager.getInstance().playSound("thisStageSound");
//    }
//    super.onResume();
//}

//    public void onDestroy()
//    {
//    	super.onDestroy();
//    	SoundManager.cleanup();
//    }

public void alertLang()
    {
        final CharSequence[] items = {"PL", "EN"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a language and click 'back'");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                Editor editor = mPrefs.edit();
                String lang = items[item].toString();
                editor.putString(KEY_LANG, lang);
                editor.commit();
               // Toast.makeText(getApplicationContext(), tmp+" lol", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

public void alertColors()
//        Player wants to change color theme for the custom one. He touches part of the
//screen with change theme button. Then he selects “Custom theme”. He can set colors of his
//custom theme by entering 3 numbers (between 0 and 255 inclusive) . Colors of elements
//visible on screen are changed.
    {
        final CharSequence[] items = {"Custom", "Red", "Green", "Blue"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a color and click 'back'");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                if(item == 0) //custom
                {
                    alertCustom();
                }
                Editor editor = mPrefs.edit();
                String color = items[item].toString();
                editor.putString(KEY_COL, color);
                editor.commit();
               // Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

public void alertSounds()
    {
    AlertDialog.Builder builder = new AlertDialog.Builder(this); 
    builder.setMessage("Sounds:")
           .setCancelable(false)
           .setPositiveButton("On", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                   Editor editor = mPrefs.edit();
                   editor.putString(KEY_SOUND, "1");
                   editor.commit();
               }
           })
           .setNegativeButton("Off", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                   Editor editor = mPrefs.edit();
                   editor.putString(KEY_SOUND, "0");
                   editor.commit();
               }
           });
    AlertDialog alert = builder.create();
    alert.show();
}

public void alertCustom()
    {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage("Not yet. Sorry...")
       .setCancelable(false)
       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
           }
       })
       .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
           }
       });
    AlertDialog alert = builder.create();
    alert.show();
}

public static int safeLongToInt(long l) {
    if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
        throw new IllegalArgumentException
            (l + " cannot be cast to int without changing its value.");
    }
    return (int) l;
}
}