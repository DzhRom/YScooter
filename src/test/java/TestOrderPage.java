
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import constatnts.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import page.main.MainPage;
import page.order.StepOrder;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestOrderPage {
    private StepOrder stepOrder;
    MainPage mainPage;

    @BeforeEach
    public void setUp() {
        Configuration.browser = "edge";
        Configuration.browserSize = "1920x1080";
        open(Constants.WHO_IS_SCOOTER);
        stepOrder = new StepOrder();
        mainPage = new MainPage();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @DisplayName("Тест соответствия вводимых данных клиента с отображением информации на форме Статус заказа")
    @ParameterizedTest
    @CsvSource(value = {
            "Имя, 0, Имя не совпадает",
            "Фамилия, 1,  Фамилия не совпадает",
            "Адрес, 2, Адрес не совпадает",
            "Станция метро, 3, Станция метро не совпадает",
            "Телефон, 4, Номер телефона не совпадает",
            "Дата доставки, 5, Дата доставки не совпадает",
            "Срок аренды, 6, Срок аренды не совпадает",
            "Цвет, 7, Цвет самока не совпадает",
            "Комментарий, 8, Комментарий не совпадает"
    })
    void checkDataClient(String data, int i, String errorMessage) throws InterruptedException {
        StringBuilder str1 = new StringBuilder();
        str1.append(data);
        str1.append('\n' + stepOrder.checkDataNameClient(data));
        String str3 = String.valueOf(str1);
        assertEquals(str3, stepOrder.dataClient(i), errorMessage);
    }

    @DisplayName("Тест отмены заказа")
    @Test
    void testCancelOrder() throws InterruptedException {
        stepOrder.stepCancelOrder();
        Thread.sleep(200);
        assertTrue(mainPage.scooter().isDisplayed());
    }

}

