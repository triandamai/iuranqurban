package com.tdn.qurban.nasabah.ui.notifikasi;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tdn.domain.model.notifikasiModel;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.nasabah.ui.home.AdapterMyTabungan;

import java.util.List;
import java.util.Objects;

public class AdapterNotifikasi extends RecyclerView.Adapter<AdapterMyTabungan.MyViewHolder> {
    private List<notifikasiModel> notifikasiModels;
    private AdapterClicked adapterClicked;

    public AdapterNotifikasi(AdapterClicked adapterClicked) {
        this.adapterClicked = adapterClicked;
    }

    @NonNull
    @Override
    public AdapterMyTabungan.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMyTabungan.MyViewHolder holder, int position) {

    }

    public void setData(List<notifikasiModel> notifikasiModels) {
        if (this.notifikasiModels == null) {
            this.notifikasiModels.addAll(notifikasiModels);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterNotifikasi.this.notifikasiModels.size();
                }

                @Override
                public int getNewListSize() {
                    return notifikasiModels.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterNotifikasi.this.notifikasiModels.get(oldItemPosition).getId() == notifikasiModels.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    notifikasiModel lama = AdapterNotifikasi.this.notifikasiModels.get(oldItemPosition);
                    notifikasiModel baru = notifikasiModels.get(newItemPosition);
                    return lama == baru && Objects.equals(lama, baru);
                }
            });
            this.notifikasiModels = notifikasiModels;
            result.dispatchUpdatesTo(this);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return notifikasiModels == null ? 0 : notifikasiModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
