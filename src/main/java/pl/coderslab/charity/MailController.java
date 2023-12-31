package pl.coderslab.charity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.charity.service.EmailService;

@RestController
public class MailController {

    private EmailService emailService;

    public MailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/sendEmail")
    public String sendEmail() {
        emailService.sendSimpleEmail("Gosia <example23121987@gmail.com>", "Test e-mail", "Testing email functionality");

        return "E-mail sent!";
    }
}
