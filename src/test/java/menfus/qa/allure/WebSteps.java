package menfus.qa.allure;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class WebSteps {




    @Step ("Открываем главную страницу")
    public WebSteps openMainPage(){
        open(baseUrl);
        return this;
    }

    @Step("Ищем репозиторий {repo}")
    public WebSteps searchForRepository(String repo){
        $(by("data-target", "qbsearch-input.inputButtonText")).click();
        $("#query-builder-test").sendKeys(repo);
        $("#query-builder-test").submit();
        return this;
    }

    @Step("Открываем таб Issues")
    public WebSteps openIssuesTab(){
        $$("ul li a").findBy(text("Issues")).click();
        return this;
    }

    @Step("Проверяем наличие Issue с названием {issue}")
    public WebSteps shouldSeeIssueWithText(String issue){
        $(withText(issue)).should(Condition.exist);
        return this;
    }
}
