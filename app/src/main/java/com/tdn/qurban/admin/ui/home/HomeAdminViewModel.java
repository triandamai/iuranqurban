package com.tdn.qurban.admin.ui.home;

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
import com.tdn.domain.model.UserModel;
import com.tdn.domain.model.NotifikasiModel;

import java.util.List;

public class HomeAdminViewModel extends ViewModel {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private MutableLiveData<Integer> nasabahAktif;
    private MutableLiveData<Integer> nasabahNonAktif;
    private MutableLiveData<UserModel> userModelMutableLiveData;
    private LiveData<List<NotifikasiModel>> notifikasiTabungan;


    public HomeAdminViewModel() {
        nasabahAktif = new MutableLiveData<>();
        nasabahNonAktif = new MutableLiveData<>();
        notifikasiTabungan = new MutableLiveData<>();
        userModelMutableLiveData = new MutableLiveData<>();

        getMyHome();
    }

    public MutableLiveData<Integer> getNasabahAktif() {
        return nasabahAktif;
    }

    public MutableLiveData<Integer> getNasabahNonAktif() {
        return nasabahNonAktif;
    }

    public MutableLiveData<UserModel> getUserModelMutableLiveData() {
        if (userModelMutableLiveData == null) {
            userModelMutableLiveData = new MutableLiveData<>();
        }
        return userModelMutableLiveData;
    }

    public LiveData<List<NotifikasiModel>> getNotifikasiTabungan() {
        if (notifikasiTabungan == null) {
            notifikasiTabungan = new MutableLiveData<>();
        }
        return notifikasiTabungan;
    }

    private void getMyHome() {
        databaseReference
                .child(Const.BASE_CHILD)
                .child(Const.CHILD_NOTIF_ADMIN).orderByChild("broad_to")
                .startAt(Const.USER_LEVEL_PANITIA)
                .endAt(Const.USER_LEVEL_ADMIN)
                .limitToFirst(8)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot s : snapshot.getChildren()) {
                                NotifikasiModel m = s.getValue(NotifikasiModel.class);
                                assert m != null;
                                m.setId(s.getKey());
                                notifikasiTabungan.getValue().add(m);
                            }
                        } else {
                            notifikasiTabungan = new MutableLiveData<>();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        notifikasiTabungan = new MutableLiveData<>();
                    }
                });
        databaseReference
                .child(Const.BASE_CHILD)
                .child(Const.CHILD_USER)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            int a = 0;
                            int n = 0;

                            for (DataSnapshot s : snapshot.getChildren()) {
                                UserModel m = s.getValue(UserModel.class);
                                if (!m.getLevel().equals(Const.USER_LEVEL_ADMIN) ||
                                        !m.getLevel().equals(Const.USER_LEVEL_PANITIA)) {
                                    if (m.getStatus().equals(Const.STATUS_USER_AKTIF)) {
                                        a = a + 1;
                                    } else {
                                        n = n + 1;
                                    }
                                } else {

                                }
                            }
                            nasabahAktif.setValue(a);
                            nasabahNonAktif.setValue(n);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        nasabahNonAktif.setValue(0);
                        nasabahAktif.setValue(0);
                    }
                });
        databaseReference.child(Const.BASE_CHILD).child(Const.CHILD_USER).child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            UserModel userModel = snapshot.getValue(UserModel.class);
                            userModel.setUid(snapshot.getKey());
                            Log.e("detail user", snapshot.toString());
                            userModelMutableLiveData.setValue(userModel);
                        } else {
                            userModelMutableLiveData = new MutableLiveData<>();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        userModelMutableLiveData = new MutableLiveData<>();
                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();

    }
}