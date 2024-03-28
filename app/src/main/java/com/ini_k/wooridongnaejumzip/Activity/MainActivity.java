package com.ini_k.wooridongnaejumzip.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.ini_k.wooridongnaejumzip.Adapter.HomePagerAdapter;
import com.ini_k.wooridongnaejumzip.Adapter.ViewPagerAdapter;
import com.ini_k.wooridongnaejumzip.Fragmant.CallFragment;
import com.ini_k.wooridongnaejumzip.Fragmant.CummunityFragment;
import com.ini_k.wooridongnaejumzip.Fragmant.FreeJumFragment;
import com.ini_k.wooridongnaejumzip.Fragmant.HomeFragment;
import com.ini_k.wooridongnaejumzip.Model.Dosa;
import com.ini_k.wooridongnaejumzip.Model.Noti;
import com.ini_k.wooridongnaejumzip.Model.User;
import com.ini_k.wooridongnaejumzip.R;
import com.ini_k.wooridongnaejumzip.Manager.CurrentUserManager;
import com.ini_k.wooridongnaejumzip.databinding.ActivityMainBinding;
import com.yh.bottomnavigation_base.IMenuListener;
import com.yh.bottomnavigationex.BottomNavigationViewEx;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import in.dd4you.animatoo.Animatoo;

public class MainActivity extends AppCompatActivity {
    BottomNavigationViewEx bottomNavigationView;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager2 viewPager;
    private ActivityMainBinding binding;
    boolean isEmpty;
    User user;
    ArrayList<Noti> notiArrayList;
    Intent intent;
    ImageView userIcon;
    HomeFragment homeFragment;
    CallFragment callFragment;
    Bundle bundle;

    ImageView mainLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVariable();
        setView();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    public void setView(){
        setLogo();
        setBottomNavigationView();
        userIcon = (ImageView) findViewById(R.id.userIcon);
        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.getId() == null){
                    System.out.println("회원정보 없음");
                    moveToLoginActivity();
                }
            }
        });
    }
    public void setLogo(){
        mainLogo = (ImageView) findViewById(R.id.mainLogo);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mainlogo);
        float maxHeight = 180f;
        float resolution = (float) bitmap.getWidth() / (float) bitmap.getHeight();
        float width = maxHeight * resolution;
        mainLogo.setImageBitmap(Bitmap.createScaledBitmap(bitmap, (int) width, (int) maxHeight, false));
    }
    public void setVariable(){
        System.out.println("변수 초기화 됨?");
        user = CurrentUserManager.getCurrentUser();
        intent = getIntent();
        notiArrayList = new ArrayList<>();
        if(intent != null){
            notiArrayList = (ArrayList<Noti>) getIntent().getSerializableExtra("NotiArray");
        }else {
            System.out.println("변수 인텐트 비었음");
        }
        if(notiArrayList == null){
            notiArrayList = user.getNotiArrayList();
        }
    }
    public void setBottomNavigationView(){
        bottomNavigationView = (BottomNavigationViewEx) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setIconSize(40f,40f);
        bottomNavigationView.setTextSize(14f);
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.setIconsMarginTop(5);
//        bottomNavigationView.setCurrentItem(2);
        bottomNavigationView.setTextVisibility(true);
        bottomNavigationView.setCurrentItem(1);
//         homeFragment = new HomeFragment();
//         bundle = new Bundle();
//        bundle.putSerializable("NotiArray", notiArrayList);
//        homeFragment.setArguments(bundle);
        callFragment = new CallFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_layout,callFragment).commit();

        bottomNavigationView.setMenuListener(new IMenuListener() {
            @Override
            public boolean onNavigationItemSelected(int i, @NonNull MenuItem menuItem, boolean b) {
                switch(menuItem.getItemId()){
                    case R.id.home :
                        homeFragment = new HomeFragment();
                        bundle = new Bundle();
                        bundle.putSerializable("NotiArray", notiArrayList);
                        homeFragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,homeFragment).commit();
                        break;
                    case R.id.freeUnSae :
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,new FreeJumFragment()).commit();
                        break;
                    case R.id.freeJum :
                        callFragment = new CallFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,callFragment).commit();
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
    public void moveToLoginActivity(){
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        Animatoo.animateSlideLeft(MainActivity.this);
//        Animatoo.animateFade(getApplicationContext());
    }
}