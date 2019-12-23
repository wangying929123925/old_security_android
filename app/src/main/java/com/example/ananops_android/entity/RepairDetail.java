package com.example.ananops_android.entity;

public class RepairDetail {
    private String actual_start_time;
    private String actual_finish_time;
    private float cost;
    private int count;

    public String getActual_start_time() {
        return actual_start_time;
    }

    public void setActual_start_time(String actual_start_time) {
        this.actual_start_time = actual_start_time;
    }

    public String getActual_finish_time() {
        return actual_finish_time;
    }

    public void setActual_finish_time(String actual_finish_time) {
        this.actual_finish_time = actual_finish_time;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
