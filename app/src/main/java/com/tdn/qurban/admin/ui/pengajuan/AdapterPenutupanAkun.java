package com.tdn.qurban.admin.ui.pengajuan;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tdn.domain.model.TutupAkunModel;
import com.tdn.qurban.core.AdapterClicked;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterPenutupanAkun extends RecyclerView.Adapter<AdapterPenutupanAkun.MyViewHolder> {
    private List<TutupAkunModel> tutupAkunModels = new ArrayList<>();
    private AdapterClicked adapterClicked;

    public AdapterPenutupanAkun(AdapterClicked adapterClicked) {
        this.adapterClicked = adapterClicked;
    }

    @NonNull
    @Override
    public AdapterPenutupanAkun.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPenutupanAkun.MyViewHolder holder, int position) {

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
                    return AdapterPenutupanAkun.this.tutupAkunModels.get(oldItemPosition).getUid() == NotifikasiModels.get(newItemPosition).getUid();
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
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
