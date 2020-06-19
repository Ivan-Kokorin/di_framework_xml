package com.ivan.framework;

public class OperationSystem {
    private String name;
    private X32or64 x32or64;

    public OperationSystem() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public X32or64 getX32or64() {
        return x32or64;
    }

    public void setX32or64(X32or64 x32or64) {
        this.x32or64 = x32or64;
    }

    @Override
    public String toString() {
        return "OperationSystem{" +
                "name='" + name + '\'' +
                ", x32or64=" + x32or64 +
                '}';
    }
}
