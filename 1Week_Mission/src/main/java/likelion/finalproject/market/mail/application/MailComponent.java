package likelion.finalproject.market.mail.application;

import likelion.finalproject.market.mail.dto.RequestEmailSend;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MailComponent {

    private final JavaMailSender mailSender;

    public void sendMail(RequestEmailSend requestEmailSend) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("garamminchan@gmail.com");
        message.setTo(requestEmailSend.getEmail());
        message.setSubject(requestEmailSend.getTitle());
        message.setText(requestEmailSend.getContent());

        mailSender.send(message);
    }
}
