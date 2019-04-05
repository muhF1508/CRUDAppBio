package com.mbarra.crudappbio.AddBiodata;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.mbarra.crudappbio.CRUDAppBio;

import org.json.JSONException;
import org.json.JSONObject;

public class AddBiodataPresenter implements AddBiodataContract.Presenter {
    AddBiodataContract.View view;

    //    TODO jadi "this.view" itu menunjukkan jika view tersebut berada di kelas ini(AddBiodataPresenter), jika tidak dikasih maka dia akan bingung krn ada 2 view di dalam kelas tersebut


    AddBiodataPresenter(AddBiodataContract.View view) {
        this.view = view;
    }


    @Override
    public void addBiodata(String nama, String kelas, String email) {
        view.showProgressDialog();
        if (nama.length() > 0 && kelas.length() > 0 && email.length() > 0) {
//            Hit API Pakai FAN
            AndroidNetworking.post(CRUDAppBio.BASE_URL + "insertDataSiswa")
                    .setPriority(Priority.HIGH)
                    .addUrlEncodeFormBodyParameter("nama", nama)
                    .addUrlEncodeFormBodyParameter("kelas", kelas)
                    .addUrlEncodeFormBodyParameter("email", email)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getBoolean("status")) {
                                    view.addSuccess();
                                } else {
                                    Log.d("IDN","Email sudah ada");
                                    view.addFailed();
                                }
                            } catch (JSONException e) {
                                Log.d("IDN","Masuk Exception");
                                view.addFailed();
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
//                            untuk response yang lebih dari 200
                            Log.d("IDN","Masuk AN Error "+anError.toString());
                            view.addFailed();
                        }
                    });
        } else {
            view.showFormNotValid();
        }
    }
}
