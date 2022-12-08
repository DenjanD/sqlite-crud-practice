package com.denjand.sqlitecrud.database.kasir;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelperKasir extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "data_kasir";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "nama_kasir";
    public static final String COLUMN_UMUR = "umur_kasir";
    public static final String COLUMN_ALAMAT = "alamat_kasir";
    public static final String db_name = "kasir.db";
    public static final int db_version = 1;

//    query SQL for create table
    public static final String db_create = "create table "+
        TABLE_NAME + "("+
        COLUMN_ID + " integer primary key autoincrement, "+
        COLUMN_NAME + " varchar(50) not null, "+
        COLUMN_UMUR + " varchar(50) not null, "+
        COLUMN_ALAMAT + " varchar(50) not null);";

    public DBHelperKasir(Context context){
        super(context, db_name, null, db_version);
        // Auto generated
    }

    //mengeksekusi perintah SQL di atas untuk membuat tabel database baru
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(db_create);
    }

    // dijalankan apabila ingin mengupgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w(DBHelperKasir.class.getName(),"Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
