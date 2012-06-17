package pl.edu.amu.wmi.lsm.snake;

import android.content.*;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

	static final String dbName = "Database";
	static final String highScoresTable = "HighScores";
	static final String colID = "ScoreID";
	static final String colName = "PlayerName";
	static final String colScore = "PlayerScore";
	static final String viewEmps = "ViewEmps";

	public DataBaseHelper(Context context) {
		super(context, dbName, null, 33);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		db.execSQL("CREATE TABLE " + highScoresTable + " (" + colID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + colName + " TEXT, "
				+ colScore + " INTEGER)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

		db.execSQL("DROP TABLE IF EXISTS " + highScoresTable);
		db.execSQL("DROP VIEW IF EXISTS " + viewEmps);
		onCreate(db);
	}

	public void insert() {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(colID, 1);
		cv.put(colName, "Monika");
		cv.put(colScore, 100);
		db.insert(dbName, colID, cv);
		db.close();
	}

	public Cursor getBestScores() {

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cur = db.rawQuery("SELECT " + colName + "," + colScore + " FROM "
				+ dbName + " ORDER BY " + colScore + " DESC LIMIT 3",
				new String[] {});

		return cur;
	}
	
	public void addScore(String name, Integer score){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(colName, name);
		cv.put(colScore, score);
		db.insert(dbName, colID, cv);
		db.close();
	}
	
	public void deleteALL(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE " + highScoresTable);
		onCreate(db);
		db.close();
	}
}
