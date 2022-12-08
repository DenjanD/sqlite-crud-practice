package com.denjand.sqlitecrud.view.kasir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.denjand.sqlitecrud.R;
import com.denjand.sqlitecrud.database.kasir.DBDataSourceKasir;
import com.denjand.sqlitecrud.models.Kasir;


public class EditDataKasir extends AppCompatActivity implements View.OnClickListener {
    private DBDataSourceKasir dataSource;
    private long id;
    private String nama;
    private String umur;
    private String alamat;
    private EditText edNama;
    private EditText edUmur;
    private EditText edAlamat;
    private TextView txId;
    private Button btnSave;
    private Button btnCancel;
    private Kasir kasir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_kasir);
        //inisialisasi variabel
        edNama = (EditText)
                findViewById(R.id.editText_nama);
        edUmur = (EditText)
                findViewById(R.id.editText_umur);
        edAlamat = (EditText)
                findViewById(R.id.editText_alamat);
        txId = (TextView)
                findViewById(R.id.text_id_kasir);
        //buat sambungan baru ke database
        dataSource = new DBDataSourceKasir(this);
        dataSource.open();
        // ambil data kasir dari extras
        Bundle bun = this.getIntent().getExtras();
        id = bun.getLong("id");
        nama = bun.getString("nama");
        umur = bun.getString("umur");
        alamat = bun.getString("alamat");
        //masukkan data-data kasir tersebut ke field editor
        txId.append(String.valueOf(id));
        edNama.setText(nama);
        edUmur.setText(umur);
        edAlamat.setText(alamat);
        //set listener pada tombol
        btnSave = (Button)
                findViewById(R.id.button_save_update);
        btnSave.setOnClickListener(this);
        btnCancel = (Button)
                findViewById(R.id.button_cancel_update);
        btnCancel.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            // apabila tombol save diklik (update kasir)
            case R.id.button_save_update :
                kasir = new Kasir();

                kasir.setUmur_kasir(edUmur.getText().toString());

                kasir.setNama_kasir(edNama.getText().toString());

                kasir.setAlamat_kasir(edAlamat.getText().toString());
                kasir.setId(id);
                dataSource.updateKasir(kasir);
                Intent i = new Intent(this,
                        ViewDataKasir.class);
                startActivity(i);
                EditDataKasir.this.finish();
                dataSource.close();
                break;
            // apabila tombol cancel diklik, finish activity
            case R.id.button_cancel_update :
                finish();
                dataSource.close();
                break;
        }
    }
}
