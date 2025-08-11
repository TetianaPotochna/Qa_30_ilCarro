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
        }
    }

    @Test
    public void loginSuccess1() {
        User user = new User().setEmail("dusm558@gmail.com").setPassword("Dusm12345@");
//        user.setEmail("dusm558@gmail.com");
//        user.setPassword("Dusm12345@");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
    }

    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("dusm558@gmail.com", "Dusm12345@");
        app.getHelperUser().submit();

        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");

        //     Assert.assertTrue(app.getHelperUser().isLogged());
        //   app.getHelperUser().clickOkButton();
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
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("dusm558gmail.com", "Dusm12345@");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void loginEmptyEmail() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("", "Dusm12345@");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Email is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }


    @Test
    public void loginWrongPassword() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("dusm558@gmail.com", "Dusm12347@");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
    }

    @Test
    public void loginEmptyPassword() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("dusm558@gmail.com", "");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Password is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void loginUnregisteredUser() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("dusm999@gmail.com", "Dusm12345@");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
    }


    @AfterMethod
    public void postCondition() {
        app.getHelperUser().clickOkButton();
    }

}