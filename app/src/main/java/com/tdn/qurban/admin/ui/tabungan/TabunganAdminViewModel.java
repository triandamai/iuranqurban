package com.tdn.qurban.admin.ui.tabungan;

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
import com.tdn.data.pref.MyUser;
import com.tdn.domain.model.TabunganModel;

import java.util.ArrayList;
import java.util.List;

public class TabunganAdminViewModel extends ViewModel {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    public LiveData<List<TabunganModel>> tabunganData;
    private Context context;

    public TabunganAdminViewModel(Context context) {
        this.tabunganData = getTabunganData();
        this.context = context;

    }

    public LiveData<List<TabunganModel>> getTabunganData() {
        final MutableLiveData<List<TabunganModel>> tabungan = new MutableLiveData<>();
        databaseReference.child(Const.BASE_CHILD).child(Const.CHILD_TABUNGAN)
                .child(MyUser.getInstance(context).getLastIdNasabah())
                .orderByChild(Const.CHILD_ORDERBYTIME)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            List<TabunganModel> tabunganModels = new ArrayList<>();
                            for (DataSnapshot data : snapshot.getChildren()) {
                                TabunganModel model = data.getValue(TabunganModel.class);
                                assert model != null;
                                model.setId(data.getKey());
                                tabunganModels.add(model);
                            }
                            tabungan.setValue(tabunganModels);
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