package page.rent;


import net.datafaker.Faker;
import page.whoisthescooterfor.StepWhoIsScooter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

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

           GregorianCalendar gc = new GregorianCalendar();
           int d = gc.getActualMaximum(Calendar.DAY_OF_MONTH);

           if (day < 0) {
               if (Math.abs(day) > date.getDayOfMonth()) {
                   int da = (gc.getActualMaximum(Calendar.DAY_OF_MONTH) - (Math.abs(day) - date.getDayOfMonth()));
                   rentPage.buttonPreviousMonth().click();
                   rentPage.dayCalendar(String.valueOf(Math.abs(da))).click();
               } else {
                   days = String.valueOf(date.getDayOfMonth() + day);
                   rentPage.dayCalendar(days).click();
               }


           } else if (day > 0) {
               if ((day + date.getDayOfMonth()) > d) {
                   rentPage.buttonNextMonth().click();
                   int da = (day + date.getDayOfMonth()) - Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
                   if (da < 10) {
                       days = "0" + da;
                       rentPage.dayCalendar(days).click();
                   } else {
                       rentPage.dayCalendar(String.valueOf(da)).click();
                   }

               } else if (day + date.getDayOfMonth() < d) {
                   days = String.valueOf(date.getDayOfMonth() + day);
                   rentPage.dayCalendar(days).click();
               } else {
                   days = String.valueOf(date.getDayOfMonth());
                   rentPage.dayCalendar(days).click();
               }
           } else {
               int da = date.getDayOfMonth();
               if (da < 10) {
                   days = "0" + da;
                   rentPage.dayCalendar(days).click();
               } else {
                   rentPage.dayCalendar(String.valueOf(da)).click();
               }
           }
       });
       Thread.sleep(200);
   }


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

   public void selectCheckboxColorBlack(){
       step("Выбрать черный цвет",()->{
               rentPage.checkboxBlack().click();
       });
   }

   public void selectCheckboxColorGrey(){
       step("Выбрать серый цвет",()->{
           rentPage.checkboxGrey().click();
       });
   }

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

   public void sendKeyComment(String comment){
       step("Ввести в поле Коментарий для курьера", ()->{
           rentPage.fieldComment().clear();
           rentPage.fieldComment().sendKeys(comment);
       });
   }

   public boolean isDisplayedFromPlaceAnOrder(){
       step("Ожидание появления формы Хотите оформить заказ?", ()->{
       });
       return rentPage.formPlaceAnOrder().isDisplayed();
   }
   public void clickButtonBack(){
       step("Нажать кнопку Назад", ()->{
           rentPage.buttonBack().click();
       });
   }

   public void clickButtonNext(){
       step("Нажать кнопку Заказать",()->{
           rentPage.buttonNext().shouldBe(visible);
           rentPage.buttonNext().click();
       });
   }
   public void clickButtonYes(){
       step("Нажать кнопку Да в форме Хотите оформить заказ",()->{
           rentPage.buttonYes().click();
       });
   }
   public void clickButtonNo(){
       step("Нажать кнопку Нет формы Хотите оформить заказ", ()->{
           rentPage.buttonNo().click();
       });
   }

   public void clickButtonShowStatusOrder(){
       step("Нажать кнопку Посмотреть статус формы Заказ оформлен", ()->{
           rentPage.showStatusOrder().click();
       });
   }


   public String getNumberOrder(String str){
       str = str.replaceAll("\\D+","");
       return str;
   }


   /*Тестовый метод заполнения заказа*/
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
