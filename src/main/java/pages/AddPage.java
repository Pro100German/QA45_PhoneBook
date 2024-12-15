package pages;

import dto.UserDtoLombok;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddPage extends BasePage{
    public AddPage(WebDriver driver){
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }
    @FindBy(xpath = "//input[@placeholder='Name']")
    WebElement inputName;
    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement inputLastName;
    @FindBy(xpath = "//input[@placeholder='Phone']")
    WebElement inputPhone;
    @FindBy(xpath = "//input[@placeholder='email']")
    WebElement inputEmail;
    @FindBy(xpath = "//input[@placeholder='Address']")
    WebElement inputAddress;
    @FindBy(xpath = "//input[@placeholder='description']")
    WebElement inputDescription;
    @FindBy(xpath = "//b/..")
    WebElement btnSave;
    @FindBy(xpath = "//button[text()='Edit']")
    WebElement btnEdit;
    @FindBy(xpath = "//input[@placeholder='Name']")
    WebElement inputEditName;




    public void typeAddForm(UserDtoLombok contact){
        inputName.sendKeys(contact.getName());
        inputLastName.sendKeys(contact.getLastName());
        inputPhone.sendKeys(contact.getPhone());
        inputEmail.sendKeys(contact.getEmail());
        inputAddress.sendKeys(contact.getAddress());
        inputDescription.sendKeys(contact.getDescription());
        btnSave.click();
    }
    public String closeAlertAndReturnText() {
        Alert alert = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }

    public boolean validateErrorMessageWrongEmail(String text) {
        return text.contains("должно иметь формат адреса электронной почты");
    }
    public boolean validateErrorMessageWrongPhone(String text){
        return text.contains("Phone number must contain only digits! And length min 10, max 15!");
    }
    public boolean validateUrlContacts(){
        return validateUrl("contacts",5);
    }
    public void clickEdit(){
        btnEdit.click();
    }
    public void typeEditForm(UserDtoLombok contact){
        inputEditName.sendKeys(contact.getName());

    }

}
