package tests;

import dto.UserDto;
import dto.UserDtoLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddPage;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.Random;

public class AddContactTests extends ApplicationManager {
    SoftAssert softAssert = new SoftAssert();
    UserDto user = new UserDto("myphone@gmail.com", "Nnoa12345$");
    AddPage addPage;

    @BeforeMethod
    public void login() {
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(user);
        new ContactsPage(getDriver()).clickBtnAdd();
        addPage = new AddPage(getDriver());

    }


    @Test(invocationCount = 3)
    public void addNewContactPositiveTest() {
        int i = new Random().nextInt(1000);
        UserDtoLombok contact = UserDtoLombok.builder()
                .name(i+"Ivan")
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
    public void addNewContactNegativeTestWrongEmail() {
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
    public void addNewContactNegativeTestWrongPhone() {
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

    @Test
    public void addNewContactNegativeTestWrongName() {
        UserDtoLombok contact = UserDtoLombok.builder()
                .name("")
                .lastName("Ivanov")
                .Phone("552233888888888")
                .email("ivan@ua.com")
                .Address("Ramle")
                .description("good student")
                .build();
        addPage.typeAddForm(contact);
        Assert.assertFalse(addPage.validateUrlContacts());
    }
    @Test
    public void addNewContactNegativeTestWrongPhone1() {
        UserDtoLombok contact = UserDtoLombok.builder()
                .name("name123")
                .lastName("Ivanov123")
                .Phone("1234567890a")
                .email("ivan@ua.com")
                .Address("Ramle")
                .description("good student")
                .build();
        addPage.typeAddForm(contact);
        String message = addPage.closeAlertAndReturnText();

        softAssert.assertTrue(message.contains("Phone number must contain only digits! And length min 10, max 15!"));

        System.out.println("code after");

        softAssert.assertFalse(addPage.validateUrlContacts());

        softAssert.assertAll();
    }
}