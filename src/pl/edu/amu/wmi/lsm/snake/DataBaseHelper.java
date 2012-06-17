package pl.edu.amu.wmi.lsm.snake;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
	
static final String dbName = "Database";
static final String highScoresTable = "HighScores";
static final String colID = "ScoreID";
static final String colName = "PlayerName";
static final String colScore = "PlayerScore";
static final String viewEmps="ViewEmps";



	public DataBaseHelper(Context context) {
		super(context, dbName, null, 33);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	db.execSQL("CREATE TABLE " +highScoresTable+ " (" + colID+ "INTEGER PRIMARY KEY AUTOINCREMENT, " + colName + "TEXT, " + colScore + "INTEGER)");
	
	}



	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
