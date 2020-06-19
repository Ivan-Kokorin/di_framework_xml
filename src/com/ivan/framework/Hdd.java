package com.ivan.framework;

public class Hdd {
    private int memory;
    private String company;

    public Hdd() {}

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Hdd{" +
                "memory=" + memory +
                ", company='" + company + '\'' +
                '}';
    }
}
