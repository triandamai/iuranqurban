package com.tdn.qurban.nasabah.ui.profil;

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
import com.tdn.domain.model.UserModel;

public class ProfilNasabahViewModel extends ViewModel {
    public LiveData<UserModel> userModelLiveData;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public ProfilNasabahViewModel() {
        this.userModelLiveData = getProfil();
    }

    private LiveData<UserModel> getProfil() {
        final MutableLiveData<UserModel> userModelMutableLiveData = new MutableLiveData<>();
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
}