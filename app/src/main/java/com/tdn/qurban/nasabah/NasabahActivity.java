package com.tdn.qurban.nasabah;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tdn.data.Const;
import com.tdn.data.pref.MyUser;
import com.tdn.domain.model.UserModel;
import com.tdn.qurban.R;
import com.tdn.qurban.admin.AdminActivity;
import com.tdn.qurban.auth.LoginActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class NasabahActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private FloatingActionButton fab;
    private NavController navController;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasabah);

        fab = findViewById(R.id.fab);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_aktivasiakun,
                R.id.nav_konfirmasipembayaran,
                R.id.nav_notifikasi,
                R.id.nav_penarikan,
                R.id.nav_penutupan,
                R.id.nav_profil,
                R.id.nav_tentang)
                .setOpenableLayout(drawer)
                .setFallbackOnNavigateUpListener(() -> {
                            if (MyUser.getInstance(this).getAktif()) {
                                NavigationUI.navigateUp(navController, mAppBarConfiguration);
                                return true;
                            } else {
                                Snackbar.make(fab, "Akun Anda Belum Aktif!", BaseTransientBottomBar.LENGTH_LONG).show();
                                return false;
                            }
                        }
                )
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        getStatus();
        cekFAB();
        onClick();
    }

    private void getStatus() {
        databaseReference
                .child(Const.BASE_CHILD)
                .child(Const.CHILD_USER)
                .child(firebaseAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {
                            UserModel s = snapshot.getValue(UserModel.class);
                            assert s != null;
                            s.setUid(firebaseAuth.getCurrentUser().getUid());

                            if (s.getStatus().equalsIgnoreCase(Const.STATUS_USER_NONAKTIF) ||
                                    s.getStatus().equalsIgnoreCase(Const.STATUS_USER_PENDING)) {
//                                fab.setVisibility(View.GONE);
                                MyUser.getInstance(NasabahActivity.this).setAktif(false);

                            } else {
                                MyUser.getInstance(NasabahActivity.this).setAktif(true);
//                                fab.setVisibility(View.VISIBLE);

                            }
                        } else {
                            MyUser.getInstance(NasabahActivity.this).setAktif(false);
                            fab.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        MyUser.getInstance(NasabahActivity.this).setAktif(false);
                        //fab.setVisibility(View.GONE);
                    }
                });

    }

    private void onClick() {
        fab.setOnClickListener(v -> {
            if (MyUser.getInstance(this).getAktif()) {
                navController.navigate(R.id.nav_konfirmasipembayaran);
            } else {
                Snackbar.make(fab, "Akun Anda Belum Aktif!", BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });
    }

    private void cekFAB() {
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            int id = destination.getId();
            if (id == R.id.nav_aktivasiakun ||
                    id == R.id.nav_konfirmasipembayaran) {
                fab.setVisibility(View.GONE);
            } else if (id == R.id.nav_home || id == R.id.nav_tabungan) {
                fab.setVisibility(View.VISIBLE);
                fab.setOnClickListener(view -> navController.navigate(R.id.nav_konfirmasipembayaran));
            } else {
                fab.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appbar_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_keluar:
                firebaseAuth.signOut();
                startActivity(new Intent(NasabahActivity.this, LoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        if (MyUser.getInstance(NasabahActivity.this).getAktif()) {
            return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                    || super.onSupportNavigateUp();
        } else {
            Snackbar.make(fab, "Akun Anda Belum Aktif", BaseTransientBottomBar.LENGTH_INDEFINITE).show();
            return false;
        }
    }
}