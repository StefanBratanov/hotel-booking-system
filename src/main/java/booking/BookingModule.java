package booking;

import com.google.inject.AbstractModule;
import common.BookingManager;

import java.util.Arrays;

public class BookingModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(BookingManager.class)
                .toInstance(HotelBookingManager.create(Arrays.asList(101, 102, 201, 203)));
    }
}
