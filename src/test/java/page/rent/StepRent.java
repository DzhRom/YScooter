package page.rent;


import page.whoisthescooterfor.StepWhoIsScooter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class StepRent {
    StepWhoIsScooter stepWhoIsScooter = new StepWhoIsScooter();
    LocalDate date = LocalDate.now();
    String fdate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    RentPage rentPage = new RentPage();

   public void stepOneOrder(){
       stepWhoIsScooter.sendKeysInFieldName("Роман");
       stepWhoIsScooter.sendKeysInFieldSurname("Романов");
       stepWhoIsScooter.sendKeysInFieldAddress("Москва 3");
       stepWhoIsScooter.choiceMetro("Котельники");
       stepWhoIsScooter.sendKeysInFieldPhone("111111111111");
       stepWhoIsScooter.clickButtonNExt();
   }

   public void clickAboutRent(){
       rentPage.aboutRent().click();
   }

   public void stepDate(){
       step("Ввод даты в поле Когда привезти самокат",()->{
          rentPage.fieldWhenToBringScooter().setValue(fdate);
           clickAboutRent();
       });
   }

   public void selectRentPeriod(int period){
       step("Выбор срока аренды самоката", ()->{
           rentPage.fieldRentalPeriod().click();
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

   public void clickButtonBack(){
       step("Нажать кнопку Назад", ()->{
           rentPage.buttonBack().click();
       });
   }

   public void clickButtonNext(){
       step("Нажать кнопку Заказать",()->{
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
       Thread.sleep(100);
       rentPage.numberOrder().shouldBe(visible);
       String str = getNumberOrder(rentPage.numberOrder().getText());
       return str;
   }

}
