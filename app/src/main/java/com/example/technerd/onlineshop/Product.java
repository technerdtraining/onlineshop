package com.example.technerd.onlineshop;

/**
 * Created by technerd on 20/01/2016.
 */
public class Product {
    private int ImageID;
    private String Title;
    private String Descrption;
    private String Price;
    private String Code;

    public Product(){

    }
    //product constructor
    public Product(int imageID,String title,String description,String price,String code){
        ImageID=imageID;
        Title=title;
        Price=price;
        Descrption=description;
        Code=code;

    }
    //Setting the setter and getter for the class
    public String getDescrption() {
        return Descrption;
    }

    public int getImageID() {
        return ImageID;
    }

    public String getPrice() {
        return Price;
    }

    public String getTitle() {
        return Title;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public void setDescrption(String descrption) {
        Descrption = descrption;
    }

    public void setImageID(int imageID) {
        ImageID = imageID;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
