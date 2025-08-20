package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Before method produce logout");
        }
    }

    @Test
    public void registrationSuccess() {
        logger.info("Test data ---> name: 'Lisa' Last name:'Snow' email: 'RANDOM' & password 'Ssnow123456$'");
        Random random = new Random();
        int i = random.nextInt(1000);
        System.out.println(i);

        System.out.println(System.currentTimeMillis());

        int z = (int) ((System.currentTimeMillis() / 1000) % 3600);
        User user = new User()
                .withFirstName("Lisa")
                .withLastName("Snow")
                .setEmail("snow" + z + "@gmail.com")
                .setPassword("Ssnow123456$");


        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");
        logger.info("Assert checked that user get message 'You are logged in success'");
    }


    @Test
    public void registrationEmptyName() {
        logger.info("Test data ---> name: 'EMPTY' Last name:'Snow' email: 'now@gmail.com' & password 'Ssnow123456$'");
        User user = new User()
                .withFirstName("")
                .withLastName("Snow")
                .setEmail("snow@gmail.com")
                .setPassword("Ssnow123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "Name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert checked that user get message 'Name is required' & button 'Y'alla' is not active'");
    }

    @Test
    public void registrationEmptyLastName() {
        logger.info("Test data ---> name: 'Lisa' Last name:'EMPTY' email: 'snow@gmail.com' & password 'Ssnow123456$'");
        User user = new User()
                .withFirstName("Lisa")
                .withLastName("")
                .setEmail("snow@gmail.com")
                .setPassword("Ssnow123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "Last name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert checked that user get message 'Last name is required' & button 'Y'alla' is not active'");
    }

    @Test
    public void registrationWrongEmail() {
        logger.info("Test data ---> name: 'Lisa' Last name:'Snow' email: 'snowgmail.com' & password 'Ssnow123456$'");
        User user = new User()
                .withFirstName("Lisa")
                .withLastName("Snow")
                .setEmail("snowgmail.com")
                .setPassword("Ssnow123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().getErrorText().contains("Wrong email format"));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert checked that user get message 'Wrong email format' & button 'Y'alla' is not active'");
    }

    @Test
    public void registrationEmptyEmail() {
        logger.info("Test data ---> name: 'Lisa' Last name:'Snow' email: 'EMPTY' & password 'Ssnow123456$'");
        User user = new User()
                .withFirstName("Lisa")
                .withLastName("Snow")
                .setEmail("")
                .setPassword("Ssnow123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "Email is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert checked that user get message 'Email is required' & button 'Y'alla' is not active'");
    }

    @Test
    public void registrationWrongPassword() {
        logger.info("Test data ---> name: 'Lisa' Last name:'Snow' email: 'snow@gmail.com' & password 'Ssnow12'");
        User user = new User()
                .withFirstName("Lisa")
                .withLastName("Snow")
                .setEmail("snow@gmail.com")
                .setPassword("Ssnow12");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "Password must contain minimum 8 symbols\n" +
                "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert checked that user get message 'Password must contain minimum 8 symbols\\n\" +\n" +
                "\"Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]' " +
                "& button 'Y'alla' is not active'");
    }

    @Test
    public void registrationEmptyPassword() {
        logger.info("Test data ---> name: 'Lisa' Last name:'Snow' email: 'snow@gmail.com' & password 'EMPTY'");
        User user = new User()
                .withFirstName("Lisa")
                .withLastName("Snow")
                .setEmail("snow@gmail.com")
                .setPassword("");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "Password is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert checked that user get message 'Password is required' & button 'Y'alla' is not active'");
    }

    @Test
    public void registrationPolicyBtnNotChecked() {
        logger.info("Test data ---> name: 'Lisa' Last name:'Snow' email: 'RANDOM' & password 'Ssnow123456$'");
        Random random = new Random();
        int i = random.nextInt(1000);
        System.out.println(i);

        System.out.println(System.currentTimeMillis());

        int z = (int) ((System.currentTimeMillis() / 1000) % 3600);
        User user = new User()
                .withFirstName("Lisa")
                .withLastName("Snow")
                .setEmail("snow" + z + "@gmail.com")
                .setPassword("Ssnow123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert checked that button 'Y'alla' is not active'");
    }


    @AfterMethod
    public void postCondition() {
        app.getHelperUser().clickOkButton();
        app.getHelperUser().checkPolicy();
        logger.info("After test check if Policy check Box is marked & make click on button 'OK' ");
    }
}
