package com.ivan.framework;

public class MatherBoard {
    private Chipseat chipseat;
    private String model;

    public MatherBoard() {}

    public Chipseat getChipseat() {
        return chipseat;
    }

    public void setChipseat(Chipseat chipseat) {
        this.chipseat = chipseat;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "MatherBoard{" +
                "chipseat=" + chipseat +
                ", model='" + model + '\'' +
                '}';
    }
}
