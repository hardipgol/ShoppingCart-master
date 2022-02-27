package com.shoppingcart.model;

public class ProductModel {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    String id,cid,name,image_path;
    int price,qty;

    public String getCart_item_id() {
        return cart_item_id;
    }

    public void setCart_item_id(String cart_item_id) {
        this.cart_item_id = cart_item_id;
    }

    String cart_item_id;
}
