package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void LoginErorValidation() throws IOException {
		ProductCatalogue productCatalogue = landingPage.loginApplication("andy.tchiakovsky@gmail.com", "178");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void ProductErroValidation() throws IOException {
		String productName = "ZARA COAT 3";
		String country = "united";

		ProductCatalogue productCatalogue = landingPage.loginApplication("andy.tchiakovsky@gmail.com", "178Brighton11");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Assert.assertFalse(cartPage.assertProductsInCart("ZARA COAT 33"));

	}

}
