package com.cybertek.library.step_definitions;

import com.cybertek.library.pages.UsersPage;
import com.cybertek.library.utilities.BrowserUtils;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

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
//        int optionsSize = usersPage.getShowRecords().getOptions().size()-1;
//        usersPage.getShowRecords().selectByIndex(optionsSize);
        List<WebElement> webElements = usersPage.allUserIDs;
        List<String> all = BrowserUtils.getElementsText(webElements);
        if (columnHead.equals("User ID")) {
            if(order.equals("ascending")) {
                for (int i = 0; i < all.size(); i++) {
                    int temp = Integer.parseInt(all.get(0));
                    int temp1 = Integer.parseInt(all.get(i));
                    assertEquals(temp1, temp);
                    temp--;
                }
            }else if(order.equals("descending")){
                usersPage.userID.click();
                for (int i = 0; i < all.size(); i++) {
                    int temp = Integer.parseInt(all.get(0));
                    int temp1 = Integer.parseInt(all.get(i));
                    assertEquals(temp, temp1);
                    temp++;
                }
            }
        }

































        //int firstID = Integer.parseInt(all.get(0)); //515
      //  System.out.println(all);
//        if(columnHead.equals("User ID")){
//            if(order.equals("ascending")){
//               for (int i=0; i<all.size(); i++){
//                   int temp = Integer.parseInt(all.get(i));
//                   System.out.println(temp);
//                   BrowserUtils.wait(1);
//                   assertTrue(firstID==temp);
//                   firstID--;
//               }
//            }else if(order.equals("descending")) {
//                usersPage.userID.click();
//                for (int i = 0; i<all.size(); i--) {
//                    int temp = Integer.parseInt(all.get(i));
//                    assertTrue(firstID == temp);
//                    firstID++;
//                }
//            }
//        }else if(columnHead.equals("Email")){
//            usersPage.emailButton.click();
//            if(order.equals("ascending")){
//                List<String> expected = all;
//                Arrays.sort(all.toArray());
//                for (int i=0; i<all.size(); i++){
//                    Assert.assertEquals(all.get(i), expected.get(i));
//                }
//            }else if(order.equals("descending")){
//
//            }
//        }

    }

    @When("I click on the {string} column")
    public void i_click_on_the_column(String columnHead) {
//       switch (columnHead){
//           case "User ID" :
//               usersPage.userID.click();
//               break;
//           case "Email" :
//               usersPage.emailButton.click();
//               break;
//       }
    }


}
// for (int i=firstID; i>=(firstID-all.size()); i--){ //10
//        System.out.println(firstID);
//        System.out.println(Integer.parseInt(all.get(i)));
//        int oneLess = firstID-1;
//        Assert.assertTrue(oneLess==i-1);
//        }
