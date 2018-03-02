package com.ilkaytasli1905.callblocker;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by İlkay Taşlı on 13.02.2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS tbl_User ("
            + "UserId INTEGER primary key AUTOINCREMENT,"
            + "Name TEXT,"
            + "Pasword TEXT);";


    public DBHelper(Context context) {
        super(context, "CallBlockerTest", null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("Message" , "buradayim");
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("Message" , "buradayim2");
    }

    public boolean  isUserCreated(){
        Cursor cursor  = this.getWritableDatabase().rawQuery("select 1 from tbl_User", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    public void insertUserInfo(String name , String password){

    }
}
