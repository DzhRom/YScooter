package page.main;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    public SelenideElement scooter(){
        return $("[class='Home_Header__iJKdX']").as("Заголовок сайта ЯндексСамокат");
    }

    public SelenideElement buttonOrder(){
        return $("[class='Button_Button__ra12g']").as("Кнопка Заказать вверхнем правом углу");
    }

    public ElementsCollection questionAndAnswer(){
        return  $$("[class='accordion__item']").as("Вопросы о самом важном");
    }

    public SelenideElement question(int i){
        String locator = "[id='accordion__heading-" + i +"']";
        return $(locator).as("Вопрос из категории вопросы оСамом важном");
    }

    public SelenideElement answer(int i){
        String locator = "[id='accordion__panel-" + i +"']";
        return $(locator).as("Ответ на вопрос о Самом важном");
    }

    public SelenideElement statusOrder(){
        return $("[class='Header_Link__1TAG7'").as("Кнопка статус заказа");
    }

    public SelenideElement fieldOrder(){
        return $("[class='Input_Input__1iN_Z Header_Input__xIoUq'").as("Поле ввода номера заказа в верхнем правом углу");
    }

    public SelenideElement buttonGo(){
        return $("[class='Button_Button__ra12g Header_Button__28dPO'").as("Кнопка Go");
    }
}
