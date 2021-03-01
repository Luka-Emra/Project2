package Tests;
import DataObjects.ProductData;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Configuration.browser;

public class BaseClass {

    @BeforeTest
    public void setUp() {

        startMaximized=true;
        baseUrl = "http://automationpractice.com";
        timeout = 9000;
        browser = "Chrome";

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }

    ProductData friendsProduct = new ProductData(
            "Printed Chiffon Dress",
            "2",
            "M"
    );

    @AfterMethod
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }
}
