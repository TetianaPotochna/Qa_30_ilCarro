package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    public void fillLoginForm(User user) {
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void submit() {

        click(By.xpath("//button[@type='submit' or text()='Y’alla!']"));

//        click(By.xpath("//*[contains(@class, 'positive-button') and contains(@class, 'ng-star-inserted')]"));

//        By submitButton = By.xpath("//button[@type='submit' or text()='Y’alla!']");
//        WebElement button = wd.findElement(submitButton);
//
//        if (button.isEnabled()) {
//            button.click();
//        By positiveButton = By.xpath("//*[contains(@class, 'positive-button') and contains(@class, 'ng-star-inserted')]");
//        wd.findElement(positiveButton).click();
//    }

    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//*[@href='/logout?url=%2Fsearch']"));
    }

    public void logout() {
        click(By.xpath("//*[@href='/logout?url=%2Fsearch']"));
    }

    public String getMessage() {
        //pause(2000);
        return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();

//        WebElement element = wd.findElement(By.cssSelector("div.dialog-container>h2"));
//        String text = element.getText();
//        return text;
    }


    public void openRegistrationForm() {
        click(By.xpath("//*[text()=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getFirstName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void checkPolicy() {
        //click(By.id("terms-of-use"));
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#terms-of-use').click()");
    }

    public boolean buttonSubmitDisabled() {
        WebElement button = wd.findElement(By.xpath("//button[@type='submit' or text()='Y’alla!']"));

        if (!button.isEnabled()) {
            WebElement incorrectInput = wd.findElement(By.xpath("//div[@class='error']/div"));
            String messageText = incorrectInput.getText();
        }
        return true;

    }

}
