package com.example.ahmed.loginapptask;
import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper{

    private static final String Database_name = "database1.db";
    private static final String table_name = "login";
    private static final String column_username = "username";
    private static final String column_password = "password";
    private static final int version = 1;

    public MyDatabase(Context context) {
        super(context , Database_name , null , version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+table_name+" (id  INTEGER PRIMARY KEY autoincrement ," +
                column_username+ "  text, "+column_password+"  text)");
           Log.e("DB" , "created table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists" + table_name);
        onCreate(db);
    }

    // get user if user found return true
    public boolean getUser (String user)
    {
        boolean found = false;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("select * from login where "+column_username + "= '"+ user + "'", null);
            //     String query_get = "select * from " + table_name + " where " + column_username + " = '" + user + "' ;";
            //         Cursor c = db.rawQuery(query_get, null);
            while (c.moveToNext()) {
                found = true;
            }
        }
        catch (Exception e)
        {
            found = false;
        }
        return found;
    }

    // insert user if inserted return true
    public Long insertUser (String user , String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_username, user);
        contentValues.put(column_password, pass);
        Long id = db.insert(table_name, null, contentValues);
        return id;
    }

// return int , number of users in database
    public int getAllUser ()
    {
        int s =0;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            //    String query_get = "select * from login";
            Cursor c = db.rawQuery("select * from login " , null);
            c.moveToFirst();
            while (!c.isAfterLast()) {
                s++;
                c.moveToNext();
            }
        }
        catch (Exception e)
        {
            Log.e(table_name , "get all user not work");
        }
        return s;
    }













    // get user and password if user found and password true return true
    public boolean getUserandPassword (String user , String pass)
    {
        boolean found = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String query_get = "select * from "+table_name+" where "+column_username+" ='"+user+"' and "+column_password+" = '"+pass+"'";
        Cursor c = db.rawQuery(query_get , null);
        while (c.moveToNext())
        {
            found = true;
            return found;
        }
        return found;
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
                update = true;
        }
        else {
            update = false;
        }
        return update;
    }

        //    db.execSQL("update users set name = '"+user+"' and password ='"+pass+"' where id ="+id , null);

}


