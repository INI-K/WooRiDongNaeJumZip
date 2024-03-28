package com.ini_k.wooridongnaejumzip.Adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ini_k.wooridongnaejumzip.Fragmant.CallFragment;
import com.ini_k.wooridongnaejumzip.Fragmant.CummunityFragment;
import com.ini_k.wooridongnaejumzip.Fragmant.FreeJumFragment;
import com.ini_k.wooridongnaejumzip.Fragmant.HomeFragment;
import com.ini_k.wooridongnaejumzip.HomeFragmentPager.HomPagerEventFragment;
import com.ini_k.wooridongnaejumzip.HomeFragmentPager.HomePagerCumunityFragment;
import com.ini_k.wooridongnaejumzip.HomeFragmentPager.HomePagerNoticeFragment;
import com.ini_k.wooridongnaejumzip.Model.Noti;
import com.ini_k.wooridongnaejumzip.R;

import java.util.ArrayList;
import java.util.List;

public class HomePagerAdapter extends FragmentStateAdapter {
    private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private HomePagerNoticeFragment fragment1;
    private HomePagerCumunityFragment fragment2;
    private HomPagerEventFragment fragment3;
    public ArrayList<Noti> notiArrayList;

    private final int mSetItemCount = 3;

    public HomePagerAdapter(FragmentActivity fragmentActivity , ArrayList<Noti> notiArrayList) {
        super(fragmentActivity);
        this.notiArrayList = notiArrayList;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int iViewIdx = getRealPosition(position);
        switch (iViewIdx) {
            case 0: {
                return new HomePagerNoticeFragment(notiArrayList);
            } //프래그먼트 순서에 맞게 넣어주세요.
            case 1: {
                return new HomePagerCumunityFragment();
            }
            case 2: {
                return new HomPagerEventFragment();
            }
//            case 3    : { return new Frag4(); }
//            case 4    : { return new Frag5(); }
//            case 5    : { return new Frag6(); }
            default: {
                return new HomePagerNoticeFragment(notiArrayList);
            } //기본으로 나와있는 프래그먼트
        }

    }

    public int getRealPosition(int _iPosition) {
        return _iPosition % mSetItemCount;
    }

    public String getFragmentName(int position) {
        switch (position) {
            case 0:
                return "공지사항";
            case 1:
                return "커뮤니티";
            case 2:
                return "이벤트";
        }
        return "";
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}