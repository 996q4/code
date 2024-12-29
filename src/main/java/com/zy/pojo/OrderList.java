package com.zy.pojo;

public class OrderList {

    private int id;
    private String order_id;
    private int product_id;
    private int product_quantity;

    public OrderList() {

    }

    public OrderList(String order_id, int product_id, int product_quantity) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.product_quantity = product_quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "order_id='" + order_id + '\'' +
                ", product_id=" + product_id +
                ", product_quantity='" + product_quantity + '\'' +
                '}';
    }
}
