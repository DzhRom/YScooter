package page.whoisthescooterfor;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class WhoScooterPage {

    public SelenideElement textWhoIsTheScooterFor(){
        return $("[class='Order_Header__BZXOb']").as("Заголовок Для кого самокат");
    }

    public SelenideElement fieldName(){
        return $x("//input[@placeholder='* Имя']").as("Поле ввода Имя. Обязательное");
    }
    public SelenideElement errorName(){
        return $x(".//div[text()='Введите корректное имя']").as("Сообщение Введите корректное имя");
    }


    public SelenideElement fieldSurname(){
        return $x(".//input[@placeholder='* Фамилия']").as("Поле ввода Фамилия");
    }
    public SelenideElement errorSurname(){
        return $x(".//div[text()='Введите корректную фамилию']").as("Ошибка Введите корректную фамилию");
    }


    public SelenideElement fieldAddress(){
        return $x(".//input[@placeholder='* Адрес: куда привезти заказ']").as("Поле ввода Адрес ");
    }
    public SelenideElement errorAddress(){
        return $x(".//div[text()='Введите корректный адрес']").as("Ошибка Введите корректный адрес");
    }

    public SelenideElement listMetro(){
        return $("[class='select-search__input']").as("Поле Станция Метро");
    }

    public SelenideElement choiceStationMetro(){
        return $("[class='select-search__select'").as("Выпадающий список");
    }



    public SelenideElement selectMetro(){
        return $x("[class='select-search__select'").as("Выпадающий список");
    }


    public SelenideElement fieldPhone(){
        return $x(".//input[@placeholder='* Телефон: на него позвонит курьер']").as("Поле ввода Телефон");
    }
    public SelenideElement errorPhone(){
        return $x(".//div[text()='Введите корректный номер']").as("Ошибка Введите корректный номер");
    }

    public SelenideElement buttonNext(){
        return $("[class='Button_Button__ra12g Button_Middle__1CSJM']").as("Кнопка Далее");
    }

}
