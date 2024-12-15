package tests;

import dto.UserDto;
import lombok.Getter;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;

public class DeleteContactTests extends ApplicationManager {
    UserDto user = new UserDto("myphone@gmail.com","Nnoa12345$");

    ContactsPage contactsPage;

    @BeforeMethod
    public void login(){
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(user);
        contactsPage = new ContactsPage(getDriver());
    }
    @Test
    public void deleteContactPositiveTest(){
        int quantityBeforeDelete = contactsPage.quantityContacts();
        System.out.println("quantityBeforeDelete-> " + quantityBeforeDelete);
        contactsPage.deleteFirstContact();
        int quantityAfterDelete = contactsPage.quantityContacts();
        System.out.println("quantityAfterBeforeDelete-> " + quantityAfterDelete);
        Assert.assertEquals(quantityBeforeDelete-1, quantityAfterDelete);

    }



}
