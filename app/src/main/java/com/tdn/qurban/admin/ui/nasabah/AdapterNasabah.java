package com.tdn.qurban.admin.ui.nasabah;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tdn.domain.model.hewanModel;
import com.tdn.domain.model.userModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.databinding.ItemNasabahBinding;

import java.util.List;
import java.util.Objects;

public class AdapterNasabah extends RecyclerView.Adapter<AdapterNasabah.MyViewHolder> {
    private List<userModel> he;
    private AdapterClicked adapterClicked;
    private ItemNasabahBinding binding;
    private Context context;

    public AdapterNasabah(Context context, AdapterClicked adapterClicked) {
        this.context = context;
        this.adapterClicked = adapterClicked;
    }

    @NonNull
    @Override
    public AdapterNasabah.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_nasabah, parent, false);
        binding.setOnClick(adapterClicked);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNasabah.MyViewHolder holder, int position) {
        holder.binding.setOnClick(adapterClicked);
        holder.binding.setPosisi(position);
        holder.binding.setData(he.get(position));
    }

    public void setData(List<userModel> notifikasiModels) {
        if (this.he == null) {
            this.he.addAll(notifikasiModels);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterNasabah.this.he.size();
                }

                @Override
                public int getNewListSize() {
                    return notifikasiModels.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterNasabah.this.he.get(oldItemPosition).getUid() == notifikasiModels.get(newItemPosition).getUid();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    userModel lama = AdapterNasabah.this.he.get(oldItemPosition);
                    userModel baru = notifikasiModels.get(newItemPosition);
                    return lama == baru && Objects.equals(lama, baru);
                }
            });
            this.he = notifikasiModels;
            result.dispatchUpdatesTo(this);
            notifyDataSetChanged();
        }
    }

    public userModel getFromPosition(int pos) {
        return he.get(pos);
    }

    @Override
    public int getItemCount() {
        return he == null ? 0 : he.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemNasabahBinding binding;

        public MyViewHolder(@NonNull ItemNasabahBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
