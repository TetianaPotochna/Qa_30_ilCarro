package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        click(By.xpath("//a[text()=' Log in ']"));
    }

    public void fillLoginForm(String email, String password) {
        type(By.id("email"), email);
        type(By.id("password"), password);
    }

    public void submitLogin() {

//        click(By.xpath("//button[@type='submit' or text()='Y’alla!']"));
//        click(By.xpath("//*[contains(@class, 'positive-button') and contains(@class, 'ng-star-inserted')]"));

        By submitButton = By.xpath("//button[@type='submit' or text()='Y’alla!']");
        WebElement button = wd.findElement(submitButton);

        if (button.isEnabled()) {
            button.click();
        By positiveButton = By.xpath("//*[contains(@class, 'positive-button') and contains(@class, 'ng-star-inserted')]");
        wd.findElement(positiveButton).click();
    }

}
    public boolean isLogged() {
        return isElementPresent(By.xpath("//*[@href='/logout?url=%2Fsearch']"));
    }
    public void logout() {
        click(By.xpath("//*[@href='/logout?url=%2Fsearch']"));
    }

}
