package com.mbarra.crudappbio.ListBiodata;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mbarra.crudappbio.ListBiodata.Adapter.AdapterRV;
import com.mbarra.crudappbio.Model.ListDataItem;
import com.mbarra.crudappbio.R;
import com.mbarra.crudappbio.UpdateBiodata.UpdateBiodataActivity;

import java.util.List;

public class ListBiodataActivity extends AppCompatActivity implements ListBiodataContract.View {
    private ListBiodataPresenter presenter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        recyclerView = findViewById(R.id.rv_biodata);
        presenter = new ListBiodataPresenter(this);
        presenter.getListBiodata();

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);


    }


    @Override
    public void showListBiodata(List<ListDataItem> biodata) {
        AdapterRV adapterRV = null;
        if (biodata != null) {
            adapterRV = new AdapterRV(presenter, biodata);
            if (biodata.size() > 0) {
                recyclerView.setAdapter(adapterRV);
                Toast.makeText(this, "Biodata sudah Didapat", Toast.LENGTH_SHORT).show();
            }
        } else {
            recyclerView.setAdapter(null);
            Toast.makeText(this, "Biodata Kosong", Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    public void goToEditBiodata(ListDataItem listDataItem) {
        Intent intent = new Intent(ListBiodataActivity.this, UpdateBiodataActivity.class);
        intent.putExtra("biodata", listDataItem);
        startActivity(intent);
    }

    @Override
    public void showMessageDeleteSuccess() {
        Toast.makeText(this, "Delete Success", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showMessageDeleteFailed() {
        Toast.makeText(this, "Delete Failed", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showMessageGetListFailed(String message) {
        Toast.makeText(this, "Get List Failed", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showMessageGetListSuccess() {
        Toast.makeText(this, "Get List Success", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showDeletion(final String id) {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Data")
                .setMessage("Apakah Anda Yakin ?")
                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.deleteBiodata(id);
                    }
                }).setNegativeButton("TIdak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getListBiodata();
    }
}