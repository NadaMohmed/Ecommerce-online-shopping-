package com.example.ecommerce.project;

import android.widget.Button;

public class Listitem {

    private  String prodname ;
    private String proquantity ;
    // get cartid to make edit ana delete
    private int cart_id ;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }




public Listitem(String name , String  quantity,int cartid /*, int price*/)
{
    this.prodname = name  ;
    this.proquantity = quantity ;
    this.cart_id = cartid ;
    //this.price=price;

}

public String getProdname ()
{

    return prodname ;

}
    public String getProdquantity ()
    {

        return proquantity ;

    }

}
