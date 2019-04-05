package com.mbarra.crudappbio.AddBiodata;

public interface AddBiodataContract {

    interface View {

        void showProgressDialog();

        void addSuccess();

        void addFailed();

        void showFormNotValid();
    }

    interface Presenter {

        void addBiodata(String nama, String kelas, String email);
    }
}
