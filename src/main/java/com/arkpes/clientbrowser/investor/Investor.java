package com.arkpes.clientbrowser.investor;

public class Investor {
    private long id;
    private String name;
    private int numOfFunds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfFunds() {
        return numOfFunds;
    }

    public void setNumOfFunds(int numOfFunds) {
        this.numOfFunds = numOfFunds;
    }
}
