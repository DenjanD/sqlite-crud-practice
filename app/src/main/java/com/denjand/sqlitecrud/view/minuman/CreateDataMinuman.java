package com.denjand.sqlitecrud.view.minuman;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.denjand.sqlitecrud.R;
import com.denjand.sqlitecrud.database.minuman.DBDataSourceMinuman;
import com.denjand.sqlitecrud.models.Minuman;

public class CreateDataMinuman extends AppCompatActivity implements View.OnClickListener {
    //inisilisasi elemen-elemen pada layout
    private Button buttonSubmit;
    private EditText edNama;
    private EditText edHarga;
    private EditText edKategori;

    //inisialisasi kontroller/Data Source
    private DBDataSourceMinuman dataSource;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_data_minuman);
        buttonSubmit = (Button) findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(this);
        edNama = (EditText) findViewById(R.id.nama_minuman);
        edHarga = (EditText) findViewById(R.id.harga_minuman);
        edKategori = (EditText) findViewById(R.id.kategori_minuman);

        // instanstiasi kelas DBDataSource
        dataSource = new DBDataSourceMinuman(this);

        //membuat sambungan baru ke database
        dataSource.open();
    }
    //KETIKA Tombol Submit Diklik
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        // Inisialisasi data barang
        String nama = null;
        String harga = null;
        String kategori = null;
        @SuppressWarnings("unused")
        //inisialisasi barang baru (masih kosong)
        Minuman minuman = null;
        if(edNama.getText()!=null &&
                edHarga.getText()!=null && edKategori.getText()!=null)
        {
 /* jika field nama, merk, dan harga tidak kosong
 * maka masukkan ke dalam data barang*/
            nama = edNama.getText().toString();
            harga = edHarga.getText().toString();
            kategori = edKategori.getText().toString();
        }
        switch(v.getId())
        {
            case R.id.button_submit:
                // insert data barang baru
                minuman = dataSource.createMinuman(nama,
                        harga, kategori);
                //konfirmasi kesuksesan
                Toast.makeText(this, "masuk Minuman\n" +
                        "nama" + minuman.getNama_minuman()
                        +
                        "harga" + minuman.getHarga_minuman()
                        +
                        "kategori" +
                        minuman.getKategori_minuman(), Toast.LENGTH_LONG).show();
                break;
        }
    }
}
