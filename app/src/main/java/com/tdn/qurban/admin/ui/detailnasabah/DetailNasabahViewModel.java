package com.tdn.qurban.admin.ui.detailnasabah;

import android.content.Context;

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
import com.tdn.domain.model.SaldoModel;
import com.tdn.domain.model.rencanaModel;
import com.tdn.domain.model.UserModel;

public class DetailNasabahViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private LiveData<UserModel> detailUsers;
    private LiveData<rencanaModel> rencanaModelMutableLiveData;
    private LiveData<SaldoModel> saldoModelLiveData;

    public DetailNasabahViewModel(Context context, String id) {

        if (id != null) {
            this.detailUsers = getDetailUsers(id);
            this.rencanaModelMutableLiveData = getRencanaModelMutableLiveData(id);
            this.saldoModelLiveData = getSaldos(id);
        }
    }

    public LiveData<SaldoModel> getSaldos(String id) {
        final MutableLiveData<SaldoModel> result = new MutableLiveData<>();
        databaseReference
                .child(Const.BASE_CHILD)
                .child(Const.CHILD_SALDO)
                .child(id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            SaldoModel saldoModel = snapshot.getValue(SaldoModel.class);
                            assert saldoModel != null;
                            saldoModel.setUid(snapshot.getKey());
                            result.setValue(saldoModel);
                        } else {
                            result.setValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        result.setValue(null);
                    }
                });
        return result;
    }


    public LiveData<UserModel> getDetailUsers(String id) {
        final MutableLiveData<UserModel> user = new MutableLiveData<>();
        databaseReference
                .child(Const.BASE_CHILD)
                .child(Const.CHILD_USER)
                .child(id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            UserModel userModel = snapshot.getValue(UserModel.class);
                            user.setValue(userModel);
                        } else {
                            user.setValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        user.setValue(null);
                    }
                });
        return user;
    }

    public LiveData<rencanaModel> getRencanaModelMutableLiveData(String id) {
        final MutableLiveData<rencanaModel> rencana = new MutableLiveData<>();
        databaseReference.child(Const.BASE_CHILD)
                .child(Const.CHILD_RENCANA)
                .child(id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            rencanaModel rencanaModel = snapshot.getValue(rencanaModel.class);
                            rencanaModel.setUid(snapshot.getKey());
                            rencana.setValue(rencanaModel);
                        } else {
                            rencana.setValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        rencana.setValue(null);

                    }
                });
        return rencana;
    }


}