package com.edward.assigment.adapter.oder;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.assigment.R;
import com.edward.assigment.modal.Book;
import com.edward.assigment.modal.Order;

import java.util.List;

import soup.neumorphism.NeumorphCardView;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    List<Order> mdata;
    OrderCallBack callback;

    public OrderAdapter(List<Order> mdata,OrderCallBack orderCallBack){
        this.mdata =mdata;
        this.callback = orderCallBack;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.AdminID.setText(mdata.get(position).get_adminId());
        holder.bookID.setText(mdata.get(position).get_bookId());
        holder.DateCreate.setText(mdata.get(position).getDateCreate());
        holder.DateReturn.setText(mdata.get(position).getDateReturn());
        String str = mdata.get(position).get_status() == 0 ? "not done" : "done";
        holder.status.setText(str);
        if (mdata.get(position).get_status() == 0){
            holder.neumorphCardView.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#7f2908")));
        }
        else {
            holder.neumorphCardView.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#b8f9be")));
        }


    }


    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView oderID,bookID,AdminID,DateCreate,DateReturn,status,userId;
        NeumorphCardView neumorphCardView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            oderID = itemView.findViewById(R.id.orderID);
            bookID = itemView.findViewById(R.id.bookID);
            AdminID = itemView.findViewById(R.id.AdminID);
            userId = itemView.findViewById(R.id.userID);
            DateCreate = itemView.findViewById(R.id.crDate);
            DateReturn = itemView.findViewById(R.id.rtDate);
            status = itemView.findViewById(R.id.status);
            neumorphCardView = itemView.findViewById(R.id.card);

            itemView.setOnClickListener(v -> callback.onOrderItemClick(getAdapterPosition(),
                    oderID,
                    bookID,
                    AdminID,
                    userId,
                    DateCreate,
                    DateReturn,
                    status ));


        }
    }
}
