package com.edward.assigment.adapter.book;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.edward.assigment.R;
import com.edward.assigment.modal.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    List<Book> mdata;
    BookCallback callback;

    public BookAdapter(List<Book> mdata , BookCallback callback) {
        this.mdata = mdata;
        this.callback = callback ;
    }


    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book,parent,false);

        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

        // bind book item data here
        // I'm just going to bint the book image..

        //load book image using Glide

        Glide.with(holder.itemView.getContext())
                .load(mdata.get(position).getDrawableResource()) // set the img book Url
                .transforms(new CenterCrop(),new RoundedCorners(16)) // i know it's deprecated i will fix it later
                .into(holder.imgBook); // destination path

        holder.ratingBar.setRating(mdata.get(position).getRating());
        holder.title.setText(mdata.get(position).getTitle());
        holder.author.setText(mdata.get(position).getAuthor());
        holder.pages.setText(String.valueOf(mdata.get(position).getPages()));
        holder.rate.setText(String.valueOf(mdata.get(position).getReview()));

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        ImageView imgBook,imgContainer;
        TextView title,author,pages,rate;
        RatingBar ratingBar;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBook = itemView.findViewById(R.id.item_book_img);
            imgContainer = itemView.findViewById(R.id.containerBook);
            title = itemView.findViewById(R.id.item_book_title);
            author = itemView.findViewById(R.id.item_book_author);
            pages = itemView.findViewById(R.id.item_book_pagesrev);
            rate = itemView.findViewById(R.id.item_book_score);
            ratingBar = itemView.findViewById(R.id.item_book_ratingbar);

            itemView.setOnClickListener(v -> callback.onBookItemClick(getAdapterPosition(),
                    imgContainer,
                    imgBook,
                    title,
                    author,
                    pages,
                    rate,
                    ratingBar));


        }
    }
}
