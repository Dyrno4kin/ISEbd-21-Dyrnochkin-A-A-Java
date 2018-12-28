package com.dyrno4kin;

public class HangarNotFoundException extends Exception{
    public HangarNotFoundException(int i) {
        super("Не найден самолет по месту " + i);
    }
}
