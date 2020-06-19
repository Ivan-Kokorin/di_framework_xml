package com.ivan.framework;

import com.ivan.framework.di.Context;

public class Main {
    public static void main(String[] args) {
//        Computer computer = new Computer();
        Context context = new Context();
        context.mapObject.put("Computer", new Computer());
        Computer computer = (Computer) (context.getById("Computer"));
        System.out.println(computer);
    }
}
