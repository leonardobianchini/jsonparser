package com.application.entities;

public class Product {
  private Integer product_id;
  private Double value;

  public Product(Integer product_id, Double value) {
    this.product_id = product_id;
    this.value = value;
  }

  public Integer getProductId() {
    return this.product_id;
  }

  public Double getValue() {
    return this.value;
  }
}
