package com.jennyputrin.kampus;
// Create BY Jenny Putri Nengsi
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvKampus;
    FloatingActionButton fabAdd;
    KampusAdapter kampusAdapter;
    DatabaseHelper databaseHelper;
    List<Kampus> mdata=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvKampus = findViewById(R.id.rv_kampus);
        fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManageKampus.class);
                intent.putExtra("ID", "");
                intent.putExtra("NAMA", "");
                intent.putExtra("ALAMAT", "");
                startActivity(intent);
            }
        });
        setRvKampus();

    }

    public void setRvKampus() {
        getalldata();
        kampusAdapter = new KampusAdapter(mdata, new KampusAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Kampus item, int position) {
                databaseHelper = new DatabaseHelper(MainActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Aksi");
                builder.setMessage("Perintah yang ingin dilakukan?")
                        .setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this, ManageKampus.class);
                                intent.putExtra("ID", String.valueOf(item.getId()));
                                intent.putExtra("NAMA", item.getNama());
                                intent.putExtra("ALAMAT", item.getAlamat());
                                startActivity(intent);
                            }
                        }).setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseHelper.hapusKampus(String.valueOf(item.getId()));
                        kampusAdapter.notifyDataSetChanged();
                        setRvKampus();

                    }
                });
                builder.show();

            }

        });
        rvKampus.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvKampus.setAdapter(kampusAdapter);
    }
    protected void onResume() {
        super.onResume();
        setRvKampus();
    }

    public void getalldata() {
        mdata=new ArrayList<>();
        databaseHelper = new DatabaseHelper(MainActivity.this);
        Cursor data = databaseHelper.retrivedata();
        if (data.getCount() == 0) {
            Toast.makeText(this, "Data Kosong", Toast.LENGTH_SHORT).show();
        } else {
            Kampus kampus = new Kampus();
            while (data.moveToNext()) {
                kampus = new Kampus();
                kampus.setId(data.getInt(0));
                kampus.setNama(data.getString(1));
                kampus.setAlamat(data.getString(2));
                mdata.add(kampus);
            }
        }
    }
}