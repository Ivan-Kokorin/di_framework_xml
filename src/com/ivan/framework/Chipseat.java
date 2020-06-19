package com.ivan.framework;

public class Chipseat {
    private String model;

    public Chipseat() {}

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Chipseat{" +
                "model='" + model + '\'' +
                '}';
    }
}
