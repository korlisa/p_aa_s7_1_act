package app.entities;

public enum BaggageType {
    CARRY_ON_BAGGAGE("carry_on_baggage"),
    CHECKED_BAGGAGE("checked_baggage");

    private String value;

    BaggageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
