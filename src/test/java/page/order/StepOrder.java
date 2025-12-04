package page.order;

import io.qameta.allure.Step;
import net.datafaker.Faker;
import page.rent.StepRent;
import page.whoisthescooterfor.StepWhoIsScooter;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

public class StepOrder {

    OrderPage orderPage = new OrderPage();
    StepWhoIsScooter stepWhoIsScooter = new StepWhoIsScooter();
    StepRent stepRent = new StepRent();

    @Step("Получить номер заказа из поля ввода Статус заказа")
    public String numberOrder() {
        step("Получить номер заказа из поля ввода Статус заказа", ()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        return  orderPage.fieldNumberOrder().getValue();
    }

    @Step("Сравнение введеных пользовательских данных  с отображаемыми данными в статусе заказа")
    public String checkDataNameClient(String dataName) throws InterruptedException {
        Faker faker = new Faker(new Locale("ru"));
        faker.locality() ;
        String name = faker.name().firstName() ;
        String surname = faker.name().lastName();
        String address = faker.address().streetAddress();
        String phone = faker.phoneNumber().subscriberNumber(12);
        String metro = "Котельники";


        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(c.getTime());
        c.add(Calendar.DATE, 1);
        String date = formatter.format(c.getTime());


        int period = faker.number().numberBetween(0, 6);
        int numColor = faker.number().numberBetween(1, 4);
        String comment = "Привет курьер!";

        stepWhoIsScooter.choiceMetro(metro);
        stepWhoIsScooter.sendKeysInFieldName(name);
        stepWhoIsScooter.sendKeysInFieldSurname(surname);
        stepWhoIsScooter.sendKeysInFieldAddress(address);
        stepWhoIsScooter.sendKeysInFieldPhone(phone);
        Thread.sleep(Duration.ofMillis(300));
        stepWhoIsScooter.clickButtonNExt();

        Thread.sleep(Duration.ofMillis(300));

        stepRent.stepDate();
        stepRent.selectRentPeriod(period);
        stepRent.checkboxColorScooter(numColor);
        stepRent.sendKeyComment(comment);

        Thread.sleep(Duration.ofMillis(300));

        stepRent.clickButtonNext();
        stepRent.clickButtonYes();
        Thread.sleep(Duration.ofMillis(300));

        stepRent.clickButtonShowStatusOrder();

        Thread.sleep(Duration.ofMillis(400));

        String data = "";
        switch ( dataName ){
            case "Имя": data = name;
                break;
            case "Фамилия": data = surname;
                break;
            case "Адрес": data = address;
                break;
            case "Телефон": data = phone;
                break;            
            case "Станция метро": data = metro;
                break;
            case "Дата доставки": data = dateDelivery();
                break;
            case "Срок аренды": if ( period == 0) {
                                data = "сутки";
                                } else if ( period == 1) {
                                    data = "двое суток";
                                    } else if ( period == 2) {
                                        data = "трое суток";
                                        } else if ( period == 3) {
                                            data = "четверо суток";
                                            } else if ( period == 4) {
                                                data = "пятеро суток";
                                                } else if ( period == 5) {
                                                    data = "шестеро суток";
                                                    } else if ( period == 6) {
                                                        data = "семеро суток";
                                                            }
                break;
            case "Цвет": if (numColor == 1){
                        data = "чёрный жемчуг";
                         } else if ( numColor == 2) {
                             data = "серая безысходность";
                            } else if ( numColor == 3 ){ data = "чёрный жемчуг, серая безысходность";
                                }else { data = "любой";}
                break;
            case "Комментарий": data = comment;
                break;
                default: data = null;
        }
        return data;
    }


    public String dataClient(int i){
        return orderPage.dataClient().get(i).getText();
    }

    @Step("Нажать кнопку Отменить заказ")
    public void clickButtonCancelOrder(){
        orderPage.buttonCancelOrder().click();
    }

    @Step("Нажать кнопку Назад")
    public void clickButtonBack(){
        orderPage.buttonBack().shouldBe(visible);
        orderPage.buttonBack().click();
    }
    @Step("Нажать кнопку Отменить")
    public void clickButtonCancel(){
        orderPage.buttonCancel().shouldBe(visible);
        orderPage.buttonCancel().click();
    }
    @Step("Нажать кнопку Хорошо при отмене заказа")
    public void clickButtonGoodCancelOrder(){
        orderPage.buttonGoodCancelOrder().shouldBe(visible);
        orderPage.buttonGoodCancelOrder().click();
    }

    //Преобразование даты в письменную форму для проверки даты доставки самоката
    public String dateDelivery(){
        Calendar c = Calendar.getInstance();
        c.setTime(c.getTime());
        c.add(Calendar.DATE, 1);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int n = c.get(Calendar.MONTH) + 1;
        String name = String.valueOf(day);
        switch (n) {
            case 1: name = name + " января"; break;
            case 2: name = name + " февраля"; break;
            case 3: name = name + " марта"; break;
            case 4: name = name + " апреля"; break;
            case 5: name = name + " мая"; break;
            case 6: name = name + " июня"; break;
            case 7: name = name + " июля"; break;
            case 8: name = name + " августа"; break;
            case 9: name = name + " сентября"; break;
            case 10: name = name + " октября"; break;
            case 11: name = name + " ноября"; break;
            case 12: name = name + " декабря"; break;
        }

        return name;
    }

    @Step("")
    public void stepCancelOrder() throws InterruptedException {
        stepRent.stepOneOrder();
        stepRent.order();
        Thread.sleep(Duration.ofMillis(200));
        stepRent.clickButtonShowStatusOrder();
        clickButtonCancelOrder();
        Thread.sleep(Duration.ofMillis(200));
        clickButtonCancel();
        clickButtonGoodCancelOrder();
    }

}
