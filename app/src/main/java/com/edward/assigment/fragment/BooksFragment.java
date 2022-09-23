package com.edward.assigment.fragment;

import android.os.Bundle;
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
import androidx.work.WorkManager;

import com.edward.assigment.R;
import com.edward.assigment.adapter.BookAdapter;
import com.edward.assigment.adapter.BookCallback;
import com.edward.assigment.adapter.CustomItemAnimator;
import com.edward.assigment.modal.Book;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

import java.util.ArrayList;
import java.util.List;

public class BooksFragment extends Fragment implements BookCallback {
    private RecyclerView rvBooks;
    private List<Book> mdata;
    TapBarMenu tapBarMenu;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.books_fragment, container, false);
        initViews();
        initData();
        setupBookAdapter();
        return view;
    }

    private void setupBookAdapter() {
        BookAdapter bookAdapter = new BookAdapter(mdata, this);
        rvBooks.setAdapter(bookAdapter);
    }

    private void initData() {
        mdata = new ArrayList<>();
        mdata.add(new Book("sach cua Edward", "Description nay do vai lz ", "Edward", 200, 10, 3, R.drawable.book1));
        mdata.add(new Book("sach cua Edward", "Description nay do vai lz ", "Edward", 200, 10, 3, R.drawable.gatsby));
        mdata.add(new Book("sach cua Edward", "Description nay do vai lz ", "Edward", 200, 10, 3, R.drawable.gatsby2));
        mdata.add(new Book("sach cua Edward", "Description nay do vai lz ", "Edward", 200, 10, 3, R.drawable.thefault));
        mdata.add(new Book("sach cua Edward", "Description nay do vai lz ", "Edward", 200, 10, 3, R.drawable.themessy));
        mdata.add(new Book("sach cua Edward", "Description nay do vai lz ", "Edward", 200, 10, 3, R.drawable.thefault));
    }

    private void initViews() {
        rvBooks = view.findViewById(R.id.rv_book);
        rvBooks.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvBooks.setHasFixedSize(true);
        rvBooks.setItemAnimator(new CustomItemAnimator());

        tapBarMenu = view.findViewById(R.id.tapBarMenu);
        tapBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapBarMenu.toggle();
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
            Fragment fragment = new BookDetailsFragment(mdata.get(pos));
            fragment.setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.share_image));
            fragment.setEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.explode));

            FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .addSharedElement(imgBook, "bookTN");
            ft.commit();

    }

    @Override
    public void onDestroyView() {
        view = null;
        super.onDestroyView();
    }
}
