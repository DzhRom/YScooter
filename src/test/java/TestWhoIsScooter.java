
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import constatnts.Constants;
import io.qameta.allure.Description;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import page.whoisthescooterfor.StepWhoIsScooter;
import page.whoisthescooterfor.WhoScooterPage;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;


public class TestWhoIsScooter {
    private StepWhoIsScooter stepWhoIsScooter;
    private WhoScooterPage scooterPage;
    private Faker faker;

    @BeforeEach
    public void setUp() {
        scooterPage = new WhoScooterPage();
        faker = new Faker(new Locale("ru"));
        stepWhoIsScooter = new StepWhoIsScooter();
        Configuration.browser = "edge";
        Configuration.browserSize = "1920x1080";
        open(Constants.WHO_IS_SCOOTER);
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }



    @Description("Параметризированный тест поля ввода Имя с корректными данными")
    @DisplayName("test Field Name Tue")
    @ParameterizedTest( name = "{index} - {0}")
    @CsvFileSource( resources = "nameTrue.csv")
    void testFieldNameTue( String name, String comment ){
        stepWhoIsScooter.sendKeysInFieldName(name);
        assertFalse(stepWhoIsScooter.isErrorName(), comment);
    }
    @Description("Параметризированный тест поля ввода Имя с некорректными данными")
    @DisplayName("test Field Name False")
    @ParameterizedTest( name = "{index} - {0}")
    @CsvFileSource( resources = "nameFalse.csv")
    void testFieldNameFalse( String name, String comment){
        stepWhoIsScooter.sendKeysInFieldName(name);
        assertTrue(stepWhoIsScooter.isErrorName(), comment);
    }


    @Description("Тест поля ввода Фамилия на валидные данные")
    @DisplayName("test Field Surname True")
    @ParameterizedTest( name = "{index} - {0}")
    @CsvFileSource( resources = "surnameTrue.csv")
    void testFieldSurnameTrue( String name, String comment){
        stepWhoIsScooter.sendKeysInFieldSurname(name);
        assertFalse(stepWhoIsScooter.isErrorSurname(), comment);
    }
    @Description("Тест поля ввода Фамилия на не валидные данные")
    @DisplayName("test Field Surname False")
    @ParameterizedTest( name = "{index} - {0}")
    @CsvFileSource( resources = "surnameFalse.csv")
    void testFieldSurnameFalse( String name, String comment ){
        stepWhoIsScooter.sendKeysInFieldSurname(name);
        assertTrue(stepWhoIsScooter.isErrorSurname(), comment);
    }


    @Description("Тест поля ввода Адрес на валидные данные")
    @DisplayName("test Field Address True")
    @ParameterizedTest( name = "{index} - {0}")
    @CsvFileSource( resources = "addressTrue.csv")
    void testFieldAddressTrue( String address, String comment){
        stepWhoIsScooter.sendKeysInFieldAddress(address);
        assertFalse(stepWhoIsScooter.isErrorAddress(), comment);
    }
    @Description("Тест поля ввода Адрес на не валидные данные")
    @DisplayName("test Field Address False")
    @ParameterizedTest( name = "{index} - {0}")
    @CsvFileSource( resources = "addressFalse.csv")
    void testFieldAddressFalse( String address, String comment){
        stepWhoIsScooter.sendKeysInFieldAddress(address);
        assertTrue(stepWhoIsScooter.isErrorAddress(), comment);
    }



    @Description("Тест поля ввода Телефон на валидные данные")
    @DisplayName("test Field Phone True")
    @ParameterizedTest( name = "{index} - {0}")
    @CsvFileSource( resources = "phoneTrue.csv")
    void testFieldPhoneTrue(String phone, String comment ){
        stepWhoIsScooter.sendKeysInFieldPhone(phone);
        assertFalse(stepWhoIsScooter.isErrorPhone(), comment);
    }
    @Description("Тест поля ввода Телефон на валидные данные")
    @DisplayName("test Field Phone False")
    @ParameterizedTest( name = "{index} - {0}")
    @CsvFileSource( resources = "phoneFalse.csv")
    void testFieldPhoneFalse(String phone, String comment ){
        stepWhoIsScooter.sendKeysInFieldPhone(phone);
        assertTrue(stepWhoIsScooter.isErrorPhone(), comment);
    }


    @Description("Проверка списка станций")
    @DisplayName("test Choice Metro")
    @Test
    void testChoiceMetro(){
        stepWhoIsScooter.choiceMetro("Котельники");
        assertEquals( "Котельники", scooterPage.listMetro().getValue());
    }

    @Description("Тест: при пустом поле Имя и заполнеными другими обязателыми полями,\" +\n" +
            "            \" при нажатии кнопки Дальше, система выдает ошибку Введите корректное имя и не пустает на следующую страницу")
    @DisplayName("test Necessarily Field Name")
    @ParameterizedTest
    @ValueSource ( strings = {"Имя", "Фамилия", "Метро", "Телефон", "Адрес"})
    void  testNecessarilyFieldName (String nameField) throws InterruptedException {
        assertTrue(stepWhoIsScooter.necessarilyField(nameField), "Система принимает пустое значение поля "+ nameField);
    }




}
