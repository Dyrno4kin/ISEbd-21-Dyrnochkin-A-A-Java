package com.dyrno4kin;

public class HangarOccupiedPlaceException extends Exception{
    public HangarOccupiedPlaceException(int i) {
        super("На месте " + i + " уже стоит самолет");
    }
}
