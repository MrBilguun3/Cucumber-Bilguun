package com.cybertek.library.step_definitions;

import com.cybertek.library.pages.UsersPage;
import com.cybertek.library.utilities.BrowserUtils;
import com.github.javafaker.Faker;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class UsersTableSearchHWStepDefs {
    Random randomInt = new Random();
    UsersPage usersPage = new UsersPage();
    Faker faker = new Faker();

    @When("I search for any valid email")
    public void i_search_for_any_valid_email() {
        usersPage.getShowRecords().selectByIndex(6);
        BrowserUtils.waitForVisibility(usersPage.showRecords, 1);
        List<WebElement> webElements = usersPage.allEmails;
        List<String> emails = BrowserUtils.getElementsText(webElements);
        int emailInt = randomInt.nextInt(emails.size()-1);
        String randomValidEmail = emails.get(emailInt);
        usersPage.searchField.sendKeys(randomValidEmail);
    }

/////////////////////

    @When("I search for any invalid email")
    public void i_search_for_any_invalid_email() {
        String invalidEmail = faker.name().firstName()+"@library.com";
        usersPage.searchField.sendKeys(invalidEmail);
    }

    @Then("the users table must display message {string}")
    public void the_users_table_must_display_message(String expectedMessage) {
        BrowserUtils.waitForVisibility(usersPage.message, 1);
        String actualMessage = usersPage.message.getText();
        Assert.assertEquals(actualMessage, expectedMessage);
    }
/////////////////////

    @Then("users table should be sorted by {string} in {string} order")
    public void users_table_should_be_sorted_by_in_order(String columnHead, String order) {
        List<WebElement> webElements = usersPage.allUserIDs;
        List<String> all = BrowserUtils.getElementsText(webElements);
        int firstID = Integer.parseInt(all.get(0));
        if(columnHead.equals("User ID")){
            usersPage.userID.click();
            if(order.equals("ascending")){
                for (int i=0; i<all.size(); i++){
                    System.out.println(firstID);
                    System.out.println(Integer.parseInt(all.get(i)));
                    Assert.assertEquals(firstID,Integer.parseInt(all.get(i)));
                    firstID--;
                }
            }else if(order.equals("descending")){
                for (int i=0; i<all.size(); i++){
                    System.out.println(firstID);
                    System.out.println(Integer.parseInt(all.get(i)));
                    Assert.assertEquals(firstID,Integer.parseInt(all.get(i)));
                    firstID++;
                }
            }
        }else if(columnHead.equals("Email")){
            usersPage.emailButton.click();
            if(order.equals("ascending")){
                List<String> expected = all;
                Arrays.sort(all.toArray());
                for (int i=0; i<all.size(); i++){
                    Assert.assertEquals(all.get(i), expected.get(i));
                }
            }else if(order.equals("descending")){

            }
        }

    }

    @When("I click on the {string} column")
    public void i_click_on_the_column(String columnHead) {
       switch (columnHead){
           case "User ID" :
               usersPage.userID.click();
               break;
           case "Email" :
               usersPage.emailButton.click();
               break;
       }
    }


}
