package com.formacion.bosonit.block17batch.config.batch.util;

public class ErrorCounter {
    private int count = 0;
    public int getCount() {
        return count;
    }
    public void increment() {
        count++;
    }
}