package com.example.byblosapplication;

public class Services {

    String serviceName;
    int hourlyRate;


    boolean firstLastV;
    boolean addressV;
    boolean dateOfBirthV;
    boolean emailStringV;
    boolean licenseTypeV;
    boolean carTypeV;
    boolean returnDateV;
    boolean pickUpDateV;
    boolean maxKilometersV;
    boolean truckAreaV;
    boolean movingStartV;
    boolean movingEndV;
    boolean numberMoversV;
    boolean numberBoxesV;


    public Services(String serviceName, int hourlyRate, boolean firstLastV, boolean addressV, boolean emailStringV, boolean licenseTypeV, boolean carType, boolean returnDate, boolean pickUpDate, boolean maxKilometers, boolean truckArea, boolean movingStart, boolean movingEnd, boolean numberMovers, boolean numberBoxes,boolean dateOfBirthV) {
        this.serviceName = serviceName;
        this.hourlyRate = hourlyRate;
        this.firstLastV = firstLastV;
        this.addressV = addressV;
        this.emailStringV = emailStringV;
        this.licenseTypeV = licenseTypeV;
        this.carTypeV = carType;
        this.returnDateV = returnDate;
        this.pickUpDateV = pickUpDate;
        this.maxKilometersV = maxKilometers;
        this.truckAreaV = truckArea;
        this.movingStartV = movingStart;
        this.movingEndV = movingEnd;
        this.numberMoversV = numberMovers;
        this.numberBoxesV = numberBoxes;
        this.dateOfBirthV =  dateOfBirthV;
    }

    public Services() {
    }

    public boolean isDateOfBirthV() {
        return dateOfBirthV;
    }

    public void setDateOfBirthV(boolean dateOfBirthV) {
        this.dateOfBirthV = dateOfBirthV;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public boolean isFirstLastV() {
        return firstLastV;
    }

    public void setFirstLastV(boolean firstLastV) {
        this.firstLastV = firstLastV;
    }

    public boolean isEmailStringV() {
        return emailStringV;
    }

    public void setEmailStringV(boolean emailStringV) {
        this.emailStringV = emailStringV;
    }

    public boolean isLicenseTypeV() {
        return licenseTypeV;
    }

    public void setLicenseTypeV(boolean licenseTypeV) {
        this.licenseTypeV = licenseTypeV;
    }

    public boolean isCarTypeV() {
        return carTypeV;
    }

    public void setCarTypeV(boolean carTypeV) {
        this.carTypeV = carTypeV;
    }

    public boolean isAddressV() {
        return addressV;
    }

    public void setAddressV(boolean addressV) {
        this.addressV = addressV;
    }

    public boolean isReturnDateV() {
        return returnDateV;
    }

    public void setReturnDateV(boolean returnDateV) {
        this.returnDateV = returnDateV;
    }

    public boolean isPickUpDateV() {
        return pickUpDateV;
    }

    public void setPickUpDateV(boolean pickUpDateV) {
        this.pickUpDateV = pickUpDateV;
    }

    public boolean isMaxKilometersV() {
        return maxKilometersV;
    }

    public void setMaxKilometersV(boolean maxKilometersV) {
        this.maxKilometersV = maxKilometersV;
    }

    public boolean isTruckAreaV() {
        return truckAreaV;
    }

    public void setTruckAreaV(boolean truckAreaV) {
        this.truckAreaV = truckAreaV;
    }

    public boolean isMovingStartV() {
        return movingStartV;
    }

    public void setMovingStartV(boolean movingStartV) {
        this.movingStartV = movingStartV;
    }

    public boolean isMovingEndV() {
        return movingEndV;
    }

    public void setMovingEndV(boolean movingEndV) {
        this.movingEndV = movingEndV;
    }

    public boolean isNumberMoversV() {
        return numberMoversV;
    }

    public void setNumberMoversV(boolean numberMoversV) {
        this.numberMoversV = numberMoversV;
    }

    public boolean isNumberBoxesV() {
        return numberBoxesV;
    }

    public void setNumberBoxesV(boolean numberBoxesV) {
        this.numberBoxesV = numberBoxesV;
    }

    @Override
    public String toString() {
        return "Services{}";
    }
}
