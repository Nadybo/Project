package com.kursach.tests;

import com.kursach.tests.base.BaseTests4Lab3;
import com.kursach.utils.AllureListener;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

public class YandexTests extends BaseTests4Lab3{
    @Test
    @DisplayName("Проверка yandex")
    @ExtendWith(AllureListener.class)
    public void Tests(){
        pageManager.getYandexPage()
                .openCatalog()
                .slectCategory()
                .TablesPage()
                .checkPageTitele("Планшеты")
                .filterBySamsung()
                .SortToCheap()
                .logFirstFiveProducts()
                .searchSecondProduct()
                .CheckingSearchResults();

    }
}
