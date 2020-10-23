package com.tdn.qurban.admin.ui.tabungan;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.android.material.chip.Chip;
import com.tdn.domain.model.HewanModel;
import com.tdn.qurban.R;
import com.trian.singleadapter.SingleAdapterRow;
import com.trian.singleadapter.onEventClick;

public class ItemJenisHewan extends LinearLayout implements SingleAdapterRow<HewanModel> {
    private Chip chip;

    public ItemJenisHewan(Context context) {
        super(context);
    }

    public ItemJenisHewan(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemJenisHewan(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ItemJenisHewan(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        chip = (Chip) findViewById(R.id.chip_hewan);
    }

    @Override
    public void bindView(HewanModel data, onEventClick<HewanModel> eventClick, int position) {
        chip.setText(data.getJenis().toString().toUpperCase());
        chip.setOnClickListener(v -> {
            eventClick.onDetail(data, position);
        });
    }
}
