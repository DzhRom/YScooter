
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import constatnts.Constants;
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
        stepMainPage = new StepMainPage();
        stepWhoIsScooter = new StepWhoIsScooter();
        mainPage = new MainPage();
        Configuration.browser = "edge";
        Configuration.browserSize = "1920x1080";
        open(Constants.MAIN_URL);
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


    @DisplayName("Тест проверка текста вопроса о Самом важном'")
    @ParameterizedTest(name = "{index} - {0}")
    @CsvFileSource( resources = "question.csv")
    void testQuestion(String expectedQuestion, int i){
       stepMainPage.scrollQuestionAndAnswer();
       assertEquals(expectedQuestion, stepMainPage.textQuestion(i));
    }

    @DisplayName("Тест проверки текста ответов на вопросы о Самом важном")
    @ParameterizedTest
    @CsvFileSource( resources = "answer.csv")
    void testAnswer(String expectedAnswer, int i){
        stepMainPage.scrollQuestionAndAnswer();
        stepMainPage.clickButtonAnswer(i);
        assertEquals(expectedAnswer, stepMainPage.textAnswer(i));
    }

    @DisplayName("Тест проверка появления поля ввода номера заказа после нажатия кнопки статус заказа в верхнем правом углу")
    @Test
    void testDisplayedFieldStatusOrder(){
        stepMainPage.clickButtonStatusOrder();
        assertTrue(mainPage.statusOrder().isDisplayed());
    }

    @DisplayName("Тест переход на страницу Для кого самокат после нажатия кнопки Заказать внизу страницы ")
    @Test
    void testClickBottomButtonOrder(){
        stepMainPage.scrollButtonBottomOrder();
        stepMainPage.clickButtonBottomOrder();
        assertTrue(stepWhoIsScooter.isDisplayedTextWhoISScooter());
    }
}
