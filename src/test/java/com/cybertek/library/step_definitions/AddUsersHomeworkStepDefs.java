package com.cybertek.library.step_definitions;

import com.cybertek.library.pages.AddUsersPage;
import com.cybertek.library.pages.LoginPage;
import com.cybertek.library.pages.PageBase;
import com.cybertek.library.pages.UsersPage;
import com.cybertek.library.utilities.*;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class AddUsersHomeworkStepDefs extends PageBase {
    LoginPage loginPage = new LoginPage();
    UsersPage usersPage = new UsersPage();
    AddUsersPage addUsersPage = new AddUsersPage();

    Random randomGenerator = new Random();
    int randomInt = randomGenerator.nextInt(1000);

    Faker faker = new Faker();
    String fullName = faker.name().fullName();
    String pass = faker.random().hex(10);
    String email = faker.name().firstName()+randomInt+"@library.com";

    @Given("I access Users page as a librarian")
    public void i_access_Users_page_as_a_librarian() {
        String url = ConfigurationReader.getProperty("url");
        Driver.getDriver().get(url);
        String email = ConfigurationReader.getProperty("librarian_email");
        String password = ConfigurationReader.getProperty("librarian_password");
        loginPage.login(email, password);
        users.click();
    }

    @Given("I click on Add User")
    public void i_click_on_Add_User() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        wait.until(ExpectedConditions.elementToBeClickable(usersPage.addUsers));
        usersPage.addUsers.click();
    }

    @Then("start date should be today's date")
    public void start_date_should_be_today_s_date() {
        String actualDate = addUsersPage.getActualStartDate();
        String expectedDate = dateTimeFormatUtils.getCurrentDate("yyyy-MM-dd");
        Assert.assertEquals(actualDate, expectedDate);
    }

    @Then("end date should be one month from today")
    public void end_date_should_be_one_month_from_today() {
        String actualFutureDate = addUsersPage.getActualEndDate();
        String expectedFutureDate = addUsersPage.getExpectedEndDate("yyyy-MM-dd", 1);
        Assert.assertEquals(actualFutureDate, expectedFutureDate);
    }
//////////////////////////////////////
    @Given("I enter new user information with random email")
    public void i_enter_new_user_information_with_random_email() {
        addUsersPage.fullName.sendKeys(fullName);
        addUsersPage.password.sendKeys(pass);
        addUsersPage.email.sendKeys(email);
    }

    @When("I click the Close link")
    public void i_click_the_Close_link() {
        addUsersPage.closeButton.click();
    }

    @Then("the users table should not contain user with that email")
    public void the_users_table_should_not_contain_user_with_that_email() {
        Assert.assertFalse(usersPage.allEmails.contains(email));
    }

    //////////////////////////////////////

    @When("I save new user information with random email")
    public void i_save_new_user_information_with_random_email() {
        addUsersPage.fullName.sendKeys(fullName);
        addUsersPage.password.sendKeys(pass);
        addUsersPage.email.sendKeys(email);
        addUsersPage.saveButton.click();
    }

    @When("the users table must contain the correct user information")
    public void the_users_table_must_contain_the_correct_user_information() {
        usersPage.searchField.sendKeys(email);
        String actualEmail = usersPage.emailResult.getText();
        BrowserUtils.wait(1);
        Assert.assertEquals(actualEmail, email);
    }
}
