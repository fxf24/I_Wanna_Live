package com.example.qudqj_000.i_wanna_live;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by qudqj_000 on 2017-06-12.
 */

public class MyManageDB {
    private static MySQLiteDatabase database = null;
    private static SQLiteDatabase scoreboard = null;
    private static MyManageDB mInstance = null;

    public final static MyManageDB getInstance(Context context) {
        if (mInstance == null) mInstance = new MyManageDB(context);
        return mInstance;
    }

    private MyManageDB(Context context) {
        database = new MySQLiteDatabase(context, "scoreDB", null, 1);
        scoreboard = database.getWritableDatabase();
    }

    public Cursor execSELECTrank(String sql) {
        Cursor cursor = scoreboard.rawQuery(sql, null);
        return cursor;
    }

    public void execINSERTrank(String name, String score){
        String sql ="Insert into scoreboard values(null,'" + name + "','" + score + "')";
        scoreboard.execSQL(sql);
    }

    public void execDELETErank(int id){
        String sql= "Delete from scoreboard where id = " + String.valueOf(id);
        scoreboard.execSQL(sql);
    }
}
