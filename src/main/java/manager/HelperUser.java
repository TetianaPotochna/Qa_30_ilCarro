package manager;

import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

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

    public String getErrorText() {
        return wd.findElement(By.cssSelector("div.error")).getText();
    }

    public void checkPolicyXY() {
        if (!wd.findElement(By.id("terms-of-use")).isSelected()) {
            WebElement label = wd.findElement(By.cssSelector("label[for='terms-of-use']"));

            Rectangle rect = label.getRect();
            int w = rect.getWidth();

            //Dimension size = wd.manage().window().getSize();

            int xOffSet = -w / 2;
            Actions actions = new Actions(wd);
            actions.moveToElement(label, xOffSet, 0).click().release().perform();
        }
    }

    public void login(User user) {
        openLoginForm();
        fillLoginForm(user);
        submit();
        clickOkButton();
    }
}
