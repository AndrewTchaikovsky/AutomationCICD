package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		
		String productName = "ZARA COAT 3";

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.findElement(By.id("userEmail")).sendKeys("andy.tchiakovsky@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("178Brighton11");
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
		
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase("ZARA COAT 3"));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector(".form-group input")), "united").build().perform();
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(4)")).click();
		
//		second option
//		driver.findElement(By.cssSelector(".form-group input")).sendKeys("united");
//		List<WebElement> countries = driver.findElements(By.cssSelector(".ta-item"));
//		countries.stream().filter(country -> country.getText().equalsIgnoreCase("United States")).findFirst()
//				.orElse(null).click();
		
		driver.findElement(By.cssSelector("a[class*='submit']")).click();
//		System.out.println("Your order id is " + driver.findElement(By.cssSelector("label[class='ng-star-inserted']")).getText());
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		

	}

}
