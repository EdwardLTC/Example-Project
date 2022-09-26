package com.edward.assigment.fragment.oder;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.edward.assigment.R;
import com.edward.assigment.modal.Order;


import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;

public class OrderDetailsFragment extends Fragment {
    View view;
    TextView oderID, bookID, AdminID, DateCreate, DateReturn, status, userId, oderDes;
    NeumorphCardView neumorphCardView;
    Order order;
    NeumorphButton btnMark;

    public OrderDetailsFragment(Order order) {
        this.order = order;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.order_details_fragment, container, false);
        initView();
        fillView();
        return view;
    }

    private void initView() {
        oderID = view.findViewById(R.id.orderID);
        bookID = view.findViewById(R.id.bookID);        //replace by name of book
        AdminID = view.findViewById(R.id.AdminID);      //replace by name of admin
        userId = view.findViewById(R.id.userID);        //replace by name of user name
        oderDes = view.findViewById(R.id.orderDes);
        DateCreate = view.findViewById(R.id.crDate);
        DateReturn = view.findViewById(R.id.rtDate);
        status = view.findViewById(R.id.status);
        neumorphCardView = view.findViewById(R.id.card);
        btnMark = view.findViewById(R.id.mrkdone);
    }

    private void fillView() {
        oderID.setText(order.get_id());
        bookID.setText(order.get_bookId()); //raw query get name
        AdminID.setText(order.get_adminId()); //raw query get name
        userId.setText(order.get_userId());//raw query get name
        DateCreate.setText(order.getDateCreate());
        DateReturn.setText(order.getDateReturn());
        String str = order.get_status() == 0 ? "not done" : "done";
        status.setText(str);
        if (order.get_status() == 0) {
            neumorphCardView.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#7f2908")));
            btnMark.setEnabled(true);
        } else {
            neumorphCardView.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#b8f9be")));
            btnMark.setEnabled(false);
        }
    }
}
