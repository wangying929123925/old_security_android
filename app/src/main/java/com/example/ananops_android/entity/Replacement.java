package com.example.ananops_android.entity;

import java.io.Serializable;

public class Replacement implements Serializable {
   private String repalcement_name;
   private String repalcement_id;
   private String replacement_type;
   private int replacement_num;
   private int replacement_inventory;
   private float replacement_price;
   private float replacement_totalPricce;

   public String getRepalcement_name() {
      return repalcement_name;
   }

   public void setRepalcement_name(String repalcement_name) {
      this.repalcement_name = repalcement_name;
   }

   public String getRepalcement_id() {
      return repalcement_id;
   }

   public void setRepalcement_id(String repalcement_id) {
      this.repalcement_id = repalcement_id;
   }

   public String getReplacement_type() {
      return replacement_type;
   }

   public void setReplacement_type(String replacement_type) {
      this.replacement_type = replacement_type;
   }

   public int getReplacement_num() {
      return replacement_num;
   }

   public void setReplacement_num(int replacement_num) {
      this.replacement_num = replacement_num;
   }

   public int getReplacement_inventory() {
      return replacement_inventory;
   }

   public void setReplacement_inventory(int replacement_inventory) {
      this.replacement_inventory = replacement_inventory;
   }

   public float getReplacement_price() {
      return replacement_price;
   }

   public void setReplacement_price(float replacement_price) {
      this.replacement_price = replacement_price;
   }

   public float getReplacement_totalPricce() {
      return replacement_totalPricce;
   }

   public void setReplacement_totalPricce(float replacement_totalPricce) {
      this.replacement_totalPricce = replacement_totalPricce;
   }
}
