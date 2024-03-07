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

@Epic("Automatização do site SauceDemo")
@Feature("Validação de Produtos")


public class ValidarProdutos3 {

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
        // Capturar screenshot da página
        capturarScreenshot("Pagina_Inicial");

        // Fazer login
        Configuracao.fazerLogin(driver);

        // Aguardar o elemento ser visível
        Configuracao.aguardarElementoVisivel(driver, By.id("item_4_title_link"));

        // Capturar screenshot da página
        capturarScreenshot("Pagina_Apos_Login");

        // Validar a apresentação do nome do produto
        WebElement produto = driver.findElement(By.id("item_4_title_link"));
        Assert.assertTrue(produto.isDisplayed(), "O produto 'Sauce Labs Backpack' não foi encontrado.");

        // Clicar no nome do produto
        produto.click();

        // Aguardar o campo de valor do produto ser visível
        Configuracao.aguardarElementoVisivel(driver, By.className("inventory_details_price"));

        // Validar a apresentação do campo de valor do produto
        WebElement campoValor = driver.findElement(By.className("inventory_details_price"));
        Assert.assertTrue(campoValor.isDisplayed(), "O campo de valor do produto não foi encontrado.");

        // Selecionar 'ADD TO CART'
        WebElement botaoAddToCart = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        botaoAddToCart.click();

        // Capturar screenshot após adicionar ao carrinho
        capturarScreenshot("Produto_Adicionado_Ao_Carrinho");

        // Aguardar o ícone do carrinho ser visível
        Configuracao.aguardarElementoVisivel(driver, By.id("shopping_cart_container"));

        // Clicar no ícone do carrinho
        WebElement carrinho = driver.findElement(By.id("shopping_cart_container"));
        carrinho.click();

        // Capturar screenshot após clicar no carrinho
        capturarScreenshot("Carrinho_Aberto");

        // Validar se o produto foi inserido no carrinho
        WebElement produtoNoCarrinho = driver.findElement(By.className("inventory_item_name"));
        Assert.assertEquals(produtoNoCarrinho.getText(), "Sauce Labs Backpack",
                "O produto não foi inserido no carrinho.");

        // Capturar screenshot após validar o produto no carrinho
        capturarScreenshot("Produto_No_Carrinho");


    //         @Step("Validar o texto do campo")
    // public void validarTextoDoCampo() {
    //     WebElement campo = driver.findElement(By.id("id_do_campo"));
    //     String textoEsperado = "Texto esperado";
    //     Assert.assertEquals(campo.getText(), textoEsperado, "O texto do campo corresponde ao esperado");
    //     Allure.addAttachment("Validação de Texto", "Texto do campo corresponde ao esperado");
    // }

    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Attachment(value = "{nome}", type = "image/png")
    public byte[] capturarScreenshot(String nome) {
        if (driver instanceof TakesScreenshot) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        return new byte[0];
    }
}
