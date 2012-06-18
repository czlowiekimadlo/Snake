package pl.edu.amu.wmi.lsm.snake;

import java.util.ArrayList;
import java.util.List;

import android.content.*;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

	static final String dbName = "Database";
	static final String highScoresTable = "HighScores";
	static final String colID = "id";
	static final String colName = "name";
	static final String colScore = "score";
	static final String viewEmps = "ViewEmps";

	public DataBaseHelper(Context context) {
		super(context, dbName, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		db.execSQL("CREATE TABLE " + highScoresTable + " (" + colID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + colName + " TEXT, "
				+ colScore + " INTEGER" + ")");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

		db.execSQL("DROP TABLE IF EXISTS " + highScoresTable);
		// db.execSQL("DROP VIEW IF EXISTS " + viewEmps);
		onCreate(db);
	}

	public Cursor getBestScores() {

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cur = db.rawQuery("SELECT " + colName + "," + colScore
				+ " FROM " + highScoresTable + " ORDER BY " + colScore
				+ " DESC LIMIT 3", new String[] {});

		return cur;
	}

	public void addScore(Score score) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(colName, score.getName());
		cv.put(colScore, score.getScore());
		db.insert(highScoresTable, null, cv);
		db.close();
	}

	public void deleteALL() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE " + highScoresTable);
		onCreate(db);
		db.close();
	}

	public List<Score> get3BestScores() {
		List<Score> scoreList = new ArrayList<Score>();
		String selectQuery = "SELECT " + colID + "," + colName + "," + colScore
				+ " FROM " + highScoresTable + " ORDER BY " + colScore
				+ " DESC LIMIT 3";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Score score = new Score();
				score.setID(Integer.parseInt(cursor.getString(0)));
				score.setName(cursor.getString(1));
				score.setScore(Integer.parseInt(cursor.getString(2)));
				scoreList.add(score);
			} while (cursor.moveToNext());
		}

		return scoreList;

	}

	public List<Score> getAllScores() {

		List<Score> scoreList = new ArrayList<Score>();
		String selectQuery = "SELECT  * FROM " + highScoresTable;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Score score = new Score();
				score.setID(Integer.parseInt(cursor.getString(0)));
				score.setName(cursor.getString(1));
				score.setScore(Integer.parseInt(cursor.getString(2)));
				scoreList.add(score);
			} while (cursor.moveToNext());
		}

		return scoreList;
	}
}
