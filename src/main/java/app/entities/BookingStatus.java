package app.entities;

public enum BookingStatus {
    RESERVED("reserved"),
    PAID("paid"),
    CANCELLED("cancelled"),
    CONFIRMED("confirmed"),
    CHECKED_IN("checked_in"),
    BOARDED("boarded"),
    DELAYED("delayed"),
    ARRIVED("arrived");

    private String value;

    BookingStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
