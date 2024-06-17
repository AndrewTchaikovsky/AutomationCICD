package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstactComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;
	String country;
	By results = By.cssSelector(".ta-results");

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".form-group input")
	WebElement searchBar;

	@FindBy(css = ".ta-item:nth-of-type(4)")
	WebElement SelectUsa;

	@FindBy(css = "a[class*='submit'")
	WebElement submitButton;

	public void chooseCountry(String country) {
		Actions a = new Actions(driver);
		a.sendKeys(searchBar, country).build().perform();
		waitForElementToAppear(results);
		SelectUsa.click();
	}

	public ConfirmationPage clickSubmit() {
		submitButton.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

}
