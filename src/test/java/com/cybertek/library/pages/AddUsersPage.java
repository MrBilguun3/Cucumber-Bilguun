package com.cybertek.library.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddUsersPage extends PageBase{
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

    @FindBy(id = "address")
    public WebElement address;

    @FindBy(xpath = "//button[.='Save changes']")
    public WebElement saveButton;

    @FindBy(xpath = "//button[@class='btn default']")
    public WebElement closeButton;

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
