package tests;

import manager.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class RegistrationTests extends ApplicationManager {
    @Test
    public void registrationPositiveTest(){
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm("my_mail","Gera1111!");
        WebElement btnRegistration = getDriver().findElement(By.xpath("//button[@name='registration']"));
        btnRegistration.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        getDriver().quit();



    }
}
