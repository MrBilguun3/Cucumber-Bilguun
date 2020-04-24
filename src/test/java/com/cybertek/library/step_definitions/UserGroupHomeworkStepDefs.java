package com.cybertek.library.step_definitions;

import com.cybertek.library.pages.UsersPage;
import com.cybertek.library.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UserGroupHomeworkStepDefs {
    UsersPage usersPage = new UsersPage();

    @Then("User group default value should be {string}")
    public void user_group_default_value_should_be(String allText) {
        String expected = allText;
        String actual = usersPage.getDefaultValue().getFirstSelectedOption().getText();
        Assert.assertEquals(actual, expected);
    }

    @Then("show user group records should have following options:")
    public void show_user_group_records_should_have_following_options(List<String> expectedOptions) {
        List<WebElement> webElements = usersPage.getAllOptions().getOptions();
        List<String> actual = BrowserUtils.getElementsText(webElements);
        Assert.assertEquals(actual, expectedOptions);

    }

    ////////////////////////
    @When("I select User group {string}")
    public void i_select_User_group(String select) {
        usersPage.getAllOptions().selectByVisibleText(select);
        String expectedSelect = select;
        String actualSelect = usersPage.getAllOptions().getFirstSelectedOption().getText();
        Assert.assertEquals(actualSelect, expectedSelect);
    }

    @Then("Groups columns in user table should only contain {string}")
    public void groups_columns_in_user_table_should_only_contain(String expectedString) {
        int size = usersPage.groupColumns.size();
        for (int i = 0; i <size ; i++) {
            String groupText = usersPage.groupColumns.get(i).getText();
            boolean found = groupText.equals(expectedString);
            Assert.assertTrue(found);
        }

    }



}
