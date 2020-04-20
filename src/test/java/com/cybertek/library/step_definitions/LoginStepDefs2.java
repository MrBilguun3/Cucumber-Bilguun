package com.cybertek.library.step_definitions;

import com.cybertek.library.pages.DashboardPage;
import com.cybertek.library.pages.LoginPage;
import com.cybertek.library.utilities.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDefs2 {
    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();

    @When("I enter username {string}")
    public void i_enter_username(String username) {
        System.out.println("Enter username: "+username);
        loginPage.email.sendKeys(username);
    }

    @When("I enter password {string}")
    public void i_enter_password(String password) {
        System.out.println("Enter password: "+password);
        loginPage.password.sendKeys(password);
    }

    @When("click the sign in button")
    public void click_the_sign_in_button() {
        System.out.println("Clicking on sign in button");
        loginPage.signIn.click();
    }

    @Then("there should be {int} users")
    public void there_should_be_users(Integer count) {
        System.out.println("Verifying user count "+ count);
        String actual = dashboardPage.usersCount.getText();
        System.out.println(actual);
        Assert.assertEquals(count+"", actual);
    }

    @When("I login using {string} and {string}")
    public void i_login_using_and(String username, String password) {
        //System.out.println("Logging in using "+ username + " and "+ password);
        loginPage.login(username, password);

    }

    @Then("there should be {int} {string}")
    public void there_should_be(Integer count, String type) {
        //System.out.println("Verifying "+ count+ " "+type);
        String actual;
        switch (type.toLowerCase()){
            case "users" :
                actual =dashboardPage.usersCount.getText().toLowerCase()+" "+ dashboardPage.usersText.getText().toLowerCase();
                Assert.assertEquals(actual, count+" "+type);
                break;
            case "books" :
                actual =dashboardPage.booksCount.getText().toLowerCase()+" "+ dashboardPage.booksText.getText().toLowerCase();
                Assert.assertEquals(actual, count+" "+type);
                break;
            case "borrowed books" :
                actual =dashboardPage.borrowedBooksCount.getText().toLowerCase()+" "+ dashboardPage.borrowedBooksText.getText().toLowerCase();
                Assert.assertEquals(actual, count+" "+type);
                break;

        }
    }
}
