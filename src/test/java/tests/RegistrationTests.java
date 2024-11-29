package tests;

import manager.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.Random;

import manager.ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.Random;

public class RegistrationTests extends ApplicationManager {

    @Test
    public void registrationPositiveTest(){
        int i = new Random().nextInt(1000);
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeRegistrationForm("frodo_baggins_"+i+"@gmail.com",
                "Password123!");
    }


}