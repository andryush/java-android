package com.arakelyan.gunshop;

public class Guns {

    private String gunName;
    private String gunDescription;
    private int gunImageId;

    public Guns() {
    }

    public Guns(String gunName, String gunDescription, int gunImageId) {
        this.gunName = gunName;
        this.gunDescription = gunDescription;
        this.gunImageId = gunImageId;
    }

    public String getGunName() {
        return gunName;
    }

    public String getGunDescription() {
        return gunDescription;
    }

    public int getGunImageId() {
        return gunImageId;
    }

    @Override
    public String toString() {
        return gunName;
    }
}
