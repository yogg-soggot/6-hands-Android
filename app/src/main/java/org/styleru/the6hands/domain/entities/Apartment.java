package org.styleru.the6hands.domain.entities;

import org.parceler.Parcel;

import java.util.List;

@Parcel(Parcel.Serialization.BEAN)
public class Apartment {

    private long id, idUser, idFacility;
    private String address;
    private int numberOfRooms, numberOfMeters, livingSpace, kitchen, ceilingHeight, floor,
            numberOfFloorsInBuilding, numberOfBalconies, numberOfLoggias, price;

    private List<Facility> facilities;
    private List<Image> images;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdFacility() {
        return idFacility;
    }

    public void setIdFacility(long idFacility) {
        this.idFacility = idFacility;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfMeters() {
        return numberOfMeters;
    }

    public void setNumberOfMeters(int numberOfMeters) {
        this.numberOfMeters = numberOfMeters;
    }

    public int getLivingSpace() {
        return livingSpace;
    }

    public void setLivingSpace(int livingSpace) {
        this.livingSpace = livingSpace;
    }

    public int getKitchen() {
        return kitchen;
    }

    public void setKitchen(int kitchen) {
        this.kitchen = kitchen;
    }

    public int getCeilingHeight() {
        return ceilingHeight;
    }

    public void setCeilingHeight(int ceilingHeight) {
        this.ceilingHeight = ceilingHeight;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getNumberOfFloorsInBuilding() {
        return numberOfFloorsInBuilding;
    }

    public void setNumberOfFloorsInBuilding(int numberOfFloorsInBuilding) {
        this.numberOfFloorsInBuilding = numberOfFloorsInBuilding;
    }

    public int getNumberOfBalconies() {
        return numberOfBalconies;
    }

    public void setNumberOfBalconies(int numberOfBalconies) {
        this.numberOfBalconies = numberOfBalconies;
    }

    public int getNumberOfLoggias() {
        return numberOfLoggias;
    }

    public void setNumberOfLoggias(int numberOfLoggias) {
        this.numberOfLoggias = numberOfLoggias;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
