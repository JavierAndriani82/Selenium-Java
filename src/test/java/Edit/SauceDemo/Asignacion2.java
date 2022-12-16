package Edit.SauceDemo;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Asignacion2 {
	
	String url = "https://www.saucedemo.com/";



@Test
public	void hacerBusquedaEnChrome () {
	
	WebDriver navegador = new ChromeDriver();
	
	navegador.get(url);
	
	WebElement UserName = navegador.findElement(By.id("user-name"));
	UserName.sendKeys("standard_user");
	
	WebElement Password = navegador.findElement(By.id("password"));
	Password.sendKeys("secret_sauce");
	
	WebElement Login = navegador.findElement(By.id("login-button"));
	Login.click();
	
	
	navegador.close();
}

}