package com.mbarra.crudappbio.UpdateBiodata;

public interface UpdateBiodataContract {

    interface View {
        void showProgressDialog();

        void updateSuccess();

        void updateFailed();
    }

    interface Presenter {
        void updateBiodata(String id, String nama, String kelas, String email);
    }
}
