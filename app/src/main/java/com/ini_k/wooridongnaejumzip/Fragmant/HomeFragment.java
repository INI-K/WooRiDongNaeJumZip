package com.ini_k.wooridongnaejumzip.Fragmant;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ini_k.wooridongnaejumzip.Adapter.HomePagerAdapter;
import com.ini_k.wooridongnaejumzip.R;

public class HomeFragment extends Fragment {

    View view;

    String TAG =" HomeFragment";

    private ViewGroup viewGroup;

    PagerSlidingTabStrip tabs;
    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        setInit();



        return viewGroup;
    }
    private void setInit() { //뷰페이저2 실행 메서드

        /* setup infinity scroll viewpager */

        ViewPager2 viewPageSetUp = viewGroup.findViewById(R.id.pager); //여기서 뷰페이저를 참조한다.
        HomePagerAdapter SetupPagerAdapter = new HomePagerAdapter(getActivity()); //프래그먼트에서는 getActivity로 참조하고, 액티비티에서는 this를 사용해주세요.
        viewPageSetUp.setAdapter(SetupPagerAdapter); //FragPagerAdapter를 파라머티로 받고 ViewPager2에 전달 받는다.
        // 무제한 스크롤 처럼 보이기 위해서는 0페이지 부터가 아니라 1000페이지 부터 시작해서 좌측으로 이동할 경우 999페이지로 이동하여 무제한 처럼 스크롤 되는 것 처럼 표현하기 위함.
//        viewPageSetUp.setCurrentItem(1000);

//        final float pageMargin = (float) getResources().getDimensionPixelOffset(R.dimen.pageMargin); //페이지끼리 간격
//        final float pageOffset = (float) getResources().getDimensionPixelOffset(R.dimen.offset); //페이지 보이는 정도
        final float pageMargin = 10f;//페이지끼리 간격
        final float pageOffset = 10f; //페이지 보이는 정도

//

        viewPageSetUp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

            }
        });
//        viewPageSetUp.setPageTransformer(new ViewPager2.PageTransformer() {
//            @Override
//            public void transformPage(@NonNull View page, float position) {
//                float offset = position * - (2 * pageOffset + pageMargin);
//                if(-1 > position) {
//                    page.setTranslationX(-offset);
//                } else if(1 >= position) {
//                    float scaleFactor = Math.max(0.7f, 1 - Math.abs(position - 0.14285715f));
//                    page.setTranslationX(offset);
//                    page.setScaleY(scaleFactor);
//                    page.setAlpha(scaleFactor);
//                } else {
//                    page.setAlpha(0f);
//                    page.setTranslationX(offset);
//                }
//            }
//        });

        TabLayout tabLayout = viewGroup.findViewById(R.id.tablayout);
//        new TabLayoutMediator(tabLayout, viewPageSetUp, (tab, position) -> {
//            // Set tab titles here if needed
//        }).attach();

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPageSetUp, (tab, position) -> {
            tab.setText(getFragmentName(position));

        });
        tabLayoutMediator.attach();

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

}