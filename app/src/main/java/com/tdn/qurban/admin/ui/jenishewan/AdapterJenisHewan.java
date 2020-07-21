package com.tdn.qurban.admin.ui.jenishewan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tdn.domain.model.hewanModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.databinding.ItemHewanBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterJenisHewan extends RecyclerView.Adapter<AdapterJenisHewan.MyViewHolder> {
    private List<hewanModel> he = new ArrayList<>();
    private AdapterClicked adapterClicked;
    private ItemHewanBinding binding;
    private Context context;

    public AdapterJenisHewan(Context context, AdapterClicked adapterClicked) {
        this.adapterClicked = adapterClicked;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterJenisHewan.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_hewan, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterJenisHewan.MyViewHolder holder, int position) {
        Log.e("Data", he.toString());
        binding.tvHewan.setText(he.get(position).getJenis());
        binding.tvNominal.setText(he.get(position).getNominal());
    }

    public void setData(List<hewanModel> notifikasiModels) {
        if (this.he == null) {
            this.he.addAll(notifikasiModels);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterJenisHewan.this.he.size();
                }

                @Override
                public int getNewListSize() {
                    return notifikasiModels.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterJenisHewan.this.he.get(oldItemPosition).getId() == notifikasiModels.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    hewanModel lama = AdapterJenisHewan.this.he.get(oldItemPosition);
                    hewanModel baru = notifikasiModels.get(newItemPosition);
                    return lama == baru && Objects.equals(lama, baru);
                }
            });
            this.he = notifikasiModels;
            result.dispatchUpdatesTo(this);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return he == null ? 0 : he.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemHewanBinding binding;

        public MyViewHolder(@NonNull ItemHewanBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
