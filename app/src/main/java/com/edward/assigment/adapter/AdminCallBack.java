package com.edward.assigment.adapter;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public interface AdminCallBack {

    void onAdminItemClick(int pos,TextView adminName, TextView adminId, TextView role);
}
