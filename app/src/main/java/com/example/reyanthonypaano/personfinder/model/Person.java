package com.example.reyanthonypaano.personfinder.model;

import com.couchbase.lite.Blob;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

public class Person {
    private BigInteger id;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String greetings;
    private String longitude;
    private String latitude;
    private Blob image;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGreetings() {
        return greetings;
    }

    public void setGreetings(String greetings) {
        this.greetings = greetings;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        DecimalFormat df = new DecimalFormat("#########.######");
        this.longitude = df.format(longitude);
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        DecimalFormat df = new DecimalFormat("#########.######");
        this.latitude = df.format(latitude);
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
