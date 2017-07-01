package booking;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class HotelBookingManagerTest {

    private HotelBookingManager underTest;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        underTest = HotelBookingManager.create(Arrays.asList(101,102,201,203));
    }

    @Test
    public void checksIfRoomIsAvailable() {
        underTest.addBooking("Smith",102, LocalDate.of(2017,6,30));
        underTest.addBooking("Anderson",201, LocalDate.of(2017,6,30));
        underTest.addBooking("Berbatov",102, LocalDate.of(2017,6,29));

        assertThat(underTest.isRoomAvailable(102,LocalDate.of(2017,6,30))).isFalse();
        assertThat(underTest.isRoomAvailable(102,LocalDate.of(2017,6,29))).isFalse();
        assertThat(underTest.isRoomAvailable(201,LocalDate.of(2017,6,30))).isFalse();
        assertThat(underTest.isRoomAvailable(102,LocalDate.of(2017,6,28))).isTrue();
        assertThat(underTest.isRoomAvailable(201,LocalDate.of(2017,6,29))).isTrue();
    }

    @Test
    public void failsToAddBookingIfRoomIsAlreadyBooked() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Room 102 has already been booked for 2017-06-30");

        underTest.addBooking("Smith",102, LocalDate.of(2017,6,30));
        underTest.addBooking("Anderson",102,LocalDate.of(2017,6,30));
    }

    @Test
    public void failsToAddBookingIfRoomNumberIsNotAValidRoom() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Room 99 is not a valid room in the hotel");

        underTest.addBooking("Smith",99, LocalDate.of(2017,6,30));
    }

    @Test
    public void addsBookingWithoutExceptions() {
        underTest.addBooking("Smith",102, LocalDate.of(2017,6,30));
    }

    @Test
    public void getsAvailableRooms() {
        LocalDate date1 = LocalDate.of(2017, 6, 30);
        LocalDate date2 = LocalDate.of(2017, 6, 29);
        LocalDate date3 = LocalDate.of(2017, 6, 28);

        underTest.addBooking("Smith",101, date1);
        underTest.addBooking("Anderson",102, date1);
        underTest.addBooking("Anderson",102, date2);
        underTest.addBooking("Berbatov",201, date1);
        underTest.addBooking("Berbatov",201, date2);
        underTest.addBooking("Johnson",203, date1);

        assertThat(underTest.getAvailableRooms(date1)).isEmpty();
        assertThat(underTest.getAvailableRooms(date2)).containsExactlyInAnyOrder(101,203);
        assertThat(underTest.getAvailableRooms(date3)).containsExactlyInAnyOrder(101,102,201,203);
    }

}