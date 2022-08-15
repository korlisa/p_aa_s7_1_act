package app.services;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    @Override
    public void sendFlightEventEmail(String address, String header, String message) {
        SimpleMailMessage flightMailMessage = new SimpleMailMessage();

        flightMailMessage.setTo(address);
        flightMailMessage.setSubject(header);
        flightMailMessage.setText(message);

        emailSender.send(flightMailMessage);
    }
}
