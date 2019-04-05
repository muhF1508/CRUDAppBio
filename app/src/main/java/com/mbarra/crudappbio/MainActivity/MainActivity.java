package com.mbarra.crudappbio.MainActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mbarra.crudappbio.AddBiodata.AddBiodataActivity;
import com.mbarra.crudappbio.ListBiodata.ListBiodataActivity;
import com.mbarra.crudappbio.R;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View{
    private Button btnListBiodata, btnAddBiodata;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnListBiodata = findViewById(R.id.btn_Alldata);
        btnAddBiodata = findViewById(R.id.btn_AddData);
        presenter = new MainActivityPresenter(this);

        btnListBiodata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.seeAllBiodata();
            }
        });

        btnAddBiodata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addBiodata();
            }
        });


    }
    @Override
    public void goToListBiodata() {
        Intent goToListBiodata = new Intent(MainActivity.this, ListBiodataActivity.class);
        startActivity(goToListBiodata);

    }

    @Override
    public void goToAddBiodata() {
        Intent goToAddBiodata = new Intent(MainActivity.this, AddBiodataActivity.class);
        startActivity(goToAddBiodata);

    }
}
