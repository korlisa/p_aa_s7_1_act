package app.util;

/**
 * Enum with category of Seats
 * {@value = BUSINESS}
 * {@value = ECONOMY}
 *
 *
 *
 * @author Eugene Kolyshev
 */
public enum Category {

    BUSINESS("Business"),
    ECONOMY ("Economy");

    private final String categoryName;

    Category(String category) {
        this.categoryName = category;
    }

    public String toString() {
        return this.categoryName;
    }
}
