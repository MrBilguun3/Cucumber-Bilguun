package com.cybertek.library.step_definitions;

import com.cybertek.library.pages.DashboardPage;
import com.cybertek.library.pages.LoginPage;
import com.cybertek.library.pages.PageBase;
import com.cybertek.library.pages.UsersPage;
import com.cybertek.library.utilities.*;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class homeworkStepDefs extends PageBase {
    LoginPage loginPage = new LoginPage();
    UsersPage usersPage = new UsersPage();

    Random randomGenerator = new Random();
    int randomInt = randomGenerator.nextInt(1000);

    Faker faker = new Faker();
    String fullName = faker.name().fullName();
    String pass = faker.random().hex(10);
    String email = faker.name().firstName()+randomInt+"@library.com";


//emailTextBx.sendKeys("username"+ randomInt +"@gmail.com");

    @Given("I access Users page as a librarian")
    public void i_access_Users_page_as_a_librarian() {
        String url = ConfigurationReader.getProperty("url");
        Driver.getDriver().get(url);
        String email = ConfigurationReader.getProperty("librarian_email");
        String password = ConfigurationReader.getProperty("librarian_password");
        loginPage.login(email, password);
        users.click();
    }

//    @Given("I click on Add User")
//    public void i_click_on_Add_User() {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }

    @Given("I click on Add User")
    public void i_click_on_Add_User() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        wait.until(ExpectedConditions.elementToBeClickable(usersPage.addUser));
        usersPage.addUser.click();
    }

    @Then("start date should be today's date")
    public void start_date_should_be_today_s_date() {
        String actualDate = usersPage.getActualStartDate();
        String expectedDate = dateTimeFormatUtils.getCurrentDate("yyyy-MM-dd");
        Assert.assertEquals(actualDate, expectedDate);
    }

    @Then("end date should be one month from today")
    public void end_date_should_be_one_month_from_today() {
        String actualFutureDate = usersPage.getActualEndDate();
        String expectedFutureDate = usersPage.getExpectedEndDate("yyyy-MM-dd", 1);
        Assert.assertEquals(actualFutureDate, expectedFutureDate);
    }
//////////////////////////////////////
    @Given("I enter new user information with random email")
    public void i_enter_new_user_information_with_random_email() {
        usersPage.fullName.sendKeys(fullName);
        usersPage.password.sendKeys(pass);
        usersPage.email.sendKeys(email);
    }

    @When("I click the Close link")
    public void i_click_the_Close_link() {
        usersPage.closeButton.click();
    }

    @Then("the users table should not contain user with that email")
    public void the_users_table_should_not_contain_user_with_that_email() {
        Assert.assertFalse(usersPage.allEmails.contains(email));
    }

    //////////////////////////////////////

    @When("I save new user information with random email")
    public void i_save_new_user_information_with_random_email() {
        usersPage.fullName.sendKeys(fullName);
        usersPage.password.sendKeys(pass);
        usersPage.email.sendKeys(email);
        usersPage.saveButton.click();
    }

    @When("the users table must contain the correct user information")
    public void the_users_table_must_contain_the_correct_user_information() {
        usersPage.searchField.sendKeys(email);
        String actualEmail = usersPage.emailResult.getText();
        BrowserUtils.wait(1);
        Assert.assertEquals(actualEmail, email);
    }
}
