
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import constatnts.Constants;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page.main.MainPage;
import page.main.StepMainPage;
import page.whoisthescooterfor.StepWhoIsScooter;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class TestMainPage {

    private StepMainPage stepMainPage;
    private StepWhoIsScooter stepWhoIsScooter;
    private MainPage mainPage;

    @BeforeEach
    public void setUp(){
        Configuration.browser = "edge";
        Configuration.browserSize = "1920x1080";
        open(Constants.MAIN_URL);
        stepMainPage = new StepMainPage();
        stepWhoIsScooter = new StepWhoIsScooter();
        mainPage = new MainPage();

    }
    @AfterEach
    public void tearDown(){
        Selenide.closeWebDriver();
    }


    @Test
    public void testMainPage(){
        assertTrue(stepMainPage.isDisplayMainText());
    }

    @Test
    void testClickButtonOrder(){
        stepMainPage.clickButtonOrder();
        assertTrue(stepWhoIsScooter.isDisplayedTextWhoISScooter());
    }


    @Description("Тест проверка текста вопроса о Самом важном")
    @DisplayName("test Question")
    @ParameterizedTest(name = "{index} - {0}")
    @CsvFileSource( resources = "question.csv")
    void testQuestion(String expectedQuestion, int i){
       stepMainPage.scrollQuestionAndAnswer();
       assertEquals(expectedQuestion, stepMainPage.textQuestion(i));
    }

    @Description("Тест проверки текста ответов на вопросы о Самом важном")
    @DisplayName("test Answer")
    @ParameterizedTest
    @CsvFileSource( resources = "answer.csv")
    void testAnswer(String expectedAnswer, int i){
        stepMainPage.scrollQuestionAndAnswer();
        stepMainPage.clickButtonAnswer(i);
        assertEquals(expectedAnswer, stepMainPage.textAnswer(i));
    }

    @Description("Тест проверка появления поля ввода номера заказа после нажатия кнопки статус заказа в верхнем правом углу")
    @DisplayName("test Displayed Field Status Order")
    @Test
    void testDisplayedFieldStatusOrder(){
        stepMainPage.clickButtonStatusOrder();
        assertTrue(mainPage.statusOrder().isDisplayed());
    }

    @Description("Тест переход на страницу Для кого самокат после нажатия кнопки Заказать внизу страницы")
    @DisplayName("test Click Bottom Button Order")
    @Test
    void testClickBottomButtonOrder(){
        stepMainPage.scrollButtonBottomOrder();
        stepMainPage.clickButtonBottomOrder();
        assertTrue(stepWhoIsScooter.isDisplayedTextWhoISScooter());
    }
}
