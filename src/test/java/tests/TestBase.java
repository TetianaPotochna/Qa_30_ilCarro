package tests;

import manager.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
    static ApplicationManager app = new ApplicationManager();

    @BeforeSuite
    public void setUp() {
        app.unit();
    }

    @AfterSuite
    public void setDown() {
        app.stop();
    }
}
