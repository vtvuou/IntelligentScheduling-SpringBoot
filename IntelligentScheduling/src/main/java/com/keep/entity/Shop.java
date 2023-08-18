package com.keep.entity;

public class Shop {
    private String ID;
    private String shopName;
    private String address;
    private Double size;

    public Shop() {}
    public Shop(String ID, String shopName, String address, Double size) {
        this.ID = ID;
        this.shopName = shopName;
        this.address = address;
        this.size = size;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "ID='" + ID + '\'' +
                ", shopName='" + shopName + '\'' +
                ", address='" + address + '\'' +
                ", size=" + size +
                '}';
    }
}
