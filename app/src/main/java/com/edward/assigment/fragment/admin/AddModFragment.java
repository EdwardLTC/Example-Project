package com.edward.assigment.fragment.admin;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.edward.assigment.R;
import com.edward.assigment.dao.DataAccesObject;
import com.edward.assigment.modal.Admin;

import cn.pedant.SweetAlert.SweetAlertDialog;
import soup.neumorphism.NeumorphButton;

public class AddModFragment extends Fragment {
    ImageView imageView;
    TextView name, id, pass;
    View view;
    NeumorphButton submit;
    DataAccesObject dataAccesObject;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_mod_fragment, container, false);
        dataAccesObject = new DataAccesObject(requireContext());
        initView();
        initToggleButton();
        addMod();
        return view;
    }

    private void initView() {   
        name = view.findViewById(R.id.modname);
        id = view.findViewById(R.id.modid);
        pass = view.findViewById(R.id.modPass);
        imageView = view.findViewById(R.id.togglePass);
        submit = view.findViewById(R.id.submit);
    }

    @SuppressLint({"NonConstantResourceId", "UseCompatLoadingForDrawables"})
    private void initToggleButton() {
        imageView.setOnClickListener(view -> {
            Integer integer = (Integer) imageView.getTag();
            integer = integer == null ? 0 : integer;
            switch (integer) {
                case R.drawable.visible_eye:
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.invisible_eye));
                    imageView.setTag(R.drawable.invisible_eye);
                    pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    break;
                case R.drawable.invisible_eye:
                default:
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.visible_eye));
                    imageView.setTag(R.drawable.visible_eye);
                    pass.setInputType(InputType.TYPE_NULL);
                    break;
            }
        });
//        name.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                submit.setEnabled(!name.getText().toString().isEmpty() && !id.getText().toString().isEmpty() && !pass.getText().toString().isEmpty());
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        id.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                submit.setEnabled(!name.getText().toString().isEmpty() && !id.getText().toString().isEmpty() && !pass.getText().toString().isEmpty());
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        pass.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                submit.setEnabled(!name.getText().toString().isEmpty() && !id.getText().toString().isEmpty() && !pass.getText().toString().isEmpty());
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
    }

    private void addMod(){
        submit.setOnClickListener(view -> new SweetAlertDialog(requireContext(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Add Action")
                .setContentText("U wanna add " + id.getText().toString() + "?")
                .setConfirmText("Yes,do it!")
                .setConfirmClickListener(sDialog -> {
                    if (dataAccesObject.handleAddMod(new Admin(id.getText().toString(),name.getText().toString(),pass.getText().toString(),1))) {
                        sDialog.setTitleText("has been Add!")
                                .setContentText("Your Moderator has been Add!")
                                .setConfirmText("OK")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        reInitView();

                    } else {
                        sDialog.setTitleText("Oops...")
                                .setContentText("Something went wrong!")
                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    }

                })
                .show());
    }

    private void  reInitView(){
        id.setText("");
        pass.setText("");
        name.setText("");
        submit.setEnabled(false);
    }
}
