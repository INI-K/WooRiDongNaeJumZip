package com.ini_k.wooridongnaejumzip.Adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ini_k.wooridongnaejumzip.Fragmant.CallFragment;
import com.ini_k.wooridongnaejumzip.Fragmant.CummunityFragment;
import com.ini_k.wooridongnaejumzip.Fragmant.FindJumZipFragment;
import com.ini_k.wooridongnaejumzip.Fragmant.FreeJumFragment;
import com.ini_k.wooridongnaejumzip.Fragmant.HomeFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
    public ViewPagerAdapter(AppCompatActivity activity){
        super(activity);

    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FreeJumFragment();
            case 1:
                return new CallFragment();
            case 2:
                return new HomeFragment();
            case 3:
                return new CummunityFragment();
            case 4:
                return new FindJumZipFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 4;       // 페이지 수
    }
}