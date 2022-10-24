package likelion.finalproject.market.mail.application;

import likelion.finalproject.market.mail.dto.RequestSendEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MailComponent {

    private final JavaMailSender mailSender;

    public boolean sendMail(RequestSendEmail requestSendEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("garamminchan@gmail.com");
        message.setTo(requestSendEmail.getEmail());
        message.setSubject(requestSendEmail.getTitle());
        message.setText(requestSendEmail.getContent());

        mailSender.send(message);

        return true;
    }
}
