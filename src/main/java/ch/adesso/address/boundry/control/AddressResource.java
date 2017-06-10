package ch.adesso.address.boundry.control;

import ch.adesso.address.boundry.entity.Address;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("address")
@Produces(MediaType.APPLICATION_JSON)
public class AddressResource {
    @GET
    public Address address(){
        return new Address("street1", "city1");
    }
}