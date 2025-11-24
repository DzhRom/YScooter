package page.whoisthescooterfor;

import com.codeborne.selenide.ClickOptions;
import net.datafaker.Faker;

import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class StepWhoIsScooter {
    WhoScooterPage scooterPage = new WhoScooterPage();

    public boolean isDisplayedTextWhoISScooter(){
        step("Проверка перехода на страницу Для кого самокат", ()->{
        });
        return scooterPage.textWhoIsTheScooterFor().isDisplayed();
    }

    public void sendKeysInFieldName(String name) {
        step("Ввод значения в поле Имя", ()-> {
            scooterPage.fieldName().sendKeys(name);
            scooterPage.textWhoIsTheScooterFor().click();
        });
    }
    public boolean isErrorName(){
        step("Проверка появления ошибки Введите корректное имя", ()->{});
        return scooterPage.errorName().isDisplayed();
    }


    public void sendKeysInFieldSurname(String surname){
        step("Ввод значения в поле Фамилия", ()-> {
            scooterPage.fieldSurname().sendKeys(surname);
            scooterPage.textWhoIsTheScooterFor().click();
        });
    }
    public boolean isErrorSurname(){
        step("Проверка появлени ошибки Введите корректное фамилия",()-> {});
        return scooterPage.errorSurname().isDisplayed();
    }

    public void sendKeysInFieldAddress(String address) {
        step("Ввод значения в поле Адрес", () -> {
            scooterPage.fieldAddress().sendKeys(address);
            scooterPage.textWhoIsTheScooterFor().click();
        });
    }
    public boolean isErrorAddress(){
        step("Проверка появления ошибки Введите корректный адрес", ()->{});
        return scooterPage.errorAddress().isDisplayed();
    }

    public void sendKeysInFieldPhone(String phone) {
        step("Ввод значения в поле Телефон", ()->{
            scooterPage.fieldPhone().sendKeys(phone);
            scooterPage.textWhoIsTheScooterFor().click();
        });
    }
    public boolean isErrorPhone(){
        step("Проверка появления сообщения Введите корректный номер", ()->{});
        return scooterPage.errorPhone().isDisplayed();
    }

    public void choiceMetro(String metro){
        step("Ввод из списка станции метро",()->{
            scooterPage.listMetro().click();
            scooterPage.listMetro().sendKeys(metro);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            scooterPage.choiceStationMetro().shouldBe(visible);

            scooterPage.choiceStationMetro().click(ClickOptions.withOffset(1,-10));

        });
    }
    public boolean isErrorMetro(){
        step("Проверка появления сообщения Выберите станцтю", ()->{});
        return scooterPage.errorStationMetro().isDisplayed();
    }

    public void clickButtonNExt(){
        step("Нажать кнопку Далее", ()->{
            scooterPage.buttonNext().click();
        });
    }

    public boolean necessarilyField(String nameField) throws InterruptedException {
        Faker faker = new Faker(new Locale("ru"));
        boolean result = true;
        switch (nameField){
            case "Имя": sendKeysInFieldSurname(faker.name().lastName());
                sendKeysInFieldAddress(faker.address().streetAddress());
                choiceMetro("Котельники");
                sendKeysInFieldPhone(faker.phoneNumber().subscriberNumber(12));
                clickButtonNExt();
                Thread.sleep(50);
                result = isErrorName();
                break;
            case "Фамилия": sendKeysInFieldName(faker.name().firstName());
                sendKeysInFieldAddress(faker.address().streetAddress());
                choiceMetro("Котельники");
                sendKeysInFieldPhone(faker.phoneNumber().subscriberNumber(12));
                clickButtonNExt();
                result = isErrorSurname();
                break;
            case "Адрес": sendKeysInFieldName(faker.name().firstName());
                sendKeysInFieldSurname(faker.name().lastName());
                choiceMetro("Котельники");
                sendKeysInFieldPhone(faker.phoneNumber().subscriberNumber(12));
                clickButtonNExt();
                Thread.sleep(50);
                result = isErrorAddress();
                break;
            case "Телефон": sendKeysInFieldName(faker.name().firstName());
                sendKeysInFieldSurname(faker.name().lastName());
                sendKeysInFieldAddress(faker.address().streetAddress());
                choiceMetro("Котельники");
                clickButtonNExt();
                Thread.sleep(50);
                result = isErrorPhone();
                break;
            case "Метро":  sendKeysInFieldName(faker.name().firstName());
                sendKeysInFieldSurname(faker.name().lastName());
                sendKeysInFieldAddress(faker.address().streetAddress());
                sendKeysInFieldPhone(faker.phoneNumber().subscriberNumber(12));
                clickButtonNExt();
                Thread.sleep(50);
                result = isErrorMetro();
                break;
        }
        return result;
    }


}
