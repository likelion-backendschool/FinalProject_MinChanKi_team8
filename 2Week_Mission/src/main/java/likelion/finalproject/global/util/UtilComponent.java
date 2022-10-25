package likelion.finalproject.global.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UtilComponent {
    public static LocalDate getDate() {
        return LocalDate.now();
    }
}
