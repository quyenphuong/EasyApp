package com.example.easyapp.ui.driver;

import java.util.Date;

public class DetailHistory {
    private String name_tour ;
    private int Id_tour ;
    private int Id_bill;
    private Date createDay;
    private Date start_tour ;
    private Date end_tour ;
    private int quantity ;
    private int Id_vehicle ;
    private int status;
    private float total ;

    public DetailHistory(String name_tour, int id_tour, int id_bill, Date createDay, Date start_tour, Date end_tour, int quantity, int id_vehicle, int status, float total) {
        this.name_tour = name_tour;
        Id_tour = id_tour;
        Id_bill = id_bill;
        this.createDay = createDay;
        this.start_tour = start_tour;
        this.end_tour = end_tour;
        this.quantity = quantity;
        Id_vehicle = id_vehicle;
        this.status = status;
        this.total = total;
    }



    public String getName_tour() {
        return name_tour;
    }

    public void setName_tour(String name_tour) {
        this.name_tour = name_tour;
    }

    public int getId_tour() {
        return Id_tour;
    }

    public void setId_tour(int id_tour) {
        Id_tour = id_tour;
    }

    public int getId_bill() {
        return Id_bill;
    }

    public void setId_bill(int id_bill) {
        Id_bill = id_bill;
    }

    public Date getCreateDay() {
        return createDay;
    }

    public void setCreateDay(Date createDay) {
        this.createDay = createDay;
    }

    public Date getStart_tour() {
        return start_tour;
    }

    public void setStart_tour(Date start_tour) {
        this.start_tour = start_tour;
    }

    public Date getEnd_tour() {
        return end_tour;
    }

    public void setEnd_tour(Date end_tour) {
        this.end_tour = end_tour;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId_vehicle() {
        return Id_vehicle;
    }

    public void setId_vehicle(int id_vehicle) {
        Id_vehicle = id_vehicle;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
