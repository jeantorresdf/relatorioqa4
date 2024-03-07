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

import java.time.Duration;

@Epic("Acessar Página")
@Feature("Página de Login")

public class AcessarPagina {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = Configuracao.configurarWebDriver();
    }

    @Test
    @Description("Teste acesso pagina")
    public void testarSauceDemo() {
        // Acessar a página
        driver.get(Configuracao.BASE_URL);
        // Capturar screenshot da página
        capturarScreenshot("Pagina_Inicial");

        // Validar a apresentação do texto na coluna
        validarTextoColuna();
    }

    // Método para validar a apresentação do texto na coluna e gera evidências dessa
    // validação
    @Step("Validar texto da coluna")
    public void validarTextoColuna() {
        WebElement textoColuna = driver.findElement(By.id("login_credentials"));
        String textoCompleto = textoColuna.getText();
        // Verificar se o texto desejado está presente dentro do texto completo
        Assert.assertTrue(textoCompleto.contains("Accepted usernames are:"));
        Allure.addAttachment("Validação do texto na coluna",
                "O texto 'Accepted usernames are:' está presente na coluna.");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Método para tirar um screenshot e capturar no Allure
    @Attachment(value = "{nome}", type = "image/png")
    public byte[] capturarScreenshot(String nome) {
        if (driver instanceof TakesScreenshot) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        return new byte[0];
    }
}
