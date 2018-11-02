package scb.hotel;

import org.apache.servicecomb.saga.omega.transaction.annotations.Compensable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class HotelBookingService {
    private Map<Integer, HotelBooking> bookings = new ConcurrentHashMap<>();

    @Compensable(compensationMethod = "cancel")
    void order(HotelBooking booking) {
        if (booking.getAmount() > 2) {
            throw new IllegalArgumentException("can not order the rooms large than two");
        }
        booking.confirm();
        bookings.put(booking.getId(), booking);
    }

    void cancel(HotelBooking booking) {
        Integer id = booking.getId();
        if (bookings.containsKey(id)) {
            bookings.get(id).cancel();
        }
    }

    Collection<HotelBooking> getAllBookings() {
        return bookings.values();
    }

    void clearAllBookings() {
        bookings.clear();
    }
}
