package com.ini_k.wooridongnaejumzip.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ini_k.wooridongnaejumzip.Manager.CurrentUserManager;
import com.ini_k.wooridongnaejumzip.Model.User;
import com.ini_k.wooridongnaejumzip.R;
import com.razzaghimahdi78.dotsloading.linear.LoadingScaly;

import java.util.ArrayList;

import at.markushi.ui.CircleButton;

public class ReadyCallActivity extends AppCompatActivity {
    ImageView profileCallOn;
    ImageView profileCallOff;

    CircleButton callOnBtn;
    CircleButton callOffBtn;

    TextView loadTextView;
    User user;
    Context context;
    FirebaseDatabase database;
    DatabaseReference myRef;

    LoadingScaly loadDot;


    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready_call);

        setVariable();
        setView();

    }

    public void setVariable(){
        user = CurrentUserManager.getCurrentUser();
        context = getApplicationContext();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("User").child(user.getId());
    }
    public void setView(){
        loadTextView = (TextView) findViewById(R.id.loadingTextView);
        profileCallOn = (ImageView) findViewById(R.id.dosaCallOn);
        profileCallOff = (ImageView) findViewById(R.id.dosaCallOff);
        callOnBtn = (CircleButton) findViewById(R.id.callOn);
        callOffBtn = (CircleButton) findViewById(R.id.callOff);
        loadDot = (LoadingScaly)findViewById(R.id.loadDot);
        callOnBtn.setPressed(true);
        callOffBtn.setPressed(true);
//        profileCallOn.setAlpha(0f);
//        profileCallOff.setAlpha(0f);
//        callOnBtn.setAlpha(0f);
//        callOffBtn.setAlpha(0f);

        checkCallEnable();

        callOnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callOffRequest();
//               firebaseTest();

            }
        });
        callOffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callOnRequest();
//                firebaseTest();
            }
        });
    }
    public void checkCallEnable(){
        ///필요시 서버연결
        System.out.println("유저 데이터 확인 : " + user.getCallOn());

        if(user.getCallOn().equals("0")){
//            callOffBtn.setAlpha(1f);
//            profileCallOff.setAlpha(1f);
//            callOnBtn.setAlpha(0f);
//            profileCallOn.setAlpha(0f);
            callOffBtn.setVisibility(View.VISIBLE);
            profileCallOff.setVisibility(View.VISIBLE);
            callOnBtn.setVisibility(View.INVISIBLE);
            profileCallOn.setVisibility(View.INVISIBLE);
            loadDot.setVisibility(View.INVISIBLE);

//            callOffBtn.setClickable(true);
//            callOnBtn.setClickable(false);
        }else {
//            callOffBtn.setAlpha(0f);
//            profileCallOff.setAlpha(0f);
//            callOnBtn.setAlpha(1f);
//            profileCallOn.setAlpha(1f);
//            callOnBtn.setClickable(true);
//            callOffBtn.setClickable(false);
            callOffBtn.setVisibility(View.INVISIBLE);
            profileCallOff.setVisibility(View.INVISIBLE);
            callOnBtn.setVisibility(View.VISIBLE);
            profileCallOn.setVisibility(View.VISIBLE);
            loadDot.setVisibility(View.VISIBLE);
        }

    }

    public void callOnRequest(){
        myRef.child("CallOn").setValue("1");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loadTextView.setText("점사 대기중");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                loadTextView.setText("value: " + error.toException());
                System.out.println("파이어 베이스 오류 : " + error.toException());
            }
        });

        user.setCallOn("1");
        checkCallEnable();
    }
    public void callOffRequest(){
        myRef.child("CallOn").setValue("0");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String value = snapshot.getValue(String.class);
//                if(snapshot.child("CallOn").getValue().equals("0"))
                loadTextView.setText("점사 비 활성화");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                loadTextView.setText("value: " + error.toException());
                System.out.println("파이어 베이스 오류 : " + error.toException());
            }
        });
        user.setCallOn("0");
        checkCallEnable();
    }
}