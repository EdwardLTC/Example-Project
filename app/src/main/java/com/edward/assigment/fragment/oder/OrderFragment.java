package com.edward.assigment.fragment.oder;

import android.os.Bundle;
import android.transition.TransitionInflater;
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

import soup.neumorphism.NeumorphFloatingActionButton;

public class OrderFragment extends Fragment implements OrderCallBack {
    private RecyclerView rvOrders;
    private List<Order> mdata;
    private OrderAdapter orderAdapter;
    NeumorphFloatingActionButton fab;
    View view;
    int modID;

    public OrderFragment(int id) {
        modID = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.oder_fragment, container, false);
        initView();
        initData();
        setupOrderAdapter();
        fabOnclick();
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
        fab = view.findViewById(R.id.fab);
        rvOrders = view.findViewById(R.id.rvOrders);
        rvOrders.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvOrders.setHasFixedSize(true);
        rvOrders.setItemAnimator(new CustomItemAnimator());
    }

    @Override
    public void onOrderItemClick(int pos, TextView oderID, TextView bookID, TextView adminID,TextView userID, TextView dateCreate, TextView dateReturn, TextView status) {
        int orderid =  mdata.get(pos).get_id();
        int bookid = mdata.get(pos).get_bookId();
        int adminid = mdata.get(pos).get_adminId();
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

    private void fabOnclick(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new AddOrderFragment(modID);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null).commit();
            }
        });
    }

}
