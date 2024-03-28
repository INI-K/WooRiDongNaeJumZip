package com.ini_k.wooridongnaejumzip.HomeFragmentPager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ini_k.wooridongnaejumzip.Adapter.NoticeAdapter;
import com.ini_k.wooridongnaejumzip.Model.Noti;
import com.ini_k.wooridongnaejumzip.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePagerNoticeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePagerNoticeFragment extends Fragment {
    public View view;
    public ArrayList<Noti> notiArrayList;
    public RecyclerView recyclerView;
    NoticeAdapter adapter;
    LinearLayoutManager layoutManager;

    public HomePagerNoticeFragment(ArrayList<Noti> notiArrayList) {
        // Required empty public constructor
        this.notiArrayList = notiArrayList;

    }

    public HomePagerNoticeFragment() {

    }

    public static HomePagerNoticeFragment newInstance() {
        HomePagerNoticeFragment fragment = new HomePagerNoticeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_pager_notice, container, false);
        setVariable();
        setView(view);

        return view;
    }

    public void setVariable() {
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new NoticeAdapter(notiArrayList);
    }
    public void setView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.noticeRecyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}