package booking;

import com.google.common.collect.Sets;
import common.BookingManager;
import common.BookingRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class HotelBookingManager implements BookingManager {

    private final Set<BookingRecord> bookings = Sets.newConcurrentHashSet();
    private final List<Integer> hotelRooms;

    private HotelBookingManager(List<Integer> hotelRooms) {
        this.hotelRooms = hotelRooms;
    }

    public boolean isRoomAvailable(Integer room, LocalDate date) {
        return bookings.stream()
                .filter(booking -> booking.getDate().equals(date))
                .noneMatch(booking -> booking.getRoom().equals(room));
    }

    public void addBooking(String guest, Integer room, LocalDate date) {
        if (!isRoomAvailable(room, date)) {
            throw new IllegalStateException(format("Room %d has already been booked for %s",
                    room, date));
        }
        if (!hotelRooms.contains(room)) {
            throw new IllegalStateException(format("Room %d is not a valid room in the hotel", room));
        }
        BookingRecord bookingRecord = new BookingRecord(guest, room, date);
        bookings.add(bookingRecord);
    }

    public Iterable<Integer> getAvailableRooms(LocalDate date) {
        return hotelRooms.stream()
                .filter(room -> isRoomAvailable(room,date))
                .collect(Collectors.toList());
    }

    public static HotelBookingManager create(List<Integer> hotelRooms) {
        return new HotelBookingManager(hotelRooms);
    }
}
