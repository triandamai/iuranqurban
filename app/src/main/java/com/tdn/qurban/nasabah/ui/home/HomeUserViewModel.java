package com.tdn.qurban.nasabah.ui.home;

import androidx.annotation.NonNull;
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


    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Const.BASE_CHILD);
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private ValueEventListener tabungan;
    private ValueEventListener saldo;
    private ValueEventListener user;
    private MutableLiveData<List<TabunganModel>> tabunganDatas;
    private MutableLiveData<saldoModel> saldoDatas;
    private MutableLiveData<Boolean> isActive;

    public HomeUserViewModel() {
        getAllData();

    }


    public MutableLiveData<saldoModel> getSaldoDatas() {
        if (saldoDatas == null) {
            saldoDatas = new MutableLiveData<>();
        }
        return saldoDatas;
    }

    public MutableLiveData<List<TabunganModel>> getTabunganDatas() {
        if (tabunganDatas == null) {
            tabunganDatas = new MutableLiveData<>();
        }
        return tabunganDatas;
    }

    public MutableLiveData<Boolean> getIsActive() {
        if (isActive == null) {
            isActive = new MutableLiveData<>();
        }
        return isActive;
    }

    public void getAllData() {
        tabungan = databaseReference.child(Const.CHILD_TABUNGAN).child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    List<TabunganModel> models = new ArrayList<>();
                    for (DataSnapshot s : snapshot.getChildren()) {
                        TabunganModel tabunganModel = s.getValue(TabunganModel.class);
                        assert tabunganModel != null;
                        models.add(tabunganModel);
                    }

                    tabunganDatas.setValue(models);
                } else {
                    tabunganDatas.setValue(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                tabunganDatas.setValue(null);
            }
        });
        saldo = databaseReference.child(Const.CHILD_SALDO).child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    saldoModel saldoModel = snapshot.getValue(saldoModel.class);
                    assert saldoModel != null;
                    saldoDatas.setValue(saldoModel);
                } else {
                    saldoDatas.setValue(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                saldoDatas.setValue(null);
            }
        });
        user = databaseReference.child(Const.CHILD_USER).child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    UserModel userModel = snapshot.getValue(UserModel.class);
                    assert userModel != null;
                    if (userModel.getStatus().equals(Const.STATUS_USER_AKTIF)) {
                        isActive.setValue(true);
                    } else {
                        isActive.setValue(false);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                isActive.setValue(false);
            }
        });

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        databaseReference.removeEventListener(tabungan);
        databaseReference.removeEventListener(saldo);
        databaseReference.removeEventListener(user);
    }


}