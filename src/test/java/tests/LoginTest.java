package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Before method produce logout");
        }
    }

    @Test
    public void loginSuccess1() {
        logger.info("Test data ---> email: 'dusm558@gmail.com' & password 'Dusm12345@'");
        User user = new User().setEmail("dusm558@gmail.com").setPassword("Dusm12345@");
//        user.setEmail("dusm558@gmail.com");
//        user.setPassword("Dusm12345@");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");

        logger.info("Assert checked that after login take a message 'Logged in success'");


    }

    @Test
    public void loginSuccess() {
        logger.info("Test data ---> email: 'dusm558@gmail.com' & password 'Dusm12345@'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("dusm558@gmail.com", "Dusm12345@");
        app.getHelperUser().submit();

        //Assert if element with text "Logged in success" is present
        //Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");

        Assert.assertTrue(app.getHelperUser().isLogged());
        //   app.getHelperUser().clickOkButton();

        logger.info("Assert checked is Element 'Logout' present");
    }

    @Test
    public void loginSuccessModel() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("dusm5@gmail.com", "Dusm12345@");
        app.getHelperUser().submit();

        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");

        //     Assert.assertTrue(app.getHelperUser().isLogged());
        // app.getHelperUser().clickOkButton();
    }

    //NEGATIVE TESTS
    @Test
    public void loginWrongEmail() {
        logger.info("Test data ---> email: 'dusm558gmail.com' & password 'Dusm12345@'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("dusm558gmail.com", "Dusm12345@");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert checked that button 'Y'alla' is not active");
    }

    @Test
    public void loginEmptyEmail() {
        logger.info("Test data ---> email: 'EMPTY' & password 'Dusm12345@'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("", "Dusm12345@");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Email is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert checked that button 'Y'alla' is not active");
    }


    @Test
    public void loginWrongPassword() {
        logger.info("Test data ---> email: 'dusm558@gmail.com' & password 'Dusm12347@'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("dusm558@gmail.com", "Dusm12347@");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        logger.info("Assert checked that user get message 'Login or Password incorrect'");
    }

    @Test
    public void loginEmptyPassword() {
        logger.info("Test data ---> email: 'dusm558@gmail.com' & password 'EMPTY'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("dusm558@gmail.com", "");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Password is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert checked that button 'Y'alla' is not active");
    }

    @Test
    public void loginUnregisteredUser() {
        logger.info("Test data ---> email: 'dusm999@gmail.com' UNREGISTERED USER & password 'Dusm12345@'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("dusm999@gmail.com", "Dusm12345@");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        logger.info("Assert checked that user get message 'Login or Password incorrect'");
    }


    @AfterMethod
    public void postCondition() {
        app.getHelperUser().clickOkButton();
        logger.info("In the end of test click on button 'OK' in a pop-up window");
    }

}