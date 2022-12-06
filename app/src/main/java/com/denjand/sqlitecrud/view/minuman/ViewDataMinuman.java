package com.denjand.sqlitecrud.view.minuman;

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
import com.denjand.sqlitecrud.database.minuman.DBDataSourceMinuman;
import com.denjand.sqlitecrud.models.Minuman;

import java.util.ArrayList;
public class ViewDataMinuman extends ListActivity implements OnItemLongClickListener{
    //inisialisasi kontroller
    private DBDataSourceMinuman dataSource;
    //inisialisasi arraylist
    private ArrayList<Minuman> values;
    private Button editButton;
    private Button delButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data_minuman);
        dataSource = new DBDataSourceMinuman(this);
        // buka kontroller
        dataSource.open();
        // ambil semua data minuman
        values = dataSource.getAllMinuman();
        // masukkan data minuman ke array adapter
        ArrayAdapter<Minuman> adapter = new ArrayAdapter<Minuman>(this, android.R.layout.simple_list_item_1, values);
        // set adapter pada list
        setListAdapter(adapter);
        // mengambil listview untuk diset onItemLongClickListener
        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Minuman minuman = (Minuman) getListAdapter().getItem(position);
                switchToGetData(minuman.getId());
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
        final Minuman m = (Minuman) getListAdapter().getItem(pos);
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
                        // Delete minuman
                        dataSource.deleteMinuman(m.getId());
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
        Minuman m = dataSource.getMinuman(id);
        Intent i = new Intent(this, EditDataMinuman.class);
        Bundle bun = new Bundle();
        bun.putLong("id", m.getId());
        bun.putString("nama", m.getNama_minuman());
        bun.putString("harga", m.getHarga_minuman());
        bun.putString("kategori", m.getKategori_minuman());
        i.putExtras(bun);
        finale();
        startActivity(i);
    }
    //method untuk get single data
    public void switchToGetData(long id) {
        Minuman m = dataSource.getMinuman(id);
        Intent i = new Intent(this, ViewSingleDataMinuman.class);
        Bundle bun = new Bundle();
        bun.putLong("id", m.getId());
        bun.putString("nama", m.getNama_minuman());
        bun.putString("harga", m.getHarga_minuman());
        bun.putString("kategori", m.getKategori_minuman());
        i.putExtras(bun);
        dataSource.close();
        startActivity(i);
    }
    //method yang dipanggil ketika edit data selesai
    public void finale() {
        ViewDataMinuman.this.finish();
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

