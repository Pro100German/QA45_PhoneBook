package tests;

import dto.UserDto;
import dto.UserDtoLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddPage;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;

public class AddContactTests extends ApplicationManager {
    UserDto user = new UserDto("myphone@gmail.com","Nnoa12345$");
    AddPage addPage;

    @BeforeMethod
    public void login(){
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(user);
        new ContactsPage(getDriver()).clickBtnAdd();
        addPage = new AddPage(getDriver());

    }
    @Test
    public void addNewContactPositiveTest(){
        UserDtoLombok contact = UserDtoLombok.builder()
                .name("Ivan")
                .lastName("Ivanov")
                .Phone("552233888888888")
                .email("ivan@ua.com")
                .Address("Ramle")
                .description("good student")
                .build();
        addPage.typeAddForm(contact);
        Assert.assertTrue(new ContactsPage(getDriver())
                .validateLastElementContactList(contact));

    }

    @Test
    public void addNewContactNegativeTestWrongEmail(){
        UserDtoLombok contact = UserDtoLombok.builder()
                .name("Ivan")
                .lastName("Ivanov")
                .Phone("552233888888888")
                .email("ivan@ua..com")
                .Address("Ramle")
                .description("good student")
                .build();
        addPage.typeAddForm(contact);
        new LoginPage(getDriver()).closeAlert();
        Assert.assertTrue(new AddPage(getDriver()).validateErrorMessageWrongEmail("должно иметь формат адреса электронной почты"));
    }
    @Test
    public void addNewContactNegativeTestWrongPhone(){
        UserDtoLombok contact = UserDtoLombok.builder()
                .name("Ivan")
                .lastName("Ivanov")
                .Phone("")
                .email("ivan@ua.com")
                .Address("Ramle")
                .description("good student")
                .build();
        addPage.typeAddForm(contact);
        new LoginPage(getDriver()).closeAlert();
        Assert.assertTrue(new AddPage(getDriver()).validateErrorMessageWrongPhone("Phone number must contain only digits! And length min 10, max 15!"));

    }
}
