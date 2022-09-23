package com.edward.assigment.fragment;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
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

import com.bumptech.glide.Glide;
import com.edward.assigment.R;
import com.edward.assigment.modal.Book;

public class BookDetailsFragment extends Fragment {
    RatingBar ratingBar;
    ImageView imgBook, imgContainer;
    TextView title, author, item_book_pagesrev, details_desc;

    View view;
    Book item;

    public BookDetailsFragment(Book item) {
        this.item = item;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.book_details_fragment, container, false);
        imgBook = view.findViewById(R.id.item_book_img);
        ViewCompat.setTransitionName(imgBook, "bookTN");
        ratingBar = view.findViewById(R.id.item_book_ratingbar);
        title = view.findViewById(R.id.item_book_title);
        author = view.findViewById(R.id.item_book_author);
        details_desc = view.findViewById(R.id.details_desc);
        item_book_pagesrev = view.findViewById(R.id.item_book_pagesrev);
        loadBookData();

        imgBook.setOnClickListener(view -> requireActivity().onBackPressed());


        return view;
    }


    private void loadBookData() {
        Transition transition = TransitionInflater.from(requireContext())
                .inflateTransition(R.transition.share_image);
        setSharedElementEnterTransition(transition);
        Glide.with(this).load(item.getDrawableResource()).into(imgBook);
        ratingBar.setRating(item.getRating());
        title.setText(String.valueOf(item.getTitle()));
        author.setText(String.valueOf(item.getAuthor()));
        details_desc.setText(String.valueOf(item.getDescription()));
        String txt = item.getPages() + " Pages" + " |" + item.getReview() + " review ";
        item_book_pagesrev.setText(txt);
    }
}
