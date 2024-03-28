package com.ini_k.wooridongnaejumzip.Fragmant;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ini_k.wooridongnaejumzip.Activity.MainActivity;
import com.ini_k.wooridongnaejumzip.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.bootpay.android.Bootpay;
import kr.co.bootpay.android.events.BootpayEventListener;
import kr.co.bootpay.android.models.BootExtra;
import kr.co.bootpay.android.models.BootItem;
import kr.co.bootpay.android.models.BootUser;
import kr.co.bootpay.android.models.Payload;

public class FreeJumFragment extends Fragment {

    View view;

    public FreeJumFragment() {
        // Required empty public constructor
    }

    public static FreeJumFragment newInstance() {
        FreeJumFragment fragment = new FreeJumFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_free_jum, container, false);
        PaymentTest(view);
        return view;
    }

    public void PaymentTest(View v) {

        BootUser user = new BootUser().setPhone("010-1234-5678"); // 구매자 정보
        BootExtra extra = new BootExtra()
                .setCardQuota("0,2,3");  // 일시불, 2개월, 3개월 할부 허용, 할부는 최대 12개월까지 사용됨 (5만원 이상 구매시 할부허용 범위)

        Double price = 100d;

        String pg = "나이스페이";
        String method = "네이버페이";

        //통계용 데이터 추가
        List<BootItem> items = new ArrayList<>();
        BootItem item1 = new BootItem().setName("마우's 스").setId("ITEM_CODE_MOUSE").setQty(1).setPrice(50d);
        BootItem item2 = new BootItem().setName("키보드").setId("ITEM_KEYBOARD_MOUSE").setQty(1).setPrice(50d);
        items.add(item1);
        items.add(item2);

        Payload payload = new Payload();
        payload.setApplicationId("66027e2100c78a001c64ae5d")
                .setOrderName("부트페이 결제테스트")
                .setOrderId("1234")
                .setPrice(price)
                .setUser(user)
                .setExtra(extra)
                .setItems(items);


        Map<String, Object> map = new HashMap<>();
        map.put("1", "abcdef");
        map.put("2", "abcdef55");
        map.put("3", 1234);
        payload.setMetadata(map);

        Bootpay.init(getActivity().getSupportFragmentManager(), getActivity().getApplicationContext())
                .setPayload(payload)
                .setEventListener(new BootpayEventListener() {
                    @Override
                    public void onCancel(String data) {
                        Log.d("cancel", data);
                        System.out.println("결제 취소 : " + data);
                    }

                    @Override
                    public void onError(String data) {
                        Log.d("error", data);
                        System.out.println("결제 에러 : " + data);
                    }

                    @Override
                    public void onClose() {
                        System.out.println("결제 닫기");
                        Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onIssued(String data) {
                        Log.d("issued", data);
                        System.out.println("결제 이슈 : " + data);
                    }

                    @Override
                    public boolean onConfirm(String data) {
                        Log.d("confirm", data);
                        System.out.println("결제 완료 : " + data);
//                        Bootpay.transactionConfirm(data); //재고가 있어서 결제를 진행하려 할때 true (방법 1)
                        return true; //재고가 있어서 결제를 진행하려 할때 true (방법 2)
//                        return false; //결제를 진행하지 않을때 false
                    }

                    @Override
                    public void onDone(String data) {
                        Log.d("done", data);
                    }

                }).requestPayment();
    }
}
