package com.ivan.framework;

public class VideoCard {
    private String company;
    private String memory;

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

    @Override
    public String toString() {
        return "VideoCard{" +
                "company='" + company + '\'' +
                ", memory='" + memory + '\'' +
                '}';
    }
}
