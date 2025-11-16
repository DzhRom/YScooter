package page.order;

import net.datafaker.Faker;
import page.rent.StepRent;
import page.whoisthescooterfor.StepWhoIsScooter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class StepOrder {

    OrderPage orderPage = new OrderPage();
    StepWhoIsScooter stepWhoIsScooter = new StepWhoIsScooter();
    StepRent stepRent = new StepRent();

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

    public String checkDataNameClient(String dataName) throws InterruptedException {
        Faker faker = new Faker(new Locale("ru"));
        faker.locality() ;
        String name = faker.name().firstName() ;
        String surname = faker.name().lastName();
        String address = faker.address().streetAddress();
        String phone = faker.phoneNumber().subscriberNumber(12);
        String metro = "Котельники";
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        int period = faker.number().numberBetween(0, 6);
        int numColor = faker.number().numberBetween(1, 4);
        String comment = "Привет курьер!";

        stepWhoIsScooter.choiceMetro(metro);
        stepWhoIsScooter.sendKeysInFieldName(name);
        stepWhoIsScooter.sendKeysInFieldSurname(surname);
        stepWhoIsScooter.sendKeysInFieldAddress(address);
        stepWhoIsScooter.sendKeysInFieldPhone(phone);
        Thread.sleep(Duration.ofMillis(500));
        stepWhoIsScooter.clickButtonNExt();

        Thread.sleep(Duration.ofMillis(500));

        stepRent.stepDate();
        stepRent.selectRentPeriod(period);
        stepRent.checkboxColorScooter(numColor);
        stepRent.sendKeyComment(comment);

        Thread.sleep(Duration.ofMillis(500));

        stepRent.clickButtonNext();
        stepRent.clickButtonYes();
        Thread.sleep(Duration.ofMillis(500));

        stepRent.clickButtonShowStatusOrder();

        Thread.sleep(Duration.ofMillis(2000));

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
            case "Дата доставки": data = date;
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

}
