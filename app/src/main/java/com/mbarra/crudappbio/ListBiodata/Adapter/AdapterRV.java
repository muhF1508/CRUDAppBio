package com.mbarra.crudappbio.ListBiodata.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.mbarra.crudappbio.CRUDAppBio;
import com.mbarra.crudappbio.ListBiodata.ListBiodataActivity;
import com.mbarra.crudappbio.ListBiodata.ListBiodataContract;
import com.mbarra.crudappbio.ListBiodata.ListBiodataPresenter;
import com.mbarra.crudappbio.Model.ListDataItem;
import com.mbarra.crudappbio.R;
import com.mbarra.crudappbio.UpdateBiodata.UpdateBiodataActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AdapterRV extends RecyclerView.Adapter<AdapterRV.MyViewHolder> {

    private List<ListDataItem> listDataItems;
    private ListBiodataPresenter presenter;


    public AdapterRV(ListBiodataPresenter presenter, List<ListDataItem> listDataItems) {
        this.presenter = presenter;
        this.listDataItems = listDataItems;
    }

    @NonNull
    @Override
    public AdapterRV.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRV.MyViewHolder myViewHolder, final int i) {
        myViewHolder.nama.setText(listDataItems.get(i).getNamaSiswa());
        myViewHolder.kelas.setText(listDataItems.get(i).getKelasSiswa());
        myViewHolder.email.setText(listDataItems.get(i).getEmailSiswa());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goToEditBiodata(listDataItems.get(i));
            }
        });

        myViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.confirmDeletion(listDataItems.get(i).getIdSiswa());
            }
        });
//
//        myViewHolder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AndroidNetworking.post(CRUDAppBio.BASE_URL + "deleteDataSiswa")
//                        .setPriority(Priority.HIGH)
//                        .addUrlEncodeFormBodyParameter("id",listDataItems.get(i).getIdSiswa())
//                        .build()
//                        .getAsJSONObject(new JSONObjectRequestListener() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                try {
//                                    if (response.getBoolean("status")){
//                                        Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
//                                    } else {
//                                        Toast.makeText(context, "Data Gagal Dihapus", Toast.LENGTH_SHORT).show();
//                                    }
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                            @Override
//                            public void onError(ANError anError) {
//
//                            }
//                        });
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listDataItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nama, kelas, email;
        ImageButton delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.namal);
            kelas = itemView.findViewById(R.id.kelasl);
            email = itemView.findViewById(R.id.emaill);
            delete = itemView.findViewById(R.id.btn_delete);

        }
    }
}
