package page.rent;


import net.datafaker.Faker;
import page.whoisthescooterfor.StepWhoIsScooter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import io.qameta.allure.Description;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class StepRent {
    StepWhoIsScooter stepWhoIsScooter = new StepWhoIsScooter();
    LocalDate date = LocalDate.now();
    String fdate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    RentPage rentPage = new RentPage();
    Faker faker = new Faker(new Locale("ru"));

   public void stepOneOrder(){
       stepWhoIsScooter.sendKeysInFieldName(faker.name().firstName());
       stepWhoIsScooter.sendKeysInFieldSurname(faker.name().lastName());
       stepWhoIsScooter.sendKeysInFieldAddress(faker.address().streetAddress());
       stepWhoIsScooter.choiceMetro("Котельники");
       stepWhoIsScooter.sendKeysInFieldPhone(faker.phoneNumber().subscriberNumber(12));
       stepWhoIsScooter.clickButtonNExt();
   }

   public void clickAboutRent(){
       rentPage.aboutRent().click();
   }

   @Step("Ввод даты в поле Когда привезти самокат")
   public void stepDate(){
       step("Ввод даты в поле Когда привезти самокат",()->{
           SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
           Calendar c = Calendar.getInstance();
           c.setTime(c.getTime());
           c.add(Calendar.DATE, 1);
           String date = formatter.format(c.getTime());
          rentPage.fieldWhenToBringScooter().setValue(date);
           clickAboutRent();
       });
   }

    @Step("Выбор даты доставки в календаре")
    public void stepSetDateNew(int day) throws InterruptedException {
        step("Выбор даты доставки в календаре", () -> {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyyy");
            Calendar c = Calendar.getInstance();
            c.setTime(c.getTime());
            c.add(Calendar.DATE, day);
            String date = format.format(c.getTime());
            rentPage.fieldWhenToBringScooter().click();
            rentPage.dayCalendar(date).click();
        });
        Thread.sleep(200);
    }





    //Выбор доставки с учетом перехода с месяца на месяц
   public void stepSetDate(int day, int month) throws InterruptedException {
       step("Выбор даты доставки в календаре", () -> {

           String days;
       rentPage.fieldWhenToBringScooter().click();
       if (month < 0) {
           for (int i = 0; i > month; i-- ){
               rentPage.buttonPreviousMonth().click();
           }
       } else if (month > 0) {
           for (int i = 0; i < month; i ++){
               rentPage.buttonNextMonth().click();
           }
       }
            //Присваиваем в d массимальное число
          LocalDate localDate = LocalDate.now();
          Calendar calendar = Calendar.getInstance();
          String setDate ;
          int  tempDay;

         if (day == 0){
             if (localDate.getDayOfMonth() < 10) {
                 setDate = datePlusO(localDate.getDayOfMonth());
                 rentPage.dayCalendar(setDate).click();
             } else {setDate = String.valueOf(day);
                 rentPage.dayCalendar(setDate).click();}

         } else if (day < 0) {
             if (localDate.getDayOfMonth() - Math.abs(day) < 0) {
                 rentPage.buttonPreviousMonth().click();
                 calendar.add(Calendar.MONTH, -1);
                 int m = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                 tempDay =  m - Math.abs(localDate.getDayOfMonth() - Math.abs(day));
                 if (tempDay < 10) {
                     setDate = datePlusO(tempDay);
                     rentPage.dayCalendar(setDate).click();
                 } else {setDate = String.valueOf(tempDay);
                 rentPage.dayCalendar(setDate).click();}
             } else if (localDate.getDayOfMonth() - Math.abs(day) > 0) {
                 tempDay = localDate.getDayOfMonth() - Math.abs(day);
                 if (tempDay < 10) {
                     setDate = datePlusO(tempDay);
                     rentPage.dayCalendar(setDate).click();
                 } else {setDate = String.valueOf(tempDay);
                 rentPage.dayCalendar(setDate).click();}
             }
         } else {

             if ( day + localDate.getDayOfMonth() > calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                 rentPage.buttonNextMonth().click();
                 tempDay = Math.abs(calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - ( day + localDate.getDayOfMonth()));
                 if (tempDay < 10) {
                     setDate = datePlusO(tempDay);
                     rentPage.dayCalendar(setDate).click();
                 } else {setDate = String.valueOf(tempDay);
                 rentPage.dayCalendar(setDate).click();}
             } else {
                 tempDay = localDate.getDayOfMonth() + day;
                 if (tempDay < 10) {
                     setDate = datePlusO(tempDay);
                     rentPage.dayCalendar(setDate).click();
                 } else {setDate = String.valueOf(tempDay);
                     rentPage.dayCalendar(setDate).click();}
             }
         }
           });
               try {
                   Thread.sleep(200);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
   }

   public String datePlusO(int day){
       String days = "0"+ String.valueOf(day);
       return days;
   }


   @Step("Выбор срока аренды самоката")
   public void selectRentPeriod(int period){
       step("Выбор срока аренды самоката", ()->{
           rentPage.fieldRentalPeriod().click();
           try {
               Thread.sleep(100);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
           rentPage.period().get(period).shouldBe(visible);
           rentPage.period().get(period).click();
       });
   }

   @Step("Выбрать черный цвет")
   public void selectCheckboxColorBlack(){
       step("Выбрать черный цвет",()->{
               rentPage.checkboxBlack().click();
       });
   }

   @Step("Выбрать серый цвет")
   public void selectCheckboxColorGrey(){
       step("Выбрать серый цвет",()->{
           rentPage.checkboxGrey().click();
       });
   }

   @Step("Выбрать цвета самоката")
   public void checkboxColorScooter(int numColor){
       step("Выбрать цвет самоката", ()->{
          if (numColor == 1){
              rentPage.checkboxBlack().click();
          } else if (numColor == 2) {
              rentPage.checkboxGrey().click();
          } else if (numColor == 3) {
              rentPage.checkboxBlack().click();
              rentPage.checkboxGrey().click();
          } else {}
       });
   }

   @Step("Ввести комментарий в поле Коментарий для курьера")
   public void sendKeyComment(String comment){
       step("Ввести в поле Коментарий для курьера", ()->{
           rentPage.fieldComment().clear();
           rentPage.fieldComment().sendKeys(comment);
       });
   }

   @Step("Ожидание появления формы Хотите оформить заказ?")
   public boolean isDisplayedFromPlaceAnOrder(){
       step("Ожидание появления формы Хотите оформить заказ?", ()->{
       });
       return rentPage.formPlaceAnOrder().isDisplayed();
   }
   @Step("Нажать кнопку Назад")
   public void clickButtonBack(){
       step("Нажать кнопку Назад", ()->{
           rentPage.buttonBack().click();
       });
   }

   @Step("Customize Toolbar...")
   public void clickButtonNext(){
       step("Нажать кнопку Заказать",()->{
           rentPage.buttonNext().shouldBe(visible);
           rentPage.buttonNext().click();
       });
   }
    @Step("Нажать кнопку Да в форме Хотите оформить заказ")
   public void clickButtonYes(){
       step("Нажать кнопку Да в форме Хотите оформить заказ",()->{
           rentPage.buttonYes().click();
       });
   }
    @Step("Нажать кнопку Нет формы Хотите оформить заказ")
   public void clickButtonNo(){
       step("Нажать кнопку Нет формы Хотите оформить заказ", ()->{
           rentPage.buttonNo().click();
       });
   }

    @Step("Нажать кнопку Посмотреть статус формы Заказ оформлен")
   public void clickButtonShowStatusOrder(){
       step("Нажать кнопку Посмотреть статус формы Заказ оформлен", ()->{
           rentPage.showStatusOrder().shouldBe(visible);
           rentPage.showStatusOrder().click();
       });
   }


   public String getNumberOrder(String str){
       str = str.replaceAll("\\D+","");
       return str;
   }


   /*Тестовый метод заполнения заказа*/
   @Step("Создание тестового заказа")
   public void order() {
       step("Создание заказа",()->{
       stepDate();
       selectRentPeriod(1);
       selectCheckboxColorBlack();
       sendKeyComment("Привеееееттт");
       clickButtonNext();
       clickButtonYes();
       });
   }

   public String numberOrder() throws InterruptedException {
       Thread.sleep(200);
       rentPage.numberOrder().shouldBe(visible);
       String str = getNumberOrder(rentPage.numberOrder().getText());
       return str;
   }

}
