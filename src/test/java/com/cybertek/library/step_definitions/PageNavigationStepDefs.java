package com.cybertek.library.step_definitions;

import com.cybertek.library.pages.DashboardPage;
import com.cybertek.library.pages.UsersPage;
import com.cybertek.library.utilities.BrowserUtils;
import com.cybertek.library.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PageNavigationStepDefs {
    DashboardPage dashboardPage = new DashboardPage();
    UsersPage usersPage = new UsersPage();

    @When("I click on {string} link")
    public void i_click_on_link(String link) {
        BrowserUtils.wait(5);
        switch (link.toLowerCase()){
            case "dashboard" :
                dashboardPage.dashboard.click();
                break;
            case "users" :
                dashboardPage.users.click();
                break;
            case "books" :
                dashboardPage.books.click();
                break;
        }

    }

    @Then("{string} page should be displayed")
    public void page_should_be_displayed(String page) {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().endsWith(page.toLowerCase()));
        String actual;
        switch (page.toLowerCase()){
            case "users" :
                actual = dashboardPage.pageHeader.getText();
                Assert.assertEquals("User Management", actual);
                break;
            case "books" :
                actual = dashboardPage.pageHeader.getText();
                Assert.assertEquals("Book Management", actual);
                break;
        }
    }
    @Then("show records default value should be {int}")
    public void show_records_default_value_should_be(Integer selected) {
        System.out.println("selected = "+selected);
        String actual = usersPage.getShowRecords().getFirstSelectedOption().getText();
        Assert.assertEquals(actual, selected.toString());
    }

    @Then("show records should have following options:")
    public void show_records_should_have_following_options(List<String> options) {
        System.out.println("Size = "+ options.size());
        System.out.println(options.toString());
        List<WebElement> webElements = usersPage.getShowRecords().getOptions();
        List<String> elementsText = BrowserUtils.getElementsText(webElements);
        Assert.assertEquals(options, elementsText);
    }

}
