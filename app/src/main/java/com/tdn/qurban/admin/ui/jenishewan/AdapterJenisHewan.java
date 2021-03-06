package com.tdn.qurban.admin.ui.jenishewan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tdn.domain.model.HewanModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterActionClicked;
import com.tdn.qurban.databinding.ItemHewanBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterJenisHewan extends RecyclerView.Adapter<AdapterJenisHewan.MyViewHolder> {
    private List<HewanModel> he = new ArrayList<>();
    private AdapterActionClicked adapterClicked;

    private Context context;

    public AdapterJenisHewan(Context context, AdapterActionClicked adapterClicked) {
        this.adapterClicked = adapterClicked;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterJenisHewan.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHewanBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_hewan, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterJenisHewan.MyViewHolder holder, int position) {

        holder.binding.tvNama.setText(he.get(position).getJenis());
        holder.binding.tvNominal.setText(he.get(position).getNominal());
        holder.binding.delete.setOnClickListener(v -> {
            adapterClicked.onDelete(position);
        });
        holder.binding.edit.setOnClickListener(v -> {
            adapterClicked.onEdit(position);
        });

    }

    public HewanModel getFromPosition(int pos) {
        return he.get(pos);
    }

    public void setData(List<HewanModel> notifikasiModels) {
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
                    HewanModel lama = AdapterJenisHewan.this.he.get(oldItemPosition);
                    HewanModel baru = notifikasiModels.get(newItemPosition);
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
