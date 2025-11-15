package page.rent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.CollectionElement;

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
}
