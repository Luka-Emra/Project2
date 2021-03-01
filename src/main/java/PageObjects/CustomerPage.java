package PageObjects;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.io.File;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

public class CustomerPage {

    File file = new File(System.getProperty("user.dir") + "/src/tbc.png");
    String path = file.getAbsolutePath();

    String textMessage = "Hello, I want to change the color of the item, " +
            "I have ordered a few hours ago, if it is possible of course. Thanks in advance.";

    SelenideElement attachFile = $(byId("fileUpload"));
    SelenideElement customerServiceLink = $(byXpath("//a[contains(text(),'customer')]"));
    SelenideElement header = $(byName("id_contact"));
    SelenideElement message = $(byId("message"));
    SelenideElement order_reference = $(byName("id_order"));
    SelenideElement product = $(byName("id_product"));
    SelenideElement submit = $(byId("submitMessage"));

    public static CustomerPage myService(){ return new CustomerPage(); }

    @Step("move to customer service")
    public CustomerPage moveToService() {
        customerServiceLink.click();
        return this;
    }

    @Step("fill the customer service form")
    public CustomerPage fillTheForm() {
        header.selectOptionContainingText("Customer");
        order_reference.selectOption(1);
        product.selectOptionContainingText("Printed");
        return this;
    }

    @Step("move to customer service, upload file and send message")
    public CustomerPage uploadFile() {
        attachFile.sendKeys(path);
        return this;
    }

    @Step("send message and submit")
    public void sendMessageAndSubmit() {
        message.sendKeys(textMessage);
        submit.click();
    }
}
