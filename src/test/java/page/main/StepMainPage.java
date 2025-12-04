package page.main;

import io.qameta.allure.Step;

public class StepMainPage {
    MainPage mainPage = new MainPage();

    @Step("Проверка отображения главной страницы сайта")
    public boolean isDisplayMainText(){
        return mainPage.scooter().isDisplayed();
    }

    @Step("Нажатие кнопки Заказать в верхнем правом углу")
    public void clickButtonOrder(){
        mainPage.buttonOrder().click();
    }

    @Step("Скролл сайта до вопросов о Самом важном")
    public void scrollQuestionAndAnswer(){
        mainPage.questionAndAnswer().get(0).scrollTo();
    }

    @Step("Скролл до кнопки Заказать внизу страницы")
    public void scrollButtonBottomOrder(){
           mainPage.buttonBottomOrder().scrollTo();
    }

    @Step("получение текста вопроса о Самом важном")
    public String textQuestion(int i){
        return mainPage.question(i).getText();
    }

    @Step("клик на вопрос о Самом важном")
    public void clickButtonAnswer(int i){
        mainPage.question(i).click();
    }

    @Step("получения текста ответа на вопрос о Самом важном")
    public String textAnswer(int i){
        return mainPage.answer(i).getText();
    }

    @Step("Нажатие кнопки статус заказа в верхнем правом углу")
    public void clickButtonStatusOrder(){
        mainPage.statusOrder().click();
    }

    @Step("Нажатие кнопки Заказать внизу страницы")
    public void clickButtonBottomOrder(){
        mainPage.buttonBottomOrder().shouldBe();
        mainPage.buttonBottomOrder().click();
    }

}
