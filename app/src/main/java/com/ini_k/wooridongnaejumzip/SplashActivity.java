package com.ini_k.wooridongnaejumzip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import in.dd4you.animatoo.Animatoo;

public class SplashActivity extends AppCompatActivity {

    Context context;
    TextView textWoo;
    TextView textRi;
    TextView textDong;
    TextView textNae;
    TextView textJum;
    TextView textZip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setVariable();
        setView();
        fadeInTextWoo();

    }
    public void setVariable(){
        context = getApplicationContext();
    }
    public void setView(){
        textWoo = (TextView) findViewById(R.id.Splash_Text_Woo);
        textRi = (TextView) findViewById(R.id.Splash_Text_Ri);
        textDong = (TextView) findViewById(R.id.Splash_Text_Dong);
        textNae = (TextView) findViewById(R.id.Splash_Text_Nae);
        textJum = (TextView) findViewById(R.id.Splash_Text_Jum);
        textZip = (TextView) findViewById(R.id.Splash_Text_Zip);
        setAlpha();
    }

    public void setAlpha(){
        textWoo.setAlpha(0f);
        textRi.setAlpha(0f);
        textDong.setAlpha(0f);
        textNae.setAlpha(0f);
        textJum.setAlpha(0f);
        textZip.setAlpha(0f);
    }

    public void fadeInTextWoo(){
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        fadeInTextDong();
                    }
                })
                .playOn(textWoo);
    }

    public void fadeInTextDong(){
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        fadeInTextJum();
                    }
                })
                .playOn(textDong);
    }
    public void fadeInTextJum(){
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        fadeInTextRi();
                    }
                })
                .playOn(textJum);
    }
    public void fadeInTextRi(){
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        fadeInTextNae();
                    }
                })
                .playOn(textRi);
    }
    public void fadeInTextNae(){
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        fadeInTextZip();
                    }
                })
                .playOn(textNae);
    }
    public void fadeInTextZip(){
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        System.out.println("끝남");
                        moveToMainActivity();
                    }
                })
                .playOn(textZip);
    }
    public void moveToMainActivity(){
        startActivity(new Intent(this, MainActivity.class));
        Animatoo.animateFade(SplashActivity.this);
    }
}