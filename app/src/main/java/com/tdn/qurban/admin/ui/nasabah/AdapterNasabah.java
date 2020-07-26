package com.tdn.qurban.admin.ui.nasabah;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tdn.data.Const;
import com.tdn.domain.model.UserModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.databinding.ItemNasabahBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterNasabah extends RecyclerView.Adapter<AdapterNasabah.MyViewHolder> {
    private List<UserModel> he = new ArrayList<>();
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
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNasabah.MyViewHolder holder, int position) {
        holder.binding.setAction(adapterClicked);
        holder.binding.setPosisi(position);
        holder.binding.setData(he.get(position));
        if (he.get(position).getStatus().equals(Const.STATUS_USER_AKTIF)) {
            binding.tvStatus.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        } else if (he.get(position).getStatus().equals(Const.STATUS_USER_NONAKTIF)) {
            binding.tvStatus.setTextColor(context.getResources().getColor(R.color.red));
        } else {
            binding.tvStatus.setTextColor(context.getResources().getColor(R.color.gray_tua));
        }
    }

    public void setData(List<UserModel> notifikasiModels) {
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
                    UserModel lama = AdapterNasabah.this.he.get(oldItemPosition);
                    UserModel baru = notifikasiModels.get(newItemPosition);
                    return lama == baru && Objects.equals(lama, baru);
                }
            });
            this.he = notifikasiModels;
            result.dispatchUpdatesTo(this);
            notifyDataSetChanged();
        }
    }

    public UserModel getFromPosition(int pos) {
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
