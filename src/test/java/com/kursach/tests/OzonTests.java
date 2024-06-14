package com.kursach.tests;

import com.kursach.tests.base.BaseTests4Lab4;
import org.junit.jupiter.api.Test;

public class OzonTests extends BaseTests4Lab4 {

    @Test
    public void testCase(){
        pageManager.getOzonHomePage()
                .inputDataField("ноутбук")
                .searchProduct()
                .checkPage();
    }

    @Test
    public void testCase2(){
        pageManager.getOzonHomePage()
                .inputDataField("ноутбук")
                .searchProduct()
                .selectBrand()
                .priceFrom("50000")
                .priceBfore("150000");

    }

    @Test
    public void testCase3(){
        pageManager.getOzonHomePage()
                .inputDataField("ноутбук")
                .searchProduct()
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
