package PageObjects;
import DataBase.InsertData;
import DataBase.SelectData;
import DataObjects.UserData;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class SignUpPage {

    InsertData insertData = new InsertData();
    SelectData selectData  = new SelectData();
    UserData userData;

    SelenideElement address = $(byId("address1"));
    SelenideElement city = $(byId("city"));
    SelenideElement create_account = $(byXpath("//i[@class='icon-user left']//parent::span"));
    SelenideElement email = $(byId("email_create"));
    SelenideElement emailPassword = $(byId("passwd"));
    SelenideElement first_name = $(byName("customer_firstname"));
    SelenideElement gender = $(byId("id_gender1"));
    SelenideElement last_name = $("input#customer_lastname");
    SelenideElement password = $(byId("passwd"));
    SelenideElement phone_num = $(byId("phone_mobile"));
    SelenideElement signIn = $(byId("SubmitLogin"));
    SelenideElement signInEmail = $(byId("email"));
    SelenideElement state = $(byName("id_state"));
    SelenideElement submit = $(byId("submitAccount"));
    SelenideElement zip_code = $(byId("postcode"));

    public static SignUpPage myPage(){ return new SignUpPage(); }

    @Step("Insert Data into Database")
    public SignUpPage insertDataIntoDB() {
        insertData.InsertIntoDB();
        return this;
    }

    @Step("Select last added user's data from Database")
    public SignUpPage selectDataFromDB() {
        userData = selectData.selectFromDB();
        return this;
    }

    @Step("create new account with provided data")
    public void create_account(){
        email.sendKeys(userData.getEmail());
        create_account.click();
        gender.click();
        first_name.sendKeys(userData.getFirst_name());
        last_name.sendKeys(userData.getLast_name());
        password.sendKeys(userData.getPassword());
        address.sendKeys(userData.getAddress());
        city.sendKeys(userData.getCity());
        state.sendKeys(userData.getState());
        zip_code.sendKeys(userData.getZip_code());
        phone_num.sendKeys(userData.getPhone_num());
        submit.click();
    }

    @Step("sign in with already created account and proceed")
    public void signIn(){
        signInEmail.sendKeys(userData.getEmail());
        emailPassword.sendKeys(userData.getPassword());
        signIn.click();
    }
}
