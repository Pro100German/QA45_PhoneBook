package tests;

import dto.UserDto;
import dto.UserDtoLombok;
import manager.ApplicationManager;
import org.testng.annotations.Test;
import pages.AddPage;
import pages.HomePage;
import pages.LoginPage;

public class AddTests extends ApplicationManager {
    AddPage addPage;

    @Test
    public void addPositiveTest(){
        UserDtoLombok user = UserDtoLombok.builder()
                .name("Ivan")
                .lastName("Ivanov")
                .Phone("552233")
                .email("ivan@ua.com")
                .Address("Ramle")
                .description("good student")
                .build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm();
        addPage = new AddPage(getDriver());
        addPage.typeAddForm(user);

    }
}
