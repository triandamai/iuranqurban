package com.tdn.qurban;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.squareup.picasso.Picasso;
import com.tdn.data.Const;
import com.tdn.domain.model.NotifikasiModel;
import com.todkars.shimmer.ShimmerRecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyBindingAdapter {
    @BindingAdapter("showShimmer")
    public static void shimmer(ShimmerRecyclerView v, boolean isShow) {
        if (isShow) {
            v.showShimmer();
        } else {
            v.hideShimmer();
        }
    }

    @BindingAdapter("viewGone")
    public static void viewgone(View v, boolean isShow) {
        v.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("setRefreshing")
    public static void refresh(SwipeRefreshLayout v, boolean isShow) {
        v.setRefreshing(isShow);
    }

    @BindingAdapter("setRefreshingListener")
    public static void refreshlistener(SwipeRefreshLayout v, SwipeRefreshLayout.OnRefreshListener listener) {
        v.setOnRefreshListener(listener);
    }

    @BindingAdapter("disbleButton")
    public static void disabledbtn(Button v, boolean disabled) {
        v.setEnabled(disabled);
    }

    @BindingAdapter("setImageUrl")
    public static void setImageUrl(ImageView v, String url) {

        Picasso.get().load(url)
                .placeholder(R.drawable.logo)
                .into(v);
    }

    @BindingAdapter("setImageUrlC")
    public static void setImageUrlC(CircleImageView v, String url) {
        Picasso.get().load(url)
                .placeholder(R.drawable.logo)
                .into(v);
    }

    @BindingAdapter("setStatus")
    public static void setStatus(TextView v, String s) {
        if (s.equalsIgnoreCase("1")) {
            v.setText("Menungggu");
        } else if (s.equalsIgnoreCase("2")) {
            v.setText("Diterima");
        } else if (s.equalsIgnoreCase("3")) {
            v.setText("Ditolak");
        } else {
            v.setText("Tidak diketahui");
        }
    }

    @BindingAdapter("setNotif")
    public static void setNotif(TextView v, NotifikasiModel n) {

    }
}
