package io.presentation.jpa.entitymapping.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created By Minhyuk Yoon on 2018. 8. 1.
 */
@Embeddable
public class Address {

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    protected Address() {
    }

    public Address(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }
}
