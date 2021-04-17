package Tests;
import PageObjects.CustomerPage;
import PageObjects.HomePage;
import PageObjects.SignUpPage;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestClass extends BaseClass {

    @Test(priority = 1, testName = "signIn", description = "Create a new account with the data retrieved from the database.")
    @Description("Create a new account with data retrieved from the database.")
    @Epic("Test001")
    @Feature("Feature 1: sign up")
    @Story("create a new account")
    @Severity(SeverityLevel.CRITICAL)
    public void signIn(){

        HomePage.myHome()
                .open()
                .moveToSignIn();

        SignUpPage.myPage()
                .insertDataIntoDB()
                .selectDataFromDB()
                .create_account();
    }

    @Test(priority = 2, testName = "sendToAFriend", groups = {"method_2"}, dataProvider = "Data_Provider",
            description = "Send a dress with a discount to the Friends")
    @Description("Send a dress with a discount to the Friends")
    @Epic("Test001")
    @Feature("Feature 2: send to the friends")
    @Story("send a dress to the friends")
    @Severity(SeverityLevel.NORMAL)
    public void sendToAFriend(String firstName, String email){

        HomePage.myHome()
                .open()
                .moveToDress()
                .withProductData(friendsProduct)
                .validateTitle()
                .addDetails()
                .sendToAFriend(firstName, email);
    }

    @Test(priority = 3, testName = "proceedToCheckout", groups = {"method_3"}, dependsOnMethods = {"signIn"},
            description = "buy items, sign in with the recent account and proceed to checkout" )
    @Description("buy items, sign in with the recent account and proceed to checkout")
    @Epic("Test001")
    @Feature("Feature 3: buy and checkout")
    @Story("buy items and proceed to checkout")
    @Severity(SeverityLevel.NORMAL)
    public void proceedToCheckout(){

        HomePage.myHome()
                .open()
                .moveToDress()
                .withProductData(friendsProduct)
                .validateTitle()
                .addDetails()
                .addToCartAndProceed();

        SignUpPage.myPage()
                .selectDataFromDB()
                .signIn();

        HomePage.myHome()
                .proceed()
                .payByBankWire();
    }

    @Test(priority = 4, testName = "buyEveningDress", groups = {"method_4"}, retryAnalyzer = RetryAnalyzer.class,
            dependsOnMethods = {"signIn"}, description = "buy an evening dress, write to customer services, upload a file and send a message")
    @Description("buy an evening dress, write to customer services, upload a file and send a message")
    @Epic("Test001")
    @Feature("Feature 4: evening dress")
    @Story("buy an evening dress and write to customer services")
    @Severity(SeverityLevel.NORMAL)
    public void buyEveningDress(){

        HomePage.myHome()
                .open()
                .buyEveningDress();

        SignUpPage.myPage()
                .selectDataFromDB()
                .signIn();

        HomePage.myHome()
                .proceed()
                .payByCheck();

        CustomerPage.myService()
                .moveToService()
                .fillTheForm()
                .uploadFile()
                .sendMessageAndSubmit();
    }

    @DataProvider(name="Data_Provider")
    public Object[][] Data() {
        Object[][] data = {{"Alina", "atkabladze@gmail.com"},
                {"Nino", "nnozadze@gmail.com"}};
        return data;
    }
}

