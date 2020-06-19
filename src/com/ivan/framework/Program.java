package com.ivan.framework;

public abstract class Program {
    private String name;
    private String version;

    public Program() {}

    public void run() {
        System.out.println("Программа запущена");
    }

    public void stop() {
        System.out.println("Работа программы завершена");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Program{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
