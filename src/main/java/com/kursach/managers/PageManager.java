package com.kursach.managers;
import com.kursach.pages.yandex.YandexHomePage;
import com.kursach.pages.lambdatest.mainPage;
import com.kursach.pages.mospolytech.schedulePage;
import com.kursach.pages.mospolytech.mospolytechHomePage;
import com.kursach.pages.yandex.catalogPlanshety;
import com.kursach.pages.ozon.OzonHomePage;
import com.kursach.pages.ozon.OzonProductPage;
import com.kursach.pages.ozon.OzonProductSearch;


public class PageManager {

    private static PageManager INTANCE = null;

    private mainPage mainpage;
    private schedulePage schedulepage;
    private mospolytechHomePage homePage;
    private YandexHomePage yandexHomePage;
    private catalogPlanshety cataloPlanshety;
    private OzonHomePage ozonHomePage;
    private OzonProductPage ozonProductPage;
    private OzonProductSearch ozonProductSearch;

    private PageManager(){

    }
    public static PageManager getInstance(){
        if (INTANCE == null){
            INTANCE = new PageManager();
        }
        return INTANCE;
    }

    public mainPage getMainPage(){
        if (mainpage == null){
            mainpage = new mainPage();
        }
        return mainpage;
    }
    public schedulePage getSchedulepage(){
        if (schedulepage == null){
            schedulepage = new schedulePage();
        }
        return schedulepage;
    }
    public mospolytechHomePage getHomePage(){
        if (homePage == null){
            homePage = new mospolytechHomePage();
        }
        return homePage;
    }
    public YandexHomePage getYandexPage(){
        if (yandexHomePage == null){
            yandexHomePage = new YandexHomePage();
        }
        return yandexHomePage;
    }
    public catalogPlanshety getCataloPlanshety(){
        if (cataloPlanshety == null){
            cataloPlanshety = new catalogPlanshety();
        }
        return cataloPlanshety;
    }
    public OzonHomePage getOzonHomePage(){
        if (ozonHomePage == null){
            ozonHomePage = new OzonHomePage();
        }
        return ozonHomePage;
    }

    public OzonProductPage getOzonProductPage(){
        if (ozonProductPage == null){
            ozonProductPage = new OzonProductPage();
        }
        return ozonProductPage;
    }

    public OzonProductSearch getOzonProductSearch(){
        if (ozonProductSearch == null){
            ozonProductSearch = new OzonProductSearch();
        }
        return ozonProductSearch;
    }
}
