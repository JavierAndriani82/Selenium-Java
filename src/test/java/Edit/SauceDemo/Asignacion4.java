/**
 * 
 */
package Edit.SauceDemo;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

/**
 * @author javier.andriani
 *
 */
public class Asignacion4 {
	
	String url = "http://automationpractice.pl";
	WebDriver driver;
	
	@BeforeSuite
	public void abrirNavegador() {
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	@Test(description="Click en Sign In y Create an Account")
	
	public void crearUsuario () {
		//Hacer click en Sign in
		driver.findElement(By.linkText("Sign in")).click();
		//Escribir mail y presionar en Create an account
		Faker faker = new Faker();
		String email = faker.internet().emailAddress();
		
		driver.findElement(By.id("email_create")).sendKeys(email);
		
		driver.findElement(By.xpath("//span[contains(.,'Create an account')]")).click();
		
		//Genero una espera por que no me toma el click 
		WebDriverWait espera = new WebDriverWait(driver, Duration.ofSeconds(5));
		espera.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[contains(@id,'days')]")));
		//completar formulario
		driver.findElement(By.xpath("//label[@for='id_gender2']")).click();
		
		driver.findElement(By.id("customer_firstname")).sendKeys("Javier Antonio");
		
		driver.findElement(By.id("customer_lastname")).sendKeys("Andriani");
		
		driver.findElement(By.id("passwd")).sendKeys("Taliban82");
		
		Select dia = new Select(driver.findElement(By.tagName("select")));
		dia.selectByIndex(6);
		
		Select mes = new Select(driver.findElement(By.id("months")));
		mes.selectByValue("2");
		
		Select año = new Select(driver.findElement(By.xpath("//select[@id='years']")));
		año.selectByValue("1986");
		
		driver.findElement(By.id("newsletter")).click();
		
		driver.findElement(By.id("optin")).click();
		
		driver.findElement(By.xpath("//span[contains(.,'Register')]")).click();
	}
	
		
		
	
}
