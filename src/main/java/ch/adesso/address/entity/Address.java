package ch.adesso.address.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Data
@AllArgsConstructor
@ToString
public class Address {
    private String id;
    private String street;
    private String city;
    private String partyId;
}
