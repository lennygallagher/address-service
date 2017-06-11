package ch.adesso.address.boundry;

import ch.adesso.address.entity.Address;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Path("address")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressResource {
    List<Address> addresses = new ArrayList<>();

    final Logger logger = Logger.getLogger(AddressResource.class.getSimpleName());

    @GET
    public Address address(){
        return new Address("street1", "city1");
    }

    @POST
    public void create(Address address){
        addresses.add(address);
        logger.log(Level.INFO,"adding: "+address.toString());
    }
}
