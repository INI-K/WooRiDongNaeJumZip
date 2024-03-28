package com.ini_k.wooridongnaejumzip.Util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatButton;

import com.ini_k.wooridongnaejumzip.R;
import com.orhanobut.dialogplus.Holder;

public class DosaCallDialogViewHolder implements Holder {

    private View itemView;
    private AppCompatButton freeBtn;
    private AppCompatButton paidBtn;

    String dosaName;

    @Override
    public void addHeader(View view) {

    }

    @Override
    public void addFooter(View view) {

    }

    @Override
    public void setBackgroundResource(int colorResource) {

    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup parent) {
        return itemView;
    }

    @Override
    public void setOnKeyListener(View.OnKeyListener keyListener) {

    }

    @Override
    public View getInflatedView() {
        return null;
    }

    @Override
    public View getHeader() {
        return null;
    }

    @Override
    public View getFooter() {
        return null;
    }

    public interface OnFreeButtonClickListener {
        void onFreeButtonClick();
    }
    public interface OnPaidButtonClickListener {
        void onPaidButtonClick();
    }

    public void setDosaName(String dosaName){
        this.dosaName = dosaName;
    }
    public DosaCallDialogViewHolder(View itemView) {
        this.itemView = itemView;
        // Initialize your button
        freeBtn = itemView.findViewById(R.id.dosaCallDialogFreeBtn);
        paidBtn = itemView.findViewById(R.id.dosaCallDialogPaidBtn);
    }



    // Other methods like addHeader, addFooter, getView

    // Method to set click listener for the button
    public void setOnFreeButtonClickListener(final OnFreeButtonClickListener listener) {
        freeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onFreeButtonClick();
                }
            }
        });
    }
    public void setOnPaidButtonClickListener(final OnPaidButtonClickListener listener) {
        paidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onPaidButtonClick();
                }
            }
        });
    }
}