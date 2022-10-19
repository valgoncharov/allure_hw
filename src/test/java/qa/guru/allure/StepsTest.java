package qa.guru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 80;

    @BeforeAll
    static void configure(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }

    @DisplayName("Example allure with Artem")
    @Test
    public void testLambdaStep(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
                    open("https://github.com/");
                });

//        step("Что-то делаем", new Allure.ThrowableRunnableVoid() {
//            @Override
//            public void run() throws Throwable {
////            }});

        step("Ищем репозиторий " + REPOSITORY,() -> {
                    $(".header-search-input").click();
                    $(".header-search-input").sendKeys("eroshenkoam/allure-example");
                    $(".header-search-input").submit();
                });
        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
                    $(linkText(REPOSITORY)).click();
                });
        step("Открываем таб Issue", () -> {
                    $("#issues-tab").click();
                });
        step("Проверяем наличие Issue с номером " + ISSUE, () ->{
            $(withText("#" + ISSUE)).should(Condition.exist);
        });


    }
}
