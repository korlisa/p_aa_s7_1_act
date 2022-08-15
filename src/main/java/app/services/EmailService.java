package app.services;


public interface EmailService {

    void sendFlightEventEmail(final String address, String header, String message);
}
