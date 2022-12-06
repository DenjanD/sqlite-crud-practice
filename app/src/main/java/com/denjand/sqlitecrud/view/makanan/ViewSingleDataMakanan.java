package com.denjand.sqlitecrud.view.makanan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.denjand.sqlitecrud.R;


public class ViewSingleDataMakanan extends AppCompatActivity {
    TextView tvNama, tvKategori, tvHarga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_single_data_makanan);
        tvNama = findViewById(R.id.tv_nama_makanan);
        tvKategori = findViewById(R.id.tv_kategori_makanan);
        tvHarga = findViewById(R.id.tv_harga_makanan);

        tvNama.setText("Nama "+getIntent().getExtras().getString("nama"));
        tvKategori.setText("Kategori "+getIntent().getExtras().getString("kategori"));
        tvHarga.setText("Harga "+getIntent().getExtras().getString("harga"));
        Button buttonOK = (Button) findViewById(R.id.bt_ok);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
