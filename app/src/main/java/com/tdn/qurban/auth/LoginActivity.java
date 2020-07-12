package com.tdn.qurban.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.tdn.data.Const;
import com.tdn.domain.model.userModel;
import com.tdn.qurban.R;
import com.tdn.qurban.admin.AdminActivity;
import com.tdn.qurban.core.ActionListener;
import com.tdn.qurban.core.AuthListener;
import com.tdn.qurban.core.VMFactory;
import com.tdn.qurban.databinding.ActivityLoginBinding;
import com.tdn.qurban.nasabah.NasabahActivity;

public class LoginActivity extends AppCompatActivity {
    public String TAG = LoginActivity.this.getClass().getName();
    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        viewModel = new ViewModelProvider(this,new VMFactory(getApplicationContext(),actionListener)).get(LoginViewModel.class);
        this.gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        this.gsc = GoogleSignIn.getClient(this,gso);
        onClick();
    }

    private void onClick() {
        binding.btnMasuk.setOnClickListener(v -> {
            signIn();
        });
        binding.tvDaftar.setOnClickListener(v -> {
            startActivity(new Intent(this,DaftarActivity.class));
        });
    }

    private void signIn() {
        Intent signInIntent = gsc.getSignInIntent();

        startActivityForResult(signInIntent, Const.REQ_SIGN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == Const.REQ_SIGN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                viewModel.firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }

    }
    private AuthListener actionListener = new AuthListener() {
        @Override
        public void onStart() {
            Snackbar.make(binding.getRoot(),"Proses..", BaseTransientBottomBar.LENGTH_LONG).show();
        }

        @Override
        public void onSuccess(@NonNull String message, userModel data) {
            Snackbar.make(binding.getRoot(),"signInWithCredential:success", BaseTransientBottomBar.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(message.equals(Const.USER_ADA)){
                        if(data.getLevel().equals(Const.USER_LEVEL_ADMIN) || data.getLevel().equals(Const.USER_LEVEL_PANITIA)){
                            startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                            finish();
                        }else {
                            startActivity(new Intent(LoginActivity.this, NasabahActivity.class));
                            finish();
                        }
                    }else {
                        startActivity(new Intent(LoginActivity.this, RegistrasiActivity.class));
                        finish();
                    }
                }
            },2000);

        }



        @Override
        public void onError(@NonNull String message) {
            Snackbar.make(binding.getRoot(),message, BaseTransientBottomBar.LENGTH_LONG).show();
        }
    };
}