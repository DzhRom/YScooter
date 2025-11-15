import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import constatnts.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.whoisthescooterfor.StepWhoIsScooter;
import page.whoisthescooterfor.WhoScooterPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;


public class TestWhoIsScooter {
    private StepWhoIsScooter stepWhoIsScooter;


    private WhoScooterPage scooterPage = new WhoScooterPage();

    @BeforeEach
    public void setUp() {
        stepWhoIsScooter = new StepWhoIsScooter();
        Configuration.browserSize = "1920x1080";
        open(Constants.WHO_IS_SCOOTER);
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @DisplayName("Тест поля ввода Имя на валидные данные")
    @Test
    void testFieldName(){
        stepWhoIsScooter.sendKeysInFieldName("Роман");
        assertFalse(stepWhoIsScooter.isErrorName());
    }

    @DisplayName("Тест поля ввода Фамилия на валидные данные")
    @Test
    void testFieldSurname(){
        stepWhoIsScooter.sendKeysInFieldSurname("Иван");
        assertFalse(stepWhoIsScooter.isErrorSurname());
    }

    @DisplayName("Тест поля ввода Адрес на валидные данные")
    @Test
    void testFieldAddress(){
        stepWhoIsScooter.sendKeysInFieldAddress("Москва");
        assertFalse(stepWhoIsScooter.isErrorAddress());
    }

    @DisplayName("Тест поля ввода Телефон на валидные данные")
    @Test
    void testFieldPhone(){
        stepWhoIsScooter.sendKeysInFieldPhone("89996663258");
        assertFalse(stepWhoIsScooter.isErrorPhone());
    }

    @DisplayName("Проверка списка станций")
    @Test
    void testChoiceMetro(){

        stepWhoIsScooter.choiceMetro("Котельники");
        assertEquals( "Котельники", scooterPage.listMetro().getValue());

    }


}
