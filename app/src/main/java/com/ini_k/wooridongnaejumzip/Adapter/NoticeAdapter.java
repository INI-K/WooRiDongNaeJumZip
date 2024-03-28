package com.ini_k.wooridongnaejumzip.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ini_k.wooridongnaejumzip.Model.Noti;
import com.ini_k.wooridongnaejumzip.R;

import java.util.ArrayList;
import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>{
    private ArrayList<Noti> notiArrayList;

    public NoticeAdapter(ArrayList<Noti> notiArrayList) {
        this.notiArrayList = notiArrayList;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.noti_item, parent, false);
        return new NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        holder.title.setText(notiArrayList.get(position).getTitle());
        holder.date.setText(notiArrayList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return notiArrayList.size();
    }

    public static class NoticeViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView date;

        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.noti_title);
            date = itemView.findViewById(R.id.noti_date);
        }
    }
}