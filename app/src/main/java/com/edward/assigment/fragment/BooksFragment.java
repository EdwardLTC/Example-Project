package com.edward.assigment.fragment;

import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionInflater;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.List;
public class BooksFragment extends Fragment implements BookCallback {
    private RecyclerView rvBooks;
    private BookAdapter bookAdapter;
    private List<Book> mdata ;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.books_fragment,container,false);
        initViews();
        initmdataBooks();
        setupBookAdapter();
        return view;
    }
    private void setupBookAdapter() {
        bookAdapter = new BookAdapter(mdata, this);
        rvBooks.setAdapter(bookAdapter);
    }

    private void initmdataBooks() {
        mdata = new ArrayList<>();
        mdata.add(new Book("sach cua Edward","Description nay do vai lz ","Edward",200,10,3,R.drawable.book1));
        mdata.add(new Book("sach cua Edward","Description nay do vai lz ","Edward",200,10,3,R.drawable.gatsby));
        mdata.add(new Book("sach cua Edward","Description nay do vai lz ","Edward",200,10,3,R.drawable.gatsby2));
        mdata.add(new Book("sach cua Edward","Description nay do vai lz ","Edward",200,10,3,R.drawable.thefault));
        mdata.add(new Book("sach cua Edward","Description nay do vai lz ","Edward",200,10,3,R.drawable.themessy));
        mdata.add(new Book("sach cua Edward","Description nay do vai lz ","Edward",200,10,3,R.drawable.thefault));
    }

    private void initViews() {
        rvBooks = view.findViewById(R.id.rv_book);
        rvBooks.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvBooks.setHasFixedSize(true);
        rvBooks.setItemAnimator(new CustomItemAnimator());
        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


    @Override
    public void onBookItemClick(int pos, ImageView imgContainer, ImageView imgBook, TextView title,
                                TextView authorName,
                                TextView nbpages,
                                TextView score,
                                RatingBar ratingBar) {

        ViewCompat.setTransitionName(imgBook, "bookTN");


//        setSharedElementReturnTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.share_image));
//        setExitTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.explode));

        Fragment fragment = new BookDetailsFragment(mdata.get(pos));
        fragment.setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.share_image));
        fragment.setEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.explode));

        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .addSharedElement(imgBook, "bookTN");
        ft.commit();

    }
}
