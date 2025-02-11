package testFunctionalities;

import core.BaseTest;
import org.testng.annotations.Test;

public class UserRegistrationTest extends BaseTest {

    @Test
    public void userRegistrationValidTest() {
        UserRegistration.openMoneyGaming();
        UserRegistration.registration("Mrs", "Mihaela", "test");
        UserRegistration.verifyTheFieldIsRequired("This field is required", "Date of birth error not displayed");
    }
}