package com.denjand.sqlitecrud.view.makanan;

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
import com.denjand.sqlitecrud.database.makanan.DBDataSourceMakanan;
import com.denjand.sqlitecrud.models.Makanan;

import java.util.ArrayList;
public class ViewDataMakanan extends ListActivity implements OnItemLongClickListener{
    //inisialisasi kontroller
    private DBDataSourceMakanan dataSource;
    //inisialisasi arraylist
    private ArrayList<Makanan> values;
    private Button editButton;
    private Button delButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data_makanan);
        dataSource = new DBDataSourceMakanan(this);
        // buka kontroller
        dataSource.open();
        // ambil semua data makanan
        values = dataSource.getAllMakanan();
        // masukkan data makanan ke array adapter
        ArrayAdapter<Makanan> adapter = new ArrayAdapter<Makanan>(this, android.R.layout.simple_list_item_1, values);
        // set adapter pada list
        setListAdapter(adapter);
        // mengambil listview untuk diset onItemLongClickListener
        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Makanan makanan = (Makanan) getListAdapter().getItem(position);
                switchToGetData(makanan.getId());
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
        final Makanan m = (Makanan) getListAdapter().getItem(pos);
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
                        // Delete makanan
                        dataSource.deleteMakanan(m.getId());
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
        Makanan m = dataSource.getMakanan(id);
        Intent i = new Intent(this, EditDataMakanan.class);
        Bundle bun = new Bundle();
        bun.putLong("id", m.getId());
        bun.putString("nama", m.getNama_makanan());
        bun.putString("harga", m.getHarga_makanan());
        bun.putString("kategori", m.getKategori_makanan());
        i.putExtras(bun);
        finale();
        startActivity(i);
    }
    //method untuk get single data
    public void switchToGetData(long id) {
        Makanan m = dataSource.getMakanan(id);
        Intent i = new Intent(this, ViewSingleDataMakanan.class);
        Bundle bun = new Bundle();
        bun.putLong("id", m.getId());
        bun.putString("nama", m.getNama_makanan());
        bun.putString("harga", m.getHarga_makanan());
        bun.putString("kategori", m.getKategori_makanan());
        i.putExtras(bun);
        dataSource.close();
        startActivity(i);
    }
    //method yang dipanggil ketika edit data selesai
    public void finale() {
        ViewDataMakanan.this.finish();
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

