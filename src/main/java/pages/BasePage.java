package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {
    static WebDriver driver;

    public static void setDriver(WebDriver wd){
        driver=wd;
    }

    public void pause(int time){
        try {
            Thread.sleep(time*3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
