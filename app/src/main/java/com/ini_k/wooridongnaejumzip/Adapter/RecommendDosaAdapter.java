package com.ini_k.wooridongnaejumzip.Adapter;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ini_k.wooridongnaejumzip.Model.Dosa;
import com.ini_k.wooridongnaejumzip.Model.Noti;
import com.ini_k.wooridongnaejumzip.R;

import java.util.ArrayList;

public class RecommendDosaAdapter extends RecyclerView.Adapter<RecommendDosaAdapter.NoticeViewHolder>{
    private ArrayList<Dosa> dosaArrayList;

    public RecommendDosaAdapter(ArrayList<Dosa> dosaArrayList) {
        this.dosaArrayList = dosaArrayList;
    }

    public interface OnItemClickListener {
        void onItemClicked(Dosa dosa);
    }

    // OnItemClickListener 참조 변수 선언
    private OnItemClickListener itemClickListener;

    // OnItemClickListener 전달 메소드
    public void setOnItemClickListener (OnItemClickListener listener) {
        itemClickListener = listener;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dosa_item, parent, false);

        return new NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        holder.name.setText(dosaArrayList.get(position).getName());
        holder.type.setText(dosaArrayList.get(position).getType());
        if(dosaArrayList.get(position).getCallEn().equals("1")){
            holder.dosaCallOff.setVisibility(View.INVISIBLE);
            holder.dosaCallOn.setVisibility(View.VISIBLE);
        }else {
            holder.dosaCallOff.setVisibility(View.VISIBLE);
            holder.dosaCallOn.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return dosaArrayList.size();
    }

    public  class NoticeViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView type;
        ImageView dosaProfile;
        ImageView dosaCallOn;
        ImageView dosaCallOff;
        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.dosaName);
            type = itemView.findViewById(R.id.dosaType);
            dosaProfile = itemView.findViewById(R.id.dosaImage);
            dosaCallOn = itemView.findViewById(R.id.dosa_item_Callon);
            dosaCallOff = itemView.findViewById(R.id.dosa_item_CallOff);

//            itemView.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View view, MotionEvent motionEvent) {
//                    int pos = getAdapterPosition() ;
//                    if (pos != RecyclerView.NO_POSITION) {
//                        if(motionEvent.getAction() == MotionEvent.ACTION_UP){
//                            System.out.println("22222");
//                            itemClickListener.onItemClicked(dosaArrayList.get(pos));
//                        }
//                    }
//                    return false;
//                }
//            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        itemClickListener.onItemClicked(dosaArrayList.get(pos));
                    }
                }
            });
        }
    }
}