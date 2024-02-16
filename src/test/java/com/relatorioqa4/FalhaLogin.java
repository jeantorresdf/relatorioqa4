package com.relatorioqa4;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.qameta.allure.Step;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;


public class FalhaLogin {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", Configuracao.CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    @Description("Login com senha incorreta e validação da mensagem de erro")
    public void testFalhaLogin() {
        driver.get(Configuracao.BASE_URL);
        fazerLogin("standard_user", "senha_incorreta");
        capturarScreenshot("Login_com_Senha_Incorreta");
        validarMensagemErro();
    }
    
    @Step("Fazendo login com usuário: {usuario} e senha: {senha}")
    public void fazerLogin(String usuario, String senha) {
        driver.findElement(By.id("user-name")).sendKeys(usuario);
        driver.findElement(By.id("password")).sendKeys(senha);
        driver.findElement(By.id("login-button")).click();
    }

    @Step("Validar mensagem de erro")
    public void validarMensagemErro() {
        String mensagemErro = driver.findElement(By.cssSelector(".error-message-container.error")).getText();
        Assert.assertEquals(mensagemErro, "Epic sadface: Username and password do not match any user in this service");
        capturarScreenshot("Mensagem_de_Erro");
    }

    @Attachment(value = "{nome}", type = "image/png")
    public byte[] capturarScreenshot(String nome) {
        if (driver instanceof TakesScreenshot) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        return new byte[0];
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
}
