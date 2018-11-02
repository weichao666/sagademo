package scb.car;

import org.apache.servicecomb.saga.omega.transaction.annotations.Compensable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CarBookingService {
    private Map<Integer, CarBooking> bookings = new ConcurrentHashMap<>();

    @Compensable(compensationMethod = "cancel")
    void order(CarBooking booking) {
        booking.confirm();
        bookings.put(booking.getId(), booking);
    }

    void cancel(CarBooking booking) {
        Integer id = booking.getId();
        if (bookings.containsKey(id)) {
            bookings.get(id).cancel();
        }
    }

    Collection<CarBooking> getAllBookings() {
        return bookings.values();
    }

    void clearAllBookings() {
        bookings.clear();
    }
}
