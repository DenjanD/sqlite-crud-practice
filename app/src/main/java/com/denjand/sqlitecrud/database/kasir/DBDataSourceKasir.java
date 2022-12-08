package com.denjand.sqlitecrud.database.kasir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.denjand.sqlitecrud.models.Kasir;

import java.util.ArrayList;

public class DBDataSourceKasir {
    //inisialiasi SQLite Database
    private SQLiteDatabase database;

    //inisialisasi kelas DBHelper
    private DBHelperKasir dbHelper;

    //ambil semua nama kolom
    private String[] allColumns = { DBHelperKasir.COLUMN_ID,
            DBHelperKasir.COLUMN_NAME,
            DBHelperKasir.COLUMN_UMUR, DBHelperKasir.COLUMN_ALAMAT};

    //DBHelper diinstantiasi pada constructor
    public DBDataSourceKasir(Context context)
    {
        dbHelper = new DBHelperKasir(context);
    }

    //membuka/membuat sambungan baru ke database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    //menutup sambungan ke database
    public void close() {
        dbHelper.close();
    }

    //method untuk create/insert kasir ke database
    public Kasir createKasir(String nama, String umur, String alamat) {

        // membuat sebuah ContentValues, yang berfungsi untuk memasangkan data dengan nama-nama kolom pada database
        ContentValues values = new ContentValues();
        values.put(DBHelperKasir.COLUMN_NAME, nama);
        values.put(DBHelperKasir.COLUMN_UMUR, umur);
        values.put(DBHelperKasir.COLUMN_ALAMAT, alamat);

        // mengeksekusi perintah SQL insert data yang akan mengembalikan sebuah insert ID
        long insertId = database.insert(DBHelperKasir.TABLE_NAME, null, values);

        // setelah data dimasukkan, memanggil perintah SQL Select menggunakan Cursor untuk melihat apakah data tadi benar2 sudah masuk dengan menyesuaikan ID = insertID
        Cursor cursor = database.query(DBHelperKasir.TABLE_NAME, allColumns, DBHelperKasir.COLUMN_ID + " = " + insertId, null, null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();

        // mengubah objek pada kursor pertama tadi ke dalam objek kasir
        Kasir newKasir = cursorToKasir(cursor);

        // close cursor
        cursor.close();

        // mengembalikan kasir baru
        return newKasir;
    }

    private Kasir cursorToKasir(Cursor cursor){
        Kasir kasir = new Kasir();

        Log.v("info", "The getLong "+cursor.getLong(0));
        Log.v("info", "The setLatLng"+cursor.getString(1)+","+cursor.getString(2));

        kasir.setId(cursor.getLong(0));
        kasir.setNama_kasir(cursor.getString(1));
        kasir.setUmur_kasir(cursor.getString(2));
        kasir.setAlamat_kasir(cursor.getString(3));

        return kasir;
    }

    public ArrayList<Kasir> getAllKasir() {
        ArrayList<Kasir> daftarKasir = new ArrayList<Kasir>();
        // select all SQL query
        Cursor cursor =
                database.query(DBHelperKasir.TABLE_NAME,
                        allColumns, null, null, null, null,
                        null);
        // pindah ke data paling pertama
        cursor.moveToFirst();
        // jika masih ada data, masukkan data kasir ke
        // daftar kasir
        while (!cursor.isAfterLast()) {
            Kasir kasir = cursorToKasir(cursor);
            daftarKasir.add(kasir);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return daftarKasir;
    }
    //ambil satu kasir sesuai id
    public Kasir getKasir(long id){
        Kasir kasir = new Kasir();

        Cursor cursor = database.query(DBHelperKasir.TABLE_NAME, allColumns, "_id="+id, null, null, null, null);

        cursor.moveToFirst();
        kasir = cursorToKasir(cursor);
        cursor.close();
        return kasir;
    }
    //update kasir yang diedit
    public void updateKasir(Kasir m)
    {
        //ambil id kasir
        String strFilter = "_id=" + m.getId();
        //memasukkan ke content values
        ContentValues args = new ContentValues();
        //masukkan data sesuai dengan kolom pada database
        args.put(DBHelperKasir.COLUMN_NAME,
                m.getNama_kasir());
        args.put(DBHelperKasir.COLUMN_UMUR,
                m.getUmur_kasir());
        args.put(DBHelperKasir.COLUMN_ALAMAT,
                m.getAlamat_kasir());
        //update query
        database.update(DBHelperKasir.TABLE_NAME, args,
                strFilter, null);
    }
    // delete kasir sesuai ID
    public void deleteKasir(long id)
    {
        String strFilter = "_id=" + id;
        database.delete(DBHelperKasir.TABLE_NAME, strFilter,
                null);
    }

}
