package tests;

import dto.UserDto;
import manager.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.TestNGListener;

import java.util.Random;
@Listeners(TestNGListener.class)

public class LoginTests extends ApplicationManager {

    private String email, password;

    @BeforeMethod
    public void registration(){
        int i = new Random().nextInt(1000);
        email = "frodo_baggins_"+i+"@gmail.com";
        password = "Password123!";
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeRegistrationForm(email, password);
        new ContactsPage(getDriver()).clickBtnSignOut();
    }

    @Test
    public void loginPositiveTest(){

        UserDto userLogin  = new UserDto("myphone@gmail.com", "Nnoa12345$");
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(userLogin);
        Assert.assertTrue(new ContactsPage(getDriver()).isSignOutPresent());
    }
    @Test
    public void loginNegativeTestEmptyEmail(){
        UserDto userLogin  = new UserDto("", password);
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(userLogin);
        new LoginPage(getDriver()).closeAlert();
        Assert.assertTrue(new LoginPage(getDriver()).validateErrorMessageLogin("Login Failed with code 401"));
    }
}