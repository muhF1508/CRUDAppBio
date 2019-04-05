package com.mbarra.crudappbio.MainActivity;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private MainActivityContract.View view;

    MainActivityPresenter(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void seeAllBiodata() {
        view.goToListBiodata();
    }

    @Override
    public void addBiodata() {
        view.goToAddBiodata();
    }
}
