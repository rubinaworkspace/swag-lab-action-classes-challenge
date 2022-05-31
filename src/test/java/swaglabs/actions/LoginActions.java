package swaglabs.actions;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;

public class LoginActions extends UIInteractions {
    @Step("Login the Swag Lab using {0}")
    public void withCredentials(String username, String password) {
        openUrl("https://www.saucedemo.com/");
        $("#user-name").sendKeys(username);
        $("#password").sendKeys(password);
        $("#login-button").click();


    }

    public String errorMessage(){
        return $("[data-test=error]").getText();
    }

    public void asAStandardUser() {

        withCredentials("standard_user","secret_sauce");
    }
}
