package com.tdn.qurban.nasabah.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tdn.domain.model.TabunganModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.databinding.ItemTabunganBinding;
import com.tdn.qurban.nasabah.ui.tabungan.AdapterTabunganNasabah;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterMyTabungan extends RecyclerView.Adapter<AdapterMyTabungan.MyViewHolder> {
    private List<TabunganModel> TabunganModels = new ArrayList<>();
    private Context context;
    private AdapterClicked adapterClicked;

    public AdapterMyTabungan(Context context, AdapterClicked adapterClicked) {
        this.context = context;
        this.adapterClicked = adapterClicked;
    }

    @NonNull
    @Override
    public AdapterMyTabungan.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTabunganBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_tabungan, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMyTabungan.MyViewHolder holder, int position) {
        holder.binding.setAction(adapterClicked);
        holder.binding.setData(TabunganModels.get(position));
        holder.binding.setPosisi(position);
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
                    return AdapterMyTabungan.this.TabunganModels.size();
                }

                @Override
                public int getNewListSize() {
                    return TabunganModels.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterMyTabungan.this.TabunganModels.get(oldItemPosition).getId() == TabunganModels.get(newItemPosition).getId();
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
