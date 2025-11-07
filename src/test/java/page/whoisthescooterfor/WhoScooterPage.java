package page.whoisthescooterfor;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class WhoScooterPage {

    public SelenideElement textWhoIsTheScooterFor(){
        return $("[class='Order_Header__BZXOb']").as("Текст заголовка Для кого самокат");
    }
}
