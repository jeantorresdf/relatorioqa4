package com.relatorioqa4;

import io.qameta.allure.Attachment;
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
import java.time.Duration;
//import com.relatorioqa4.Configuracao;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

@Epic("Automatização do site SauceDemo")
@Feature("Validação de Produtos")

public class ValidarProdutos {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = Configuracao.configurarWebDriver();
    }

    @Test
    @Description("Teste de login e validação do produto")
    public void testarSauceDemo() {
        // Acessar a página
        driver.get(Configuracao.BASE_URL);

        // Fazer login
        fazerLogin(Configuracao.USUARIO, Configuracao.SENHA);

        // Aguardar o elemento ser visível
        aguardarElementoVisivel(By.id("item_4_title_link"));
        //(By.xpath("//div[@id='item_4_title_link']/div"));

        // Capturar screenshot da página
        capturarScreenshot("Pagina_Apos_Login");

        // Validar a apresentação do nome do produto
        WebElement produto = driver.findElement(By.id("item_4_title_link"));
        Assert.assertTrue(produto.isDisplayed(), "O produto 'Sauce Labs Backpack' não foi encontrado.");

        // Clicar no nome do produto
        produto.click();

        // Aguardar o campo de valor do produto ser visível
        aguardarElementoVisivel(By.className("inventory_details_price"));

        // Validar a apresentação do campo de valor do produto
        WebElement campoValor = driver
                .findElement(By.className("inventory_details_price"));
        Assert.assertTrue(campoValor.isDisplayed(), "O campo de valor do produto não foi encontrado.");

        // Selecionar 'ADD TO CART'
        WebElement botaoAddToCart = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        botaoAddToCart.click();
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

    @Step("Aguardar elemento visível")
    public void aguardarElementoVisivel(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
