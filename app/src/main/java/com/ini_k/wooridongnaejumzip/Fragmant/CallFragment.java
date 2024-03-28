package com.ini_k.wooridongnaejumzip.Fragmant;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.ini_k.wooridongnaejumzip.Adapter.RecommendDosaAdapter;
import com.ini_k.wooridongnaejumzip.Adapter.SliderAdapterExample;
import com.ini_k.wooridongnaejumzip.Model.AdImage;
import com.ini_k.wooridongnaejumzip.Model.Dosa;
import com.ini_k.wooridongnaejumzip.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import in.dd4you.animatoo.FrAnimatoo;

public class CallFragment extends Fragment {
//https://picsum.photos/200/300

    public ArrayList<AdImage> adImageArrayList;
    public ArrayList<Dosa> dosaArrayList;
    public ArrayList<Dosa> discountDosaArrayList;
    public View view;
    SliderView sliderView;
    SliderAdapterExample sliderAdapterExample;
    RecyclerView recommendDosaRecyclerView;
    RecyclerView discountRecyclerView;
    RecommendDosaAdapter recommendDosaAdapter;
    RecommendDosaAdapter discountDosaAdapter;
    PermissionListener permissionlistener;
    Context context;

    boolean isScroll = false;

    public CallFragment() {
        // Required empty public constructor
    }

    public static CallFragment newInstance() {
        CallFragment fragment = new CallFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_call, container, false);
        generateTestData();
        setVariable();
        setView(view);

        permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                System.out.println("권한 허가");
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                for (int i = 0; i < deniedPermissions.size(); i++) {
                    System.out.println("전화 권한 없음33 : " + deniedPermissions.get(i));
                }
            }
        };
        checkPermission(context, permissionlistener);

        return view;
    }

    public void setVariable() {
//        adImageArrayList = new ArrayList<>();
        context = getActivity().getApplicationContext();
        sliderAdapterExample = new SliderAdapterExample(context);
        sliderAdapterExample.renewItems(adImageArrayList);
        recommendDosaAdapter = new RecommendDosaAdapter(dosaArrayList);
        discountDosaAdapter = new RecommendDosaAdapter(discountDosaArrayList);
    }

    public void setView(View view) {
        sliderView = view.findViewById(R.id.callFragmentViewPager);
        sliderView.setSliderAdapter(sliderAdapterExample);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
        recommendDosaRecyclerView = view.findViewById(R.id.recommendDosaRecyclerView);
        recommendDosaRecyclerView.setAdapter(recommendDosaAdapter);
        recommendDosaRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        discountRecyclerView = view.findViewById(R.id.discountDosaRecyclerView);
        discountRecyclerView.setAdapter(discountDosaAdapter);
        discountRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recommendDosaAdapter.setOnItemClickListener(new RecommendDosaAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Dosa dosa) {

//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
//                startActivity(intent);
                DosaInfoFragment dosaInfoFragment = new DosaInfoFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("dosa", dosa);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                FrAnimatoo.animateZoom(fragmentTransaction);  //fire the zoom animation
                dosaInfoFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.main_layout, dosaInfoFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        discountDosaAdapter.setOnItemClickListener(new RecommendDosaAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Dosa dosa) {
                DosaInfoFragment dosaInfoFragment = new DosaInfoFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("dosa", dosa);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                FrAnimatoo.animateZoom(fragmentTransaction);  //fire the zoom animation
                dosaInfoFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.main_layout, dosaInfoFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    public void generateTestData() {
        adImageArrayList = new ArrayList<>();

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        //tv.setText("x=>" + width + ",y=>" + height);
        for (int i = 1; i <= 3; i++) {
            AdImage adImage = new AdImage();
            adImage.setImageUrl("https://picsum.photos/" + String.valueOf(width) + "/300");
            adImage.setDescription("광고번호 : " + i);
            adImageArrayList.add(adImage);
        }

        dosaArrayList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            String[] type = {"운세", "심리", "사주"};
            String[] firstName = {"수연", "다은", "하은", "서율", "소은", "시아", "사랑", "수현", "지원", "하연"};
            String[] familyName = {"김", "나", "박", "이", "최"};
            // Create a Random object
            Random randomType = new Random();
            Random randomName = new Random();
            Random randomFirstName = new Random();
            int indexType = randomType.nextInt(3);
            int indexName = randomName.nextInt(10);
            int indexFirstName = randomFirstName.nextInt(5);
            Dosa dosa = new Dosa();
            dosa.setName(familyName[indexFirstName] + firstName[indexName]);
            dosa.setType(type[indexType] + i);
            int callRandom = randomType.nextInt(2);
            dosa.setCallEn(String.valueOf(callRandom));
            dosa.setCallNum("tel:01057030573");
            dosaArrayList.add(dosa);
        }
        discountDosaArrayList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            String[] type = {"운세", "심리", "사주"};
            String[] firstName = {"전윤혁", "유선욱", "배승욱", "유효원", "전인하", "이태식", "전재환", "정선민", "김미진", "임수아", "안상현", "이경옥", "표시혁",
                    "풍민기", "황문옥", "백인웅", "하경준", "서명훈", "봉미옥", "한용성", "전용욱", "장정태", "전태주", "유은용", "임유연",
                    "홍시언", "고연우", "손효주", "표명훈", "추인웅"};

            // Create a Random object
            Random randomType = new Random();
            Random randomName = new Random();
            Random randomFirstName = new Random();
            int indexType = randomType.nextInt(3);
            int indexName = randomName.nextInt(30);
            int indexFirstName = randomFirstName.nextInt(5);
            Dosa dosa = new Dosa();
            dosa.setName(firstName[indexName]);
            dosa.setType(type[indexType]);
            int callRandom = randomType.nextInt(2);
            dosa.setCallEn(String.valueOf(callRandom));
            dosa.setCallNum("tel:01057030573");
            discountDosaArrayList.add(dosa);
        }
    }
    public void checkPermission(Context mContext, PermissionListener PermissionListener) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) { // [타겟 31 이상]
                TedPermission.with(mContext)
                        .setPermissionListener(PermissionListener) // [퍼미션이 부여 체크 이벤트 리스너 지정]
                        .setPermissions(
                                Manifest.permission.CALL_PHONE
                                // -----------------------------------------
                        )
                        .check();

            } else { // [타겟 31 미만]
                TedPermission.with(mContext)
                        .setPermissionListener(PermissionListener) // [퍼미션이 부여 체크 이벤트 리스너 지정]
                        .setPermissions(
                                android.Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                        .check();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}