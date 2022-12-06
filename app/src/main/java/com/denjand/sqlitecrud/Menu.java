package com.denjand.sqlitecrud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.denjand.sqlitecrud.view.barang.CreateData;
import com.denjand.sqlitecrud.view.barang.ViewData;
import com.denjand.sqlitecrud.view.makanan.CreateDataMakanan;
import com.denjand.sqlitecrud.view.makanan.ViewDataMakanan;

public class Menu extends AppCompatActivity implements View.OnClickListener {
    private Button bTambah;
    private Button bLihat;
    private Button bTambahMakanan;
    private Button bLihatMakanan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        bTambah = (Button)
                findViewById(R.id.button_tambah);
        bTambah.setOnClickListener(this);
        bLihat = (Button) findViewById(R.id.button_view);
        bLihat.setOnClickListener(this);

        bTambahMakanan = (Button)
                findViewById(R.id.button_tambah_makanan);
        bTambahMakanan.setOnClickListener(this);
        bLihatMakanan = (Button) findViewById(R.id.button_view_makanan);
        bLihatMakanan.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_tambah:
                Intent i = new Intent(this, CreateData.class);
                startActivity(i);
                break;
            case R.id.button_view:
                Intent i2 = new Intent(this, ViewData.class);
                startActivity(i2);
                break;
            case R.id.button_tambah_makanan:
                Intent tambahMakanan = new Intent(this, CreateDataMakanan.class);
                startActivity(tambahMakanan);
                break;
            case R.id.button_view_makanan:
                Intent viewMakanan = new Intent(this, ViewDataMakanan.class);
                startActivity(viewMakanan);
                break;
        }
    }
}
