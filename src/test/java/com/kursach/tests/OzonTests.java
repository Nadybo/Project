package com.kursach.tests;

import com.kursach.tests.base.BaseTests4Lab4;
import org.junit.jupiter.api.Test;

public class OzonTests extends BaseTests4Lab4 {

    @Test
    public void tests(){
        pageManager.getOzonHomePage()
                .inputDataField("ноутбук")
                .searchProduct()
                .checkPage()
                .selectProduct()
                .favoritProducts()
                .motionProductPage()
                .cookies()
                .motionBasketPage()
                .basketPage()
                .clickSecondButton()
                .deleteInBasket();
    }
}
