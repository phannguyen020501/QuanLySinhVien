package com.example.quanlysinhvien;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME="StudentManager";
    private static final String TABLE_NAME="student";
    private static final String MSSV = "mssv";
    private static final String NAME="name";
    private static final String BIRTHDAY="birthday";
    private static final String EMAIL = "email";

    private static final String QueryCreateTB="create table "+TABLE_NAME+"("+
            MSSV +" int primary key,"+
            NAME+" text,"+
            EMAIL+" text,"+
            BIRTHDAY+" text)";

    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public DatabaseHandler(@Nullable Context context){
        super(context,DATABASE_NAME,null, VERSION);
    }





    //được gọi nếu table này chưa đc tạo
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(QueryCreateTB);
            db.execSQL("insert into students(mssv,name,email,birthday) values('20194134','Phan Ngoc Nguyen','nguyen.pn194134@sis.hust.edu.vn','02/05/2001')");

            db.setTransactionSuccessful();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Long addStudent(Student student)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(String.valueOf(MSSV),student.getMssv());
        values.put(NAME,student.getName());
        values.put(EMAIL, student.getEmail());
        values.put(BIRTHDAY,student.getBirthday());
        Long id= db.insert(TABLE_NAME,null,values);
        db.close();
        return id;
    }
    public int updateStudent(Student student)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(NAME,student.getName());
        values.put(String.valueOf(MSSV),student.getMssv());
        values.put(BIRTHDAY,student.getBirthday());
        values.put(EMAIL, student.getEmail());

        int succ=db.update(TABLE_NAME,values,MSSV +"=?",new String[]{String.valueOf(student.getMssv())});
        if(succ>0) //thành công
        {

        }
        db.close();
        return succ;
    }
    public List<Student> getAllStudent()
    {
        List<Student> list=new ArrayList<>();

        String select="select * from "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(select,null);
        cursor.moveToPosition(-1);
        while (cursor.moveToNext())
        {
            Student student=new Student();
            student.setMssv(cursor.getInt(0));
            student.setName(cursor.getString(1));
            student.setEmail(cursor.getString(2));
            student.setBirthday(cursor.getString(3));
            list.add(student);
        }
        db.close();
        return list;
    }
    public int deleteStudent(Student student)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,MSSV+"=?",new String[]{String.valueOf(student.getMssv())});
    }
}
