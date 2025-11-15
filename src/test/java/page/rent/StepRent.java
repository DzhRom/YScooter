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
           rentPage.aboutRent().click();
           System.out.print(rentPage.fieldWhenToBringScooter().getValue());
       });
   }

   public void selectRentPeriod(){
       step("Выбор срока аренды самоката", ()->{
           rentPage.fieldRentalPeriod().click();
           rentPage.period().get(0).shouldBe(visible);
           rentPage.period().get(0).click();

       });
   }
}
