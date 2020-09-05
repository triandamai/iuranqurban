package com.tdn.qurban.nasabah.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tdn.data.Const;
import com.tdn.domain.model.TabunganModel;
import com.tdn.domain.model.UserModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.databinding.ItemTabunganBinding;
import com.tdn.qurban.nasabah.ui.tabungan.AdapterTabunganNasabah;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.tdn.data.Const.currency;

public class AdapterMyTabungan extends RecyclerView.Adapter<AdapterMyTabungan.MyViewHolder> {
    private List<TabunganModel> TabunganModels = new ArrayList<>();
    private Context context;
    private AdapterClicked adapterClicked;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public AdapterMyTabungan(Context context, AdapterClicked adapterClicked) {
        this.context = context;
        this.adapterClicked = adapterClicked;
    }

    @NonNull
    @Override
    public AdapterMyTabungan.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTabunganBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_tabungan, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMyTabungan.MyViewHolder holder, int position) {
        holder.binding.setAction(adapterClicked);

        holder.binding.tvTanggal.setText(TabunganModels.get(position).created_at_to_date());
        holder.binding.tvJumlah.setText(currency(TabunganModels.get(position).getNominal()));
        if (TabunganModels.get(position).getStatus().equalsIgnoreCase(Const.STATUS_NOTIF_TAMBAHSALDO_DITERIMA)) {
            holder.binding.tvStatus.setText("Sudah Di terima Oleh Admin");
        } else if (TabunganModels.get(position).getStatus().equalsIgnoreCase(Const.STATUS_NOTIF_TAMBAHSALDO_DITOLAK)) {
            holder.binding.tvStatus.setText("Ditolak");
        } else if (TabunganModels.get(position).getStatus().equalsIgnoreCase(Const.STATUS_NOTIF_TAMBAHSALDO_MENUNGGU)) {
            holder.binding.tvStatus.setText("Menunggu Konfirmasi");
        } else if (TabunganModels.get(position).getStatus().equalsIgnoreCase(Const.STATUS_NOTIF_TAMBAHSALDO_DITARIK)) {
            holder.binding.tvStatus.setText("Sudah Di Ambil");
        }
        holder.binding.setPosisi(position);

        databaseReference
                .child(Const.BASE_CHILD)
                .child(Const.CHILD_TABUNGAN)
                .child(firebaseAuth.getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            UserModel u = snapshot.getValue(UserModel.class);
                            u.setUid(snapshot.getKey());
                            holder.binding.tvNamauser.setText(u.getNama());
                        } else {

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    @Override
    public int getItemCount() {
        return TabunganModels == null ? 0 : TabunganModels.size();
    }

    public void setData(List<TabunganModel> TabunganModels) {
        if (this.TabunganModels == null) {
            this.TabunganModels.addAll(TabunganModels);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterMyTabungan.this.TabunganModels.size();
                }

                @Override
                public int getNewListSize() {
                    return TabunganModels.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterMyTabungan.this.TabunganModels.get(oldItemPosition).getId() == TabunganModels.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    TabunganModel lama = TabunganModels.get(oldItemPosition);
                    TabunganModel baru = TabunganModels.get(newItemPosition);

                    return lama == baru && Objects.equals(lama, baru);
                }
            });
            this.TabunganModels = TabunganModels;
            result.dispatchUpdatesTo(this);
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemTabunganBinding binding;

        public MyViewHolder(@NonNull ItemTabunganBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
