package com.mbarra.crudappbio.ListBiodata;

import com.mbarra.crudappbio.Model.ListDataItem;
import com.mbarra.crudappbio.Model.ResponseListData;

import java.util.List;

public interface ListBiodataContract {
    interface View {
        void showListBiodata(List<ListDataItem> biodata);

        void goToEditBiodata(ListDataItem listDataItem);

        void showMessageDeleteSuccess();

        void showMessageDeleteFailed();

        void showMessageGetListFailed(String message);

        void showMessageGetListSuccess();

        void showDeletion(String id);

    }

    interface Presenter {
        void getListBiodata();

        void deleteBiodata(String id);

        void goToEditBiodata(ListDataItem listDataItem1);

        void confirmDeletion(String id);
    }
}
