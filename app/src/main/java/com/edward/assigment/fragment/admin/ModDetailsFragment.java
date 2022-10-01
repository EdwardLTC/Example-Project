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
import com.edward.assigment.dao.DataAccesObject;
import com.edward.assigment.modal.Admin;

import cn.pedant.SweetAlert.SweetAlertDialog;
import soup.neumorphism.NeumorphButton;

public class ModDetailsFragment extends Fragment {
    Admin mod;
    EditText name, des;
    TextView role,id;
    NeumorphButton btnEdit,btnDel;
    View view;
    DataAccesObject dataAccesObject;

    public ModDetailsFragment(Admin mod) {
        this.mod = mod;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mod_details_fragment, container, false);
        dataAccesObject = new DataAccesObject(requireContext());
        initView();
        initData();
        editBtn();
        delBtn();
        return view;
    }

    private void initView() {
        name = view.findViewById(R.id.nameAdmin);
        id = view.findViewById(R.id.idAdmin);
        role = view.findViewById(R.id.roleAdmin);
        des = view.findViewById(R.id.modDes);
        btnEdit = view.findViewById(R.id.edit);
        btnDel = view.findViewById(R.id.del);
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
                btnEdit.setEnabled(!name.getText().toString().equals(mod.get_username()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void delBtn(){
        btnDel.setOnClickListener(view -> new SweetAlertDialog(requireContext(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Won't be able to recover this Moderator!")
                .setConfirmText("Yes,delete it!")
                .setConfirmClickListener(sDialog -> {
                    if (dataAccesObject.handleRemoveMod(mod.get_id())) {
                        sDialog.setTitleText("Deleted!")
                                .setContentText("Your Moderator has been deleted!")
                                .setConfirmText("OK")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        requireActivity().onBackPressed();
                    } else {
                        sDialog.setTitleText("Oops...")
                                .setContentText("Something went wrong!")
                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    }

                })
                .show());
    }
}
