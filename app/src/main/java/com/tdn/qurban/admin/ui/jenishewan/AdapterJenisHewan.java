package com.tdn.qurban.admin.ui.jenishewan;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tdn.domain.model.hewanModel;
import com.tdn.domain.model.notifikasiModel;
import com.tdn.qurban.core.AdapterClicked;

import java.util.List;
import java.util.Objects;

public class AdapterJenisHewan extends RecyclerView.Adapter<AdapterJenisHewan.MyViewHolder> {
    private List<hewanModel> he;
    private AdapterClicked adapterClicked;

    public AdapterJenisHewan(AdapterClicked adapterClicked) {
        this.adapterClicked = adapterClicked;
    }

    @NonNull
    @Override
    public AdapterJenisHewan.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterJenisHewan.MyViewHolder holder, int position) {

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
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
