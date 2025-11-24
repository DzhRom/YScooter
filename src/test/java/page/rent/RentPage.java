package page.rent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class RentPage {
    public SelenideElement aboutRent(){
        return $("[class='Order_Header__BZXOb']").as("Страница Про аренду");
    }

    public SelenideElement fieldWhenToBringScooter(){
        return $x(".//input[@placeholder='* Когда привезти самокат']").as("Поле Когда привезти самокат");
    }

    public SelenideElement fieldRentalPeriod(){
        return $("[class='Dropdown-placeholder']").as("Поле Срок аренды");
    }

    public ElementsCollection period(){
        return (ElementsCollection) $$("[class='Dropdown-option']").as("Список сроков аренды");
    }

    public SelenideElement checkboxBlack(){
        return $("[id='black']").as("Чек-бокс черный");
    }
    public SelenideElement checkboxGrey(){
        return $("[id='grey']").as("Чек-бок серый");
    }

    public SelenideElement fieldComment(){
        return $x(".//input[@placeholder='Комментарий для курьера']").as("Поле Комментарий для курьера");
    }

    public SelenideElement buttonBack(){
        return $("[class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i']").as("Кнопка назад");
    }

    public SelenideElement buttonNext(){
        return $("[class='Button_Button__ra12g Button_Middle__1CSJM']").as("Кнопка Заказать");
    }

    public SelenideElement buttonYes(){
        return $x(".//button[text()='Да']").as("Кнопка Да формы Хотите оформить заказ?");
    }

    public SelenideElement buttonNo(){
        return $(".//button[text()='Нет']").as("Нопка Нет Хотите оформить заказ?");
    }

    public SelenideElement numberOrder(){
        return $("[class='Order_Text__2broi']").as("Номер заказа");
    }

    public SelenideElement showStatusOrder(){
        return $x(".//button[text()='Посмотреть статус']").as("Кнопка  Посмотреть статус формы Заказ оформлен");
    }

    // Календарь
    public SelenideElement showCalendar(){
        return $("[class='react-datepicker__current-month']").as("Клик на календарь");
    }
    public SelenideElement buttonPreviousMonth(){
        return $("[class='react-datepicker__navigation react-datepicker__navigation--previous']").as("Прошлый месяц");
    }
    public SelenideElement buttonNextMonth(){
        return $("[class='react-datepicker__navigation react-datepicker__navigation--next']").as("Следующий месяц");
    }
    public SelenideElement dayCalendar(String day){
        return $x("//div[contains(@class,'react-datepicker__day react-datepicker__day--0"+day+"')]").as("обор дня");
    }
}
