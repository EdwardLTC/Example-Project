package com.edward.assigment.fragment.books;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.edward.assigment.R;
import com.edward.assigment.adapter.SpinnerAdapter;
import com.edward.assigment.dao.DataAccesObject;
import com.edward.assigment.modal.Book;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import cn.pedant.SweetAlert.SweetAlertDialog;
import soup.neumorphism.NeumorphButton;

public class AddBookFragment extends Fragment {
    View view;
    NeumorphButton btn;
    EditText bookid, bookname, bookauthor, des;
    DataAccesObject dataAccesObject;
    Spinner cate;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_book_fragment, container, false);
        dataAccesObject = new DataAccesObject(requireContext());
        initView();
        initSpinner();
        cate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#1E4175"));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn.setOnClickListener(view -> {
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    // All your networking logic
                    // should be here
                }
            });
            Book book = new Book(bookid.getText().toString(),
                    dataAccesObject.getCategoryFromName(cate.getSelectedItem().toString()),
                    bookname.getText().toString(), bookauthor.getText().toString(), R.drawable.book1,
                    des.getText().toString(), 3.2F);

            if (dataAccesObject.handleAddBook(book)) {
                new SweetAlertDialog(requireContext(), SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Good job!")
                        .setContentText("You book has been add!")
                        .show();
                reInitEdit();
            } else {
                new SweetAlertDialog(requireContext(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!")
                        .show();
            }
        });

        return view;
    }

    private void initView() {
        btn = view.findViewById(R.id.submit);
        bookid = view.findViewById(R.id.bookid);
        bookname = view.findViewById(R.id.bookname);
        bookauthor = view.findViewById(R.id.author);
        cate = view.findViewById(R.id.spinner);
        des = view.findViewById(R.id.des);
    }

    private void initSpinner() {
        ArrayList<String> arraySpinner = dataAccesObject.getAllCategoryName();
        String[] list = new String[arraySpinner.size() + 1];
        for (int i = 0; i < arraySpinner.size(); i++) {
            list[i] = arraySpinner.get(i);
        }
        list[arraySpinner.size()] = "Category";
        SpinnerAdapter adapter = new SpinnerAdapter(requireContext(), R.layout.spinner_view, list);
        cate.setAdapter(adapter);
        cate.setSelection(adapter.getCount());

    }

    private void reInitEdit() {
        bookid.setText("");
        bookname.setText("");
        initSpinner();
        bookauthor.setText("");
    }
}
