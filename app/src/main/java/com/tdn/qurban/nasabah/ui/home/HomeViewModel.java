package com.tdn.qurban.nasabah.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tdn.data.Const;
import com.tdn.data.repository.FirebaseDatabaseRepository;
import com.tdn.data.repository.tabunganRepository;
import com.tdn.domain.model.tabunganModel;

import java.util.List;

public class HomeViewModel extends ViewModel {


    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Const.BASE_CHILD);
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private tabunganRepository repo;
    private MutableLiveData<List<tabunganModel>> tabunganDatas;

    public HomeViewModel() {
        getMyTabungan();
    }

    public MutableLiveData<List<tabunganModel>> getTabunganDatas() {
        if (tabunganDatas == null) {
            tabunganDatas = new MutableLiveData<>();
        }
        return tabunganDatas;
    }

    public void getMyTabungan() {
        databaseReference.child(Const.CHILD_TABUNGAN).child(firebaseAuth.getCurrentUser().getUid());
        repo = new tabunganRepository(databaseReference);
        repo.addListener(new FirebaseDatabaseRepository.FirebaseDatabaseRepositoryCallback<tabunganModel>() {
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
        repo.removeListener();
    }
}