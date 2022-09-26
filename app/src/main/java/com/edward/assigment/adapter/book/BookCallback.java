package com.edward.assigment.adapter.book;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public interface BookCallback {

    void onBookItemClick(int pos,
                         ImageView imgContainer,
                         ImageView imgBook,
                         TextView title,
                         TextView authorName,
                         TextView nbpages,
                         TextView score,
                         RatingBar ratingBar);


}
