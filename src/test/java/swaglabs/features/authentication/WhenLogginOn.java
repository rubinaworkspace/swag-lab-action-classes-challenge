package swaglabs.features.authentication;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import swaglabs.actions.LoginActions;
import swaglabs.actions.common.PageHeader;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenLogginOn {

    @Steps
    LoginActions login;
    PageHeader pageHeader;

    @Test
    public void withValidCredentials(){
        login.withCredentials("standard_user","secret_sauce");
        assertThat(pageHeader.title()).isEqualToIgnoringCase("Products");
    }

    @Test
    public void withInvalidCredentials(){
        login.withCredentials("standard_user","wrong_password");
        assertThat(login.errorMessage()).contains("Username and password do not match");
    }

    @ParameterizedTest
    @CsvSource(delimiterString = "|", value = {
            "standard_user  | wrong_password    | Username and password do not match",
            "locked_out_user| secret_sauce      | Sorry, this user has been locked",
            "standard_user  | ''                |Password is required",
            "''             |secret_sauce       |Epic sadface: Username is required"
    })
    public  void withInvalidCredentialsParameterized(String username, String password, String errorMsg){
        login.withCredentials(username, password);
        assertThat(login.errorMessage()).contains(errorMsg);
    }
}
