package com.tdn.qurban.nasabah.ui.tabungan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tdn.data.Const;
import com.tdn.domain.model.TabunganModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.databinding.ItemTabunganBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterTabunganNasabah extends RecyclerView.Adapter<AdapterTabunganNasabah.MyViewHolder> {
    private List<TabunganModel> TabunganModels = new ArrayList<>();
    private Context context;
    private AdapterClicked adapterClicked;

    public AdapterTabunganNasabah(Context context, AdapterClicked adapterClicked) {
        this.context = context;
        this.adapterClicked = adapterClicked;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTabunganBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_tabungan, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.setAction(adapterClicked);

        holder.binding.tvTanggal.setText(TabunganModels.get(position).created_at_to_date());
        holder.binding.setPosisi(position);
        if (TabunganModels.get(position).getStatus().equals(Const.STATUS_NOTIF_TUTUP_DITERIMA)) {
            holder.binding.tvStatus.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        } else if (TabunganModels.get(position).getStatus().equals(Const.STATUS_NOTIF_TUTUP_DITOLAK)) {
            holder.binding.tvStatus.setTextColor(context.getResources().getColor(R.color.red));
        } else {
            holder.binding.tvStatus.setTextColor(context.getResources().getColor(R.color.gray_muda));
        }
        Log.e("tes tabungan", TabunganModels.toString());
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
                    return AdapterTabunganNasabah.this.TabunganModels.size();
                }

                @Override
                public int getNewListSize() {
                    return TabunganModels.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterTabunganNasabah.this.TabunganModels.get(oldItemPosition).getId() == TabunganModels.get(newItemPosition).getId();
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
