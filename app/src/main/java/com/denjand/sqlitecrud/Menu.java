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
import com.denjand.sqlitecrud.view.minuman.CreateDataMinuman;
import com.denjand.sqlitecrud.view.minuman.ViewDataMinuman;
import com.denjand.sqlitecrud.view.kasir.CreateDataKasir;
import com.denjand.sqlitecrud.view.kasir.ViewDataKasir;

public class Menu extends AppCompatActivity implements View.OnClickListener {
    private Button bTambah;
    private Button bLihat;
    private Button bTambahMakanan;
    private Button bLihatMakanan;
    private Button bTambahMinuman;
    private Button bLihatMinuman;
    private Button bTambahKasir;
    private Button bLihatKasir;

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

        bTambahMinuman = (Button)
                findViewById(R.id.button_tambah_minuman);
        bTambahMinuman.setOnClickListener(this);
        bLihatMinuman = (Button) findViewById(R.id.button_view_minuman);
        bLihatMinuman.setOnClickListener(this);

        bTambahKasir = (Button)
                findViewById(R.id.button_tambah_kasir);
        bTambahKasir.setOnClickListener(this);
        bLihatKasir = (Button) findViewById(R.id.button_view_kasir);
        bLihatKasir.setOnClickListener(this);
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
            case R.id.button_tambah_minuman:
                Intent tambahMinuman = new Intent(this, CreateDataMinuman.class);
                startActivity(tambahMinuman);
                break;
            case R.id.button_view_minuman:
                Intent viewMinuman = new Intent(this, ViewDataMinuman.class);
                startActivity(viewMinuman);
                break;
            case R.id.button_tambah_kasir:
                Intent tambahKasir = new Intent(this, CreateDataKasir.class);
                startActivity(tambahKasir);
                break;
            case R.id.button_view_kasir:
                Intent viewKasir = new Intent(this, ViewDataKasir.class);
                startActivity(viewKasir);
                break;
        }
    }
}
