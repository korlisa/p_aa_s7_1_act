package app.entities;

public enum AdditionalServices {
    EXTRA_LEGROOM_SEAT("extra_legroom_seat"),
    PRIORITY_BOARDING("priority_boarding"),
    MEALS("meals"),
    BLANKET_AND_PILLOW("blanket_and_pillow"),
    ENTERTAINMENT("entertainment"),
    INFLIGHT_WIFI("inflight_wifi"),
    DUTY_FREE("duty_free"),
    LOUNGE_ACCESS("lounge_access");

    private String value;

    AdditionalServices(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
