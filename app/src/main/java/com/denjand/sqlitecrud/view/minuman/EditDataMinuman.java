package com.denjand.sqlitecrud.view.minuman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.denjand.sqlitecrud.R;
import com.denjand.sqlitecrud.database.minuman.DBDataSourceMinuman;
import com.denjand.sqlitecrud.models.Minuman;


public class EditDataMinuman extends AppCompatActivity implements View.OnClickListener {
    private DBDataSourceMinuman dataSource;
    private long id;
    private String nama;
    private String harga;
    private String kategori;
    private EditText edNama;
    private EditText edHarga;
    private EditText edKategori;
    private TextView txId;
    private Button btnSave;
    private Button btnCancel;
    private Minuman minuman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_minuman);
        //inisialisasi variabel
        edNama = (EditText)
                findViewById(R.id.editText_nama);
        edHarga = (EditText)
                findViewById(R.id.editText_harga);
        edKategori = (EditText)
                findViewById(R.id.editText_kategori);
        txId = (TextView)
                findViewById(R.id.text_id_minuman);
        //buat sambungan baru ke database
        dataSource = new DBDataSourceMinuman(this);
        dataSource.open();
        // ambil data minuman dari extras
        Bundle bun = this.getIntent().getExtras();
        id = bun.getLong("id");
        nama = bun.getString("nama");
        harga = bun.getString("harga");
        kategori = bun.getString("kategori");
        //masukkan data-data minuman tersebut ke field editor
        txId.append(String.valueOf(id));
        edNama.setText(nama);
        edHarga.setText(harga);
        edKategori.setText(kategori);
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
            // apabila tombol save diklik (update minuman)
            case R.id.button_save_update :
                minuman = new Minuman();

                minuman.setHarga_minuman(edHarga.getText().toString());

                minuman.setNama_minuman(edNama.getText().toString());

                minuman.setKategori_minuman(edKategori.getText().toString());
                minuman.setId(id);
                dataSource.updateMinuman(minuman);
                Intent i = new Intent(this,
                        ViewDataMinuman.class);
                startActivity(i);
                EditDataMinuman.this.finish();
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
