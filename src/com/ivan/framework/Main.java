package com.ivan.framework;

import com.ivan.framework.di.Context;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) {
        Computer computer = new Computer();
        Context context = new Context("settings.xml");
        System.out.println(context.getById("computer"));
    }
}
