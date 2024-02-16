package com.relatorioqa4;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;


public class LogarSite {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", Configuracao.CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    @Description("Login no site com usuário e senha")
    public void testLogin() {
        driver.get(Configuracao.BASE_URL);
        fazerLogin(Configuracao.USUARIO, Configuracao.SENHA);
        capturarScreenshot("página_de_login");
        // Adicione verificações ou interações adicionais após o login, se necessário
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Step("Fazendo login com usuário: {usuario} e senha: {senha}")
    public void fazerLogin(String usuario, String senha) {
        driver.findElement(By.id("user-name")).sendKeys(usuario);
        driver.findElement(By.id("password")).sendKeys(senha);
        driver.findElement(By.id("login-button")).click();
    }

    @Attachment(value = "{nome}", type = "image/png")
    public byte[] capturarScreenshot(String nome) {
        if (driver instanceof TakesScreenshot) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        return new byte[0];
    }
}
