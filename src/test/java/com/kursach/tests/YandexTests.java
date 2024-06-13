package com.kursach.tests;

import com.kursach.tests.base.BaseTests4Lab3;
import org.junit.jupiter.api.Test;

public class YandexTests extends BaseTests4Lab3{
    @Test
    public void Tests(){
        pageManager.getYandexPage()
                .openCatalog()
                .slectCategory()
                .TablesPage()
                .filterBySamsung()
                .SortToCheap()
                .logFirstFiveProducts()
                .searchSecondProduct();

    }
}
