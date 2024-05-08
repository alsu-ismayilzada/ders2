 package org.example.model;

public class Auto {

    private String price;
    private String region;
    private String brand;
    private String model;
    private String graduationYear;
    private String banType;
    private String color;
    private String engine;
    private String mileage;
    private String transmission;
    private String gear;
    private String isNew;
    private String seats;
    private String situation;
    private String market;

    public Auto(String price, String region, String brand, String model, String graduationYear, String banType, String color, String engine, String mileage, String transmission, String gear, String isNew, String seats, String situation, String market) {
        this.price = price;
        this.region = region;
        this.brand = brand;
        this.model = model;
        this.graduationYear = graduationYear;
        this.banType = banType;
        this.color = color;
        this.engine = engine;
        this.mileage = mileage;
        this.transmission = transmission;
        this.gear = gear;
        this.isNew = isNew;
        this.seats = seats;
        this.situation = situation;
        this.market = market;
    }

    public String getPrice() {
        return price;
    }

    public String getRegion() {
        return region;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public String getBanType() {
        return banType;
    }

    public String getColor() {
        return color;
    }

    public String getEngine() {
        return engine;
    }

    public String getMileage() {
        return mileage;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getGear() {
        return gear;
    }

    public String getIsNew() {
        return isNew;
    }

    public String getSeats() {
        return seats;
    }

    public String getSituation() {
        return situation;
    }

    public String getMarket() {
        return market;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    public void setBanType(String banType) {
        this.banType = banType;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public void setMarket(String market) {
        this.market = market;
    }
}
