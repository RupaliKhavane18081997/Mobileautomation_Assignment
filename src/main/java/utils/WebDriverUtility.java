package utils;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

public class WebDriverUtility {
	public static WebDriverWait wait;
	
	public WebDriverUtility(AppiumDriver driver ) {
//		this.wait = wait;
		this.wait = new WebDriverWait(driver,10);
	}
		
	/**
	 * 
	 * @param driver
	 * @param time
	 */
	public void implicitWait(WebDriver driver,int time) {
		driver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);
	}
	/**
	 * 
	 * @param driver
	 * @param element
	 * @param time
	 */
	public void explicitWait(WebElement element,int waitTime) {
		
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * check for visibility of element
	 * @param driver
	 * @param elemnet
	 */
//	public void  waitForElemnetToBeVisible( WebElement elemnet){
////		wait = new WebDriverWait(driver,30);
//		wait.until(ExpectedConditions.visibilityOf(elemnet));
//	}
	
	public void  waitForElemnetToBeVisible(WebElement elemnet){
//		WebDriverWait wait = new WebDriverWait(driver,30);
		String elementName = elemnet.getAttribute("text");
		if(elementName==null) {
			elementName=elemnet.getAttribute("value");
		}
		try {
			wait.until(ExpectedConditions.visibilityOf(elemnet));
		} catch (Exception e) {
			BaseTest.test.info(elementName + " is not present");
			LogManager.getLogger().info(elementName + "is not present");
		}
//		driver.findElementByAndroidUIAutomator(locatorString);
	}
	
	/**
	 * check for elment is visible and clickable
	 * @param driver
	 * @param elemnet
	 */
	public void  waitForElemnetToBeClickable( WebElement elemnet){
//		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(elemnet));
	}
	
	/**
	    * This method wait for the element to be clicked , its custom wait created to avoid elemenInterAceptable Exception
	    * @param element
	    * @throws throwable
	    * 
	    */
	   
	   public void waitAndClick(WebElement element) throws InterruptedException
	   {
		   int count = 0;
	       while(count<20) {
		    	   try {
		    	       element.click();
		    	       break;
		    	   }catch(Throwable e){
		    		   Thread.sleep(1000);
		    		   count++;
		    	   }
	       }
	   }
	   /**
	     * 
	     * @param driver
	     * @param screenshotName
	     * @throws Throwable
	     */
	    public void takeScreenshot(WebDriver driver, String screenshotName) throws Throwable {
	    	TakesScreenshot ts=(TakesScreenshot)driver;
	    	File src=ts.getScreenshotAs(OutputType.FILE);
	    	File dest=new File("./screenshot/"+screenshotName+".PNG");
	    	FileUtils.copyFile(src, dest);
	    }
	    /**
		    * This method used for clicking on element using java script
		    * @param driver
		    * @param element
		    */
		   public void jsClick(WebDriver driver, WebElement element) {
			   JavascriptExecutor executor = (JavascriptExecutor)driver;
			   executor.executeScript("arguments[0].click();", element);
		   }
		   /**
			 * 
			 * @param driver
			 * @param element
			 */
			
//			public void tapInMiddle(AppiumDriver driver ,WebElement element) {
//				   Point location = element.getLocation();
//				   int x=location.getX();
//				   int y=location.getY();
//				   Dimension size = element.getSize();
//				   int height = size.getHeight();
//				   int width = size.getWidth();
//				   TouchAction action=new TouchAction((PerformsTouchActions) driver);
//				   action.tap(PointOption.point(x+width/2, y-height/2)).release().perform();
//			   }
			   
		   
}
