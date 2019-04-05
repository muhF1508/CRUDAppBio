package com.mbarra.crudappbio.MainActivity;

public interface MainActivityContract {

    interface View {

        void goToListBiodata();

        void goToAddBiodata();
    }

    interface Presenter {

        void seeAllBiodata();

        void addBiodata();

    }

}
