package com.denjand.sqlitecrud.view.kasir;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.denjand.sqlitecrud.R;


public class ViewSingleDataKasir extends AppCompatActivity {
    TextView tvNama, tvAlamat, tvUmur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_single_data_kasir);
        tvNama = findViewById(R.id.tv_nama_kasir);
        tvAlamat = findViewById(R.id.tv_alamat_kasir);
        tvUmur = findViewById(R.id.tv_umur_kasir);

        tvNama.setText("Nama "+getIntent().getExtras().getString("nama"));
        tvAlamat.setText("Alamat "+getIntent().getExtras().getString("alamat"));
        tvUmur.setText("Umur "+getIntent().getExtras().getString("umur"));
        Button buttonOK = (Button) findViewById(R.id.bt_ok);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
