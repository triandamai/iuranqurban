package com.tdn.qurban.nasabah.ui.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tdn.data.Const;
import com.tdn.domain.model.TabunganModel;
import com.tdn.domain.model.saldoModel;
import com.tdn.domain.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class HomeUserViewModel extends ViewModel {


    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public LiveData<List<TabunganModel>> listTabunganLiveData;
    public LiveData<Boolean> isUserActive;
    public LiveData<saldoModel> saldoModel;


    public HomeUserViewModel() {
        saldoModel = getSaldoDatas();
        isUserActive = getIsActive();
        listTabunganLiveData = getTabunganDatas();
    }


    public LiveData<saldoModel> getSaldoDatas() {
        final MutableLiveData<saldoModel> saldo = new MutableLiveData<>();
        databaseReference.child(Const.BASE_CHILD).child(Const.CHILD_SALDO).child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            saldoModel saldoModel = snapshot.getValue(saldoModel.class);
                            saldoModel.setUid(firebaseAuth.getCurrentUser().getUid());
                            assert saldoModel != null;
                            saldo.setValue(saldoModel);
                        } else {
                            saldo.setValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        saldo.setValue(null);
                    }
                });
        return saldo;
    }

    public LiveData<List<TabunganModel>> getTabunganDatas() {
        final MutableLiveData<List<TabunganModel>> listTabungan = new MutableLiveData<>();
        databaseReference.child(Const.BASE_CHILD)
                .child(Const.CHILD_TABUNGAN)
                .orderByChild(Const.CHILD_ORDERBYUID)
                .startAt(firebaseAuth.getCurrentUser().getUid())
                .endAt(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            List<TabunganModel> list = new ArrayList<>();
                            for (DataSnapshot data : snapshot.getChildren()) {
                                TabunganModel model = data.getValue(TabunganModel.class);
                                assert model != null;
                                model.setId(data.getKey());

                                list.add(model);
                            }
                            listTabungan.setValue(list);
                        } else {
                            listTabungan.setValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        listTabungan.setValue(null);
                    }
                });
        return listTabungan;
    }

    public LiveData<Boolean> getIsActive() {
        final MutableLiveData<Boolean> active = new MutableLiveData<>();
        databaseReference.child(Const.BASE_CHILD)
                .child(Const.CHILD_USER)
                .child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {
                            UserModel userModel = snapshot.getValue(UserModel.class);
                            assert userModel != null;
                            userModel.setUid(snapshot.getKey());

                            if (userModel.getStatus().equals(Const.STATUS_USER_AKTIF)) {
                                active.setValue(true);
                            } else {
                                active.setValue(false);
                            }
                        } else {
                            active.setValue(false);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        active.setValue(false);
                    }
                });
        return active;
    }


    @Override
    protected void onCleared() {
        super.onCleared();

    }


}