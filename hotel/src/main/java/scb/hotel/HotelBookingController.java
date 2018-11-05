package scb.hotel;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestSchema(schemaId = "hotel")
@RequestMapping(path = "/")
public class HotelBookingController {

    @Autowired
    private HotelBookingService service;

    private final AtomicInteger id = new AtomicInteger(0);

    @CrossOrigin
    @GetMapping(path = "/bookings")
    public List<HotelBooking> getAll() {
        return new ArrayList<>(service.getAllBookings());
    }

    @PostMapping(path = "/order/{name}/{rooms}")
    public HotelBooking order(@PathVariable String name, @PathVariable Integer rooms) {
        HotelBooking booking = new HotelBooking();
        booking.setId(id.incrementAndGet());
        booking.setName(name);
        booking.setAmount(rooms);
        service.order(booking);
        return booking;
    }

    @DeleteMapping(path = "/bookings")
    public void clear() {
        service.clearAllBookings();
        id.set(0);
    }
}
