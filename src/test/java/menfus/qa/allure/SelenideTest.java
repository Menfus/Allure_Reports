package menfus.qa.allure;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;

@DisplayName("Обычный Selinide Test")
public class SelenideTest {


    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "normal";

    }

    @DisplayName("Осуществляется поиск в табе Issues")
    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open(baseUrl);

        $(by("data-target", "qbsearch-input.inputButtonText")).click();
        $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
        $("#query-builder-test").submit();

        $$("ul li a").findBy(text("Issues")).click();
        $(withText("title")).should(Condition.exist);
    }

}


