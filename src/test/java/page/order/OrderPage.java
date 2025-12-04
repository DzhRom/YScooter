package page.order;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class OrderPage {

    public SelenideElement fieldNumberOrder() {
        return $("[class='Input_Input__1iN_Z Track_Input__1g7lq Input_Filled__1rDxs Input_Responsible__1jDKN']").as("Поле ввода номера заказа");
    }

    public SelenideElement buttonCancelOrder() {
        return $x(".//button[text()='Отменить заказ']").as("Кнопка Отменить заказ");
    }

    public ElementsCollection nameDataClient(){
        return $$("[class='Track_Title__1XfhB']").as("Поля наименований данных клиента");
    }

    public ElementsCollection clientData(){
        return $$("[class='Track_Value__15eEX']").as("Поля данных клиента");
    }

    public ElementsCollection dataClient(){
        return $$("[class='Track_Row__1sN1F']").as("Строка данных клиента");
    }

    public SelenideElement buttonBack() {
        return $x(".//button[text()='Назад']").as("Кнопка назад в окне отмены заказа");
    }
     public SelenideElement buttonCancel() {
        return $x(".//button[text()='Отменить']").as("Кнопка Отменить в окне отмены заказа ");
    }

    public SelenideElement buttonGoodCancelOrder() {
        return $x(".//button[text()='Хорошо']").as("Заказа отменем кнопка Хорошо");
    }
}
