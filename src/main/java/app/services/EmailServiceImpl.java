package app.services;

import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.util.stream.Collectors;

/** реализация сервиса для рассылки писем
 * @author Minibaeva Elvira
 */
@AllArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;
    private final  UserService userService;

    @Override
    public void sendSimpleEmail(String toAddress, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String [] to = new String[userService.findAll().size()];
        switch (toAddress) {
            case ("Users") :
                to = userService.findAllEmails();
                break;
            case ("Passengers") :
                to= userService.findAllEmailsPassengers();
                break;
            case ("Managers") :
                to = userService.findAllEmailsManagers();
                break;
            case ("Admins") :
                to= userService.findAllEmailsAdmins();
                break;
        }

        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        emailSender.send(simpleMailMessage);
        System.out.println("mail send!");

    }

    @Override
    public void sendFlightEventEmail(String address, String header, String message) {
        SimpleMailMessage flightMailMessage = new SimpleMailMessage();

        flightMailMessage.setTo(address);
        flightMailMessage.setSubject(header);
        flightMailMessage.setText(message);

        emailSender.send(flightMailMessage);
    }
}
