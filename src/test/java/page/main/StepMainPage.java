package page.main;

import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.logevents.SelenideLogger.step;


public class StepMainPage {
    MainPage mainPage = new MainPage();

    public boolean isDisplayMainText(){
        step("Проверка отображения главной страницы сайта", ()->{
            });
        return mainPage.scooter().isDisplayed();
    }

    public void clickButtonOrder(){
        step("Нажатие кнопки Заказать в верхнем правом углу", ()->{
            mainPage.buttonOrder().click();
        });
    }

    public void scrollQuestionAndAnswer(){
        step("Скролл сайта до вопросов о Самом важном", ()->{
           mainPage.questionAndAnswer().get(0).scrollTo();
        });
    }

    public String textQuestion(int i){
        step("получение текста вопроса о Самом важном ", ()->{});
        return mainPage.question(i).getText();
    }

    public void clickButtonAnswer(int i){
        step("клик на вопрос о Самом важном", ()->{
            mainPage.question(i).click();
        });
    }

    public String textAnswer(int i){
        step("получения текста ответа на вопрос о Самом важном", ()-> {});
        return mainPage.answer(i).getText();
    }

    public void clickButtonStatusOrder(){
        step("Нажатие кнопки статус заказа в верхнем правом углу", ()->{
            mainPage.statusOrder().click();
        });
    }

   /** public boolean displayedFieldStatusOrder(){
        step("��������� ���� ����� ������ ������", ()->{});
        return mainPage.statusOrder().isDisplayed();
    }
    */
}
