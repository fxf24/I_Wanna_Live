package com.example.qudqj_000.i_wanna_live;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by qudqj_000 on 2017-06-12.
 */

public class MySQLiteDatabase extends SQLiteOpenHelper {

    public MySQLiteDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table if not exists scoreboard (" +
                "id integer primary key autoincrement," +
                "name text not null," +
                "score text);" ;
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists scoreboard";
        db.execSQL(sql);
        onCreate(db);
    }
}
