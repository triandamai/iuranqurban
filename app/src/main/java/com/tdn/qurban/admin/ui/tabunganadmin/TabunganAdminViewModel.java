package com.tdn.qurban.admin.ui.tabunganadmin;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tdn.data.Const;
import com.tdn.domain.model.TabunganModel;

import java.util.ArrayList;
import java.util.List;

public class TabunganAdminViewModel extends ViewModel {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    // TODO: Implement the ViewModel
    public LiveData<List<TabunganModel>> getTabunganACC() {
        final MutableLiveData<List<TabunganModel>> tabunganModel = new MutableLiveData<>();
        databaseReference.child(Const.BASE_CHILD).child(Const.CHILD_TABUNGAN)
                .orderByChild(Const.CHILD_ORDERBYSTATUS)
                .equalTo(Const.STATUS_NOTIF_TAMBAHSALDO_DITERIMA)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            List<TabunganModel> hewanModelList = new ArrayList<>();
                            for (DataSnapshot data : snapshot.getChildren()) {

                                TabunganModel hewanModel = data.getValue(TabunganModel.class);
                                hewanModel.setId(data.getKey());

                                hewanModelList.add(hewanModel);


                            }
                            tabunganModel.setValue(hewanModelList);

                        } else {
                            tabunganModel.setValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        tabunganModel.setValue(null);
                    }
                });
        return tabunganModel;
    }

    public LiveData<List<TabunganModel>> getTabunganMENUNGGU() {
        final MutableLiveData<List<TabunganModel>> tabunganModel = new MutableLiveData<>();
        databaseReference.child(Const.BASE_CHILD).child(Const.CHILD_TABUNGAN)
                .orderByChild(Const.CHILD_ORDERBYSTATUS)
                .equalTo(Const.STATUS_NOTIF_TAMBAHSALDO_MENUNGGU)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            List<TabunganModel> hewanModelList = new ArrayList<>();
                            for (DataSnapshot data : snapshot.getChildren()) {

                                TabunganModel hewanModel = data.getValue(TabunganModel.class);
                                hewanModel.setId(data.getKey());

                                hewanModelList.add(hewanModel);


                            }
                            tabunganModel.setValue(hewanModelList);

                        } else {
                            tabunganModel.setValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        tabunganModel.setValue(null);
                    }
                });
        return tabunganModel;
    }

    public LiveData<List<TabunganModel>> getTabunganTOLAK() {
        final MutableLiveData<List<TabunganModel>> tabunganModel = new MutableLiveData<>();
        databaseReference.child(Const.BASE_CHILD).child(Const.CHILD_TABUNGAN)
                .orderByChild(Const.CHILD_ORDERBYSTATUS)
                .equalTo(Const.STATUS_NOTIF_TAMBAHSALDO_DITOLAK)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            List<TabunganModel> hewanModelList = new ArrayList<>();
                            for (DataSnapshot data : snapshot.getChildren()) {

                                TabunganModel hewanModel = data.getValue(TabunganModel.class);
                                hewanModel.setId(data.getKey());

                                hewanModelList.add(hewanModel);


                            }
                            tabunganModel.setValue(hewanModelList);

                        } else {
                            tabunganModel.setValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        tabunganModel.setValue(null);
                    }
                });
        return tabunganModel;
    }
}