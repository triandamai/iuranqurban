package com.tdn.qurban.nasabah.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tdn.data.Const;
import com.tdn.data.repository.FirebaseDatabaseRepository;
import com.tdn.data.repository.saldoRepository;
import com.tdn.data.repository.tabunganRepository;
import com.tdn.data.repository.userRepository;
import com.tdn.domain.model.saldoModel;
import com.tdn.domain.model.tabunganModel;
import com.tdn.domain.model.userModel;

import java.util.List;

public class HomeViewModel extends ViewModel {


    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Const.BASE_CHILD);
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private tabunganRepository repotabungan;
    private saldoRepository reposaldo;
    private userRepository userRepository;
    private MutableLiveData<List<tabunganModel>> tabunganDatas;
    private MutableLiveData<List<saldoModel>> saldoDatas;
    private MutableLiveData<Boolean> isActive;

    public HomeViewModel() {
        getMyTabungan();
        getMySaldo();
        cekMyAccount();
    }

    private void cekMyAccount() {
        databaseReference.child(Const.CHILD_USER)
                .child(firebaseAuth.getCurrentUser().getUid());
        userRepository = new userRepository(databaseReference);
        userRepository.addListener(new FirebaseDatabaseRepository.FirebaseDatabaseRepositoryCallback<userModel>() {
            @Override
            public void onSuccess(List<userModel> result) {
                if (result != null && result.size() > 0) {
                    if (result.get(0).getStatus().equals(Const.STATUS_USER_AKTIF)) {
                        isActive.setValue(true);
                    } else {
                        isActive.setValue(true);
                    }
                } else {
                    isActive.setValue(true);
                }
            }

            @Override
            public void onError(Exception e) {
                isActive.setValue(true);
            }
        });
    }

    private void getMySaldo() {
        databaseReference.child(Const.CHILD_SALDO)
                .child(firebaseAuth.getCurrentUser().getUid());
        reposaldo = new saldoRepository(databaseReference);
        reposaldo.addListener(new FirebaseDatabaseRepository.FirebaseDatabaseRepositoryCallback<saldoModel>() {
            @Override
            public void onSuccess(List<saldoModel> result) {
                saldoDatas.setValue(result);
            }

            @Override
            public void onError(Exception e) {
                saldoDatas.setValue(null);
            }
        });

    }

    public MutableLiveData<List<saldoModel>> getSaldoDatas() {
        if (saldoDatas == null) {
            saldoDatas = new MutableLiveData<>();
        }
        return saldoDatas;
    }

    public MutableLiveData<List<tabunganModel>> getTabunganDatas() {
        if (tabunganDatas == null) {
            tabunganDatas = new MutableLiveData<>();
        }
        return tabunganDatas;
    }

    public MutableLiveData<Boolean> getIsActive() {
        return isActive;
    }

    public void getMyTabungan() {
        databaseReference.child(Const.CHILD_TABUNGAN).child(firebaseAuth.getCurrentUser().getUid());
        repotabungan = new tabunganRepository(databaseReference);
        repotabungan.addListener(new FirebaseDatabaseRepository.FirebaseDatabaseRepositoryCallback<tabunganModel>() {
            @Override
            public void onSuccess(List<tabunganModel> result) {
                tabunganDatas.setValue(result);
            }

            @Override
            public void onError(Exception e) {
                tabunganDatas.setValue(null);
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repotabungan.removeListener();
    }
}