package com.choucair.formacion.pageobjects;

import com.choucair.formacion.utilidades.MetodosComunes;
import freemarker.cache.StrongCacheStorage;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.security.Key;

@DefaultUrl("https://www.elcolombiano.com")

public class BuscarNoticiasPage extends PageObject {

    @FindBy ( xpath = "//strong[contains(text(),'Antioquia')]")
    WebElementFacade ubicacion;

    @FindBy (xpath="//span[contains(text(),'Movilidad')]")
            WebElementFacade movilidad;

    @FindBy(xpath="//span[@class='icon-font-plus']")
    WebElementFacade aumentoFuente;

    @FindBy(xpath = "//input[@id='_1844294147_keywords']")
    WebElementFacade buscarNoticia;

    @FindBy(xpath = "//input[@id='_30547279_keywords']")
    WebElementFacade segundoBuscador;

    @FindBy(xpath = "//select[@id='_30547279_startYear']")
    WebElementFacade yearInicial;

    @FindBy(xpath = "//button[@id='buttonStartDate']")
    WebElementFacade calendarioInicial;

    @FindBy(xpath = "//select[@id='_30547279_endYear']")
    WebElementFacade yearFinal;

    @FindBy(xpath = "//button[@id='buttonEndDate']")
    WebElementFacade calendarioFinal;

    @FindBy(xpath = "//li[@id='_20191887_pagtool3']")
    WebElementFacade btnNumeroPagina;



    public void navegarMenu(){
        Actions act = new Actions(getDriver());
        act.moveToElement(ubicacion).perform();
        movilidad.waitUntilClickable().click();


    }

    public void seleccionarNoticia(String numerocaso){

        JavascriptExecutor jse = (JavascriptExecutor) getDriver();

        $("//article["+numerocaso+"]//div[1]//div[1]//div[1]//a[1]//div[1]//img[1]").click();


        jse.executeScript("window.scrollBy(0, 300)");

        aumentoFuente.click();

        String sizeLetra = $("//div[@class='detalle-general detalle-texto']//div[1]//p[1]").getCssValue("font-size");

        String fuenteMax = "20px";

        Serenity.takeScreenshot(); //Evidencias
        if (sizeLetra != fuenteMax){

            aumentoFuente.click();
        }
    }

    public void BusquedaNoticia(String noticiaParticular){
        buscarNoticia.sendKeys(noticiaParticular);
        buscarNoticia.sendKeys(Keys.ENTER);
        segundoBuscador.sendKeys(noticiaParticular);
    }

    public void fechaInicial(String diaInicial, String mesInicial){

        Actions act = new Actions(getDriver());
        act.moveToElement(yearInicial).click().perform();
        yearInicial.sendKeys("1998",Keys.ENTER);
        act.moveToElement(calendarioInicial).click().perform();


        while (true) {

            String mesA = $("//div[@id='startdate-pick']//span[@class='ui-datepicker-month']").getText();

            if (mesA.compareTo(mesInicial) == 0){

                break;

            }

            else {
                    $("//*[@id='startdate-pick']/div/div/a[1]").click();
            }
        }

        act.moveToElement($("//div[@id='startdate-pick']//a[@class='ui-state-default'][contains(text(),"+ diaInicial+")]")).click().perform();

    }

    public void fechaFinal(String diafinal, String mesFinal) {

        Actions act = new Actions(getDriver());
        act.moveToElement(yearFinal).click().perform();
        yearFinal.sendKeys("2010", Keys.ENTER);
        act.moveToElement(calendarioFinal).click().perform();


        while (true) {

            String mesB = $("//div[@id='enddate-pick']//span[@class='ui-datepicker-month']").getText();

            if (mesB.compareTo(mesFinal) == 0){

                break;

            }

            else {

                $("//div[@id='enddate-pick']/div/div/a[1]").click();

            }

        }

       act.moveToElement($("//div[@id='enddate-pick']//a[@class='ui-state-default'][contains(text(),"+diafinal+")]")).click().perform();
    }

    public void paginadorNoticia(String numeroPagina, String numeroNoticia) {

        $("//li[@id='_20191887_pagtool3'][contains(text(),"+numeroPagina+")]").click();
        //for ( int i = 1; i <= 5; i++){

      //      if(i == 5){
                $("//li["+numeroNoticia+"]//div[1]//div[1]//figure[1]//a[1]//div[1]//img[1]").click();

       //     }

       // }

        waitFor(60).seconds();

    }

    public void sacarReportes() {

        Serenity.takeScreenshot();

    }
}
