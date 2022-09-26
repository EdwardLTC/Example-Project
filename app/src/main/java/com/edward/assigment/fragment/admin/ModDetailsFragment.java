package com.edward.assigment.fragment.admin;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.edward.assigment.R;
import com.edward.assigment.modal.Admin;

import soup.neumorphism.NeumorphButton;

public class ModDetailsFragment extends Fragment {
    Admin mod;
    EditText name, des;
    TextView role,id;
    NeumorphButton btnEdit;
    View view;

    public ModDetailsFragment(Admin mod) {
        this.mod = mod;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mod_details_fragment, container, false);
        initView();
        initData();
        editBtn();
        return view;
    }

    private void initView() {
        name = view.findViewById(R.id.nameAdmin);
        id = view.findViewById(R.id.idAdmin);
        role = view.findViewById(R.id.roleAdmin);
        des = view.findViewById(R.id.modDes);
        btnEdit = view.findViewById(R.id.edit);
    }

    private void initData() {
        name.setText(mod.get_username());
        id.setText(mod.get_id());
        String m_role = mod.get_role() == 0 ? "Admin" : "Moderator";
        role.setText(m_role);
        des.setText("hahahaahahah");
    }

    private void editBtn() {
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!name.getText().toString().equals(mod.get_username())) {
                    btnEdit.setEnabled(true);
                } else {
                    btnEdit.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
