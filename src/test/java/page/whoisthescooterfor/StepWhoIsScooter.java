package page.whoisthescooterfor;

import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class StepWhoIsScooter {
    WhoScooterPage scooterPage = new WhoScooterPage();

    public boolean isDisplayedTextWhoISScooter(){
        step("Проверка отображения страницы Для кого самокат", ()->{
        });
        return scooterPage.textWhoIsTheScooterFor().isDisplayed();
    }
}
