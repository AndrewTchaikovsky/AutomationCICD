package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstactComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;
	By productsBy = By.cssSelector(".cartSection h3");

	@FindBy(css = ".totalRow button")
	WebElement button;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public List<WebElement> getCartProducts() {
		return driver.findElements(productsBy);
	}

	public boolean assertProductsInCart(String productName) {
		Boolean match = getCartProducts().stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckoutPage clickCheckout() {
		button.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}

}
