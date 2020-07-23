package ru.job4j_design_new.lsp_new.car;

import com.sun.source.tree.IfTree;

public class Car {

    private String name;
    private double weight;
    private boolean standardPlace = true;

    public Car(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isStandardPlace() {
        return standardPlace;
    }

    public void setStandardPlace(boolean standardPlace) {
        this.standardPlace = standardPlace;
    }


}
