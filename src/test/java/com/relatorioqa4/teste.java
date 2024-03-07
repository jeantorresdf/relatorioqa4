package com.relatorioqa4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class teste {
 
    //@Test
//    public static void main(String[] args) throws InterruptedException {
        


    	public static void main(String[] args) {
//		System.setProperty("webdriver.gecko.driver", "/Users/wcaquino/Downloads/geckodriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.ikhon.com.br");
		System.out.println(driver.getTitle());
		driver.quit();
	}




}
