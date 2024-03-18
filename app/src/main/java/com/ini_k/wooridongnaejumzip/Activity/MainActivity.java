package com.ini_k.wooridongnaejumzip.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.ini_k.wooridongnaejumzip.Adapter.ViewPagerAdapter;
import com.ini_k.wooridongnaejumzip.Fragmant.CallFragment;
import com.ini_k.wooridongnaejumzip.Fragmant.CummunityFragment;
import com.ini_k.wooridongnaejumzip.Fragmant.FindJumZipFragment;
import com.ini_k.wooridongnaejumzip.Fragmant.FreeJumFragment;
import com.ini_k.wooridongnaejumzip.Fragmant.HomeFragment;
import com.ini_k.wooridongnaejumzip.R;
import com.ini_k.wooridongnaejumzip.databinding.ActivityMainBinding;
import com.yh.bottomnavigation_base.IMenuListener;
import com.yh.bottomnavigationex.BottomNavigationViewEx;

import in.dd4you.animatoo.FrAnimatoo;

public class MainActivity extends AppCompatActivity {
    BottomNavigationViewEx bottomNavigationView;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager2 viewPager;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView();
        setVariable();
    }

    public void setView(){
        setBottomNavigationView();
    }
    public void setVariable(){

    }
    public void setBottomNavigationView(){
        bottomNavigationView = (BottomNavigationViewEx) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setIconSize(40f,40f);
        bottomNavigationView.setTextSize(14f);
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.setIconsMarginTop(5);
        bottomNavigationView.setCurrentItem(2);
        bottomNavigationView.setTextVisibility(true);

        getSupportFragmentManager().beginTransaction().add(R.id.main_layout,new HomeFragment()).commit();

        bottomNavigationView.setMenuListener(new IMenuListener() {
            @Override
            public boolean onNavigationItemSelected(int i, @NonNull MenuItem menuItem, boolean b) {
                switch(menuItem.getItemId()){
                    case R.id.home :
//                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                        FrAnimatoo.animateZoom(fragmentTransaction);  //fire the zoom animation
//                        fragmentTransaction.replace(R.id.main_layout, new HomeFragment());
//                        fragmentTransaction.commit();
//                        FrAnimatoo.animateFade(fragmentTransaction);
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,new HomeFragment()).commit();
                        break;
                    case R.id.freeUnSae :
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,new FreeJumFragment()).commit();
                        break;
                    case R.id.freeJum :
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,new CallFragment()).commit();
                        break;
                    case R.id.reservationInfo :
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,new CummunityFragment()).commit();
                        break;
                    case R.id.serch :
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,new CummunityFragment()).commit();
                        break;
                }
                return true;
            }
        });
    }
}