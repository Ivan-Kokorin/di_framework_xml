package com.ivan.framework;

public class MsOffice extends Program {
    @Override
    public void run() {
        System.out.println("Офис запущен");
    }

    @Override
    public void stop() {
        System.out.println("Работа офиса завершена");
    }
}
