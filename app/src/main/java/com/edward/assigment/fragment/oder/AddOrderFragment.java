package com.edward.assigment.fragment.oder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.edward.assigment.R;
import com.edward.assigment.adapter.SpinnerAdapter;
import com.edward.assigment.dao.DataAccesObject;
import com.edward.assigment.modal.Admin;
import com.edward.assigment.modal.Order;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;
import soup.neumorphism.NeumorphButton;

public class AddOrderFragment extends Fragment {
    int modID;
    DataAccesObject dataAccesObject;
    View view;
    Spinner spinner;
    TextView dayCreate, modName;
    EditText orderid, userID;
    NeumorphButton btnSubmit;

    public AddOrderFragment(int modID) {
        this.modID = modID;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_order_fragment, container, false);
        dataAccesObject = new DataAccesObject(requireContext());
        initView();
        initData();
        submitOnclick();
        return view;
    }

    private void initView() {
        spinner = view.findViewById(R.id.bookSpin);
        dayCreate = view.findViewById(R.id.dayCreate);
        modName = view.findViewById(R.id.mod);
        userID = view.findViewById(R.id.userID);
        btnSubmit = view.findViewById(R.id.submit);
    }

    private void initData() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        initSpinner();
        dayCreate.setText(formatter.format(date));
        modName.setText(dataAccesObject.GetNameAdminFromId(modID));

    }

    private void initSpinner() {
        ArrayList<String> arraySpinner = dataAccesObject.getAllBookName();
        String[] list = new String[arraySpinner.size() + 1];
        for (int i = 0; i < arraySpinner.size(); i++) {
            list[i] = arraySpinner.get(i);
        }
        list[arraySpinner.size()] = "Book name ?";
        SpinnerAdapter adapter = new SpinnerAdapter(requireContext(), R.layout.spinner_view, list);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount());

    }

    private boolean isValidUserID(String id) {
        String name = dataAccesObject.GetUserNameFromId(id);
        return name != null;
    }

    private void submitOnclick() {


        btnSubmit.setOnClickListener(view -> new SweetAlertDialog(requireContext(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Add Action")
                .setContentText("U wanna add this Order")
                .setConfirmText("Yes,do it!")
                .setConfirmClickListener(sDialog -> {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    Order order = new Order(dataAccesObject.GetIdBookFromName(spinner.getSelectedItem().toString()),
                            modID, userID.getText().toString(), formatter.format(date), null, 0);
                    if (isValidUserID(order.get_userId())) {
                        if (dataAccesObject.handleAddOrder(order)) {
                            sDialog.setTitleText("has been Add!")
                                    .setContentText("Your Order has been Add!")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(null)
                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        } else {
                            sDialog.setTitleText("Oops...")
                                    .setContentText("Something went wrong!")
                                    .setConfirmText("oke")
                                    .setConfirmClickListener(null)
                                    .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        }
                    } else {
                        sDialog.setTitleText("Oops...")
                                .setContentText("The user Id doesn't existed")
                                .setConfirmText("oke")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    }

                })
                .show());


    }

}
