package com.application.entities;

import java.util.ArrayList;

public class Order {
  private Integer order_id;
  private Double total;
  private String date;
  private ArrayList<Product> products = new ArrayList<Product>();

  public Order (Integer order_id, String date, Integer product_id, Double value){
    this.order_id = order_id;
    this.date = date;
    this.total = value;
    this.products.add(new Product(product_id, value));
  }

  public void addProduct(Integer product_id, Double value){
    this.products.add(new Product(product_id, value));
    this.total += value;
  }

  public Integer getOrderId(){
    return this.order_id;
  }

  public Double getTotal(){
    return this.total;
  }

  public String getDate(){
    return this.date;
  }
}
