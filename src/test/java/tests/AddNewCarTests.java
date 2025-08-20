package tests;

import models.Car;
import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddNewCarTests extends TestBase{

    @BeforeClass
    public void preCondition(){
        if (!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().setEmail("dusm558@gmail.com").setPassword("Dusm12345@"));
            logger.info("If user not login, make login successful");
        }
    }

    @Test
    public void addNewCarSuccess(){
        logger.info("Test data ---> location:'Haifa, Israel' manufacture:'Mersedes' model:'SLC200' year:'2022' fuel:'Petrol'" +
                " seats:'4' carClass:'A' price:'50'");
        int i = (int)((System.currentTimeMillis()/1000)%3600);
        Car car = Car.builder()
                .location("Haifa, Israel")
                .manufacture("Mersedes")
                .model("SLC200")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("A")
                .carRegNumber("698-900-" + i)
                .price(50)
                .build();

        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().attachPhoto("D:\\QA_30\\Qa_30_ilCarro\\ba0b4f6d356b00ea51bd487b8627d3ea.jpg");
        app.getHelperCar().submitCarForm();
        
    }




}
