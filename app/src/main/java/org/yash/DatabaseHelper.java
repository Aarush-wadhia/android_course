package org.yash;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "university";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlStatement = "CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT );";
        db.execSQL(sqlStatement);

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "Yash");
        contentValues.put("email", "yash@gmail.com");
        db.insert("students", null, contentValues);

        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("name", "Priya");
        contentValues1.put("email", "priya@gmail.com");
        db.insert("students", null, contentValues1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
