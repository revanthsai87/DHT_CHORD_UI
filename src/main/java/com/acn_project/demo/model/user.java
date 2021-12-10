package com.acn_project.demo.model;

public class user {
private String domain;
private String a_1;
private String a_2;
private String a_3;

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAdmin() {
        return a_1;
    }

    public String getA_1() {
        return a_1;
    }

    public void setA_1(String a_1) {
        this.a_1 = a_1;
    }

    public String getA_2() {
        return a_2;
    }

    public void setA_2(String a_2) {
        this.a_2 = a_2;
    }

    public String getA_3() {
        return a_3;
    }

    public void setA_3(String a_3) {
        this.a_3 = a_3;
    }

    @Override
    public String toString() {
        return "user{" +
                "domain='" + domain + '\'' +
                ", a_1='" + a_1 + '\'' +
                ", a_2='" + a_2 + '\'' +
                ", a_3='" + a_3 + '\'' +
                '}';
    }


    public String getDomain() {
        return domain;
    }

}
