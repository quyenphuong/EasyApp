package com.example.easyapp.model;

public class ShippingModel {

    private String nameship;
    private String addressship;
    private String noteship;
    private String totalship;
    private String typeship;
    private String cod;

    public ShippingModel(){

    }

    public ShippingModel(String nameship, String addressship, String noteship, String totolship, String typeship, String cod) {
        this.nameship = nameship;
        this.addressship = addressship;
        this.noteship = noteship;
        this.totalship = totolship;
        this.typeship = typeship;
        this.cod = cod;
    }

    public String getNameship() {
        return nameship;
    }

    public void setNameship(String nameship) {
        this.nameship = nameship;
    }

    public String getAddressship() {
        return addressship;
    }

    public void setAddressship(String addressship) {
        this.addressship = addressship;
    }

    public String getNoteship() {
        return noteship;
    }

    public void setNoteship(String noteship) {
        this.noteship = noteship;
    }

    public String getTotolship() {
        return totalship;
    }

    public void setTotolship(String totolship) {
        this.totalship = totolship;
    }

    public String getTypeship() {
        return typeship;
    }

    public void setTypeship(String typeship) {
        this.typeship = typeship;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
}
