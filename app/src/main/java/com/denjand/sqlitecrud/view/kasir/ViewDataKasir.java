package com.denjand.sqlitecrud.view.kasir;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.denjand.sqlitecrud.R;
import com.denjand.sqlitecrud.database.kasir.DBDataSourceKasir;
import com.denjand.sqlitecrud.models.Kasir;

import java.util.ArrayList;
public class ViewDataKasir extends ListActivity implements OnItemLongClickListener{
    //inisialisasi kontroller
    private DBDataSourceKasir dataSource;
    //inisialisasi arraylist
    private ArrayList<Kasir> values;
    private Button editButton;
    private Button delButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data_kasir);
        dataSource = new DBDataSourceKasir(this);
        // buka kontroller
        dataSource.open();
        // ambil semua data kasir
        values = dataSource.getAllKasir();
        // masukkan data kasir ke array adapter
        ArrayAdapter<Kasir> adapter = new ArrayAdapter<Kasir>(this, android.R.layout.simple_list_item_1, values);
        // set adapter pada list
        setListAdapter(adapter);
        // mengambil listview untuk diset onItemLongClickListener
        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Kasir kasir = (Kasir) getListAdapter().getItem(position);
                switchToGetData(kasir.getId());
            }
        });
    }
    //apabila ada long click
    @Override
    public boolean onItemLongClick(final AdapterView<?> adapter, View v, int pos, final long id) {
        //tampilkan alert dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_view);
        dialog.setTitle("Pilih Aksi");
        dialog.show();
        final Kasir m = (Kasir) getListAdapter().getItem(pos);
        editButton = (Button) dialog.findViewById(R.id.button_edit_data);
        delButton = (Button) dialog.findViewById(R.id.button_delete_data);
        //apabila tombol edit diklik
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                switchToEdit(m.getId());
                dialog.dismiss();
            }
        }
        );
        //apabila tombol delete di klik
        delButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Delete kasir
                        dataSource.deleteKasir(m.getId());
                        dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                }
        );
        return true;
    }
    //method untuk edit data
    public void switchToEdit(long id) {
        Kasir k = dataSource.getKasir(id);
        Intent i = new Intent(this, EditDataKasir.class);
        Bundle bun = new Bundle();
        bun.putLong("id", k.getId());
        bun.putString("nama", k.getNama_kasir());
        bun.putString("umur", k.getUmur_kasir());
        bun.putString("alamat", k.getAlamat_kasir());
        i.putExtras(bun);
        finale();
        startActivity(i);
    }
    //method untuk get single data
    public void switchToGetData(long id) {
        Kasir k = dataSource.getKasir(id);
        Intent i = new Intent(this, ViewSingleDataKasir.class);
        Bundle bun = new Bundle();
        bun.putLong("id", k.getId());
        bun.putString("nama", k.getNama_kasir());
        bun.putString("umur", k.getUmur_kasir());
        bun.putString("alamat", k.getAlamat_kasir());
        i.putExtras(bun);
        dataSource.close();
        startActivity(i);
    }
    //method yang dipanggil ketika edit data selesai
    public void finale() {
        ViewDataKasir.this.finish();
        dataSource.close();
    }
    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }
    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }
}

