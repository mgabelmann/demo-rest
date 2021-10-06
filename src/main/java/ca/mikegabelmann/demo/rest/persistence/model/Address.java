package ca.mikegabelmann.demo.rest.persistence.model;

public class Address {
    private String streetAddress;
    private String city;
    private String postal;

    public Address(String streetAddress, String city, String postal) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.postal = postal;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getPostal() {
        return postal;
    }
}
