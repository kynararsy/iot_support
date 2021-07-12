package com.example.iot_asma_support;

public class History {
    private String date;
    private int detak;
    private int kbb;
    private float debu;

    public History(String date, int detak, int kbb, float debu){
        this.date = date; this.detak = detak; this.kbb = kbb; this.debu = debu;
    }

    public History(){}
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDetak() {
        return detak;
    }

    public void setDetak(int detak) {
        this.detak = detak;
    }

    public int getKbb() {
        return kbb;
    }

    public void setKbb(int kbb) {
        this.kbb = kbb;
    }

    public float getDebu() {
        return debu;
    }

    public void setDebu(float debu) {
        this.debu = debu;
    }
}
