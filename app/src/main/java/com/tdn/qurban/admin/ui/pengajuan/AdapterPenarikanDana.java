package com.tdn.qurban.admin.ui.pengajuan;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tdn.domain.model.NotifikasiModel;
import com.tdn.qurban.core.AdapterClicked;

import java.util.List;
import java.util.Objects;

public class AdapterPenarikanDana extends RecyclerView.Adapter<AdapterPenarikanDana.MyViewHolder> {
    private List<NotifikasiModel> NotifikasiModels;
    private AdapterClicked adapterClicked;

    public AdapterPenarikanDana(AdapterClicked adapterClicked) {
        this.adapterClicked = adapterClicked;
    }

    @NonNull
    @Override
    public AdapterPenarikanDana.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPenarikanDana.MyViewHolder holder, int position) {

    }

    public void setData(List<NotifikasiModel> NotifikasiModels) {
        if (this.NotifikasiModels == null) {
            this.NotifikasiModels.addAll(NotifikasiModels);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterPenarikanDana.this.NotifikasiModels.size();
                }

                @Override
                public int getNewListSize() {
                    return NotifikasiModels.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterPenarikanDana.this.NotifikasiModels.get(oldItemPosition).getId() == NotifikasiModels.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    NotifikasiModel lama = AdapterPenarikanDana.this.NotifikasiModels.get(oldItemPosition);
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
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
