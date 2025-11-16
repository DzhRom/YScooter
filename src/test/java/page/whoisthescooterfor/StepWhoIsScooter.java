package page.whoisthescooterfor;

import com.codeborne.selenide.ClickOptions;

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
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            scooterPage.choiceStationMetro().shouldBe(visible);

            scooterPage.choiceStationMetro().click(ClickOptions.withOffset(1,-10));

        });
    }

    public void clickButtonNExt(){
        step("Нажать кнопку Далее", ()->{
            scooterPage.buttonNext().click();
        });
    }

}
