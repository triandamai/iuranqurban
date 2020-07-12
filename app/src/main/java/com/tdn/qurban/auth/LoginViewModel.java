package com.tdn.qurban.auth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tdn.data.Const;
import com.tdn.domain.model.userModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.ActionListener;
import com.tdn.qurban.core.AuthListener;

import java.util.concurrent.Executor;

public class LoginViewModel extends ViewModel {
    String TAG = LoginViewModel.this.getClass().getSimpleName();

    private AuthListener actionListener;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    @SuppressLint("StaticFieldLeak")
    private Context context;
    public LoginViewModel(Context context, AuthListener listener) {
        this.context = context;
        this.actionListener = listener;
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.firebaseAuth = FirebaseAuth.getInstance();

    }


    public void firebaseAuthWithGoogle(String idToken) {
        actionListener.onStart();
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if( user != null) {
                            databaseReference.child(Const.BASE_CHILD + "/" + Const.CHILD_USER + "/" + user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        userModel usermodel = snapshot.getValue(userModel.class);
                                        usermodel.setUid(snapshot.getKey());
                                        actionListener.onSuccess(Const.USER_ADA,usermodel);
                                    } else {
                                        actionListener.onSuccess(Const.USER_TIDAK_ADA, null);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    actionListener.onError("signInWithCredential:failure");
                                }
                            });
                        }else {
                            actionListener.onError("signInWithCredential:user not exist");
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        actionListener.onError("signInWithCredential:failure");
                    }
                });
    }

}
