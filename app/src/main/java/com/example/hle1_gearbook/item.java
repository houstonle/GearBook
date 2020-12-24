package com.example.hle1_gearbook;


import java.io.Serializable;

public class item implements Serializable {

    private static final long serialVersionUID = 1234L;

    private String date;
    private String maker;
    private String description;
    private Double price;
    private String comment;

    public item(String date, String maker, String description, Double price, String comment){
        this.date = date;
        this.maker = maker;
        this.description = description;
        this.price = price;
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}//end class item
