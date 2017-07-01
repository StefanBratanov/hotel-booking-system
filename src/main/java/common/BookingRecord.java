package common;

import java.time.LocalDate;
import java.util.Objects;

public class BookingRecord {

    private final String guest;
    private final Integer room;
    private final LocalDate date;

    public BookingRecord(String guest, Integer room, LocalDate date) {
        this.guest = guest;
        this.room = room;
        this.date = date;
    }

    public Integer getRoom() {
        return room;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getGuest() {
        return guest;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BookingRecord)) {
            return false;
        }

        BookingRecord other = (BookingRecord) o;

        return Objects.equals(guest, other.getGuest()) &&
                Objects.equals(room, other.getRoom()) &&
                Objects.equals(date, other.getDate());

    }

    @Override
    public int hashCode() {
        return Objects.hash(guest, room, date);
    }
}
