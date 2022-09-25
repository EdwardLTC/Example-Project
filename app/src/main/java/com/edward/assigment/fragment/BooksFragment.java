package com.edward.assigment.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.assigment.R;
import com.edward.assigment.adapter.BookAdapter;
import com.edward.assigment.adapter.BookCallback;
import com.edward.assigment.adapter.CustomItemAnimator;
import com.edward.assigment.modal.Book;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BooksFragment extends Fragment implements BookCallback {
    private RecyclerView rvBooks;
    private List<Book> mdata;
    private BookAdapter bookAdapter;
    MaterialSearchBar searchBar ;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.books_fragment, container, false);

        initSearch();
        initViews();
        initData();
        setupBookAdapter();
        return view;
    }

    private void setupBookAdapter() {
        bookAdapter = new BookAdapter(mdata, this);
        rvBooks.setAdapter(bookAdapter);
    }

    private void initData() {
        mdata = new ArrayList<>();
        mdata.add(new Book("sach cua E", "Description nay do lam ", "Edward", 200, 10, 3, R.drawable.book1));
        mdata.add(new Book("sach cua Ed", "Description nay do lam ", "Edward", 200, 10, 3.5F, R.drawable.gatsby));
        mdata.add(new Book("sach cua Edw", "Description nay do lam ", "Edward", 200, 10, 3.4F, R.drawable.gatsby2));
        mdata.add(new Book("sach cua Edwa", "Description nay do lam ", "Edward", 200, 10, 1, R.drawable.thefault));
        mdata.add(new Book("sach cua Edwar", "Description nay do lam ", "Edward", 200, 10, 3, R.drawable.themessy));
        mdata.add(new Book("sach cua Edward", "Description nay do lam ", "Edward", 200, 10, 3, R.drawable.thefault));
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
                ArrayList<String> suggest = new ArrayList<>();
                for (Book results:mdata) {
                    if (results.getTitle().contains(searchBar.getText().toLowerCase())){
                        suggest.add(results.getTitle());
                    }
                }
                searchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
//                if (!enabled)
//                    rvBooks.setAdapter(bookAdapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }


            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
    }
    private void startSearch(String text) { // iniit late https://www.youtube.com/watch?v=5_jyEGe6ZHo
        bookAdapter = new BookAdapter(mdata,this);
        rvBooks.setAdapter(bookAdapter);
    }


    @Override
    public void onDestroyView() {
        view = null;
        super.onDestroyView();
    }
}
