package com.tdn.qurban.admin.ui.tabungan;

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

import java.util.ArrayList;
import java.util.List;

public class ListTabunganViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public ListTabunganViewModel() {
        getListtab();
    }

    public LiveData<List<String>> getListtab() {
        final MutableLiveData<List<String>> data = new MutableLiveData<>();
        databaseReference
                .child(Const.BASE_CHILD)
                .child(Const.CHILD_TABUNGAN)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            List<String> s = new ArrayList<>();
                            for (DataSnapshot d : snapshot.getChildren()) {
                                String val = d.getKey();
                                s.add(val);
                            }
                            data.setValue(s);
                        } else {
                            data.setValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}