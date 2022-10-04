package com.edward.assigment.fragment.books;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.edward.assigment.R;
import com.edward.assigment.dao.DataAccesObject;
import com.edward.assigment.modal.Category;

import cn.pedant.SweetAlert.SweetAlertDialog;
import soup.neumorphism.NeumorphButton;

public class AddCategoryFragment extends Fragment {
    View view;
    EditText name;
    NeumorphButton btnSubmit;
    DataAccesObject dataAccesObject;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.add_catergory_fragment,container,false);
        initView();
        dataAccesObject = new DataAccesObject(requireContext());
        btnSubmit.setOnClickListener(view -> createDialogAdd());
        return view;
    }

    public void initView(){
        name = view.findViewById(R.id.categoryname);
        btnSubmit =view.findViewById(R.id.submitCategory);
    }

    public void createDialogAdd(){
        Category category = new Category(name.getText().toString());
        if (dataAccesObject.handleAddCategory(category)) {
            new SweetAlertDialog(requireContext(), SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Good job!")
                    .setContentText("You Category has been add!")
                    .show();
            name.setText("");
        }else {
            new SweetAlertDialog(requireContext(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Something went wrong!")
                    .show();
        }
    }
}
