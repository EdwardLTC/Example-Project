package com.edward.assigment.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.assigment.R;
import com.edward.assigment.adapter.AdminAdapter;
import com.edward.assigment.adapter.AdminCallBack;
import com.edward.assigment.adapter.BookAdapter;
import com.edward.assigment.adapter.CustomItemAnimator;
import com.edward.assigment.modal.Admin;
import com.edward.assigment.modal.Book;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import soup.neumorphism.NeumorphFloatingActionButton;

public class ModeratorSystemFragment extends Fragment implements AdminCallBack {
    private RecyclerView rvAdmins;
    private List<Admin> mdata;
    private AdminAdapter adminAdapter;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.moder_fragment, container, false);
        initViews();
        initData();
        setupAdminAdapter();
        initFab();
        return view;
    }
    private void setupAdminAdapter() {
        adminAdapter = new AdminAdapter(mdata, this);
        rvAdmins.setAdapter(adminAdapter);
    }

    private void initData() {
        mdata = new ArrayList<>();
        mdata.add(new Admin("1","Edward","new Pass",1));
        mdata.add(new Admin("2","Edward","new Pass",1));
        mdata.add(new Admin("3","Edward","new Pass",1));
        mdata.add(new Admin("4","Edward","new Pass",1));
    }

    private void initViews() {
        rvAdmins = view.findViewById(R.id.rv_mod);
        rvAdmins.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvAdmins.setHasFixedSize(true);
    }

    private void initFab(){
        NeumorphFloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(view -> requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new AddModFragment()).commit());
    }

    @Override
    public void onAdminItemClick(int pos, TextView adminName, TextView adminId, TextView role) {
        Toast.makeText(requireContext(), "click", Toast.LENGTH_SHORT).show();
    }
}
