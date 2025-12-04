import com.codeborne.selenide.Configuration;

import com.codeborne.selenide.Selenide;
import constatnts.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import page.order.OrderPage;
import page.order.StepOrder;
import page.rent.RentPage;
import page.rent.StepRent;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;
import io.qameta.allure.Description;


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

    @Description("Тест поля Когда привезти самока c валидными данными")
    @DisplayName("test Date Rent True")
    @ParameterizedTest()
    @CsvSource(value = {"1, 0", "2, 0", "0,1" })
    public void testDateRentTrue(int day, int month) throws InterruptedException {
        stepRent.stepSetDate(day, month);
        stepRent.selectRentPeriod(1);
        stepRent.clickButtonNext();
        assertTrue(stepRent.isDisplayedFromPlaceAnOrder());
    }


    @Description("Тест поля Когда привезти самока c не валидными данными")
    @DisplayName("test Date Rent False")
    @ParameterizedTest()
    @CsvSource (value  = {"-10, 0", "-1, 0", "0, -2", "0, 0"})
    public void testDateRentFalse(int day, int month) throws InterruptedException {
        stepRent.stepSetDate(day, month);
        stepRent.selectRentPeriod(1);
        stepRent.clickButtonNext();
        assertFalse(stepRent.isDisplayedFromPlaceAnOrder());
    }


    @Description("Параметризированный тест списка срока аренд")
    @DisplayName("test Select Period Rent")
    @ParameterizedTest()
    @CsvSource( value = {"0", "1", "2", "3", "4", "5", "6"})
    void testSelectPeriodRent(int period) throws InterruptedException {
        stepRent.stepSetDate(1, 0);
        stepRent.selectRentPeriod(period);
        stepRent.clickButtonNext();
        assertTrue(stepRent.isDisplayedFromPlaceAnOrder());
    }


    @Description("Тест поля Срок аренды")
    @DisplayName("test Rental Period")
    @Test
    void testRentalPeriod(){
        stepRent.selectRentPeriod(1);
    }

    @Description("Тест выбора цвета самоката")
    @DisplayName("test Checkbox Color")
    @Test
    void testCheckboxColor(){
        stepRent.selectCheckboxColorBlack();
        stepRent.selectCheckboxColorGrey();
    }

    @Description("Тест поля ввода Комментарий для курьера")
    @DisplayName("test Comment")
    @Test
    void testComment(){
        stepRent.sendKeyComment("Привет. Как дела курьер?!");
    }

    @Description("Тест сравнения статусов заказов")
    @DisplayName("test Status Order")
    @Test
    void testStatusOrder() throws InterruptedException {
        stepRent.order();
        String str = stepRent.numberOrder();
        stepRent.clickButtonShowStatusOrder();
        assertEquals(stepOrder.numberOrder(), str);
    }

}