package com.cybertek.library.pages;

import com.cybertek.library.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends PageBase{
    public DashboardPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "user_count")
    public WebElement usersCount;

    @FindBy(id = "book_count")
    public WebElement booksCount;

    @FindBy(id = "borrowed_books")
    public WebElement borrowedBooksCount;

    @FindBy(tagName = "h6")
    public WebElement usersText;

    @FindBy(xpath = "(//h6[@class='text-muted'])[2]")
    public WebElement booksText;

    @FindBy(xpath = "(//h6[@class='text-muted'])[3]")
    public WebElement borrowedBooksText;

}
