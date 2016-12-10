package com.example.brandon.lab1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by brandon on 2016-10-20
 * This really does nothing tbh
 */
public class ChatDataBaseHelper extends SQLiteOpenHelper {
    static String ACTIVITY_NAME ="ChatDataBaseHelper";
    static String DATABASE_NAME="chats.db";
    static int VERSION_NUMBER= 90;

    static String name="DB1";
    static String KEY_ID="ID";
    static String KEY_MESSAGE="MESSAGE";

   public ChatDataBaseHelper(Context ctx){
       super(ctx,DATABASE_NAME,null,VERSION_NUMBER);
   }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE " + name +
                " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_MESSAGE + " VARCHAR(50))");}

    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer){
        Log.i(ACTIVITY_NAME,"onUpgrade working YAY!!!" );

        db.execSQL("DROP TABLE IF EXISTS " +name);
        onCreate(db);

    }

    public void onDowngrade (SQLiteDatabase db, int oldVar, int newVar){
        Log.i(ACTIVITY_NAME,"The downgrade worked");
        db.execSQL("DROP TABLE IF EXISTS " +name);
        onCreate(db);
    }

    }
