
package pom.GeneralStore;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BasePage;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.VerificationUtils;

public class GenearlStoreApp extends BasePage {
	
	public GenearlStoreApp()
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@FindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	private AndroidElement SelectCountry;
	
	@FindBy(xpath="//android.widget.TextView[@text=\"India\"]")
	private AndroidElement India;
	
	@FindBy(xpath="//android.widget.EditText[@text=\"Enter name here\"]")
	private AndroidElement EnterName;
	
	@FindBy(xpath="//android.widget.Button[@text=\"Let's  Shop\"]")
	private AndroidElement LetsShopButton;
	
	@FindBy(xpath="//android.widget.TextView[@text=\"Products\"]")
	private AndroidElement Products;
	
	@FindBy(xpath="//android.widget.Button[@text=\"Let's  Shop\"]")
	private AndroidElement AppInApp_screen;
	
	@FindBy(id="com.androidsample.generalstore:id/productImage")
	private AndroidElement Sample_Product;
	
	@FindBy(xpath="//android.widget.TextView[@text=\"Air Jordan 4 Retro\"]//..//android.widget.TextView[@text=\"ADD TO CART\"]")
	private AndroidElement AddToCartButton;
	
	@FindBy(xpath="//android.widget.TextView[@text=\"PG 3\"]")
	private AndroidElement PG3;
	
	@FindBy(xpath="//android.widget.TextView[@text=\"PG 3\"]//..//android.widget.TextView[@text=\"ADD TO CART\"]")
	private AndroidElement PG3AddToCart;
	
	@FindBy(xpath="//android.widget.TextView[@text=\"Nike SFB Jungle\"]")
	private AndroidElement NikeSFBJungle;
	
	@FindBy(xpath="//android.widget.TextView[@text=\"Nike SFB Jungle\"]//..//android.widget.TextView[@text=\"ADD TO CART\"]")
	private AndroidElement NikeSFBJungleAddToCart;
	
	@FindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private AndroidElement CartButton;
	
	@FindBy(xpath="//android.widget.CheckBox[@text=\"Send me e-mails on discounts related to selected products in future\"]")
	private AndroidElement SendMeEmailCheckbox;
	
	@FindBy(xpath="//android.widget.Button[@text=\"Visit to the website to complete purchase\"]")
	private AndroidElement VisitWebsiteButton;
	
	@FindBy(xpath="(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\"])[1]")
	private AndroidElement ProductPrize1;
	
	@FindBy(xpath="(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\"])[2]")
	private AndroidElement ProductPrize2;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/totalAmountLbl\"]")
	private AndroidElement totalPricePayable;
	
	@FindBy(xpath ="//android.widget.EditText")
	private AndroidElement GoogleSearch;
	
	
	public void GenearlStoreAppUserInformation(String username) throws Exception
	{
		
		click(SelectCountry,"SelectCountry");
		Thread.sleep(10);
		clickAfterScrolling(India, 10, "India");
		driver.navigate().back();
		click(EnterName,"EnterName");
		sendKeys(EnterName,username);
		driver.hideKeyboard();
		click(LetsShopButton,"Lets Shop Button");
		VerificationUtils.validate("true", present(Products), "Product title is Present");
		VerificationUtils.validate("true", present(Sample_Product), "Sample of Product title is Present");
		VerificationUtils.validate("true", present(AddToCartButton), "Add To Cart Button is Present");
		
	}
	
	public void AddToCartProduct() throws Exception
	{
		Scrolltoelement(PG3, 6, "PG 3");
		click(PG3AddToCart,"PG 3 Add To Cart");
		Scrolltoelement(NikeSFBJungle, 4, "NikeSFBJungle");
		click(NikeSFBJungleAddToCart,"Nike SFB Jungle Add To Cart");
		click(CartButton,"CartButton");
		VerificationUtils.validate("true", present(PG3), "PG3 added Product is Present");
		VerificationUtils.validate("true", present(NikeSFBJungle), "Nike SFB Jungle Product is Present");
	}
	
	public void SumOfAddedProduct() throws Exception
	{
		String price1= ProductPrize1.getText().replace("$","");
		String price2=  ProductPrize2.getText().replace("$","");
		double priceone = Double.parseDouble(price1);
		double pricetwo =Double.parseDouble(price2);
		double totalprice= priceone+pricetwo;
		String Payable = totalPricePayable.getText().replace("$","").replace(" ","");
		double totalPayable = Double.parseDouble(Payable);
		
		System.out.println(totalprice);
		System.out.println(totalPayable);
	VerificationUtils.validate(true, totalprice==totalPayable, "Total Price of Product is :" +Payable);
		
	}
	
	public void SendEmailOnDiscount() throws Exception
	
	{
		click(SendMeEmailCheckbox,"SendMeEmailCheckbox");
		click(VisitWebsiteButton,"VisitWebsiteButton");
		
		
		sendKeys(GoogleSearch,"General Store");
		Thread.sleep(2000l);
		driver.navigate().back();
		
		//click(GoogleSearch,"GoogleSearch");
		//sendKeys("GoogleSearch",GeneralStore);
		VerificationUtils.validate(true, EnterName.isDisplayed(), "Enter Is Display");
		VerificationUtils.validate(true, LetsShopButton.isDisplayed(), "Lets Shop Button Is Display");
		VerificationUtils.validate(true, SelectCountry.isDisplayed(), "Select Country Is Display");
		
	}
	
}
