package com.cybertek.library.step_definitions;

import com.cybertek.library.pages.UsersPage;
import com.cybertek.library.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UsersTableStepDefs {
    UsersPage usersPage = new UsersPage();

    @When("I search for {string}")
    public void i_search_for(String searchString) {
        BrowserUtils.waitForClickability(usersPage.searchField, 5);
        usersPage.searchField.sendKeys(searchString);
        BrowserUtils.wait(1);
    }

    @Then("Table should contain rows with {string}")
    public void table_should_contain_rows_with(String expectedString) {
        int size = usersPage.allUserIDs.size();

        for (int i = 0; i <size ; i++) {
            String id = usersPage.allUserIDs.get(i).getText().toLowerCase();
            String name = usersPage.allFullNames.get(i).getText().toLowerCase();
            String email = usersPage.allEmails.get(i).getText().toLowerCase();

            System.out.println("ROW: "+ (i+1));
            System.out.println(id+"\t"+name+"\t"+email);

            boolean found = id.contains(expectedString) ||
                            name.contains(expectedString) ||
                            email.contains(expectedString);
            Assert.assertTrue(found);
        }

    }

    @Then("table should have following column names:")
    public void table_should_have_following_column_names(List<String> expectedColumnNames) {
        System.out.println(expectedColumnNames);
        List<String> actualColumnNames = BrowserUtils.getElementsText(usersPage.columnNames);
        assertEquals(expectedColumnNames, actualColumnNames);
    }

    @Then("table should contain this data")
    public void table_should_contain_this_data(Map<String, String> user) {
        System.out.println(user.entrySet());

        String name = user.get("Full Name");
        String email = user.get("Email");
        String userID = user.get("User ID");

        System.out.println(name+"\n"+email+"\n"+userID);
        //get all rows. verify that at least one of the rows contains all of the user info
        List<WebElement> allRows = usersPage.allRows;
        List<String> allRowsText = BrowserUtils.getElementsText(allRows);

        boolean found = false;
        for (String row : allRowsText){
            System.out.println("row = "+row);
            found = row.contains(userID) && row.contains(name) && row.contains(email);
            if(found){
                break;
            }
        }
        Assert.assertTrue(found);
    }

    @Then("Each user id must be unique")
    public void each_user_id_must_be_unique() {
        int optionsSize = usersPage.getShowRecords().getOptions().size()-1;
        usersPage.getShowRecords().selectByIndex(optionsSize);
        BrowserUtils.wait(1);

        List<String> list = BrowserUtils.getElementsText(usersPage.allUserIDs);
        System.out.println(list);

        Set<String> set = new HashSet<>();
        set.addAll(list);

        assertEquals(list.size(), set.size());
    }



}
