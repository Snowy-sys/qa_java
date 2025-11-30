package com.example;

import java.util.List;

public class Lion {

    public Predator predator;

    boolean hasMane;

    public Lion(String sex, Predator predator) throws Exception {
        if ("Самец".equals(sex)) {
            hasMane = true;
        } else if ("Самка".equals(sex)) {
            hasMane = false;
        } else {
            throw new Exception("Используйте допустимые значения пола животного - самей или самка");
        }

        this.predator = predator;
    }

    // Реализовал с помощью инъекции зависимости. В создании объекта нет необходимости
    // Feline feline = new Feline();

    public int getKittens() {
        return predator.getKittens();
    }

    public boolean doesHaveMane() {
        return hasMane;
    }

    public List<String> getFood() throws Exception {
        return predator.getFood("Хищник");
    }
}
