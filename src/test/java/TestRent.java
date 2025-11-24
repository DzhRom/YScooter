import com.codeborne.selenide.Configuration;

import com.codeborne.selenide.Selenide;
import constatnts.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import page.order.OrderPage;
import page.order.StepOrder;
import page.rent.RentPage;
import page.rent.StepRent;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestRent {
    private StepRent stepRent;
    private OrderPage orderPage;
    private StepOrder stepOrder;
    private RentPage rentPage;

    @BeforeEach
    public void setUp() {
        Configuration.browser = "edge";
        Configuration.browserSize = "1920x1080";
        open(Constants.WHO_IS_SCOOTER);
        stepRent = new StepRent();
        stepRent.stepOneOrder();
        orderPage = new OrderPage();
        stepOrder = new StepOrder();
        rentPage = new RentPage();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @DisplayName("Тест поля Когда привезти самока")
    @ParameterizedTest()
    @CsvSource (value  = {"0, -1", "-2, 0", "-1, 0", "0, 0", "1, 0", " 2, 0", "10, 0", "0, 2"})
    public void testDateRent(int day, int month) throws InterruptedException {
        stepRent.stepSetDate(day, month);

    }

    @DisplayName("Тест поля Срок аренды")
    @Test
    void testRentalPeriod(){
        stepRent.selectRentPeriod(1);
    }

    @DisplayName("Тест выбора цвета самоката")
    @Test
    void testCheckboxColor(){
        stepRent.selectCheckboxColorBlack();
        stepRent.selectCheckboxColorGrey();
    }

    @DisplayName("Тест поля ввода Комментарий для курьера")
    @Test
    void testComment(){
        stepRent.sendKeyComment("Привет. Как дела курьер?!");
    }

    @DisplayName("Тест сравнения статусов заказов")
    @Test
    void testStatusOrder() throws InterruptedException {
        stepRent.order();
        String str = stepRent.numberOrder();
        stepRent.clickButtonShowStatusOrder();
        assertEquals(stepOrder.numberOrder(), str);
    }
}
