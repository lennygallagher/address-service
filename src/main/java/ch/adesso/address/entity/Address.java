package ch.adesso.address.entity;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by gallagher on 10.06.2017.
 */
public class Address {
    private final String street;
    private final String city;

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

}
