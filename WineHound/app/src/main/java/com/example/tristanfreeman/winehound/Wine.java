package com.example.tristanfreeman.winehound;

import java.io.Serializable;

/**
 * Created by tristanfreeman on 4/3/15.
 */
public class Wine implements Serializable{
    private long wId;
    private String wName;
    private String wBrand;
    private int wMl;
    private String wRetailer;
    private String wNotes;
    private float wRating;


    public Wine(){

    }
    public Wine(int id, String name, float rating){
        wId = id;
        wName = name;
        wRating = rating;

    }



    public long getId(){
        return wId;
    }
    public void setId(long id){
        this.wId = id;
    }

    public String getName(){
        return wName;
    }
    public void setName(String name){
        this.wName = name;
    }

    public String getBrand(){
        return wBrand;
    }
    public void setBrand(String brand){
        this.wBrand = brand;
    }

    public int getMl(){
        return wMl;
    }
    public void setMl(int ml){
        this.wMl = ml;
    }

    public String getRetailer(){
        return wRetailer;
    }
    public void setRetailer(String retailer){
        this.wRetailer = retailer;
    }

    public String getNotes(){
        return wNotes;
    }
    public void setNotes(String notes){
        this.wNotes = notes;
    }

    public float getRating(){
        return wRating;
    }
    public void setRating(float rating){
        this.wRating = rating;
    }

}
