package swaglabs.actions;

import net.serenitybdd.core.steps.UIInteractions;
import org.openqa.selenium.By;
import swaglabs.domain.ProductInfo;

import java.util.List;

public class CatalogActions extends UIInteractions {

    public List<String> productTitles() {

        return findAll(".inventory_item_name").texts();

    }

    public List <ProductInfo> products(){
        return findAll(".inventory_item_description").
                map(item->{
                    String title= item.findBy(".inventory_item_name").getText();
                    String desc= item.findBy(".inventory_item_desc").getText();
                    double price= Double.parseDouble(item
                                        .findBy(".inventory_item_price").getText().replace("$",""));
                    String img= item.findBy(By.cssSelector("img.inventory_item_img")).getAttribute("src");
                    return new ProductInfo(title, desc, price,img);
                });

    }
}
