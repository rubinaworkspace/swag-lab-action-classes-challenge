package swaglabs.features.authentication;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import swaglabs.actions.LoginActions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenLogginOn {

    @Steps
    LoginActions login;

    @Test
    public void withValidCredentials(){
        login.withCredentials("standard_user","secret_sauce");
        //assertThat(inventorList.itemsNames()).isNotEmpty();


    }

}
