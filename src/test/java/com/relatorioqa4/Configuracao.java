package com.relatorioqa4;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import io.qameta.allure.Attachment;


import java.time.Duration;

public class Configuracao {

    // Dados de Configuração
    public static final String FIREFOX_DRIVER_PATH = "C:\\Windows\\geckodriver.exe";
    public static final String CHROME_DRIVER_PATH = "C:\\Windows\\chromedriver.exe";
    public static final String BASE_URL = "https://www.saucedemo.com";
    public static final String USUARIO = "standard_user";
    public static final String locked_out_user = "locked_out_user";    
    public static final String visual_user = "visual_user";
    public static final String SENHA = "secret_sauce";
    public static final String senha_incorreta = "SENHAINCORRETA";
    public static final String ALLURE_URL = "https://www.allure.com";
    public static final String IKHON_URL = "https://www.ikhon.com.br";

    // Configurar o WebDriver Chrome
    public static WebDriver configurarWebDriver() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        //driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1200, 765));
        return driver;
        //return new ChromeDriver();
    }

    // Configurar o WebDriver firefox
    public static WebDriver configurarWebDriverFirefox() {
        System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);
        FirefoxOptions options = new FirefoxOptions();
        //options.setCapability("marionette", true); // Habilita o protocolo Marionette
        WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().setSize(new Dimension(1200, 765));
        return driver;
    }

    // Método para fazer login
    public static void fazerLogin(WebDriver driver) {
        driver.findElement(By.id("user-name")).sendKeys(USUARIO);
        driver.findElement(By.id("password")).sendKeys(SENHA);
        driver.findElement(By.id("login-button")).click();
    }
    
    // Método para fazer login senha incorreta
    public static void fazerLoginSenhaIncorreta(WebDriver driver) {
        driver.findElement(By.id("user-name")).sendKeys(USUARIO);
        driver.findElement(By.id("password")).sendKeys(senha_incorreta);
        driver.findElement(By.id("login-button")).click();
    }

    // Método para aguardar elemento visível
    public static void aguardarElementoVisivel(WebDriver driver, By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Método aguardar elemento visível
    public static void aguardarElementoVisivel(By id) {
        throw new UnsupportedOperationException("Unimplemented method 'aguardarElementoVisivel'");
    }

    // Método captura rela - allure
    @Attachment(value = "{nome}", type = "image/png")
    public static byte[] capturarScreenshot(String nome, TakesScreenshot driver) {
        if (driver instanceof TakesScreenshot) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        return new byte[0];
    }

    //Método validar mensagem de erro
    //@Step("Validar mensagem de erro")
    // public void validarMensagemErro() {
    //     String mensagemErro = driver.findElement(By.cssSelector(".error-message-container.error")).getText();
    //     Assert.assertEquals(mensagemErro, "Epic sadface: Username and password do not match any user in this service");
    //     capturarScreenshot("Mensagem_de_Erro");
    // }

    // Método validar mensagem de erro    
    public static void validarMensagemErro(WebDriver driver) {
        String mensagemErro = driver.findElement(By.cssSelector(".error-message-container.error")).getText();
        Assert.assertEquals(mensagemErro, "Epic sadface: Username and password do not match any user in this service");
        //capturarScreenshot(driver, "Mensagem_de_Erro");
    }

    // public static WebDriver configurarWebDriver() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'configurarWebDriver'");
    // }


    // public static void fazerLogin(WebDriver driver) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'fazerLogin'");
    // }

}
