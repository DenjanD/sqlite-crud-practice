package com.denjand.sqlitecrud.database.minuman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.denjand.sqlitecrud.models.Minuman;

import java.util.ArrayList;

public class DBDataSourceMinuman {
    //inisialiasi SQLite Database
    private SQLiteDatabase database;

    //inisialisasi kelas DBHelper
    private DBHelperMinuman dbHelper;

    //ambil semua nama kolom
    private String[] allColumns = { DBHelperMinuman.COLUMN_ID,
            DBHelperMinuman.COLUMN_NAME,
            DBHelperMinuman.COLUMN_HARGA, DBHelperMinuman.COLUMN_KATEGORI};

    //DBHelper diinstantiasi pada constructor
    public DBDataSourceMinuman(Context context)
    {
        dbHelper = new DBHelperMinuman(context);
    }

    //membuka/membuat sambungan baru ke database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    //menutup sambungan ke database
    public void close() {
        dbHelper.close();
    }

    //method untuk create/insert minuman ke database
    public Minuman createMinuman(String nama, String harga, String kategori) {

        // membuat sebuah ContentValues, yang berfungsi untuk memasangkan data dengan nama-nama kolom pada database
        ContentValues values = new ContentValues();
        values.put(DBHelperMinuman.COLUMN_NAME, nama);
        values.put(DBHelperMinuman.COLUMN_HARGA, harga);
        values.put(DBHelperMinuman.COLUMN_KATEGORI, kategori);

        // mengeksekusi perintah SQL insert data yang akan mengembalikan sebuah insert ID
        long insertId = database.insert(DBHelperMinuman.TABLE_NAME, null, values);

        // setelah data dimasukkan, memanggil perintah SQL Select menggunakan Cursor untuk melihat apakah data tadi benar2 sudah masuk dengan menyesuaikan ID = insertID
        Cursor cursor = database.query(DBHelperMinuman.TABLE_NAME, allColumns, DBHelperMinuman.COLUMN_ID + " = " + insertId, null, null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();

        // mengubah objek pada kursor pertama tadi ke dalam objek minuman
        Minuman newMinuman = cursorToMinuman(cursor);

        // close cursor
        cursor.close();

        // mengembalikan minuman baru
        return newMinuman;
    }

    private Minuman cursorToMinuman(Cursor cursor){
        Minuman minuman = new Minuman();

        Log.v("info", "The getLong "+cursor.getLong(0));
        Log.v("info", "The setLatLng"+cursor.getString(1)+","+cursor.getString(2));

        minuman.setId(cursor.getLong(0));
        minuman.setNama_minuman(cursor.getString(1));
        minuman.setHarga_minuman(cursor.getString(2));
        minuman.setKategori_minuman(cursor.getString(3));

        return minuman;
    }

    public ArrayList<Minuman> getAllMinuman() {
        ArrayList<Minuman> daftarMinuman = new ArrayList<Minuman>();
        // select all SQL query
        Cursor cursor =
                database.query(DBHelperMinuman.TABLE_NAME,
                        allColumns, null, null, null, null,
                        null);
        // pindah ke data paling pertama
        cursor.moveToFirst();
        // jika masih ada data, masukkan data minuman ke
        // daftar minuman
        while (!cursor.isAfterLast()) {
            Minuman minuman = cursorToMinuman(cursor);
            daftarMinuman.add(minuman);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return daftarMinuman;
    }
    //ambil satu minuman sesuai id
    public Minuman getMinuman(long id){
        Minuman minuman = new Minuman();

        Cursor cursor = database.query(DBHelperMinuman.TABLE_NAME, allColumns, "_id="+id, null, null, null, null);

        cursor.moveToFirst();
        minuman = cursorToMinuman(cursor);
        cursor.close();
        return minuman;
    }
    //update minuman yang diedit
    public void updateMinuman(Minuman m)
    {
        //ambil id minuman
        String strFilter = "_id=" + m.getId();
        //memasukkan ke content values
        ContentValues args = new ContentValues();
        //masukkan data sesuai dengan kolom pada database
        args.put(DBHelperMinuman.COLUMN_NAME,
                m.getNama_minuman());
        args.put(DBHelperMinuman.COLUMN_HARGA,
                m.getHarga_minuman());
        args.put(DBHelperMinuman.COLUMN_KATEGORI,
                m.getKategori_minuman());
        //update query
        database.update(DBHelperMinuman.TABLE_NAME, args,
                strFilter, null);
    }
    // delete minuman sesuai ID
    public void deleteMinuman(long id)
    {
        String strFilter = "_id=" + id;
        database.delete(DBHelperMinuman.TABLE_NAME, strFilter,
                null);
    }

}
