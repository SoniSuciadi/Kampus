package com.jennyputrin.kampus;
// Create BY Jenny Putri Nengsi
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ManageKampus extends AppCompatActivity {
    private EditText etNama,etAlamat;
    private Button btnSimpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_kampus);
        etNama=findViewById(R.id.et_nama);
        etAlamat=findViewById(R.id.et_alamat);
        btnSimpan=findViewById(R.id.btn_simpan);
        String id=getIntent().getStringExtra("ID");
        etNama.setText(getIntent().getStringExtra("NAMA"));
        etAlamat.setText(getIntent().getStringExtra("ALAMAT"));
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper= new DatabaseHelper(ManageKampus.this);
                if (id.equals("")){
                    long hasil=databaseHelper.addKampus(etNama.getText().toString(),etAlamat.getText().toString());
                    if (hasil!=-1){
                        Toast.makeText(ManageKampus.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ManageKampus.this, "Data Gagal Disimpan", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    long hasil=databaseHelper.editKampus(id,etNama.getText().toString(),etAlamat.getText().toString());
                    if (hasil!=-1){
                        Toast.makeText(ManageKampus.this, "Data Berhasil DiUbah", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ManageKampus.this, "Data Gagal Diubah", Toast.LENGTH_SHORT).show();
                    }
                }
                startActivity(new Intent(ManageKampus.this,MainActivity.class));
            }
        });
    }
}