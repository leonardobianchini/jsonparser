package com.application.utils;

public class OrderHelper {

  public Integer user_id;
  public String name;
  public Integer order_id;
  public Integer product_id;
  public String date;
  public Double value;

  private static String formatDate(String date) {
    String year = date.substring(0, 4);
    String month = date.substring(4, 6);
    String day = date.substring(6, 8);
    return year + "-" + month + "-" + day;
  }

  public OrderHelper getAttributes(String line) {
    String idUser = line.substring(0, 10);
    this.user_id = Integer.parseInt(idUser);
    this.name = line.substring(10, 55);
    String idOrder = line.substring(55, 65);
    this.order_id = Integer.parseInt(idOrder);
    String idProd = line.substring(65, 75);
    this.product_id = Integer.parseInt(idProd);
    String value = line.substring(75, 87);
    this.value = Double.parseDouble(value);
    this.date = formatDate(line.substring(87, 95));

    return this;
  }
}
