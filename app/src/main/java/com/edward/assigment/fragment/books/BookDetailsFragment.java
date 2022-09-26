package com.edward.assigment.fragment.books;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.taufiqrahman.reviewratings.BarLabels;
import com.taufiqrahman.reviewratings.RatingReviews;

import java.util.Random;

import soup.neumorphism.NeumorphButton;

public class BookDetailsFragment extends Fragment {
    RatingBar ratingBar;
    ImageView imgBook;
    TextView item_book_pagesrev;
    EditText title, author, details_desc;
    NeumorphButton btnEdit;
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
        btnEdit = view.findViewById(R.id.edit);
        loadBookData();
        initEdit();
        imgBook.setOnClickListener(view -> requireActivity().onBackPressed());
        initGarph();
        return view;
    }


    private void loadBookData() {
        Transition transition = TransitionInflater.from(requireContext())
                .inflateTransition(R.transition.share_image);
        setSharedElementEnterTransition(transition);
        Glide.with(this).load(item.getDrawableResource()).placeholder(R.drawable.book1).into(imgBook);
        ratingBar.setRating(item.getRating());
        title.setText(String.valueOf(item.getTitle()));
        author.setText(String.valueOf(item.getAuthor()));
        details_desc.setText(String.valueOf(item.getDescription()));
        String txt = item.getId();
        item_book_pagesrev.setText(txt);
    }

    private void initEdit() {
        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnEdit.setEnabled(!title.getText().toString().equals(item.getTitle()) ||
                        !author.getText().toString().equals(item.getAuthor()) ||
                        !details_desc.getText().toString().equals(item.getDescription()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        author.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnEdit.setEnabled(!title.getText().toString().equals(item.getTitle()) ||
                        !author.getText().toString().equals(item.getAuthor()) ||
                        !details_desc.getText().toString().equals(item.getDescription()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        details_desc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnEdit.setEnabled(!title.getText().toString().equals(item.getTitle()) ||
                        !author.getText().toString().equals(item.getAuthor()) ||
                        !details_desc.getText().toString().equals(item.getDescription()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initGarph() {
        RatingReviews ratingReviews = view.findViewById(R.id.rating_reviews);

        Pair[] colors = new Pair[]{
                new Pair<>(Color.parseColor("#0e9d58"), Color.parseColor("#1e88e5")),
                new Pair<>(Color.parseColor("#bfd047"), Color.parseColor("#5c6bc0")),
                new Pair<>(Color.parseColor("#ffc105"), Color.parseColor("#d81b60")),
                new Pair<>(Color.parseColor("#ef7e14"), Color.parseColor("#8bc34a")),
                new Pair<>(Color.parseColor("#d36259"), Color.parseColor("#ea80fc"))
        };

        int[] raters = new int[]{
                new Random().nextInt(100),
                new Random().nextInt(100),
                new Random().nextInt(100),
                new Random().nextInt(100),
                new Random().nextInt(100)
        };

        ratingReviews.createRatingBars(100, BarLabels.STYPE1, colors, raters);
    }
}
