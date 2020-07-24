package com.example.whitneybb.utils.randomDuties;

import java.util.Random;

public abstract class DoWork {

    public DoWork() {

    }

    public int giveNumber() {
        Random r = new Random();
        return r.nextInt(1000);
    }

}
