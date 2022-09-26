package com.edward.assigment.fragment.books;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.assigment.R;
import com.edward.assigment.adapter.book.BookAdapter;
import com.edward.assigment.adapter.book.BookCallback;
import com.edward.assigment.adapter.CustomItemAnimator;
import com.edward.assigment.dao.DataAccesObject;
import com.edward.assigment.modal.Book;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BooksFragment extends Fragment implements BookCallback {
    private RecyclerView rvBooks;
    private List<Book> mdata;
    private BookAdapter bookAdapter;
    private DataAccesObject dataAccesObject;
    MaterialSearchBar searchBar ;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.books_fragment, container, false);
        dataAccesObject = new DataAccesObject(requireContext());
        initData();
        initSearch();
        initViews();
        setupBookAdapter();
//        searchBar.setLastSuggestions(mdata);
        return view;
    }

    private void setupBookAdapter() {
        bookAdapter = new BookAdapter(mdata, this);
        rvBooks.setAdapter(bookAdapter);
    }

    private void initData() {
        mdata = new ArrayList<>();
        mdata.addAll(dataAccesObject.getAllBook());
    }

    private void initViews() {
        rvBooks = view.findViewById(R.id.rv_book);
        rvBooks.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvBooks.setHasFixedSize(true);
        rvBooks.setItemAnimator(new CustomItemAnimator());
    }

    @Override
    public void onBookItemClick(int pos, ImageView imgContainer, ImageView imgBook, TextView title,
                                TextView authorName,
                                TextView nbpages,
                                TextView score,
                                RatingBar ratingBar) {
        ViewCompat.setTransitionName(imgBook, "bookTN");
        Fragment fragment = new BookDetailsFragment(mdata.get(pos));
        fragment.setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.share_image));
        fragment.setEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.explode));

        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .addSharedElement(imgBook, "bookTN");
        ft.commit();

    }

    private void initSearch(){
        searchBar = view.findViewById(R.id.search);
        searchBar.setHint("Search");
        searchBar.setCardViewElevation(10);
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                bookAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

//        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
//            @Override
//            public void onSearchStateChanged(boolean enabled) {
//                if (!enabled)
//                    setupBookAdapter();
//            }
//
//            @Override
//            public void onSearchConfirmed(CharSequence text) {
//                startSearch(text.toString());
//            }
//
//
//            @Override
//            public void onButtonClicked(int buttonCode) {
//
//            }
//        });
    }


    @Override
    public void onDestroyView() {
        view = null;
        super.onDestroyView();
    }
}
