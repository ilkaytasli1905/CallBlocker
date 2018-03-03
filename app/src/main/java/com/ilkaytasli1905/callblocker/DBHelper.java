package com.ilkaytasli1905.callblocker;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by İlkay Taşlı on 13.02.2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    String systemLanguage = "";
    final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS tbl_User ("
            + "UserId INTEGER primary key AUTOINCREMENT,"
            + "Name TEXT,"
            + "Password TEXT);";


    public DBHelper(Context context,String systemLanguage) {
        super(context, "CallBlockerDb", null, 1);
        this.systemLanguage = systemLanguage;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
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

    public Boolean insertUserInfo(String name , String password,Context context){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Name" , name);
            values.put("Password" , password);
            db.insert("tbl_User",null,values);
            db.close();
            if(systemLanguage.equals("TR")){
                Toast.makeText(context,"Kaydetme işlemi tamamlandı.",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(context,"Save process completed.",Toast.LENGTH_LONG).show();
            }
            return true;
        }
        catch(Exception ex){
            if(systemLanguage.equals("TR")) {
                Toast.makeText(context,"Kaydetme işlemi sırasında hata oluştu.",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(context,"Error occured during save process.",Toast.LENGTH_LONG).show();
            }
            return false;
        }
    }
    public Boolean passwordControl(String password , Context context){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor  = db.rawQuery("select Password from tbl_User", null);
            if(cursor.moveToFirst()){
                do{
                    if(cursor.getString(0).equals(password)){
                        cursor.close();
                        db.close();
                        if(systemLanguage.equals("TR")){
                            Toast.makeText(context,"Giriş işlemi tamamlandı.",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(context,"Login process completed.",Toast.LENGTH_LONG).show();
                        }
                        return true;
                    }
                    else{
                        cursor.close();
                        db.close();
                        if(systemLanguage.equals("TR")){
                            Toast.makeText(context,"Şifre hatalı.",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(context,"Password is wrong.",Toast.LENGTH_LONG).show();
                        }
                        return false;
                    }
                }while(cursor.moveToNext());
            }
            return true;
        }
        catch(Exception ex){
            if(systemLanguage.equals("TR")) {
                Toast.makeText(context,"Giriş işlemi sırasında hata oluştu.",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(context,"Error occured during login process.",Toast.LENGTH_LONG).show();
            }
            return false;
        }
    }
    public String getUserName(){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor  = db.rawQuery("select Name from tbl_User", null);
            if(cursor.moveToFirst()){
                do{
                   String a = cursor.getString(0);
                        cursor.close();
                        db.close();
                        return a;
                }while(cursor.moveToNext());
            }
            return "";
        }
        catch(Exception ex){
            return "";
        }
    }
}
