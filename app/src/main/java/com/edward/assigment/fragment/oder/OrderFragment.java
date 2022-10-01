package com.edward.assigment.fragment.oder;

import android.os.Bundle;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.assigment.R;
import com.edward.assigment.adapter.CustomItemAnimator;
import com.edward.assigment.adapter.oder.OrderAdapter;
import com.edward.assigment.adapter.oder.OrderCallBack;
import com.edward.assigment.dao.DataAccesObject;
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
        DataAccesObject dataAccesObject = new DataAccesObject(requireContext());
        mdata = dataAccesObject.getOrder();
    }

    private void initView() {
        rvOrders = view.findViewById(R.id.rvOrders);
        rvOrders.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvOrders.setHasFixedSize(true);
        rvOrders.setItemAnimator(new CustomItemAnimator());
    }

    @Override
    public void onOrderItemClick(int pos, TextView oderID, TextView bookID, TextView adminID,TextView userID, TextView dateCreate, TextView dateReturn, TextView status) {
        String orderid =  mdata.get(pos).get_id();
        String bookid = mdata.get(pos).get_bookId();
        String adminid = mdata.get(pos).get_adminId();
        String userid = mdata.get(pos).get_userId();
        String datecreate = mdata.get(pos).getDateCreate();
        String datereturns = mdata.get(pos).getDateReturn();
        int stt = status.getText().toString().equals("done") ? 1 : 0 ;
        Order tempOrder = new Order(orderid,bookid,adminid,userid,datecreate,datereturns,stt);

        Fragment fragment = new OrderDetailsFragment(tempOrder);
        fragment.setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.share_image));
        fragment.setEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.explode));

        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null);
        ft.commit();
    }


}
