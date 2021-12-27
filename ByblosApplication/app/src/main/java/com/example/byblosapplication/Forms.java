package com.example.byblosapplication;

public class Forms {

    boolean accepted;
    String firstLast;
    String address;
    String dateOfBirth;
    String emailString;
    String licenseType;
    String carType;
    String returnDate;
    String pickUpDate;
    String maxKilometers;
    String truckArea;
    String movingStart;
    String movingEnd;
    String numberMovers;
    String numberBoxes;
    String username;
    String serviceName;

    public Forms() {
    }

    public Forms(boolean accepted, String firstLast, String address, String dateOfBirth, String emailString, String licenseType, String carType, String returnDate, String pickUpDate, String maxKilometers, String truckArea, String movingStart, String movingEnd, String numberMovers, String numberBoxes, String username, String serviceName) {
        this.accepted = accepted;
        this.firstLast = firstLast;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.emailString = emailString;
        this.licenseType = licenseType;
        this.carType = carType;
        this.returnDate = returnDate;
        this.pickUpDate = pickUpDate;
        this.maxKilometers = maxKilometers;
        this.truckArea = truckArea;
        this.movingStart = movingStart;
        this.movingEnd = movingEnd;
        this.numberMovers = numberMovers;
        this.numberBoxes = numberBoxes;
        this.username = username;
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
    public String getFirstLast() {
        return firstLast;
    }

    public void setFirstLast(String firstLast) {
        this.firstLast = firstLast;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailString() {
        return emailString;
    }

    public void setEmailString(String emailString) {
        this.emailString = emailString;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getMaxKilometers() {
        return maxKilometers;
    }

    public void setMaxKilometers(String maxKilometers) {
        this.maxKilometers = maxKilometers;
    }

    public String getTruckArea() {
        return truckArea;
    }

    public void setTruckArea(String truckArea) {
        this.truckArea = truckArea;
    }

    public String getMovingStart() {
        return movingStart;
    }

    public void setMovingStart(String movingStart) {
        this.movingStart = movingStart;
    }

    public String getMovingEnd() {
        return movingEnd;
    }

    public void setMovingEnd(String movingEnd) {
        this.movingEnd = movingEnd;
    }

    public String getNumberMovers() {
        return numberMovers;
    }

    public void setNumberMovers(String numberMovers) {
        this.numberMovers = numberMovers;
    }

    public String getNumberBoxes() {
        return numberBoxes;
    }

    public void setNumberBoxes(String numberBoxes) {
        this.numberBoxes = numberBoxes;
    }
}
