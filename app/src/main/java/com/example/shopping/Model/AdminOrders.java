package com.example.shopping.Model;

 public class AdminOrders {
     private String  name, phone, address, city, totalAmount, date, time;

     public AdminOrders(){}

     public AdminOrders(String name, String phone, String address, String city, String totalAmount, String date, String time) {
         this.name = name;
         this.phone = phone;
         this.address = address;
         this.city = city;
         this.totalAmount = totalAmount;
         this.date = date;
         this.time = time;
     }


     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getPhone() {
         return phone;
     }

     public void setPhone(String phone) {
         this.phone = phone;
     }

     public String getAddress() {
         return address;
     }

     public void setAddress(String address) {
         this.address = address;
     }

     public String getCity() {
         return city;
     }

     public void setCity(String city) {
         this.city = city;
     }

     public String getTotalAmount() {
         return totalAmount;
     }

     public void setTotalAmount(String totalAmount) {
         this.totalAmount = totalAmount;
     }

     public String getDate() {
         return date;
     }

     public void setDate(String date) {
         this.date = date;
     }

     public String getTime() {
         return time;
     }

     public void setTime(String time) {
         this.time = time;
     }
 }
