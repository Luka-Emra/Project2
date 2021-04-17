package PageObjects;
import DataObjects.ProductData;
import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    ProductData friendsProduct;

    SelenideElement addToCart = $(byXpath("//button[@class='exclusive']/span"));
    SelenideElement addToCart2 = $(byXpath("//a[@title='Add to cart']//span"));
    SelenideElement agreeTerms = $(byId("uniform-cgv"));
    SelenideElement bestSellers = $(byXpath("//a[@class='blockbestsellers']"));
    SelenideElement confirm = $(byXpath("//button[@type='submit' and contains(@class, 'button-medium')]"));
    SelenideElement dresses = $(byXpath("//a[@title='Women' and @class='sf-with-ul']//parent::li//following::li[9]/a[@title='Dresses']"));
    SelenideElement eveningDresses = $(byXpath("//ul[starts-with(@class, 'submenu')]/li[2]/a[text()='Evening Dresses']"));
    SelenideElement form = $(byId("send_friend_form"));
    SelenideElement friend_email = $(byId("friend_email"));
    SelenideElement friend_name = $(byId("friend_name"));
    SelenideElement image1 = $(byXpath("//ul[@id='blockbestsellers']//img[@alt='Printed Chiffon Dress']"));
    SelenideElement image2 = $(byXpath("//img[@alt='Printed Dress']"));
    SelenideElement more = $(byXpath("//ul[@id='blockbestsellers']//span[text()='-20%']/parent::div//following::div[1]//span[text()='More']"));
    SelenideElement ok = $(byXpath("//input[@value='OK']"));
    SelenideElement payByBankWire = $("a.bankwire");
    SelenideElement payByCheck = $("a.cheque");
    SelenideElement proceedToCheckout1 = $(byXpath("//span[contains(text(), 'checkout')]"));
    SelenideElement proceedToCheckout2 = $(byXpath("//p[contains(@class, 'clearfix')]//span"));
    SelenideElement quantity = $(byId("quantity_wanted"));
    SelenideElement send = $(byId("sendEmail"));
    SelenideElement sendToAFriend = $(byId("send_friend_button"));
    SelenideElement signIn = $(byXpath("//a[@class='login']"));
    SelenideElement size_dropdown = $(byId("group_1"));

    public static HomePage myHome(){ return new HomePage(); }

    @Step("open home page")
    public HomePage open() {
        Selenide.open("/index.php");
        return this;
    }

    @Step("move to sign in")
    public void moveToSignIn(){
        signIn.click();
    }

    @Step("move to dress")
    public HomePage moveToDress(){
        bestSellers.click();
        actions().moveToElement(image1).click(more).perform();
        return this;
    }

    @Step("Use the data of the provided object")
    public HomePage withProductData(ProductData productData) {
        this.friendsProduct = productData;
        return this;
    }

    @Step("Validate the title of the page")
    public HomePage validateTitle(){
        Assert.assertEquals(title(),  friendsProduct.getName()+" - My Store");
        return this;
    }

    @Step("add quantity and size")
    public HomePage addDetails(){
        quantity.clear();
        quantity.sendKeys(friendsProduct.getQty());

        Select size = new Select(size_dropdown);
        size.selectByVisibleText(friendsProduct.getSize());
        return this;
    }

    @Step("send the product to friends")
    public void sendToAFriend(String firstName, String email){
        sendToAFriend.click();
        form.shouldBe(Condition.visible);
        friend_name.clear();
        friend_name.sendKeys(firstName);
        friend_email.clear();
        friend_email.sendKeys(email);
        send.click();
        ok.click();
    }

    @Step("add items to the cart and proceed to checkout")
    public void addToCartAndProceed(){
        addToCart.click();
        proceedToCheckout1.click();
        proceedToCheckout2.click();
    }

    @Step("add Evening Dress to the cart")
    public void buyEveningDress(){
        actions().moveToElement(dresses).click(eveningDresses).perform();
        actions().moveToElement(image2).click(addToCart2).perform();
        proceedToCheckout1.click();
        proceedToCheckout2.click();
    }

    @Step("proceed to checkout after sign in")
    public HomePage proceed(){
        proceedToCheckout2.click();
        agreeTerms.click();
        proceedToCheckout2.click();
        return this;
    }

    @Step("pay by bank wire and confirm")
    public void payByBankWire(){
        payByBankWire.click();
        confirm.click();
    }

    @Step("pay by check and confirm")
    public HomePage payByCheck(){
        payByCheck.click();
        confirm.click();
        return this;
    }
}
