package com.mbarra.crudappbio.ListBiodata;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.mbarra.crudappbio.CRUDAppBio;
import com.mbarra.crudappbio.Model.ListDataItem;
import com.mbarra.crudappbio.Model.ResponseListData;

import org.json.JSONException;
import org.json.JSONObject;

public class ListBiodataPresenter implements ListBiodataContract.Presenter {

    ListBiodataContract.View view;

    public ListBiodataPresenter(ListBiodataContract.View view) {
        this.view = view;
    }

    @Override
    public void getListBiodata() {
        AndroidNetworking.get(CRUDAppBio.BASE_URL + "getAllSiswa")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(ResponseListData.class, new ParsedRequestListener<ResponseListData>() {
                    @Override
                    public void onResponse(ResponseListData response) {
                        view.showListBiodata(response.getData());
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    @Override
    public void deleteBiodata(String id) {
        AndroidNetworking.post(CRUDAppBio.BASE_URL + "deleteDataSiswa")
                .setPriority(Priority.HIGH)
                .addBodyParameter("id", id)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        getListBiodata();
                        try {
                            if (response.getBoolean("status")) {
                                getListBiodata();
                                view.showMessageDeleteSuccess();
//                                kenapa try krn ada kemungkinan responnya bukan JsonObject
                            } else {
                                view.showMessageDeleteFailed();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        view.showMessageDeleteFailed();

                    }
                });

    }

    @Override
    public void goToEditBiodata(ListDataItem listDataItem1) {
        view.goToEditBiodata(listDataItem1);
    }

    @Override
    public void confirmDeletion(String id) {
        view.showDeletion(id);

    }

}
