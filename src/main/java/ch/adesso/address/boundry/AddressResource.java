package ch.adesso.address.boundry;

import ch.adesso.address.entity.Address;
import ch.adesso.address.kafka.KafkaProvider;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;


@Path("address")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class AddressResource {
    final Logger logger = Logger.getLogger(AddressResource.class.getSimpleName());

    @Inject
    private KafkaProducer<String, String> producer;

    @GET
    public Address address(){
        UUID addressUuid = UUID.randomUUID();
        UUID partyUuid = UUID.randomUUID();
        return new Address(addressUuid.toString(),"street1", "city1", partyUuid.toString());
    }

    @POST
    public Address create(Address address){
        setId(address);
        produceRecord(address);
        logger.log(Level.INFO,"Creating: "+address.toString());
        return address;
    }

    private void setId(Address address) {
        UUID uuid = UUID.randomUUID();
        address.setId(uuid.toString());
    }

    private void produceRecord(Address address) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(KafkaProvider.TOPIC, address.toString());
        producer.send(producerRecord);
    }
}
