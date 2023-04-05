package com.application.entities;

import java.util.ArrayList;

public class User {
  private Integer user_id;
  private String name;
  private ArrayList<Order> orders = new ArrayList<Order>();

  public User (Integer user_id, String name, Integer order_id, String date, Integer product_id, Double value) {
    this.user_id = user_id;
    this.name = name.trim();
    this.orders.add(new Order(order_id, date, product_id, value));
  }

  private int checkOrderExists(Integer orderId) {
    for(Order order : this.orders) {
      if(order.getOrderId().equals(orderId)){
        return this.orders.indexOf(order);
      }
     }
    return -1; 
  }

  public void addOrder(Integer order_id, String date, Integer product_id, Double value){
    int orderExists = checkOrderExists(order_id);
    if ( orderExists > -1){
      this.orders.get(orderExists).addProduct(product_id, value);
    } else {
      this.orders.add(new Order(order_id, date, product_id, value));
    }
  }

  public Integer getUserId(){
    return this.user_id;
  }

  public String getName(){
    return this.name;
  }
}
