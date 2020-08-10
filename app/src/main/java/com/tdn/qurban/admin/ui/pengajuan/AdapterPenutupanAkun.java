package com.tdn.qurban.admin.ui.pengajuan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tdn.domain.model.TutupAkunModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.databinding.ItemTutupakunBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterPenutupanAkun extends RecyclerView.Adapter<AdapterPenutupanAkun.MyViewHolder> {
    private List<TutupAkunModel> tutupAkunModels = new ArrayList<>();
    private AdapterClicked adapterClicked;
    private Context context;

    public AdapterPenutupanAkun(AdapterClicked adapterClicked, Context context) {
        this.adapterClicked = adapterClicked;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterPenutupanAkun.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTutupakunBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_tutupakun, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPenutupanAkun.MyViewHolder holder, int position) {
        holder.binding.setAction(adapterClicked);
        holder.binding.setPosisi(position);
        holder.binding.tvNama.setText("");
        holder.binding.tvDetail.setText("Mengajuan Penutupan Akun");
        holder.binding.tvStatus.setText("");
    }

    public void setData(List<TutupAkunModel> NotifikasiModels) {
        if (this.tutupAkunModels == null) {
            this.tutupAkunModels.addAll(NotifikasiModels);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterPenutupanAkun.this.tutupAkunModels.size();
                }

                @Override
                public int getNewListSize() {
                    return NotifikasiModels.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterPenutupanAkun.this.tutupAkunModels.get(oldItemPosition).getId() == NotifikasiModels.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    TutupAkunModel lama = AdapterPenutupanAkun.this.tutupAkunModels.get(oldItemPosition);
                    TutupAkunModel baru = NotifikasiModels.get(newItemPosition);
                    return lama == baru && Objects.equals(lama, baru);
                }
            });
            this.tutupAkunModels = NotifikasiModels;
            result.dispatchUpdatesTo(this);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return tutupAkunModels == null ? 0 : tutupAkunModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemTutupakunBinding binding;

        public MyViewHolder(ItemTutupakunBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
