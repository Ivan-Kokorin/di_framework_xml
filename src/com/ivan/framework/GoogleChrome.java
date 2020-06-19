package com.ivan.framework;

public class GoogleChrome extends Program {
    @Override
    public void run() {
        System.out.println("Гугл Хром запущен");
    }

    @Override
    public void stop() {
        System.out.println("Работа Гугл Хром завершена");
    }
}
