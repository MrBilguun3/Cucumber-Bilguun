package com.cybertek.library.pages;

import io.cucumber.java.eo.Se;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UsersPage extends PageBase{
    @FindBy(name = "tbl_users_length")
    public WebElement showRecords;

    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> allRows;

    public Select getShowRecords(){
        return new Select(showRecords);
    }

    @FindBy(xpath = "//a[@href='tpl/add-user.html']")
    public WebElement addUser;

    @FindBy(name = "start_date")
    public WebElement todaysDate;

    @FindBy(name = "end_date")
    public WebElement monthLaterDate;

    @FindBy(name = "full_name")
    public WebElement fullName;

    @FindBy(name = "password")
    public WebElement password;

    @FindBy(name = "email")
    public WebElement email;

    @FindBy(xpath = "//table/tbody//td[4]")
    public WebElement emailResult;

    @FindBy(xpath = "//button[@class='btn default']")
    public WebElement closeButton;

    @FindBy(xpath = "//table/tbody//td[2]")
    public List<WebElement> allUserIDs;

    @FindBy(xpath = "//table/tbody//td[3]")
    public List<WebElement> allFullNames;

    @FindBy(xpath = "//table/tbody//td[4]")
    public List<WebElement> allEmails;

    @FindBy(xpath = "//button[.='Save changes']")
    public WebElement saveButton;

    @FindBy(xpath = "//input")
    public WebElement searchField;

    @FindBy(tagName = "th")
    public List<WebElement> columnNames;

    @FindBy(className = "sorting_desc")
    public WebElement userIDButton;

    public String getExpectedEndDate(String datePattern, int addMonths){
        LocalDate futureDate = LocalDate.now().plusMonths(addMonths);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(datePattern);
        return dtf.format(futureDate);
    }
    public String getActualStartDate(){
        String actualStartDate = todaysDate.getAttribute("value");
        return actualStartDate;
    }
    public String getActualEndDate(){
        String actualEndDate = monthLaterDate.getAttribute("value");
        return actualEndDate;
    }


}
