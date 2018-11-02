package scb.car;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestSchema(schemaId = "car")
@RequestMapping(path = "/")
public class CarBookingController {
    @Autowired
    private CarBookingService service;

    private final AtomicInteger id = new AtomicInteger(0);

    @CrossOrigin
    @GetMapping(path = "/bookings")
    public List<CarBooking> getAll() {
        return new ArrayList<>(service.getAllBookings());
    }

    @PostMapping(path = "/order/{name}/{cars}")
    public CarBooking order(@PathVariable String name, @PathVariable Integer cars) {
        CarBooking booking = new CarBooking();
        booking.setId(id.incrementAndGet());
        booking.setName(name);
        booking.setAmount(cars);
        service.order(booking);
        return booking;
    }

    @DeleteMapping(path = "/bookings")
    void clear() {
        service.clearAllBookings();
        id.set(0);
    }
}
