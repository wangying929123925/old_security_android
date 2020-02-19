package com.example.ananops_android.entity;

import java.io.Serializable;

public class Replacement implements Serializable {
   private String name;
   private String id;
   private String type;
   private int count;
   private int store;
   private float price;
   private float replacement_totalPricce;
   private String manufacture;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public int getCount() {
      return count;
   }

   public void setCount(int count) {
      this.count = count;
   }

   public int getStore() {
      return store;
   }

   public void setStore(int store) {
      this.store = store;
   }

   public float getPrice() {
      return price;
   }

   public void setPrice(float price) {
      this.price = price;
   }

   public float getReplacement_totalPricce() {
      return replacement_totalPricce;
   }

   public void setReplacement_totalPricce(float replacement_totalPricce) {
      this.replacement_totalPricce = replacement_totalPricce;
   }

   public String getManufacture() {
      return manufacture;
   }

   public void setManufacture(String manufacture) {
      this.manufacture = manufacture;
   }
}
