package com.denjand.sqlitecrud.view.barang;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.denjand.sqlitecrud.R;


public class ViewSingleData extends AppCompatActivity {
    TextView tvNama, tvMerk, tvHarga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_single_data);
        tvNama = findViewById(R.id.tv_nama_makanan);
        tvMerk = findViewById(R.id.tv_harga_makanan);
        tvHarga = findViewById(R.id.tv_kategori_makanan);

        tvNama.setText("Barang "+getIntent().getExtras().getString("nama"));
        tvMerk.setText("Merk "+getIntent().getExtras().getString("merk"));
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
