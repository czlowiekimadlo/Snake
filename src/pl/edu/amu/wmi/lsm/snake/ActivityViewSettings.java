package pl.edu.amu.wmi.lsm.snake;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityViewSettings extends ListActivity  {
   private SharedPreferences mPrefs;
   private final String SHARED_PREFERENCE = "Settings";
   private final String KEY_LANG = "lang";
   public String lang;
   private final String KEY_COL = "color";
   public String color;
   private final String KEY_SOUND = "sound";
   public String sound;

    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.settings);

                mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
                try {
                lang = loadPreferences(KEY_LANG);
                } catch(Exception e) { lang = "EN"; }

                String[] settings = null;
                if(lang.equals("PL")) { settings = getResources().getStringArray(R.array.settings_array_pl); }
                else settings = getResources().getStringArray(R.array.settings_array);
                setListAdapter(new ArrayAdapter<String>(this, R.layout.settings, settings));
                
                ListView lv = getListView();
                lv.setTextFilterEnabled(true);
                      lv.setOnItemClickListener(new OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //int wybor = safeLongToInt(id);
            switch (position) {
                case 0: // change color
                    alertColors();
                    break;
                case 1: // language change
                    alertLang();
                    break;
                case 2: // sounds
                    alertSounds();
                    break;
                case 3: // powrot do glownego
                    Intent i = new Intent(ActivityViewSettings.this, SnakeActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.putExtra(KEY_COL, mPrefs.getString(KEY_COL, null));
                    i.putExtra(KEY_LANG, mPrefs.getString(KEY_LANG, "ENG"));
                    i.putExtra(KEY_SOUND, mPrefs.getString(KEY_SOUND, "on"));
                    startActivity(i);
                    ActivityViewSettings.this.finish();
                    break;
            }
          //Toast.makeText(getApplicationContext(), b.toString() ,Toast.LENGTH_SHORT).show();
        }
      });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putString(KEY_COL, color);
            outState.putString(KEY_LANG, lang);
            outState.putString(KEY_SOUND, sound);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedIS) {
            Log.i("onRestoreInstanceState", "onRestoreInstanceState");
            super.onRestoreInstanceState(savedIS);
            color = savedIS.getString(KEY_COL);
            lang = savedIS.getString(KEY_LANG);
            sound = savedIS.getString(KEY_SOUND);
    }

//    @Override
//    protected void onPause() {
//        savePreferences(KEY_COL, color);
//    }

public void alertLang()
    {
        final CharSequence[] items = {"PL", "EN"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(lang.equals("PL")) builder.setTitle("Wybierz język");
        else builder.setTitle("Pick a language");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                lang = items[item].toString();
//                Editor editor = mPrefs.edit();
//                editor.putString(KEY_LANG, lang);
//                editor.commit();
                savePreferences(KEY_LANG, lang);
                dialog.dismiss();
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
        CharSequence[] items = {"Custom", "Red", "Green", "Blue"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(lang.equals("PL")) builder.setTitle("Wybierz kolor");
        else builder.setTitle("Pick a color");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                if(item == 0) //custom
                {
                    alertCustom();
                }
                if(item == 1) //red
                {
                    color = "255;160;122";
                }
                if(item == 2) //green
                {
                    color = "0;255;0";
                }
                if(item == 3) //blue
                {
                    color = "0;0;255";
                }
                savePreferences(KEY_COL, color);
                dialog.dismiss();
                //Toast.makeText(getApplicationContext(), mPrefs.getString(KEY_COL, null), Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

public void alertSounds()
    {
    String przycisk = null;
    String on, off;
    if(lang.equals("PL")) { przycisk = "Muzyka:"; on = "Wł."; off = "Wył."; }
    else { przycisk = "Music:"; on = "on"; off = "off"; }
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage(przycisk)
           .setCancelable(false)
           .setPositiveButton(on, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                   savePreferences(KEY_SOUND, "1");
               }
           })
           .setNegativeButton(off, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                   savePreferences(KEY_SOUND, "off");
               }
           });
    AlertDialog alert = builder.create();
    alert.show();
}

public void alertCustom()
    {
        String cancel = null;
        if(lang.equals("PL")) cancel = "Anuluj";
        else cancel = "Cancel";

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        if(lang.equals("PL")) alert.setTitle("Wpisz kolor RGB, np. '255;255;255'");
        else alert.setTitle("Type RGB color, e.g. '255;255;255'");
                        final EditText inputR = new EditText(this);
                        alert.setView(inputR);
                        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            String value = inputR.getText().toString().trim();
                                //Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                                savePreferences(KEY_COL, value);
                   }
                    });
               alert.setNegativeButton(cancel,
                   new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.cancel();
                            }
                            });
                    alert.show();
}

private void savePreferences(String key, String value) {
    mPrefs = getPreferences(MODE_PRIVATE);
    SharedPreferences.Editor editor = mPrefs.edit();
    editor.putString(key, value);
    editor.commit();
}

        private String loadPreferences(String key) {
            mPrefs = getPreferences(MODE_PRIVATE);
            String loadedString = mPrefs.getString(key, "");
            return loadedString;
        }

}
