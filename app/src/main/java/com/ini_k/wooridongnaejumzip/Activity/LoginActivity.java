package com.ini_k.wooridongnaejumzip.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ini_k.wooridongnaejumzip.Manager.CurrentUserManager;
import com.ini_k.wooridongnaejumzip.Manager.ServerConnectionManager;
import com.ini_k.wooridongnaejumzip.Model.Noti;
import com.ini_k.wooridongnaejumzip.Model.User;
import com.ini_k.wooridongnaejumzip.R;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

import in.dd4you.animatoo.Animatoo;

public class LoginActivity extends AppCompatActivity {

    AppCompatButton loginBtn;
    EditText editTextId;
    EditText editTextPwd;
    Context context;
    ServerConnectionManager serverConnectionManager;
    RequestQueue queue;
    RotateLoading rotateLoading;
    User user;

    FirebaseDatabase database;
    DatabaseReference myRef;
    Intent intent;
    ArrayList<Noti> notiArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setView();
        setVariable();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveToMainActivity();
    }

    public void moveToMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        Animatoo.animateSlideRight(LoginActivity.this);
//        Animatoo.animateFade(getApplicationContext());
    }

    public void setVariable() {
        user = CurrentUserManager.getCurrentUser();
        context = getApplicationContext();
        serverConnectionManager = new ServerConnectionManager();
        queue = Volley.newRequestQueue(context);
        database = FirebaseDatabase.getInstance();
        intent = getIntent();
        notiArrayList = (ArrayList<Noti>) intent.getSerializableExtra("Noti");
    }

    public void setView() {
        rotateLoading = (RotateLoading) findViewById(R.id.rotateloading);
        loginBtn = (AppCompatButton) findViewById(R.id.loginBtn);
        editTextId = (EditText) findViewById(R.id.LoginActivityEditTextView_ID);
        editTextPwd = (EditText) findViewById(R.id.LoginActivityEditTextView_Password);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = editTextId.getText().toString().trim();
                String userPWD = editTextPwd.getText().toString().trim();
//                serverLogin(userID,userPWD);
                rotateLoading.start();
//                testingData(userID,userPWD);
                loginToFireBase(userID, userPWD);
            }
        });
    }

    public void serverLogin(String ID, String PWD) {
        queue = Volley.newRequestQueue(context);
        String url = serverConnectionManager.getSERVER_URL() + "";
        StringRequest getRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("받은 내역 확인 :" + response);
                        if (rotateLoading.isStart()) {
                            rotateLoading.stop();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (rotateLoading.isStart()) {
                            rotateLoading.stop();
                        }
                    }
                });
        queue.add(getRequest);
    }

    public void testingData(String ID, String PWD) {
        if (ID.equals("aaa")) {
            user.setId(ID);
            user.setIsDosa("1");
            user.setCallOn("0");
            //Welcom Dos
            System.out.println("도사데이터 확인 : " + user.getId());
            System.out.println("도사데이터 확인 : " + user.getIsDosa());
            System.out.println("도사데이터 확인 : " + user.getCallOn());
            moveToReadyCallActivity();
        } else {
            moveToMainActivity();
        }
    }

    public void moveToReadyCallActivity() {
        startActivity(new Intent(LoginActivity.this, ReadyCallActivity.class));
        Animatoo.animateFade(LoginActivity.this);
//        Animatoo.animateFade(getApplicationContext());
    }

    public void loginToFireBase(String userId, String userPwd) {
        final List<User> users = new ArrayList<>();

//        myRef = database.getReference().child("User").child(userId);
        myRef = database.getReference().child("User");

        Query query = myRef.orderByChild("Id").equalTo(userId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    users.add(user);
                }

                if (users.get(0).getPwd().equals(userPwd)) {
                    System.out.println("유저스 확인 : " + users.get(0).getIsDosa());
                    CurrentUserManager.setCurrentUser(users.get(0));
                    user = CurrentUserManager.getCurrentUser();
                    user.generateInfo(user);
                    if (user.getIsDosa().equals("1")) {
                        rotateLoading.stop();
                        moveToReadyCallActivity();
                    } else {
                        rotateLoading.stop();
                        moveToMainActivity();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "로그인 실패",
                            Toast.LENGTH_SHORT).show();
                }

//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    String value = dataSnapshot.getValue(String.class);
//                    if (dataSnapshot.getKey().equals("Pwd")) {
//                        if (value.equals(userPwd)) {
//                            System.out.println("로그인 성공");
//                            System.out.println("로그인 성공 도샤 여부: " + snapshot.child("isDosa"));
//                            user.setId(snapshot.child("Id").getValue(String.class));
//                            user.setPwd(snapshot.child("Pwd").getValue(String.class));
//                            user.setIsDosa(snapshot.child("isDosa").getValue(String.class));
//                            user.setCallOn(snapshot.child("CallOn").getValue(String.class));
//                            user.generateInfo(user);
//                            if(user.getIsDosa().equals("1")){
//                                rotateLoading.stop();
//                                moveToReadyCallActivity();
//                            }else{
//                                rotateLoading.stop();
//                                moveToMainActivity();
//                            }
//                        }
//                    }
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginActivity.this, "error: " + error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
