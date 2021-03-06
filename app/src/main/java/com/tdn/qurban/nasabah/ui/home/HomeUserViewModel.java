package com.tdn.qurban.nasabah.ui.home;

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
import com.tdn.domain.model.SaldoModel;
import com.tdn.domain.model.TabunganModel;
import com.tdn.domain.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class HomeUserViewModel extends ViewModel {


    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public LiveData<List<TabunganModel>> listTabunganLiveData;
    public LiveData<Boolean> isUserActive;
    public LiveData<SaldoModel> saldoModel;
    public LiveData<UserModel> userModel;


    public HomeUserViewModel() {
        saldoModel = getSaldoDatas();
        isUserActive = getIsActive();
        listTabunganLiveData = getTabunganDatas();
        userModel = getUser();
    }

    private LiveData<UserModel> getUser() {
        final MutableLiveData<UserModel> userModelMutableLiveData = new MutableLiveData<>();
        databaseReference
                .child(Const.BASE_CHILD)
                .child(Const.CHILD_USER)
                .child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            UserModel userModel = snapshot.getValue(UserModel.class);
                            assert userModel != null;
                            userModel.setUid(snapshot.getKey());
                            userModelMutableLiveData.setValue(userModel);
                        } else {
                            userModelMutableLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        userModelMutableLiveData.setValue(null);
                    }
                });
        return userModelMutableLiveData;
    }


    public LiveData<SaldoModel> getSaldoDatas() {
        final MutableLiveData<SaldoModel> saldo = new MutableLiveData<>();
        databaseReference
                .child(Const.BASE_CHILD)
                .child(Const.CHILD_SALDO)
                .child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            SaldoModel saldoModel = snapshot.getValue(SaldoModel.class);
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
                .child(firebaseAuth.getCurrentUser().getUid())
                .orderByChild(Const.CHILD_ORDERBYCREATEDAT)
                .limitToLast(3)
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