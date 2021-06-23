package com.example.form;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(Username text primary key, Password text ,Name text, Address text, Gender text, Status text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");

    }
    //inserting database REGISTER
    public boolean insert (String UsernameValue , String PasswordValue, String NameValue, String AddressValue, String GenderValue, String StatusValue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username",UsernameValue);
        contentValues.put("Password",PasswordValue);
        contentValues.put("Name",NameValue);
        contentValues.put("Address",AddressValue);
        contentValues.put("Gender", GenderValue);
        contentValues.put("Status",StatusValue);
        long ins = db.insert("user",null,contentValues);
        if(ins==-1) return false;
        else  return true;

    }
    //checking if Username exists REGISTER
    public Boolean chkUsername(String UsernameValue){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where Username=?",new String[]{UsernameValue});
        if(cursor.getCount()>0) return false;
        else return true;
    }
    //checking the Username and Password LOGIN validation
    public boolean UsernamePassword(String sUsername, String sPassword){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where Username=? and Password=?",new String[]{sUsername,sPassword});
        if(cursor.getCount()>0) return  true;
        else return false;
    }


}
