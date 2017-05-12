package com.deucate.kartik.cricketscorecounter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.deucate.kartik.cricketscorecounter.AddActivity.SQL_CREATE_ENTRIES;

 class MatchDatabase extends SQLiteOpenHelper {



     MatchDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //TODO: As Name

    }
}
