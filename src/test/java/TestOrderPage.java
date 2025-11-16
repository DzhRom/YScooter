import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import constatnts.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import page.order.StepOrder;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestOrderPage {
    private StepOrder stepOrder;

    @BeforeEach
    public void setUp() {
        Configuration.browser = "edge";
        Configuration.browserSize = "1920x1080";
        open(Constants.WHO_IS_SCOOTER);
        stepOrder = new StepOrder();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @DisplayName("Тест соответствия вводимых данных клиента с отображением информации на форме О Заказе")
    @ParameterizedTest
    @CsvSource(value = {
            "Имя, 0",
            "Фамилия, 1",
            "Адрес, 2",
            "Станция метро, 3",
            "Телефон, 4",
            "Дата доставки, 5",
            "Срок аренды, 6",
            "Цвет, 7",
            "Комментарий, 8"
    })
    void checkDataClient(String data, int i) throws InterruptedException {
        StringBuilder str1 = new StringBuilder();
        str1.append(data);
        str1.append('\n' + stepOrder.checkDataNameClient(data));
        String str3 = String.valueOf(str1);
        assertEquals(str3, stepOrder.dataClient(i));
    }

}
