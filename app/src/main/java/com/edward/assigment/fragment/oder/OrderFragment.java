package com.edward.assigment.fragment.oder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.assigment.R;
import com.edward.assigment.adapter.CustomItemAnimator;
import com.edward.assigment.adapter.book.BookAdapter;
import com.edward.assigment.adapter.oder.OrderAdapter;
import com.edward.assigment.adapter.oder.OrderCallBack;
import com.edward.assigment.modal.Book;
import com.edward.assigment.modal.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment implements OrderCallBack {
    private RecyclerView rvOrders;
    private List<Order> mdata;
    private OrderAdapter orderAdapter;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.oder_fragment, container, false);
        initView();
        initData();
        setupOrderAdapter();
        return view;
    }

    private void setupOrderAdapter() {
        orderAdapter = new OrderAdapter(mdata, this);
        rvOrders.setAdapter(orderAdapter);
    }

    private void initData() {
        mdata = new ArrayList<>();
        mdata.add(new Order("01", "b1", "ad1", "usr1", "22/12/2022", "22/12/2022", 0));
        mdata.add(new Order("02", "b2", "ad2", "usr2", "22/12/2022", "22/12/2022", 1));
        mdata.add(new Order("02", "b2", "ad2", "usr1", "22/12/2022", "22/12/2022", 0));
        mdata.add(new Order("02", "b2", "ad2", "usr1", "22/12/2022", "22/12/2022", 1));
    }

    private void initView() {
        rvOrders = view.findViewById(R.id.rvOrders);
        rvOrders.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvOrders.setHasFixedSize(true);
        rvOrders.setItemAnimator(new CustomItemAnimator());
    }

    @Override
    public void onOrderItemClick(int pos, TextView oderID, TextView bookID, TextView adminID,TextView userID, TextView dateCreate, TextView dateReturn, TextView status) {
        String orderid = oderID.getText().toString();
        String bookname = bookID.getText().toString();
        String adminname = adminID.getText().toString();
        String userid =userID.getText().toString();
        String datecreate = dateCreate.getText().toString();
        String datereturns = dateReturn.getText().toString();
        int stt = Integer.parseInt(status.getText().toString());
        Order tempOrder = new Order(orderid,bookname,adminname,userid,datecreate,datereturns,stt);
    }
}
