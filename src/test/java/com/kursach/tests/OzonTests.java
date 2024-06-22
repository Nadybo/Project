package com.kursach.tests;

import com.kursach.tests.base.BaseTests4Lab4;
import com.kursach.utils.AllureListener;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

public class OzonTests extends BaseTests4Lab4 {

    @Test
    @DisplayName("Проверка поиск продукта")
    @ExtendWith(AllureListener.class)
    public void testCase(){
        pageManager.getOzonHomePage()
                .inputDataField("ноутбук")
                .searchProduct()
                .checkPage();
    }

    @Test
    @DisplayName("Проверка сортировка продукта")
    @ExtendWith(AllureListener.class)
    public void testCase2(){
        pageManager.getOzonHomePage()
                .inputDataField("ноутбук")
                .searchProduct()
                .selectBrand()
                .priceFrom("50000")
                .priceBfore("150000")
                .checkingPriceFiled("50 000");

    }

    @Test
    @DisplayName("Проверка добавление товара в корзину и список избранные и удаления")
    @ExtendWith(AllureListener.class)
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
