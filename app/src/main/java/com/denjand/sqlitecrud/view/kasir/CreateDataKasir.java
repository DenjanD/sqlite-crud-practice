package com.denjand.sqlitecrud.view.kasir;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.denjand.sqlitecrud.R;
import com.denjand.sqlitecrud.database.kasir.DBDataSourceKasir;
import com.denjand.sqlitecrud.models.Kasir;

public class CreateDataKasir extends AppCompatActivity implements View.OnClickListener {
    //inisilisasi elemen-elemen pada layout
    private Button buttonSubmit;
    private EditText edNama;
    private EditText edUmur;
    private EditText edAlamat;

    //inisialisasi kontroller/Data Source
    private DBDataSourceKasir dataSource;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_data_kasir);
        buttonSubmit = (Button) findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(this);
        edNama = (EditText) findViewById(R.id.nama_kasir);
        edUmur = (EditText) findViewById(R.id.umur_kasir);
        edAlamat = (EditText) findViewById(R.id.alamat_kasir);

        // instanstiasi kelas DBDataSource
        dataSource = new DBDataSourceKasir(this);

        //membuat sambungan baru ke database
        dataSource.open();
    }
    //KETIKA Tombol Submit Diklik
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        // Inisialisasi data kasir
        String nama = null;
        String umur = null;
        String alamat = null;
        @SuppressWarnings("unused")
        //inisialisasi barang baru (masih kosong)
        Kasir kasir = null;
        if(edNama.getText()!=null &&
                edUmur.getText()!=null && edAlamat.getText()!=null)
        {
 /* jika field nama, merk, dan harga tidak kosong
 * maka masukkan ke dalam data barang*/
            nama = edNama.getText().toString();
            umur = edUmur.getText().toString();
            alamat = edAlamat.getText().toString();
        }
        switch(v.getId())
        {
            case R.id.button_submit:
                // insert data barang baru
                kasir = dataSource.createKasir(nama,
                        umur, alamat);
                //konfirmasi kesuksesan
                Toast.makeText(this, "masuk Kasir\n" +
                        "nama" + kasir.getNama_kasir()
                        +
                        "umur" + kasir.getUmur_kasir()
                        +
                        "alamat" +
                        kasir.getAlamat_kasir(), Toast.LENGTH_LONG).show();
                break;
        }
    }
}
