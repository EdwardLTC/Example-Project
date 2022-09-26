package com.edward.assigment.adapter.book;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("deprecation")
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    List<Book> mdata;
    List<Book> filteredData;
    BookCallback callback;
    private final ItemFilter mFilter = new ItemFilter();

    public BookAdapter(List<Book> mdata, BookCallback callback) {
        this.mdata = mdata;
        filteredData = new ArrayList<>(mdata);
        this.callback = callback;
    }


    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);

        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        // bind book item data here
        // I'm just going to bint the book image..

        //load book image using Glide
        Glide.with(holder.itemView.getContext())
                .load(filteredData.get(position).getDrawableResource()) // set the img book Url
                .placeholder(R.drawable.book1)
                .transforms(new CenterCrop(), new RoundedCorners(16)) // i know it's deprecated i will fix it later
                .into(holder.imgBook); // destination path
        holder.ratingBar.setRating(filteredData.get(position).getRating());
        holder.title.setText(filteredData.get(position).getTitle());
        holder.author.setText(filteredData.get(position).getAuthor());
        holder.id.setText(String.valueOf(filteredData.get(position).getId()));
        holder.rate.setText(String.valueOf(filteredData.get(position).getReview()));

    }


    @Override
    public int getItemCount() {
        return filteredData.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        ImageView imgBook, imgContainer;
        TextView title, author, id, rate;
        RatingBar ratingBar;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBook = itemView.findViewById(R.id.item_book_img);
            imgContainer = itemView.findViewById(R.id.containerBook);
            title = itemView.findViewById(R.id.item_book_title);
            author = itemView.findViewById(R.id.item_book_author);
            id = itemView.findViewById(R.id.idbook);
            rate = itemView.findViewById(R.id.item_book_score);
            ratingBar = itemView.findViewById(R.id.item_book_ratingbar);

            itemView.setOnClickListener(v -> callback.onBookItemClick(getAdapterPosition(),
                    imgContainer,
                    imgBook,
                    title,
                    author,
                    id,
                    rate,
                    ratingBar));


        }
    }

    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();
            List<Book> list = mdata;
            ArrayList<Book> nlist = new ArrayList<>();
            Book filterable;
            for (int i = 0; i < list.size(); i++) {
                filterable = list.get(i);
                if (filterable.getTitle().toLowerCase().contains(filterString)) {
                    nlist.add(filterable);
                }
            }
            results.values = nlist;
            results.count = nlist.size();
            System.err.println(nlist);
            return results;
        }

        @SuppressLint("NotifyDataSetChanged")
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData.clear();
            filteredData.addAll((ArrayList<Book>) results.values) ;
            notifyDataSetChanged();
        }

    }
}
