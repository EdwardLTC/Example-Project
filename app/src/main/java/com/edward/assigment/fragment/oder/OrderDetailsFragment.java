package com.edward.assigment.fragment.oder;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.edward.assigment.R;
import com.edward.assigment.dao.DataAccesObject;
import com.edward.assigment.modal.Admin;
import com.edward.assigment.modal.Order;


import java.text.SimpleDateFormat;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;

public class OrderDetailsFragment extends Fragment {
    View view;
    TextView oderID, bookID, AdminID, DateCreate, DateReturn, status, userId, oderDes;
    NeumorphCardView neumorphCardView;
    Order order;
    NeumorphButton btnMark;
    DataAccesObject dataAccesObject;

    public OrderDetailsFragment(Order order) {
        this.order = order;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.order_details_fragment, container, false);
        dataAccesObject = new DataAccesObject(requireContext());
        initView();
        fillView();
        markClick();
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
        oderID.setText(String.valueOf(order.get_id()));
        bookID.setText(dataAccesObject.GetBookFromId(String.valueOf(order.get_bookId())));
        AdminID.setText(dataAccesObject.GetNameAdminFromId(order.get_adminId()));
        userId.setText(dataAccesObject.GetUserNameFromId(order.get_userId()));
        DateCreate.setText(order.getDateCreate());
        DateReturn.setText(order.getDateReturn());
        String str = order.get_status() == 0 ? "..." : "done";
        status.setText(str);
        if (order.get_status() == 0) {
            neumorphCardView.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#7f2908")));
            btnMark.setEnabled(true);
        } else {
            neumorphCardView.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#b8f9be")));
            btnMark.setEnabled(false);
        }
    }

    private void markClick() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        btnMark.setOnClickListener(view -> new SweetAlertDialog(requireContext(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Mark done Action")
                .setContentText("U wanna mark  " + order.get_id() + " done ?")
                .setConfirmText("Yes,do it!")
                .setConfirmClickListener(sDialog -> {
                    if (dataAccesObject.handleMarkDoneOrder(order.get_id(), formatter.format(date))) {
                        sDialog.setTitleText("has been Add!")
                                .setContentText("Your Moderator has been Add!")
                                .setConfirmText("OK")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        order.set_status(1);
                        order.setDateReturn(formatter.format(date));
                        fillView();
                        fillView();

                    } else {
                        sDialog.setTitleText("Oops...")
                                .setContentText("Something went wrong!")
                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    }

                })
                .show());

    }

}
