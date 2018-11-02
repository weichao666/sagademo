package scb.booking;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.apache.servicecomb.saga.omega.context.annotations.SagaStart;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@RestSchema(schemaId = "booking")
@RequestMapping(path = "/")
public class BookingController {
    private RestTemplate template = RestTemplateBuilder.create();

    @SagaStart
    @PostMapping("/booking/{name}/{rooms}/{cars}")
    public String order(@PathVariable String name, @PathVariable Integer rooms, @PathVariable Integer cars) {
        template.postForEntity(
                "cse://car/order/{name}/{cars}",
                null, String.class, name, cars);
        template.postForEntity(
                "cse://hotel/order/{name}/{rooms}",
                null, String.class, name, rooms);
        postBooking();

        return name + " booking " + rooms + " rooms and " + cars + " cars OK";
    }

    //This method is used by the byteman to inject the faults such as the timeout or the crash
    private void postBooking() {
    }
}
