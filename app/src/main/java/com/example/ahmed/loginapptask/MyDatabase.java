package com.example.ahmed.loginapptask;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper{

    private static final String Database_name = "logindatabase.db";
    private static String table_name = "login";
    private static String column_username = "username";
    private static String column_password = "password";
    private static final int version = 2;

    public MyDatabase(Context context) {

        super(context , Database_name , null , version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists login (id integer primary key,username text , password text )");
        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if  exists login ");
        onCreate(db);
    }


   // Cursor c = db.rawQuery("select * from users where name ='"+name+ "' AND password = '" +pass+"'", null);


    // get user if user found return true
    public boolean getUser (String user)
    {
        boolean found = false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from login where name ='"+user+ "'", null);
    //     String query_get = "select * from " + table_name + " where " + column_username + " = '" + user + "' ;";
    //         Cursor c = db.rawQuery(query_get, null);
        while (c.moveToNext()) {
                 found = true;
                }
             db.close();

        return found;
    }



    public int getAllUser ()
    {
        int s =0;
            SQLiteDatabase db = this.getReadableDatabase();
        //    String query_get = "select * from login";
            Cursor c = db.rawQuery("select * from login", null);
            c.moveToFirst();
            while (!c.isAfterLast()) {
                  s++;
                c.moveToNext();
            }
            db.close();
        return s;
    }



    // get user and password if user found and password true return true
    public boolean getUserandPassword (String user , String pass)
    {
        boolean found = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String query_get = "select * from"+table_name+"where"+column_username+"='"+user+"' and "+column_password+" ='"+pass+"'";
        Cursor c = db.rawQuery(query_get , null);
        while (c.moveToNext())
        {
            found = true;
            db.close();
            return found;
        }
        return found;
    }



    // insert user if inserted return true
    public boolean insertUser (String user , String pass)
    {
        boolean insert;
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(column_username, user);
                contentValues.put(column_password, pass);
                db.insert(table_name, null, contentValues);
                db.close();
                insert = true;

        return insert;
    }

    // insert user if updated return true
    public boolean updateUser (String user , String oldpass , String newpass)
    {
        boolean update = false;
        if (getUserandPassword(user , oldpass))
        {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(column_username, user);
                contentValues.put(column_password, newpass);
                int id = db.update(table_name, contentValues,column_username+"=?", new String[]{user});
                db.close();
                update = true;
        }
        else {
            update = false;
        }
        return update;
    }

        //    db.execSQL("update users set name = '"+user+"' and password ='"+pass+"' where id ="+id , null);

}


