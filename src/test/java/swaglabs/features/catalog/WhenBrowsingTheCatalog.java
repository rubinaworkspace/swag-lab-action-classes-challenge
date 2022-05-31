package swaglabs.features.catalog;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import swaglabs.actions.CatalogActions;
import swaglabs.actions.LoginActions;
import swaglabs.domain.ProductInfo;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@ExtendWith(SerenityJUnit5Extension.class)
public class WhenBrowsingTheCatalog {
    @Steps
    LoginActions login;

    @Steps
    CatalogActions catalog;


    @BeforeEach
    void login()
    {
        login.asAStandardUser();
    }

    @Test
    public void shouldSeeAllInventoryProducts(){


        assertThat(catalog.productTitles()).contains(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie",
                "Test.allTheThings() T-Shirt (Red)"
        );

    }

    @Test
    public void eachProductShouldHaveADescriptionAndPrice(){

        assertThat(catalog.products()).allMatch(
                item-> !item.description().isEmpty() &&
                        item.price() > 0 &&
                        !item.description().isEmpty()

        );
    }

    @Test
    void eachProductShouldHaveADifferentImage(){

        List<String> productImages= catalog.products().stream().map(ProductInfo::image).toList();
        assertThat(productImages).doesNotHaveDuplicates();
    }

}
