package com.relatorioqa4;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;



public class AcessarPagina2 {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        //driver = Configuracao.configurarWebDriverFirefox();
        driver = Configuracao.configurarWebDriver();
    // public void setUp() throws InterruptedException {
    //     System.setProperty("webdriver.chrome.driver", Configuracao.CHROME_DRIVER_PATH);
    //     driver = new ChromeDriver();
    //     driver.manage().window().maximize();
    //     Thread.sleep(1000);
    }


    @Test
    @Description("Acessar a página https://www.saucedemo.com")
    @Epic("Acessar Página")
    @Feature("Página de Login")
    public void testAcessarPagina() {
        driver.get(Configuracao.BASE_URL);
        // Inserir códigos de interação com a página, se necessário
        // Exemplo de captura de screenshot usando o WebDriver do Selenium
        tirarScreenshot("screenshot_da_pagina_inicial");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Método para tirar um screenshot e capturar no Allure
    private void tirarScreenshot(String nomeDoScreenshot) {
        if (driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(nomeDoScreenshot, new ByteArrayInputStream(screenshot));
        }
    }    

}
