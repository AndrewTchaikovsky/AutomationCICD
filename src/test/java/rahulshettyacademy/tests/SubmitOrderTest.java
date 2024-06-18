package rahulshettyacademy.tests;

import java.io.IOException;
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
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test
	public void submitOrder() throws IOException {
		
		String country = "united";

		ProductCatalogue productCatalogue = landingPage.loginApplication("andy.tchiakovsky@gmail.com", "178Brighton11");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);

		CartPage cartPage = productCatalogue.goToCartPage();
		Assert.assertTrue(cartPage.assertProductsInCart(productName));

		CheckoutPage checkoutPage = cartPage.clickCheckout();
		checkoutPage.chooseCountry(country);

		ConfirmationPage confirmationPage = checkoutPage.clickSubmit();
		Assert.assertTrue(confirmationPage.getMessageText().equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("andy.tchiakovsky@gmail.com", "178Brighton11");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	// THIS IS THE ADDITIONAL CODE FOR THE GITHUB PURPOSE

}
