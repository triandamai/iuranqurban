package com.tdn.qurban.admin.ui.profil;

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
import com.tdn.domain.model.userModel;

public class ProfilViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Const.BASE_CHILD);
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private MutableLiveData<userModel> userModelMutableLiveData;
    private ValueEventListener profilListener;

    public ProfilViewModel() {
        getMyProfil();
    }

    private void getMyProfil() {
        profilListener = databaseReference.child(Const.CHILD_USER)
                .child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        databaseReference.addValueEventListener(profilListener);
    }

    public MutableLiveData<userModel> getUserModelMutableLiveData() {
        if (userModelMutableLiveData == null) {
            userModelMutableLiveData = new MutableLiveData<>();
        }
        return userModelMutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        databaseReference.removeEventListener(profilListener);
    }
}