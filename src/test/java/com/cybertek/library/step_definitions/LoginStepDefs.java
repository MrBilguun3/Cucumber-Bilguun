package com.cybertek.library.step_definitions;

import com.cybertek.library.pages.AddUsersPage;
import com.cybertek.library.pages.LoginPage;
import com.cybertek.library.utilities.ConfigurationReader;
import com.cybertek.library.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

public class LoginStepDefs {
    LoginPage loginPage = new LoginPage();
    AddUsersPage addUsersPage = new AddUsersPage();

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        System.out.println("Going to the login page");
        // login
        // Driver.getDriver()  --> gives us a driver object
        String url = ConfigurationReader.getProperty("url");
        Driver.getDriver().get(url);

    }

    @When("I login as a librarian")
    public void i_login_as_a_librarian() {
        String email;
        String password;
        System.out.println("Logging in as a librarian");
        email = ConfigurationReader.getProperty("librarian_email");
        password = ConfigurationReader.getProperty("librarian_password");
        loginPage.login(email, password);
    }

    @Then("dashboard should be displayed")
    public void dashboard_should_be_displayed() {
        System.out.println("Verifying dashboard page");
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
       // wait.until(ExpectedConditions.urlContains("dashboard"));
        String actualTitle = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(actualTitle.endsWith("dashboard"));
    }

    @When("I login as a student")
    public void i_login_as_a_student() {
        System.out.println("Logging in as a student");
        String email = ConfigurationReader.getProperty("student_email");
        String password = ConfigurationReader.getProperty("student_password");
        loginPage.login(email, password);

    }

    @When("I login as an admin")
    public void i_login_as_a_admin() {
        System.out.println("Logging in as an admin");
    }

    @Given("I login using following credentials:")
    public void i_login_using_following_credentials(Map<String, String> credentials) {
        System.out.println(credentials);
        String email = credentials.get("email");
        String password = credentials.get("password");
        System.out.println("email = " + email);
        System.out.println("password = " + password);
        loginPage.login(email, password);
    }

    @Then("dialog fields must have matching placeholder")
    public void dialog_fields_must_have_matching_placeholder(Map<String, String> fields) {
        for (String key : fields.keySet()) {
            System.out.println("Key = " + key + "\nValue = " + fields.get(key));
            System.out.println();
        }

        String expectedFullName = fields.get("fullname");
        String actualFullName = addUsersPage.fullName.getAttribute("placeholder");
        Assert.assertEquals(actualFullName, expectedFullName);

        String expectedEmail = fields.get("email");
        String actualEmail = addUsersPage.email.getAttribute("placeholder");
        Assert.assertEquals(actualEmail, expectedEmail);

        String expectedPassword = fields.get("password");
        String actualPassword = addUsersPage.password.getAttribute("placeholder");
        Assert.assertEquals(actualPassword, expectedPassword);

        String actualAddressField = addUsersPage.address.getAttribute("placeholder");
        Assert.assertTrue(actualAddressField.isEmpty());
    }
}
