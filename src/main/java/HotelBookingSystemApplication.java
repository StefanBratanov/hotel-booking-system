import booking.BookingModule;
import booking.HotelBookingManager;
import com.google.inject.Guice;
import com.google.inject.Injector;
import common.BookingManager;

import java.time.LocalDate;
import java.util.Arrays;

public class HotelBookingSystemApplication {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BookingModule());
        BookingManager bookingManager = injector.getInstance(BookingManager.class);

        LocalDate today = LocalDate.of(2012, 7, 21);
        System.out.println(bookingManager.isRoomAvailable(101, today));
        bookingManager.addBooking("Smith", 101, today);
        System.out.println(bookingManager.getAvailableRooms(today));
        System.out.println(bookingManager.isRoomAvailable(101, today));
        bookingManager.addBooking("Jones", 101, today);

    }
}
