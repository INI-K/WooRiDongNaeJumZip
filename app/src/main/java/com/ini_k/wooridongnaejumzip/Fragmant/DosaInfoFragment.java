package com.ini_k.wooridongnaejumzip.Fragmant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ini_k.wooridongnaejumzip.Model.Dosa;
import com.ini_k.wooridongnaejumzip.Model.Noti;
import com.ini_k.wooridongnaejumzip.R;
import com.ini_k.wooridongnaejumzip.Util.DosaCallDialogViewHolder;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

import in.dd4you.animatoo.FrAnimatoo;

public class DosaInfoFragment extends Fragment {

    View view;
    AppCompatButton callBtn;
    Dosa dosa;
    TextView dosaTitleName;
    TextView dosaName;
    TextView counCellType;
    ImageView backBtn;

    public DosaInfoFragment() {
        // Required empty public constructor
    }


    public static DosaInfoFragment newInstance() {
        DosaInfoFragment fragment = new DosaInfoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dosa_info, container, false);
        if (getArguments() != null) {
            dosa = (Dosa) getArguments().getSerializable("dosa");
            System.out.println("도사 정보 있음");
        } else {
            System.out.println("도사 정보 없음");
        }
        setVariable();
        setView(view);
        return view;
    }

    public void setVariable() {
    }

    public void setView(View view) {
//        backBtn = view.findViewById(R.id.dosaInfoBackBtn);
        counCellType = view.findViewById(R.id.infoCounCellType);
        dosaName = view.findViewById(R.id.dosaInfoDosaName);
        dosaTitleName = view.findViewById(R.id.dosaTitleName);
        counCellType.setText(dosa.getType());
        dosaName.setText(dosa.getName());
        dosaTitleName.setText(dosa.getName());
        callBtn = view.findViewById(R.id.dosaCallBtn);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View customView = LayoutInflater.from(getContext()).inflate(R.layout.dosa_call_dialog, null);
                ViewHolder viewHolder = new ViewHolder(customView);
                DialogPlus dialog = DialogPlus.newDialog(getContext())
                        .setContentBackgroundResource(R.drawable.dialog_round)
                        .setContentHolder(viewHolder)
                        .setExpanded(false)
                        .create();

                TextView dosaName = (TextView) dialog.getHolderView().findViewById(R.id.dosaCallDialogName);
                FrameLayout exit = (FrameLayout) dialog.getHolderView().findViewById(R.id.exitDosaCallDialog);
                AppCompatButton freeCallBtn = (AppCompatButton) dialog.getHolderView().findViewById(R.id.dosaCallDialogFreeBtn);
                AppCompatButton paidCallBtn = (AppCompatButton) dialog.getHolderView().findViewById(R.id.dosaCallDialogPaidBtn);
                dosaName.setText(dosa.getName());
                freeCallBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(dosa.getCallNum()));
                        startActivity(intent);
                    }
                });
                paidCallBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(dosa.getCallNum()));
                        startActivity(intent);
                    }
                });
                exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }
}