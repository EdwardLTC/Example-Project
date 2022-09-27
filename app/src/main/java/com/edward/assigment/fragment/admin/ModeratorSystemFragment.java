package com.edward.assigment.fragment.admin;

import android.os.Bundle;
import android.transition.TransitionInflater;
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
import com.edward.assigment.adapter.admin.AdminAdapter;
import com.edward.assigment.adapter.admin.AdminCallBack;
import com.edward.assigment.dao.DataAccesObject;
import com.edward.assigment.modal.Admin;

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
        DataAccesObject dataAccesObject = new DataAccesObject(requireContext());
        mdata = dataAccesObject.getAllAdmin(1);
    }

    private void initViews() {
        rvAdmins = view.findViewById(R.id.rv_mod);
        rvAdmins.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvAdmins.setHasFixedSize(true);
    }

    private void initFab() {
        NeumorphFloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(view -> requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new AddModFragment()).addToBackStack(null).commit());
    }

    @Override
    public void onAdminItemClick(int pos, TextView adminName, TextView adminId, TextView role) {
        Fragment fragment = new ModDetailsFragment(mdata.get(pos));
        fragment.setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.share_image));
        fragment.setEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.explode));
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null).commit();
    }
}
