package com.relatorioqa4;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.time.Duration;

@Epic("Automatização do site SauceDemo")
@Feature("Logar")

public class FalhaLogin2 {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = Configuracao.configurarWebDriver();
    }

    @Test
    @Description("Login com senha incorreta e validação da mensagem de erro")
    public void testFalhaLogin() {
        // Acessar a página
        driver.get(Configuracao.BASE_URL);
        // Capturar screenshot da página
        capturarScreenshot("Pagina_Inicial");
   
        // Fazer login
        Configuracao.fazerLoginSenhaIncorreta(driver);

        //Aguardar o elemento ser visível
        //Configuracao.aguardarElementoVisivel(driver, By.className("error-message-container error"));
        //Configuracao.aguardarElementoVisivel(driver,
        //        By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3/text()"));

        // *[@id="login_button_container"]/div/form/div[3]/h3/text()

    // @Step("Fazendo login com usuário: {usuario} e senha: {senha}")
    // public void fazerLogin(String usuario, String senha) {
    //     driver.findElement(By.id("user-name")).sendKeys(usuario);
    //     driver.findElement(By.id("password")).sendKeys(senha);
    //     driver.findElement(By.id("login-button")).click();
    // }

    // Método validar mensagem de erro
    Configuracao.validarMensagemErro(driver);
    capturarScreenshot("mensagem_erro.png");
}

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Método para capturar um screenshot e anexar no Allure
    private void capturarScreenshot(String nomeDoScreenshot) {
        if (driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(nomeDoScreenshot, new ByteArrayInputStream(screenshot));
        }
    }
}
