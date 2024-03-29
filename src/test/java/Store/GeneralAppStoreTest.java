package Store;

import org.testng.annotations.Test;

import Base.BaseTest;
import pom.GeneralStore.GenearlStoreApp;
import pom.createIncident.FireSeriveCasuality;
import utils.PropertyUtils;


public class GeneralAppStoreTest extends BaseTest {

	
	@Test
	public void TestCase01GenearlStoreAppUserInformation() throws Exception {
		
		GenearlStoreApp GenearlStoreApp = new GenearlStoreApp(); 
		GenearlStoreApp.GenearlStoreAppUserInformation(PropertyUtils.getTestConfiguration("User"));
		System.out.println("User Information Test Case Pass");
		}
	
	
	@Test
	public void TestCase02AddToCartProduct() throws Exception {
		
		
		GenearlStoreApp GenearlStoreApp = new GenearlStoreApp(); 
		GenearlStoreApp.AddToCartProduct();
		System.out.println("Add to cart Test Case Pass");
		
		}
	
	@Test
	public void TestCase03SumOfAddedProduct() throws Exception {
		GenearlStoreApp GenearlStoreApp = new GenearlStoreApp(); 
		GenearlStoreApp.SumOfAddedProduct();
		System.out.println("Sum of Added Product Test Case Pass");
		
		}
	
	@Test
	public void TestCase04SendEmailonDiscount() throws Exception {
		
		GenearlStoreApp GenearlStoreApp = new GenearlStoreApp(); 
		GenearlStoreApp.SendEmailOnDiscount();
		System.out.println("Send Email On Discount Test case Pass");
		}
	
}
