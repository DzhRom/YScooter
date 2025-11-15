import com.codeborne.selenide.Configuration;
import constatnts.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.rent.RentPage;
import page.rent.StepRent;
import page.whoisthescooterfor.StepWhoIsScooter;

import static com.codeborne.selenide.Selenide.open;

public class TestRent {
    private StepRent stepRent;

    @BeforeEach
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        open(Constants.WHO_IS_SCOOTER);
        stepRent = new StepRent();
        stepRent.stepOneOrder();
    }

    @DisplayName("Тест поля Когда привезти самока")
    @Test
    public void testDateRent() {
        stepRent.stepDate();
    }

    @DisplayName("Тест поля Срок аренды")
    @Test
    void testRentalPeriod(){
        stepRent.selectRentPeriod();
    }

}
