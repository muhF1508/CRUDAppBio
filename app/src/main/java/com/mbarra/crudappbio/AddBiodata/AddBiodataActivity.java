package com.mbarra.crudappbio.AddBiodata;

import android.app.ProgressDialog;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.model.Progress;
import com.mbarra.crudappbio.MainActivity.MainActivity;
import com.mbarra.crudappbio.R;

public class AddBiodataActivity extends AppCompatActivity implements AddBiodataContract.View {
    private EditText Nama, Kelas, Email;
    private Button btnTambah;

    private AddBiodataPresenter presenter;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Nama = findViewById(R.id.edt_nama);
        Kelas = findViewById(R.id.edt_kelas);
        Email = findViewById(R.id.edt_email);
        btnTambah = findViewById(R.id.btn_add);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");

        presenter = new AddBiodataPresenter(this);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addBiodata(
                        Nama.getText().toString(),
                        Kelas.getText().toString(),
                        Email.getText().toString()
                );
            }
        });
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void addSuccess() {
        progressDialog.dismiss();
        Toast.makeText(this, "Tambah Biodata Sukses", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void addFailed() {
        progressDialog.dismiss();
        Toast.makeText(this, "Gagal Menambah Biodata", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showFormNotValid() {
        Toast.makeText(getApplicationContext(), "Show Data", Toast.LENGTH_SHORT).show();
    }
}
