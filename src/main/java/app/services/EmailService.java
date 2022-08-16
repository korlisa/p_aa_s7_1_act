package app.services;


/** интерфейс для расылки писем
 * @author Minibaeva Elvira
 */
public interface EmailService {
    void sendSimpleEmail(final String toAddress, final String subject, final String message);

    void sendFlightEventEmail(final String address, String header, String message);
}
