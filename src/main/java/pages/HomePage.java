package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage extends BasePage{
    public HomePage (WebDriver driver){
        setDriver(driver);
        driver.get("https://telranedu.web.app/home");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10),this);
    }

    @FindBy(xpath = "//a[text()='LOGIN']")
    WebElement btnLogin;
    @FindBy(xpath = "//a[text()='ADD']")
    WebElement btnADD;

   // WebElement btnLogin1 = driver.findElement(By.xpath("//a[text()='LOGIN']"));

    public void clickBtnLoginHeader(){
        btnLogin.click();
        pause(3);
    }
    public void clickBtnAdd(){
        btnADD.click();
        pause(2);

    }
}
