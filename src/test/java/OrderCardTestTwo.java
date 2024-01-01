import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class OrderCardTestTwo {
    private String generateData(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldBeSuccessfullyOrder() {
        String planningDate = generateData(7, "dd.MM.yyyy");
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Ка");
        $$(".menu-item__control").findBy(Condition.text("Казань")).click();
        $("[data-test-id=date] input").click();
        if (!generateData(4, "MM").equals(generateData(7, "MM"))) {
            $$(".calendar__arrow").last().click();
            $$(".calendar__day").findBy(Condition.text(generateData(7, "d"))).click();
        } else {
            $$(".calendar__day").find(Condition.text(generateData(7, "d"))).click();
        }
        $("[data-test-id=name] input").setValue("Андрей Юрьевич Марков");
        $("[data-test-id=phone] input").setValue("+78000055544");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));
    }
}
