package com.example.hle1_gearbook;


import java.util.ArrayList;

public class itemList {

    private static ArrayList<item> items;
    private Double sumPrice;

    public itemList(){
        items = new ArrayList<item>();
        sumPrice = 0.0;
    }

    public ArrayList<item> getItems(){
        return items;
    }

    public void addItem(com.example.hle1_gearbook.item Item){
        items.add(Item);
    }

    public void remove(int index){
        items.remove(index);
    }

    public item getItem(int index){
        return items.get(index);
    }

    //Resets price to 0 then goes through array list of items and add each price to the sum price
    public Double getPrice(){
        sumPrice = 0.0;
        for(item currentItem : items){
            sumPrice += currentItem.getPrice();
        }
        return sumPrice;
    }

}//end class itemList
