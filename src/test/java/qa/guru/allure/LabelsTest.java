package qa.guru.allure;


import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LabelsTest {

    @BeforeAll
    static void configure(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    @Feature("Issue  в репозитории")
    @Story("Создание Issue")
    @Owner("goncharovv")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://testing.github.com/")
    @DisplayName("Создание Issue для авторизованного пользователя")
    public void testStaticLabels(){

    }

    @Test
    public void testDynamicLabels(){
        Allure.getLifecycle().updateTestCase(
                t -> t.setName("Создание Issue для авторизованного пользователя"));
        //Используется для точек расширения
        if (true){
            Allure.feature("Issue в репозитории");
        }else {
            Allure.feature("Issue не в репозитории");
        }
        Allure.feature("Issue в репозитории");
        Allure.story("Создание Issue");
        Allure.label("owner", "goncharovv");
        Allure.label("severity", SeverityLevel.CRITICAL.value());
        Allure.link("https://testing.github.com/");

    }
}
