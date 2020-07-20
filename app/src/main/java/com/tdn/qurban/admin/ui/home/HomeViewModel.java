package com.tdn.qurban.admin.ui.home;

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
import com.tdn.domain.model.notifikasiModel;
import com.tdn.domain.model.userModel;

import java.util.List;
import java.util.Objects;

public class HomeViewModel extends ViewModel {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Const.BASE_CHILD);
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private MutableLiveData<Integer> nasabahAktif;
    private MutableLiveData<Integer> nasabahNonAktif;
    private LiveData<List<notifikasiModel>> notifikasiTabungan;
    private ValueEventListener jumlahNasabahListener;
    private ValueEventListener notifikasiListener;


    public HomeViewModel() {
        nasabahAktif = new MutableLiveData<>();
        nasabahNonAktif = new MutableLiveData<>();
        notifikasiTabungan = new MutableLiveData<>();
        nasabahAktif.setValue(0);
        nasabahNonAktif.setValue(0);
        notifikasiTabungan.getValue().addAll(null);

        getMyHome();
    }

    public MutableLiveData<Integer> getNasabahAktif() {
        return nasabahAktif;
    }

    public MutableLiveData<Integer> getNasabahNonAktif() {
        return nasabahNonAktif;
    }

    public LiveData<List<notifikasiModel>> getNotifikasiTabungan() {
        return notifikasiTabungan;
    }

    private void getMyHome() {
        notifikasiListener = databaseReference
                .child(Const.CHILD_NOTIF_ADMIN).orderByChild("broad_to")
                .startAt(Const.USER_LEVEL_PANITIA)
                .endAt(Const.USER_LEVEL_ADMIN)
                .limitToFirst(8)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot s : snapshot.getChildren()) {
                                notifikasiModel m = s.getValue(notifikasiModel.class);
                                assert m != null;
                                m.setId(s.getKey());
                                Objects.requireNonNull(notifikasiTabungan.getValue()).add(m);
                            }
                        } else {
                            Objects.requireNonNull(notifikasiTabungan.getValue()).addAll(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Objects.requireNonNull(notifikasiTabungan.getValue()).addAll(null);
                    }
                });
        jumlahNasabahListener = databaseReference.child(Const.CHILD_USER)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            int a = 0;
                            int n = 0;
                            for (DataSnapshot s : snapshot.getChildren()) {
                                userModel m = s.getValue(userModel.class);
                                if (!m.getLevel().equals(Const.USER_LEVEL_ADMIN) || !m.getLevel().equals(Const.USER_LEVEL_PANITIA)) {
                                    if (m.getStatus().equals(Const.STATUS_USER_AKTIF)) {
                                        a = a + 1;
                                    } else {
                                        n = n + 1;
                                    }
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
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        databaseReference.removeEventListener(jumlahNasabahListener);
        databaseReference.removeEventListener(notifikasiListener);
    }
}