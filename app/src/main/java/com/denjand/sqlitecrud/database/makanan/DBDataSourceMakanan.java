package com.denjand.sqlitecrud.database.makanan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.denjand.sqlitecrud.models.Makanan;

import java.util.ArrayList;

public class DBDataSourceMakanan {
    //inisialiasi SQLite Database
    private SQLiteDatabase database;

    //inisialisasi kelas DBHelper
    private DBHelperMakanan dbHelper;

    //ambil semua nama kolom
    private String[] allColumns = { DBHelperMakanan.COLUMN_ID,
            DBHelperMakanan.COLUMN_NAME,
            DBHelperMakanan.COLUMN_HARGA, DBHelperMakanan.COLUMN_KATEGORI};

    //DBHelper diinstantiasi pada constructor
    public DBDataSourceMakanan(Context context)
    {
        dbHelper = new DBHelperMakanan(context);
    }

    //membuka/membuat sambungan baru ke database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    //menutup sambungan ke database
    public void close() {
        dbHelper.close();
    }

    //method untuk create/insert makanan ke database
    public Makanan createMakanan(String nama, String harga, String kategori) {

        // membuat sebuah ContentValues, yang berfungsi untuk memasangkan data dengan nama-nama kolom pada database
        ContentValues values = new ContentValues();
        values.put(DBHelperMakanan.COLUMN_NAME, nama);
        values.put(DBHelperMakanan.COLUMN_HARGA, harga);
        values.put(DBHelperMakanan.COLUMN_KATEGORI, kategori);

        // mengeksekusi perintah SQL insert data yang akan mengembalikan sebuah insert ID
        long insertId = database.insert(DBHelperMakanan.TABLE_NAME, null, values);

        // setelah data dimasukkan, memanggil perintah SQL Select menggunakan Cursor untuk melihat apakah data tadi benar2 sudah masuk dengan menyesuaikan ID = insertID
        Cursor cursor = database.query(DBHelperMakanan.TABLE_NAME, allColumns, DBHelperMakanan.COLUMN_ID + " = " + insertId, null, null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();

        // mengubah objek pada kursor pertama tadi ke dalam objek makanan
        Makanan newMakanan = cursorToMakanan(cursor);

        // close cursor
        cursor.close();

        // mengembalikan makanan baru
        return newMakanan;
    }

    private Makanan cursorToMakanan(Cursor cursor){
        Makanan makanan = new Makanan();

        Log.v("info", "The getLong "+cursor.getLong(0));
        Log.v("info", "The setLatLng"+cursor.getString(1)+","+cursor.getString(2));

        makanan.setId(cursor.getLong(0));
        makanan.setNama_makanan(cursor.getString(1));
        makanan.setHarga_makanan(cursor.getString(2));
        makanan.setKategori_makanan(cursor.getString(3));

        return makanan;
    }

    public ArrayList<Makanan> getAllMakanan() {
        ArrayList<Makanan> daftarMakanan = new ArrayList<Makanan>();
        // select all SQL query
        Cursor cursor =
                database.query(DBHelperMakanan.TABLE_NAME,
                        allColumns, null, null, null, null,
                        null);
        // pindah ke data paling pertama
        cursor.moveToFirst();
        // jika masih ada data, masukkan data makanan ke
        // daftar makanan
        while (!cursor.isAfterLast()) {
            Makanan makanan = cursorToMakanan(cursor);
            daftarMakanan.add(makanan);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return daftarMakanan;
    }
    //ambil satu makanan sesuai id
    public Makanan getMakanan(long id){
        Makanan makanan = new Makanan();

        Cursor cursor = database.query(DBHelperMakanan.TABLE_NAME, allColumns, "_id="+id, null, null, null, null);

        cursor.moveToFirst();
        makanan = cursorToMakanan(cursor);
        cursor.close();
        return makanan;
    }
    //update makanan yang diedit
    public void updateMakanan(Makanan m)
    {
        //ambil id makanan
        String strFilter = "_id=" + m.getId();
        //memasukkan ke content values
        ContentValues args = new ContentValues();
        //masukkan data sesuai dengan kolom pada database
        args.put(DBHelperMakanan.COLUMN_NAME,
                m.getNama_makanan());
        args.put(DBHelperMakanan.COLUMN_HARGA,
                m.getHarga_makanan());
        args.put(DBHelperMakanan.COLUMN_KATEGORI,
                m.getKategori_makanan());
        //update query
        database.update(DBHelperMakanan.TABLE_NAME, args,
                strFilter, null);
    }
    // delete makanan sesuai ID
    public void deleteMakanan(long id)
    {
        String strFilter = "_id=" + id;
        database.delete(DBHelperMakanan.TABLE_NAME, strFilter,
                null);
    }

}
