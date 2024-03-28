package com.ini_k.wooridongnaejumzip.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.ini_k.wooridongnaejumzip.Manager.CurrentUserManager;
import com.ini_k.wooridongnaejumzip.Model.Noti;
import com.ini_k.wooridongnaejumzip.Model.User;
import com.ini_k.wooridongnaejumzip.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.ArrayList;

import in.dd4you.animatoo.Animatoo;

public class SplashActivity extends AppCompatActivity {


    boolean isEmpty;
    Context context;
    TextView textWoo;
    TextView textRi;
    TextView textDong;
    TextView textNae;
    TextView textJum;
    TextView textZip;

    ArrayList<Noti> notiArrayList = new ArrayList<>();

    Handler handler;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        setVariable();
        setView();
        fadeInTextWoo();
//        crawlingNoti();
//        crawlEvent();

        System.out.println("호출?");
    }

    public void setVariable() {
        context = getApplicationContext();
        user = CurrentUserManager.getCurrentUser();
        handler = new Handler(Looper.getMainLooper());
    }

    public void setView() {
        textWoo = (TextView) findViewById(R.id.Splash_Text_Woo);
        textRi = (TextView) findViewById(R.id.Splash_Text_Ri);
        textDong = (TextView) findViewById(R.id.Splash_Text_Dong);
        textNae = (TextView) findViewById(R.id.Splash_Text_Nae);
        textJum = (TextView) findViewById(R.id.Splash_Text_Jum);
        textZip = (TextView) findViewById(R.id.Splash_Text_Zip);
        setAlpha();
    }

    public void setAlpha() {
        textWoo.setAlpha(0f);
        textRi.setAlpha(0f);
        textDong.setAlpha(0f);
        textNae.setAlpha(0f);
        textJum.setAlpha(0f);
        textZip.setAlpha(0f);
    }

    public void fadeInTextWoo() {
        YoYo.with(Techniques.FadeIn)
                .duration(500)
                .repeat(0)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        fadeInTextDong();
                    }
                })
                .playOn(textWoo);
    }

    public void fadeInTextDong() {
        YoYo.with(Techniques.FadeIn)
                .duration(500)
                .repeat(0)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        fadeInTextJum();
                    }
                })
                .playOn(textDong);
    }

    public void fadeInTextJum() {
        YoYo.with(Techniques.FadeIn)
                .duration(500)
                .repeat(0)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        fadeInTextRi();
                    }
                })
                .playOn(textJum);
    }

    public void fadeInTextRi() {
        YoYo.with(Techniques.FadeIn)
                .duration(500)
                .repeat(0)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        fadeInTextNae();
                    }
                })
                .playOn(textRi);
    }

    public void fadeInTextNae() {
        YoYo.with(Techniques.FadeIn)
                .duration(500)
                .repeat(0)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        fadeInTextZip();
                    }
                })
                .playOn(textNae);
    }

    public void fadeInTextZip() {
        YoYo.with(Techniques.FadeIn)
                .duration(500)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        crawlingNoti();

                    }
                })
                .playOn(textZip);
    }

    public void moveToMainActivity(ArrayList<Noti> notiArrayList) {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.putExtra("NotiArray",notiArrayList);
        startActivity(intent);
//        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        Animatoo.animateFade(SplashActivity.this);
//        Animatoo.animateFade(getApplicationContext());
    }

    public void crawlingNoti() {
        new Thread() {
            public void run() {
                //크롤링 할 구문
                String URL = "https://woorijum.com/bbs/board.php?bo_table=notice";
                Document doc = null;
                try {
                    doc = Jsoup.connect(URL).get();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Elements temele = doc.select(".bo_tit");
                isEmpty = temele.isEmpty(); //빼온 값 null체크
                Log.d("Tag", "isNull? : " + isEmpty); //로그캣 출력
                if (isEmpty == false) { //null값이 아니면 크롤링 실행
                    for (int i = 0; i < temele.size(); i++) {
                        String tem = temele.get(0).text().substring(5);
                        //                            System.out.println("공지사항 제목 "+ i+ "번째 : "+temele.get(i).text());
                        Noti noti = new Noti();
                        noti.setTitle(temele.get(i).text());
                        notiArrayList.add(noti);
                    }
                    Elements temeleWriter = doc.select(".sv_member");
                    for (int i = 0; i < temele.size(); i++) {
//                            System.out.println("공지사항 글쓴이 "+ i+ "번째 : "+temeleWriter.get(i).text());
                        notiArrayList.get(i).setWriter(temeleWriter.get(i).text());
                    }
                    Elements temeleDate = doc.select(".td_datetime");
                    for (int i = 0; i < temele.size(); i++) {
//                            System.out.println("공지사항 날짜 "+ i+ "번째 : "+temeleDate.get(i).text());
                        notiArrayList.get(i).setDate(temeleDate.get(i).text());
                    }
                }

                System.out.println("공지 사항 어레이 확인 ");
                for (int i = 0; i < notiArrayList.size(); i++) {
                    System.out.println(i + "번째 " + "제목 : " + notiArrayList.get(i).getTitle());
                    System.out.println(i + "번째 " + "글쓴이 : " + notiArrayList.get(i).getWriter());
                    System.out.println(i + "번째 " + "날짜 " + notiArrayList.get(i).getDate());
                }
                user.setNotiArrayList(notiArrayList);
                moveToMainActivity(notiArrayList);
            }
        }.start();
    }

    public void crawlCommunity() {

        handler.post(new Runnable() {
            @Override
            public void run() {
                //크롤링 할 구문
                String URL = "https://woorijum.com/bbs/board.php?bo_table=community";
                Document doc = null;
                try {
                    doc = Jsoup.connect(URL).get();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Elements temele = doc.select(".bo_tit");
                isEmpty = temele.isEmpty(); //빼온 값 null체크
                Log.d("Tag", "isNull? : " + isEmpty); //로그캣 출력
                if (isEmpty == false) { //null값이 아니면 크롤링 실행
                    for (int i = 0; i < temele.size(); i++) {
                        String tem = temele.get(0).text().substring(5);
                        System.out.println("커뮤니티 제목 " + i + "번째 : " + temele.get(i).text());
                        System.out.println("커뮤니티 주소 " + i + "번째 : " + temele.get(i).baseUri());
                    }
                    Elements temeleWriter = doc.select(".sv_member");
                    for (int i = 0; i < temele.size(); i++) {
                        System.out.println("커뮤니티 글쓴이 " + i + "번째 : " + temeleWriter.get(i).text());
                    }
                    Elements temeleDate = doc.select(".td_datetime");
                    for (int i = 0; i < temele.size(); i++) {
//                            String tem = temele.get(0).text().substring(5);
                        System.out.println("커뮤니티 날짜 " + i + "번째 : " + temeleDate.get(i).text());
//                            System.out.println("크롤링 확인 "+ i+ "번째 : "+temele.get(i).baseUri());
                    }
                }
            }
        });
//        crawlEvent();
    }

    public void crawlEvent() {
        new Thread() {
            @Override
            public void run() {
                try {
                    String URL = "https://woorijum.com/bbs/board.php?bo_table=event";
                    Document doc = Jsoup.connect(URL).get();
                    Elements temele = doc.select(".bo_tit");
                    isEmpty = temele.isEmpty(); //빼온 값 null체크
                    Log.d("Tag", "isNull? : " + isEmpty); //로그캣 출력
                    if (isEmpty == false) { //null값이 아니면 크롤링 실행
                        for (int i = 0; i < temele.size(); i++) {
                            String tem = temele.get(0).text().substring(5);
                            System.out.println("이벤트 제목 " + i + "번째 : " + temele.get(i).text());
                        }
//                        Elements temeleDate = doc.select("div").select("ul").select("li").select("div").select("div").select("div");
                        Elements temeleDate = doc.select("div[style*=font-size:12px;color:#999999;height:20px;line-height:20px;]");
                        for (int i = 0; i < temeleDate.size(); i++) {
//                            String tem = temele.get(0).text().substring(5);
                            System.out.println("이벤트 날짜 " + i + "번째 : " + temeleDate.get(i).text());
//                            System.out.println("크롤링 확인 "+ i+ "번째 : "+temele.get(i).baseUri());
                        }

                        Elements temeleImage = doc.select("div.webzin_list ul li img");
                        System.out.println("크롤링 이미지 확인 " + temeleImage.text());
                        for (int i = 0; i < temeleImage.size(); i++) {
//                            String tem = temele.get(0).text().substring(5);
                            System.out.println("이벤트 이미지 주소 " + i + "번째 : " + temeleImage.get(i).attr("src"));
//                            System.out.println("크롤링 확인 "+ i+ "번째 : "+temele.get(i).baseUri());
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void getNotiCrawl() throws IOException {
        try {
            //크롤링 할 구문
            String URL = "https://woorijum.com/bbs/board.php?bo_table=notice";
            Document doc = Jsoup.connect(URL).get();
            Elements temele = doc.select(".bo_tit");
            isEmpty = temele.isEmpty(); //빼온 값 null체크
            Log.d("Tag", "isNull? : " + isEmpty); //로그캣 출력
            if (isEmpty == false) { //null값이 아니면 크롤링 실행
                for (int i = 0; i < temele.size(); i++) {
                    String tem = temele.get(0).text().substring(5);
                    //                            System.out.println("공지사항 제목 "+ i+ "번째 : "+temele.get(i).text());
                    Noti noti = new Noti();
                    noti.setTitle(temele.get(i).text());
                    notiArrayList.add(noti);
                }
                Elements temeleWriter = doc.select(".sv_member");
                for (int i = 0; i < temele.size(); i++) {
//                            System.out.println("공지사항 글쓴이 "+ i+ "번째 : "+temeleWriter.get(i).text());
                    notiArrayList.get(i).setWriter(temeleWriter.get(i).text());
                }
                Elements temeleDate = doc.select(".td_datetime");
                for (int i = 0; i < temele.size(); i++) {
//                            System.out.println("공지사항 날짜 "+ i+ "번째 : "+temeleDate.get(i).text());
                    notiArrayList.get(i).setDate(temeleDate.get(i).text());
                }
            }

            System.out.println("공지 사항 어레이 확인 ");
            for (int i = 0; i < notiArrayList.size(); i++) {
                System.out.println(i + "번째 " + "제목 : " + notiArrayList.get(i).getTitle());
                System.out.println(i + "번째 " + "글쓴이 : " + notiArrayList.get(i).getWriter());
                System.out.println(i + "번째 " + "날짜 " + notiArrayList.get(i).getDate());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}