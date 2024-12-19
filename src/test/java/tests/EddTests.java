package tests;

import data_provider.DPContact;
import dto.UserDto;
import dto.UserDtoLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import static utils.RandomUtils.*;

public class EddTests extends ApplicationManager {
    UserDto user = new UserDto("myphone@gmail.com","Nnoa12345$");

    SoftAssert softAssert = new SoftAssert();


    ContactsPage contactsPage;

    @BeforeMethod
    public void login(){

        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(user);
        contactsPage = new ContactsPage(getDriver());
    }
    @Test
    public void eddContactsPositiveTest(){
        UserDtoLombok contact = UserDtoLombok.builder()
                .name(generateString(5))
                .lastName(generateString(10))
                .email(generateEmail(7))
                .Phone(generatePhone(12))
                .Address("Address "+ generateString(10))
                .build();
        contactsPage.editContact(contact);
        Assert.assertTrue(contactsPage.validateCardContact(contact));

//        new ContactsPage(getDriver()).clickContacts();
//        new ContactsPage(getDriver()).clickEdit();
//        new ContactsPage(getDriver()).clearName();
//        new ContactsPage(getDriver()).typeNewName();
//        new ContactsPage(getDriver()).clickBtnSave();



    }
    @Test(dataProvider = "newContactDP", dataProviderClass = DPContact.class)
    public void eddContactsTestDP(UserDtoLombok contact) {
        contactsPage.editContact(contact);
        Assert.assertTrue(contactsPage.validateCardContact(contact));
    }
    @Test(dataProvider = "newConatactDPFFile", dataProviderClass = DPContact.class)
    public void eddContactsTestDPfromFile(UserDtoLombok contact) {
        contactsPage.editContact(contact);
        Assert.assertTrue(contactsPage.validateCardContact(contact));
    }
}
//newConatactDPFFile