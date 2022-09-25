package com.edward.assigment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.assigment.R;
import com.edward.assigment.modal.Admin;

import java.util.List;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.AdminViewHolder> {

    List<Admin> mdata;
    AdminCallBack callback;

    public AdminAdapter(List<Admin> mdata, AdminCallBack callback) {
        this.mdata = mdata;
        this.callback = callback;
    }

    @NonNull
    @Override
    public AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdminViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_admin, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewHolder holder, int position) {
        holder.name.setText(mdata.get(position).get_username());
        holder.id.setText(mdata.get(position).get_id());
        String m_role = mdata.get(position).get_role() == 0 ? "Admin" : "Moderator";
        holder.role.setText(m_role);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class AdminViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView id;
        TextView role;

        public AdminViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameAdmin);
            id = itemView.findViewById(R.id.idAdmin);
            role = itemView.findViewById(R.id.roleAdmin);
            itemView.setOnClickListener(v -> callback.onAdminItemClick(getAdapterPosition(), name, id, role));
        }
    }
}
