import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class OrderCardTest {
    @Test
    public void shouldBe (){
       open("http://localhost:9999");
       $("[data-test-id=city] input").setValue("Казань");
    }

}
