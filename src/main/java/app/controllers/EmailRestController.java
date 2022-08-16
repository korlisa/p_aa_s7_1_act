package app.controllers;

import app.services.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**Контроллер для рассылки писем всем пользователям,
 * только пассажирам, менеджерам, администраторам
 *
 * @author Minibaeva Elvira
 */
@Api(tags = "EmailRestController")
@AllArgsConstructor
@RestController
@RequestMapping("/email")
public class EmailRestController {
    private final EmailService emailService;

    @ApiOperation(value = "send simple email to Users, Passenger, Airline Managers, Admins")
    @GetMapping("/{toAddress}/{subject}/{message}")
    public ResponseEntity<Void> sendSimpleEmail(@PathVariable("toAddress") String toAddress, @PathVariable("subject") String subject,
                                                @PathVariable("message") String message) {

        emailService.sendSimpleEmail(toAddress, subject, message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
