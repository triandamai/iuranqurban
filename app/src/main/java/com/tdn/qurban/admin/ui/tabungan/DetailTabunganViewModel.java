package com.tdn.qurban.admin.ui.tabungan;

import android.content.Context;

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
import com.tdn.data.pref.MyUser;
import com.tdn.domain.model.TabunganModel;

public class DetailTabunganViewModel extends ViewModel {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public LiveData<TabunganModel> tabunganModelLiveData;

    public DetailTabunganViewModel(Context context) {
        this.tabunganModelLiveData = getTabnganById(MyUser.getInstance(context).getLastIdTabungan());
    }

    public LiveData<TabunganModel> getTabnganById(String id) {
        final MutableLiveData<TabunganModel> tabungan = new MutableLiveData<>();
        databaseReference.child(Const.BASE_CHILD)
                .child(Const.CHILD_TABUNGAN)
                .child(id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            TabunganModel tabunganModel = snapshot.getValue(TabunganModel.class);
                            assert tabunganModel != null;
                            tabunganModel.setId(snapshot.getKey());
                            tabungan.setValue(tabunganModel);
                        } else {
                            tabungan.setValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        tabungan.setValue(null);
                    }
                });
        return tabungan;
    }
}