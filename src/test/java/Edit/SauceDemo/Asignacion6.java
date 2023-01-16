package Edit.SauceDemo;


import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.CapturaEvidencia;
import Utilities.DatosExcel;

public class Asignacion6 {
	
	String url = "https://www.saucedemo.com/";
	WebDriver driver;
	String rutaEvidencia = "..\\SauceDemo\\Evidencias\\";
	String nombreDocumento = "Evidencia SauceDemo.com";
	
	@BeforeSuite 
	public void abrirNavegador() throws InvalidFormatException, IOException, InterruptedException {
		
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencia + nombreDocumento, "Probando el Login con multiples Users" , 20);
	}
	
	@Test(description= "Login")
	public void logearse(String email, String password, String nombre, String apellido, String CP) throws InvalidFormatException, IOException, InterruptedException {
		driver.findElement(By.id("user-name")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("login-button")).click();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento, "Login" );
		
		if(driver.findElement(By.xpath("//button[@name='add-to-cart-test.allthethings()-t-shirt-(red)']")).isDisplayed()) {
			driver.findElement(By.xpath("//button[@name='add-to-cart-test.allthethings()-t-shirt-(red)']")).click();
			driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
			driver.findElement(By.id("checkout")).click();
			
			CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img2.jpg", rutaEvidencia + nombreDocumento, "Login" );
			
			driver.findElement(By.id("first-name")).sendKeys(nombre);
			driver.findElement(By.id("last-name")).sendKeys(apellido);
			driver.findElement(By.id("postal-code")).sendKeys(CP);
			driver.findElement(By.id("continue")).click();
			
			CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img3.jpg", rutaEvidencia + nombreDocumento, "Login" );
			
			driver.findElement(By.id("finish")).click();
			
			CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img3.jpg", rutaEvidencia + nombreDocumento, "Login" );
			
		}else {
			System.out.println("Login invalido");
			CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento, "Login" );
		}
	}
	
	@DataProvider(name="Datos Login")
	public Object[][] leerDatosLogin() throws Exception {
		return DatosExcel.leerExcel(
				"..\\SauceDemo\\Datos\\Datos_Login.xlsx", 
				"Hoja1");
	}
	
	@DataProvider(name="Datos Login")
	public Object[][] leerDatosPersonales() throws Exception {
		return DatosExcel.leerExcel(
				"..\\SauceDemo\\Datos\\Datos_Login.xlsx", 
				"Hoja2");
	}
	
	@AfterSuite
	public void cerraNavegador() throws InvalidFormatException, IOException, InterruptedException {
		driver.close();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento, "Login" );
	}
	
	
	
}
