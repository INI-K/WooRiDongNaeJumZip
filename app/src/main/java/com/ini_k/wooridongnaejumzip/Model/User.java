package com.ini_k.wooridongnaejumzip.Model;

import java.util.ArrayList;

public class User {
    String CallOn;
    String isDosa;
    String Id;
    String Pwd;

    ArrayList<Noti> notiArrayList;

    public String getCallOn() {
        return CallOn;
    }

    public void setCallOn(String callOn) {
        CallOn = callOn;
    }

    public String getIsDosa() {
        return isDosa;
    }

    public void setIsDosa(String isDosa) {
        this.isDosa = isDosa;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String pwd) {
        Pwd = pwd;
    }

    public void generateInfo(User user){
        System.out.println("유저 정보 id: " + user.getId());
        System.out.println("유저 정보 pwd: " + user.getPwd());
        System.out.println("유저 정보 callon: " + user.getCallOn());
        System.out.println("유저 정보 isDosa: " + user.getIsDosa());
    }

    public ArrayList<Noti> getNotiArrayList() {
        return notiArrayList;
    }

    public void setNotiArrayList(ArrayList<Noti> notiArrayList) {
        this.notiArrayList = notiArrayList;
    }
}
