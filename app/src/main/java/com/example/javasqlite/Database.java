package com.example.javasqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    //database name
    public  static  final String DATABASE_NAME="student.db";

    //table name
    public  static  final String TABLE_NAME="student_details";

    //tabel columns
    public  static final  String COLUMN_1="student_id";
    public  static  final String COLUMN_2="student_name";
    public  static  final String COLUMN_3="subject";
    public  static  final String COLUMN_4="marks";
    public  static  final String COLUMN_5="result";


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table "+ TABLE_NAME +"(student_id INTEGER PRIMARY KEY AUTOINCREMENT,student_name TEXT,subject TEXT,marks INTEGER,result TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }

    //insert into database
    public  boolean insertData(String studentname,String subject,String marks,String result)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(COLUMN_2,studentname);
        contentValues.put(COLUMN_3,subject);
        contentValues.put(COLUMN_4,marks);
        contentValues.put(COLUMN_5,result);


        long success= db.insert(TABLE_NAME,null,contentValues); //insert data
        if(success==-1)
        {
            return  false;
        }
        else
        {
            return  true;
        }

    }

//show data
    public Cursor readData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);
        return  cursor;
    }

//update data
    public  boolean updateData(String student_id,String name,String subject,String marks,String result)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_1,student_id);
        contentValues.put(COLUMN_2,name);
        contentValues.put(COLUMN_3,subject);
        contentValues.put(COLUMN_4,marks);
        contentValues.put(COLUMN_5,result);
        db.update(TABLE_NAME,contentValues,"student_id = ? ",new String[]{student_id});
        return  true;
    }

    //delete record
    public  Integer deleteRecord(String student_id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"student_id = ?",new String[]{student_id});
    }
}
