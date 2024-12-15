package tests;

import dto.UserDto;
import dto.UserDtoLombok;
import manager.ApplicationManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class EddTests extends ApplicationManager {
    UserDto user = new UserDto("myphone@gmail.com","Nnoa12345$");


    ContactsPage contactsPage;

    @BeforeMethod
    public void login(){
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(user);
        contactsPage = new ContactsPage(getDriver());
    }
    @Test
    public void eddContactsPositiveTest(){

        new ContactsPage(getDriver()).clickContacts();
        new ContactsPage(getDriver()).clickEdit();
        new ContactsPage(getDriver()).clearName();
        new ContactsPage(getDriver()).typeNewName();
        new ContactsPage(getDriver()).clickBtnSave();


    }

}
