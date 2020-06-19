package com.ivan.framework;

import java.util.ArrayList;

public class SoftWare {
    private OperationSystem operationSystem;
    private ArrayList<Program> programs;

    public SoftWare() {
        programs = new ArrayList<>();
    }

    public OperationSystem getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(OperationSystem operationSystem) {
        this.operationSystem = operationSystem;
    }

    public ArrayList<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(ArrayList<Program> programs) {
        this.programs = programs;
    }

    @Override
    public String toString() {
        String strProg = "";
        if(programs != null) {
            for (Program prog : programs) {
                strProg += prog + ", ";
            }
        }
        return "SoftWare{" +
                "operationSystem=" + operationSystem +
                ", programs=" + strProg +
                '}';
    }
}
