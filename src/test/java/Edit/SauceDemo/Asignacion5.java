package Edit.SauceDemo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;



public class Asignacion5 {
	
	String url = "https://www.saucedemo.com/";
	WebDriver driver;
	File pantalla;
	String rutaEvidencia = "..\\SauceDemo\\Evidencias\\";
	
	@BeforeSuite
	public void abrirNavegador()  {
		
		
		
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}
		
	@Test(description= "Inicio de sesion", priority=1)
	public void logearse() throws IOException {
		
		pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla, new File(rutaEvidencia + "01_pantallaInicial.jpg" ));
		
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		
		pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla, new File(rutaEvidencia + "02_login.jpg" ));
		
		driver.findElement(By.id("login-button")).click();
		
	}
	
	@Test(description="Seleccionar un producto y confirmar la orden", priority=2)
	public void comprar() throws IOException {
		
		pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla, new File(rutaEvidencia + "03_inventario.jpg" ));
		
		String urlEsperada = "https://www.saucedemo.com/inventory.html";
		String urlActual = driver.getCurrentUrl();
		
		
		Assert.assertEquals(urlActual, urlEsperada);
		
		driver.findElement(By.cssSelector("div.page_wrapper div.inventory_container div.inventory_list div.inventory_item:nth-child(6) div.inventory_item_img a:nth-child(1) > img.inventory_item_img")).click();
		driver.findElement(By.name("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
		driver.findElement(By.xpath("//span[contains(.,'1')]")).click();
		driver.findElement(By.cssSelector("#checkout")).click();
		driver.findElement(By.cssSelector("#first-name")).sendKeys("Javier Antonio");
		driver.findElement(By.cssSelector("#last-name")).sendKeys("Andriani");
		driver.findElement(By.cssSelector("#postal-code")).sendKeys("1832");
		
		pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla, new File(rutaEvidencia + "04_completarInfo.jpg" ));
		
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		
		pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla, new File(rutaEvidencia + "05_chequearInfo.jpg" ));
		
		String urlFinalizar = "https://www.saucedemo.com/checkout-step-two.html";
		String urlFinal = driver.getCurrentUrl();
		
		Assert.assertEquals(urlFinalizar, urlFinal);
		
		driver.findElement(By.id("finish")).click();
		
		pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla, new File(rutaEvidencia + "06_compraFinalizada.jpg" ));
	}	
	
	
	@AfterSuite
	public void cerrarNavegador() {
		driver.close();
	}

}
