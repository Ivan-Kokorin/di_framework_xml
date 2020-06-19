package com.ivan.framework;

public class VideoCard {
    private String company;
    private String memory;
    private Cooler cooler;

    public VideoCard() {}

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public Cooler getCooler() {
        return cooler;
    }

    public void setCooler(Cooler cooler) {
        this.cooler = cooler;
    }

    @Override
    public String toString() {
        return "VideoCard{" +
                "company='" + company + '\'' +
                ", memory='" + memory + '\'' +
                ", cooler=" + cooler +
                '}';
    }
}
