package com.arkpes.clientbrowser.client;

public class Client {
    private long id;
    private String name;
    private String description;
    private int numOfInvestments;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumOfInvestments() {
        return numOfInvestments;
    }

    public void setNumOfInvestments(int numOfInvestments) {
        this.numOfInvestments = numOfInvestments;
    }
}
