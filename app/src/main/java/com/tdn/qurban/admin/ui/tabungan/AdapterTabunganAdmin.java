package com.tdn.qurban.admin.ui.tabungan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterTabunganAdmin extends RecyclerView.Adapter<AdapterTabunganAdmin.MyViewHolder> {
    private List<TabunganModel> TabunganModels = new ArrayList<>();
    private AdapterClicked adapterClicked;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private Context context;

    public AdapterTabunganAdmin(Context context, AdapterClicked adapterClicked) {
        this.adapterClicked = adapterClicked;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterTabunganAdmin.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTabunganBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_tabungan, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTabunganAdmin.MyViewHolder holder, int position) {
        holder.binding.setData(TabunganModels.get(position));
        holder.binding.setAction(adapterClicked);
        holder.binding.tvTanggal.setText(TabunganModels.get(position).created_at_to_date());
        databaseReference.child(Const.BASE_CHILD)
                .child(Const.CHILD_USER)
                .child(TabunganModels.get(position).getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            UserModel u = snapshot.getValue(UserModel.class);
                            assert u != null;
                            holder.binding.tvNamauser.setText(u.getNama());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        holder.binding.setPosisi(position);
    }

    public void setData(List<TabunganModel> NotifikasiModels) {
        if (this.TabunganModels == null) {
            this.TabunganModels.addAll(NotifikasiModels);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterTabunganAdmin.this.TabunganModels.size();
                }

                @Override
                public int getNewListSize() {
                    return NotifikasiModels.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterTabunganAdmin.this.TabunganModels.get(oldItemPosition).getId() == NotifikasiModels.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    TabunganModel lama = AdapterTabunganAdmin.this.TabunganModels.get(oldItemPosition);
                    TabunganModel baru = NotifikasiModels.get(newItemPosition);
                    return lama == baru && Objects.equals(lama, baru);
                }
            });
            this.TabunganModels = NotifikasiModels;
            result.dispatchUpdatesTo(this);
            notifyDataSetChanged();
        }
    }

    public TabunganModel getFromPosition(int pos) {
        return TabunganModels.get(pos);
    }

    @Override
    public int getItemCount() {
        return TabunganModels == null ? 0 : TabunganModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemTabunganBinding binding;

        public MyViewHolder(@NonNull ItemTabunganBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
