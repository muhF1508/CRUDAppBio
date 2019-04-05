package com.mbarra.crudappbio.UpdateBiodata;

import android.util.Patterns;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.mbarra.crudappbio.CRUDAppBio;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class UpdateBiodataPresenter implements UpdateBiodataContract.Presenter {
    private UpdateBiodataContract.View view;

    public UpdateBiodataPresenter(UpdateBiodataContract.View view) {
        this.view = view;
    }

    @Override
    public void updateBiodata(String id, String nama, String kelas, String email) {
        if (id.length() > 0 && nama.length() > 0 && kelas.length() > 0 && email.length() > 0 && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.showProgressDialog();
            AndroidNetworking.post(CRUDAppBio.BASE_URL + "updateDataSiswa")
                    .setPriority(Priority.HIGH)
                    .addUrlEncodeFormBodyParameter("id", id)
                    .addUrlEncodeFormBodyParameter("nama", nama)
                    .addUrlEncodeFormBodyParameter("kelas", kelas)
                    .addUrlEncodeFormBodyParameter("email", email)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
//                            jika respon codenya 200, jika 200 dan turunannya seperti 201-299 masuknya onResponse
                            try {
                                if (response.getBoolean("status")) {
                                    view.updateSuccess();
                                } else {
                                    view.updateFailed();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
//                            selain 200 respon codenya
                            view.updateFailed();

                        }
                    });

        }
    }
}
