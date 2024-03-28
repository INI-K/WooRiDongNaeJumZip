package com.ini_k.wooridongnaejumzip.Model;

import java.io.Serializable;

public class Dosa implements Serializable {
    String name;
    String callEn;
    String type;
    String callNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCallEn() {
        return callEn;
    }

    public void setCallEn(String callEn) {
        this.callEn = callEn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCallNum() {
        return callNum;
    }

    public void setCallNum(String callNum) {
        this.callNum = callNum;
    }
}
