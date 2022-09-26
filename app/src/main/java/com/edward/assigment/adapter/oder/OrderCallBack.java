package com.edward.assigment.adapter.oder;

import android.widget.TextView;

public interface OrderCallBack {
    void onOrderItemClick(int pos, TextView oderID,
                          TextView bookID,
                          TextView adminID,
                          TextView userID,
                          TextView dateCreate,
                          TextView dateReturn,
                          TextView status);
}
