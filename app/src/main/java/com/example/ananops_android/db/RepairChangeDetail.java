package com.example.ananops_android.db;

public class RepairChangeDetail {

    /**
     * id : 1081284045918424771
     * level : 1
     * result : 0
     * suggestion : 无
     * title : string
     * totalCost : 5.0
     */

    private long id;
    private int level;
    private int result;
    private String suggestion;
    private String title;
    private double totalCost;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
