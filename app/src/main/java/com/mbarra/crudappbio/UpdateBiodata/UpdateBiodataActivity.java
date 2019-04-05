package com.mbarra.crudappbio.UpdateBiodata;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mbarra.crudappbio.Model.ListDataItem;
import com.mbarra.crudappbio.R;

public class UpdateBiodataActivity extends AppCompatActivity implements UpdateBiodataContract.View {
    EditText nama, kelas, email;
    TextView ID;
    Button btnUpdate;

    ProgressDialog progressDialog;
    private UpdateBiodataPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ID = findViewById(R.id.tv_id);
        nama = findViewById(R.id.edt_namaU);
        kelas = findViewById(R.id.edt_kelasU);
        email = findViewById(R.id.edt_emailU);
        btnUpdate = findViewById(R.id.btn_update);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");

        presenter = new UpdateBiodataPresenter(this);

//        sebelum
        final ListDataItem listDataItem = (ListDataItem)getIntent().getSerializableExtra("biodata");

        ID.setText(listDataItem.getIdSiswa());
        nama.setText(listDataItem.getNamaSiswa());
        kelas.setText(listDataItem.getKelasSiswa());
        email.setText(listDataItem.getEmailSiswa());
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.updateBiodata(listDataItem.getIdSiswa(), nama.getText().toString(),kelas.getText().toString(),email.getText().toString());
            }
        });

    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();

    }

    @Override
    public void updateSuccess() {
        progressDialog.dismiss();
        Toast.makeText(this, "Update Sukses", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void updateFailed() {
        progressDialog.dismiss();
        Toast.makeText(this, "Update Gagal", Toast.LENGTH_SHORT).show();

    }
}
