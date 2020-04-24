package com.cybertek.library.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class UsersPage extends PageBase{

    @FindBy(id = "user_groups")
    public WebElement defaultValue;

    public Select getDefaultValue(){
        return new Select(defaultValue);
    }

    @FindBy(id = "user_groups")
    public WebElement option;

    public Select getAllOptions(){
        return new Select(option);
    }

    @FindBy(name = "tbl_users_length")
    public WebElement showRecords;

    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> allRows;

    public Select getShowRecords(){
        return new Select(showRecords);
    }

    @FindBy(xpath = "//a[@href='tpl/add-user.html']")
    public WebElement addUsers;

    @FindBy(xpath = "//table/tbody//td[4]")
    public WebElement emailResult;

    @FindBy(xpath = "//table/tbody//td[2]")
    public List<WebElement> allUserIDs;

    @FindBy(xpath = "//table/tbody//td[3]")
    public List<WebElement> allFullNames;

    @FindBy(xpath = "//table/tbody//td[4]")
    public List<WebElement> allEmails;

    @FindBy(xpath = "//input")
    public WebElement searchField;

    @FindBy(tagName = "th")
    public List<WebElement> columnNames;

    @FindBy(xpath = "//td[5]")
    public List<WebElement> groupColumns;

    @FindBy(className = "dataTables_empty")
    public WebElement message;

    @FindBy(className = "sorting_desc")
    public WebElement userID;

    @FindBy(linkText = "Email")
    public WebElement emailButton;

}
