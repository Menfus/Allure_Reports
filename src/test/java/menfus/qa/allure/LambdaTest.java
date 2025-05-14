package menfus.qa.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class LambdaTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String TITLE = "title";


    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "normal";
    }


    @Test
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open(baseUrl);
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(by("data-target", "qbsearch-input.inputButtonText")).click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });
        step("Открываем таб Issues", () -> {
            $$("ul li a").findBy(text("Issues")).click();
        });
        step("Проверяем наличие Issue с названием" + TITLE, () -> {
            $(withText(TITLE)).should(Condition.exist);
        });

    }
}



