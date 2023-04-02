package com.example.picselector;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteOpenHelper;


public class DBAdapter extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Rating.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContract.DBEntry.TABLE_NAME + " (" +
                    DBContract.DBEntry.COLUMN_PICTURE_NAME + " TEXT PRIMARY KEY," +
                    DBContract.DBEntry.COLUMN_RATING + " TEXT,"+
                    DBContract.DBEntry.COLUMN_COMMENT + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DBContract.DBEntry.TABLE_NAME;



    public DBAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    public Boolean insertRating(String pictureName, String rating, String comment)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.DBEntry.COLUMN_PICTURE_NAME, pictureName);
        contentValues.put(DBContract.DBEntry.COLUMN_RATING, rating);
        contentValues.put(DBContract.DBEntry.COLUMN_COMMENT, comment);
        long result=DB.insert(DBContract.DBEntry.TABLE_NAME, null, contentValues);
        return result != -1;
    }


    public Boolean updateRating(String pictureName, String rating, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.DBEntry.COLUMN_PICTURE_NAME, pictureName);
        contentValues.put(DBContract.DBEntry.COLUMN_RATING, rating);
        contentValues.put(DBContract.DBEntry.COLUMN_COMMENT, comment);
        Cursor cursor = db.rawQuery("Select * from Picturedetails where picturename = ?", new String[]{pictureName});
        if (cursor.getCount() > 0) {
            long result = db.update(DBContract.DBEntry.TABLE_NAME, contentValues, "pictureName=?", new String[]{pictureName});
            return result != -1;
        } else {
            return false;
        }
    }


    public Boolean deleteRating(String pictureName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Picturedetails where pictureName = ?", new String[]{pictureName});
        if (cursor.getCount() > 0) {
            long result = db.delete(DBContract.DBEntry.TABLE_NAME, "pictureName=?", new String[]{pictureName});
            return result != -1;
        } else {
            return false;
        }

    }
    public Cursor getRatingData ()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Picturedetails", null);
        return cursor;

    }
}

