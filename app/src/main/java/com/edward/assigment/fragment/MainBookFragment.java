package com.edward.assigment.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.edward.assigment.R;

import github.com.st235.lib_expandablebottombar.ExpandableBottomBar;
import github.com.st235.lib_expandablebottombar.Menu;
import github.com.st235.lib_expandablebottombar.MenuItem;
import github.com.st235.lib_expandablebottombar.MenuItemDescriptor;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;

public class MainBookFragment extends Fragment {

    @SuppressLint("NonConstantResourceId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_book_fragment, container, false);
        ExpandableBottomBar expandableBottomBar = view.findViewById(R.id.expandable_bottom_bar);
        Menu menu = expandableBottomBar.getMenu();
        showFragment(new BooksFragment());
        menu.add(
                new MenuItemDescriptor.Builder(requireContext(), R.id.home, R.drawable.ic_baseline_library_books_24, R.string.books, Color.parseColor("#00AEEF")).build()
        );
        menu.add(
                new MenuItemDescriptor.Builder(requireContext(), R.id.addBook, R.drawable.ic_baseline_add_chart_24, R.string.addBook, Color.parseColor("#64E9B1")).build()
        );
        menu.add(
                new MenuItemDescriptor.Builder(requireContext(), R.id.addCategory, R.drawable.ic_baseline_category_24, R.string.addCategory, Color.parseColor("#FA6D7A")).build()
        );

        expandableBottomBar.setOnItemSelectedListener((view1, menuItem, aBoolean) -> {
            switch (menuItem.getId()) {
                case R.id.home:
                case R.id.addBook:
                case R.id.addCategory:
                    showFragment(new BooksFragment());
                    break;
            }
            return null;
        });

        return view;
    }

    private void showFragment(Fragment fragment) {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout, fragment)
                .commit();
    }
}
