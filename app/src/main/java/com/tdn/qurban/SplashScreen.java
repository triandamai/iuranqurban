package com.tdn.qurban;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tdn.data.Const;
import com.tdn.domain.model.userModel;
import com.tdn.qurban.admin.AdminActivity;
import com.tdn.qurban.auth.LoginActivity;
import com.tdn.qurban.auth.RegistrasiActivity;
import com.tdn.qurban.databinding.ActivitySplashBinding;
import com.tdn.qurban.nasabah.NasabahActivity;

public class SplashScreen extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        if (firebaseUser != null) {
            databaseReference
                    .child(Const.BASE_CHILD)
                    .child(Const.CHILD_USER)
                    .child(firebaseUser.getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                userModel usermodel = new userModel();
                                usermodel.setUid(snapshot.getKey());
                                Log.e("hasil", snapshot.toString());

                                usermodel = snapshot.getValue(userModel.class);
                                usermodel.setUid(snapshot.getKey());
                                assert usermodel != null;
                                if (usermodel.getLevel().equals(Const.USER_LEVEL_NASABAH)) {
                                    Snackbar.make(binding.getRoot(), "Sync " + usermodel.getNama(), BaseTransientBottomBar.LENGTH_LONG).show();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            startActivity(new Intent(SplashScreen.this, NasabahActivity.class));
                                            finish();
                                        }
                                    }, 1000);
                                } else {
                                    Snackbar.make(binding.getRoot(), "Sync " + usermodel.getNama(), BaseTransientBottomBar.LENGTH_LONG).show();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            startActivity(new Intent(SplashScreen.this, AdminActivity.class));
                                            finish();
                                        }
                                    }, 1000);
                                }
                            } else {
                                Snackbar.make(binding.getRoot(), "User doesn't log in", BaseTransientBottomBar.LENGTH_LONG).show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                                        finish();
                                    }
                                }, 1000);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Snackbar.make(binding.getRoot(), "signInWithCredential:db failure", BaseTransientBottomBar.LENGTH_LONG).show();

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                                    finish();
                                }
                            }, 1000);
                        }
                    });

        } else {
            Snackbar.make(binding.getRoot(), "User doesn't log in", BaseTransientBottomBar.LENGTH_LONG).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    finish();
                }
            }, 1000);
        }
    }


}