package com.tdn.qurban.admin.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tdn.data.Const;
import com.tdn.data.pref.MyUser;
import com.tdn.domain.model.NotifikasiModel;
import com.tdn.domain.model.UserModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.databinding.ItemNotifikasiBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterNotifikasiAdmin extends RecyclerView.Adapter<AdapterNotifikasiAdmin.MyViewHolder> {
    private List<NotifikasiModel> NotifikasiModels = new ArrayList<>();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private AdapterClicked adapterClicked;
    private ItemNotifikasiBinding binding;
    private Context context;

    public AdapterNotifikasiAdmin(Context context) {
        //this.adapterClicked = adapterClicked;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterNotifikasiAdmin.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_notifikasi, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNotifikasiAdmin.MyViewHolder holder, int position) {
        NotifikasiModel n = NotifikasiModels.get(position);
        binding.setData(NotifikasiModels.get(position));
        TextView v = binding.tvIsiNotifikasi;
        databaseReference.child(Const.BASE_CHILD)
                .child(Const.CHILD_USER)
                .child(n.getFrom_uid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            UserModel u = snapshot.getValue(UserModel.class);
                            assert u != null;
                            u.setUid(snapshot.getKey());

                            if (n.getTipe().equals(Const.TIPE_NOTIF_AJUKAN)) {
                                MyUser.getInstance(context).setLastIdNasabah(u.getUid());
                                binding.btnAksi.setOnClickListener(v1 -> {
                                    Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_detail_nasabah);
                                });
                                v.setText(u.getNama() + " Mengajukan Pengajuan Dana Status : " + n.getStatus());
                            } else if (n.getTipe().equals(Const.TIPE_NOTIF_TARIK)) {
                                binding.btnAksi.setOnClickListener(v1 -> {
                                    MyUser.getInstance(context).setLastIdNasabah(u.getUid());
                                });
                                v.setText(u.getNama() + " Mengajukan Penarikan Dana Status : " + n.getStatus());
                            } else if (n.getTipe().equals(Const.TIPE_NOTIF_AKTIVASI)) {
                                binding.btnAksi.setOnClickListener(v1 -> {
                                    MyUser.getInstance(context).setLastIdNasabah(u.getUid());
                                });
                                v.setText(u.getNama() + " Meminta persetujuan aktivasi akun Status " + n.getStatus());
                            } else if (n.getTipe().equals(Const.TIPE_NOTIF_TAMBAHSALDO)) {
                                binding.btnAksi.setOnClickListener(v1 -> {
                                    MyUser.getInstance(context).setLastIdNasabah(u.getUid());
                                    Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_tabungan);
                                });
                                v.setText(u.getNama() + " Menambahkan saldo ,Menunggu persetujuan");
                            } else {
                                v.setText("Notifikasi tidak diketahui");
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    public void setData(List<NotifikasiModel> NotifikasiModels) {
        if (this.NotifikasiModels == null) {
            this.NotifikasiModels.addAll(NotifikasiModels);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterNotifikasiAdmin.this.NotifikasiModels.size();
                }

                @Override
                public int getNewListSize() {
                    return NotifikasiModels.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterNotifikasiAdmin.this.NotifikasiModels.get(oldItemPosition).getId() == NotifikasiModels.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    NotifikasiModel lama = AdapterNotifikasiAdmin.this.NotifikasiModels.get(oldItemPosition);
                    NotifikasiModel baru = NotifikasiModels.get(newItemPosition);
                    return lama == baru && Objects.equals(lama, baru);
                }
            });
            this.NotifikasiModels = NotifikasiModels;
            result.dispatchUpdatesTo(this);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return NotifikasiModels == null ? 0 : NotifikasiModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemNotifikasiBinding binding;

        public MyViewHolder(@NonNull ItemNotifikasiBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
