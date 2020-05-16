package com.example.ananops_android.entity;

import java.io.Serializable;

public class Replacement implements Serializable {

   /**
    * id : 887476148190064640
    * version : null
    * creator : ycadmin
    * creatorId : 886966462575543296
    * createdTime : 2020-05-09 11:36:41
    * lastOperator : ycadmin
    * lastOperatorId : 886966462575543296
    * updateTime : 2020-05-09 11:36:41
    * pageNum : null
    * pageSize : null
    * orderBy : null
    * name : IASZone_8753
    * price : null
    * type : IASZone
    * manufacture : Gantch
    * model : FB56-WTS01HM1.2
    * store : null
    * longitude : null
    * latitude : null
    * refNo : 887476148190064641
    * deviceId : ad496790-11a0-11ea-a2aa-13da45b8cdf8
    * groupId : 826086529397949440
    */

   private Long id;
   private String creator;
   private Long creatorId;
   private String createdTime;
   private String name;
   private int price;
   private String type;
   private String manufacture;
   private String model;
   private int store;
   private String deviceId;
   private Long groupId;
   private int count;

   public int getCount() {
      return count;
   }

   public void setCount(int count) {
      this.count = count;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getCreator() {
      return creator;
   }

   public void setCreator(String creator) {
      this.creator = creator;
   }

   public Long getCreatorId() {
      return creatorId;
   }

   public void setCreatorId(Long creatorId) {
      this.creatorId = creatorId;
   }

   public String getCreatedTime() {
      return createdTime;
   }

   public void setCreatedTime(String createdTime) {
      this.createdTime = createdTime;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getManufacture() {
      return manufacture;
   }

   public void setManufacture(String manufacture) {
      this.manufacture = manufacture;
   }

   public String getModel() {
      return model;
   }

   public void setModel(String model) {
      this.model = model;
   }

   public int getStore() {
      return store;
   }

   public void setStore(int store) {
      this.store = store;
   }

   public String getDeviceId() {
      return deviceId;
   }

   public void setDeviceId(String deviceId) {
      this.deviceId = deviceId;
   }

   public Long getGroupId() {
      return groupId;
   }

   public void setGroupId(Long groupId) {
      this.groupId = groupId;
   }
}
