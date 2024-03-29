package Base;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import utils.ReporterClass;
import utils.Utilitycommon;
import utils.WebDriverUtility;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Dimension;


public class BasePage extends BaseTest implements ReporterClass {

	public WebDriverUtility wLib = new WebDriverUtility(BaseTest.driver);
	public Utilitycommon util=new Utilitycommon();
//	public static AndroidDriver<AndroidElement> driver = BaseTest.driver;
	/**
	 * @param element
	 * @param elementName
	 */
	public void clickScreenshot(AndroidElement element, String elementName) {
		try {
			wLib.waitForElemnetToBeVisible( element);
			element.click();
			ReporterClass.ReportLoggerScreenshot(info, elementName + " is clicked");
			LogManager.getLogger().info(elementName + " is clicked");
		} catch (Exception e) {
//			ReportLoggerScreenshot(info, elementName + " unable to click");
			LogManager.getLogger().info(elementName + " unable to click");
//			returnExceptionMessage(driver,e);
			Assert.fail();
		}
	}

	/**
	 * click on element and take screenshot with message written in attribute "
	 * 'label'+ is clicked " and Print message in testng report and in console
	 * <b>Strore logs</b>
	 * 
	 * @param element
	 */
	public void clickScreenshot(AndroidElement element) {
//		System.out.println("Trying clicking element "+element);
		wLib.waitForElemnetToBeVisible(element);
//		String elementName = element.getAttribute("text");
		String elementName = element.getText();
		try {
			element.click();
			ReporterClass.ReportLoggerScreenshot(info, elementName + " is clicked");
			LogManager.getLogger().info(elementName + " is clicked");
		} catch (Exception e) {
			ReporterClass.ReportLoggerScreenshot(info, elementName + " unable to click");
			LogManager.getLogger().info(elementName + " unable to click");
			//Assert.fail();
		}

	}
	


	/**
	 * No Screenshot Click log the event on console and log on file and extent
	 * Report
	 */
	public void click(AndroidElement element) {
		wLib.waitForElemnetToBeVisible( element);
		String elementName = element.getAttribute("text");
		if(elementName==null) {
			elementName=element.getAttribute("value");
		}
		try {
			element.click();
			BaseTest.test.info(elementName + " is clicked");
			LogManager.getLogger().info(elementName + " is clicked");
		} catch (Exception e) {
			BaseTest.test.info(elementName + " unable to click");
			LogManager.getLogger().info(elementName + " unable to click");
			Assert.fail();
		}

	}

	/**
	 * No Screenshot Click log the event on console and log on file and extent
	 * Report
	 */
	public void click(AndroidElement element, String elementName) {
		try {
			wLib.waitForElemnetToBeVisible( element);
			element.click();
			BaseTest.test.info(elementName + " is clicked");
			LogManager.getLogger().info(elementName + " is clicked");
		} catch (Exception e) {
			BaseTest.test.info(elementName + " unable to clicked");
			LogManager.getLogger().info(elementName + " unable to clicked");
		}
	}

	public void sendKeys(AndroidElement element, String txt) {
		wLib.waitForElemnetToBeVisible( element);
		String name = element.getAttribute("text");
//		if(name==null) {
//			name=element.getAttribute("label");
//		}
		try {
			element.sendKeys(txt);
			BaseTest.test.info("text " + txt + " in " + name + " text box is sent");
			LogManager.getLogger().info("text " + txt + " in " + name + " text box is sent");
		//ReporterClass.ReportLoggerScreenshot(info, "text " + txt + " in " + name + " text box is sent");
		} catch (Exception e) {
			BaseTest.test.info("send Keys " + txt + " in " + name + " is failed");
			LogManager.getLogger().info("send Keys " + txt + " in " + name + " is failed");
			Assert.fail();
		}
	}

	public void sendKeys(AndroidElement element, String txt, String fieldName) {
		wLib.waitForElemnetToBeVisible( element);
		try {
			element.sendKeys(txt);
			BaseTest.test.info(txt + " is sent to " + fieldName + " text field ");
			LogManager.getLogger().info(txt + " is sent to " + fieldName + " text field ");
			ReporterClass.ReportLoggerScreenshot(info, "text " + txt + " is sent to " + fieldName + " text field ");
		} catch (Exception e) {
			BaseTest.test.info("send Keys " + txt + " in " + fieldName + " is failed");
			LogManager.getLogger().info("send Keys " + txt + " in " + fieldName + " is failed");
			Assert.fail();
		}
	}

	/**
	 * pending
	 */
	public void sendKeysSlowly(AndroidElement element, String txt, String fieldName) {
		wLib.waitForElemnetToBeVisible( element);
		element.sendKeys(txt.substring(0, 1));
		for (int i = 1; i < txt.length(); i++) {
			BaseTest.driver.findElementByXPath(
					"//XCUIElementTypeOther[@name=\"Re-enter Account Number *\"]/preceding-sibling:: XCUIElementTypeTextField")
					.sendKeys(txt.substring(i, i + 1));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}

		BaseTest.test.info("Filling " + txt + " in " + fieldName);
		Reporter.log("Filling " + txt + " in " + fieldName, true);
	}


	public static boolean isDisplayed(AndroidElement element) {
		try {
			new WebDriverWait(BaseTest.driver, 30).until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();
		} catch (NoSuchElementException | TimeoutException exception) {
			BaseTest.test.fail("Element is not present");
			return false;
		}
	}
	
	
	public boolean isNotDisplayed(AndroidElement element) {
		try {
			new WebDriverWait(BaseTest.driver, 15).until(ExpectedConditions.invisibilityOf(element));
			return element.isDisplayed();
		} catch (NoSuchElementException | TimeoutException exception) {
			System.out.println("Element is not present");
			return true;
		}
	}
	/*
	 * public void clickAfterScrolling(AndroidElement element, int scrollNumber,
	 * String elementName) throws InterruptedException { // AndroidDriver driver =
	 * BaseTest.driver; for(int i=1;i<scrollNumber;i++) { try {
	 * element.isDisplayed(); System.out.println("Element is not present");
	 * ReporterClass.ReportLoggerScreenshot(info, elementName + " is clicked");
	 * LogManager.getLogger().info(elementName + " is clicked"); break; } catch
	 * (Exception e) { util.swipe(300, 1400, 300, 300);
	 * System.out.println("swipe is performed");
	 * //LogManager.getLogger().info(elementName + " unable to click"); //
	 * Assert.fail(); }
	 * 
	 * } element.click(); }
	 */
		
//		public void scrollandClick(AndroidElement element, String direction){
//			
//			Direction direct=null;
//			
//			if (direction=="up")
//				{
//					direct=Direction.UP;
//					System.out.println("Scrolling up");
//				}
//			else {
//				direct=Direction.DOWN;
//				System.out.println("Scrolling down");
//
//			}
//				
//			swipe(element, direct);
//			element.click();
//		}
		
	
		public void Scrolltoelement(AndroidElement element, int scrollNumber, String elementName) throws InterruptedException {
//			AndroidDriver driver = BaseTest.driver;
			for(int i=1;i<scrollNumber;i++) {
				try {
					element.isDisplayed();
					System.out.println("Element is not present");
					ReporterClass.ReportLoggerScreenshot(info, elementName + " is Scroll");
					LogManager.getLogger().info(elementName + " is clicked");
					break;
				}
				catch (Exception e) {
					Thread.sleep(1000);
					util.swipe(300, 1400, 300, 300);
					System.out.println("swipe is performed");
					LogManager.getLogger().info(elementName + " unable to Scroll");
//					Assert.fail();
				}
			}
	}
		
		
public void clickonWebElement(WebElement element1)
		{
	try
		{
			WebDriverWait wait=new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.elementToBeClickable(element1));
			 element1.click();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		}



		
public void switchToNativeview() {
		    Set<String> str = driver.getContextHandles();
		    for (String newsrt : str) {
		      if (newsrt.contains("NATIVE")) {
		        driver.context(newsrt);
		      }
		   
		    } 
		  }

			/*
			 * public void switchToWeb() throws InterruptedException { Thread.sleep(4000L);
			 * Set<String> str = driver.getContextHandles(); for (String newsrt : str) { if
			 * (newsrt.contains("chrome")) { driver.context(newsrt); } if
			 * (newsrt.contains("CHROMIUM")) { driver.context(newsrt); }
			 * System.out.println("Switch to web");
			 * 
			 * } }
			 */


public void switchToWeb() throws InterruptedException {
	Thread.sleep(4);
	Set<String> str = driver.getContextHandles();
	for (String newsrt : str) {
		System.out.println(newsrt);
		if (newsrt.contains("WEBVIEW_chrome")) {
			BaseTest.driver.context(newsrt);
			System.out.println("Switch to web : " + newsrt);
		}
	}
}



public void scrollWithUiAutomator(String exactText) {
	((AndroidDriver)driver).
	findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))" +".scrollIntoView(new UiSelector().text(\""+exactText+"\"))");
}
public void swipe(int X1, int Y1, int X2, int Y2) {
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {}
	new TouchAction(driver).press(PointOption.point(X1, Y1))
	 .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
	 .moveTo(PointOption.point(X2, Y2))
	 .release().perform();
	System.out.println("Swipe is performed");
}
public void openNotifications() {
	try {
		((AndroidDriver) driver).openNotifications();
	} catch (Exception e) {
		LogManager.getLogger().info("Open Notification");
		Assert.fail();
	}
}
public void clickontext(String text, String elementName) {
	AndroidElement element = (AndroidElement) driver.findElementByXPath("//android.widget.TextView[@text='"+text+"']");
	wLib.waitForElemnetToBeVisible(element);
	element.click();
	BaseTest.test.info(elementName + " is clicked");
	LogManager.getLogger().info(elementName + " is clicked");
}


public void scrolluptillelement( String scrollToText) {
    Dimension size = driver.manage().window().getSize();
    int startX = size.width / 2;
    int startY = (int) (size.height * 0.1);
    int endY = (int) (size.height * 0.5);

    while (driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + scrollToText + "\")")).size() == 0) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(startX, startY))
                   .waitAction()
                   .moveTo(PointOption.point(startX, endY))
                   .release()
                   .perform();
    }
    ReporterClass.ReportLoggerScreenshot(info, scrollToText + " is Scroll");
	LogManager.getLogger().info(scrollToText + " is Scroll");

}


public static String present(AndroidElement element) {

	String present = Boolean.toString(isDisplayed(element));
	return present;
}



public static void SwipetoModule()
{
	TouchAction touchAction = new TouchAction(driver);
    touchAction.press(PointOption.point(913, 342))
               .waitAction()
               .moveTo(PointOption.point(458, 357))
               .release()
               .perform();
}

/*
 * public void clickAfterScrolling(AndroidElement element, int scrollNumber,
 * String elementName) throws Exception { for (int i = 1; i <= scrollNumber;
 * i++) { try { element.isDisplayed(); LogManager.getLogger().
 * info("-------Mentioned Element is present--------\n \n");
 * 
 * element.click(); ReporterClass.ReportLoggerScreenshot(info, elementName +
 * " is clicked"); LogManager.getLogger().info(elementName + " is clicked");
 * break; } catch (Exception e) { util.swipe(426, 2126, 323, 164);
 * LogManager.getLogger().info("-------swipe is performed--------\n \n"); if
 * (isDisplayed(element)) { LogManager.getLogger().info("Click on " +
 * elementName); clickScreenshot(element); } else {
 * LogManager.getLogger().info("-------Again go to swipe operation--------\n \n"
 * ); }
 * 
 * }
 * 
 * } }
 */

public void clickAfterScrolling(AndroidElement element, int scrollNumber, String elementName) throws Exception {
	for (int i = 1; i <= scrollNumber; i++) {
		try {
			element.isDisplayed();
			LogManager.getLogger().info("-------Mentioned Element is present--------\n \n");

			clickScreenshot(element);
			ReporterClass.ReportLoggerScreenshot(info, elementName + " is clicked");
			LogManager.getLogger().info(elementName + " is clicked");
			break;
		} catch (Exception e) {
			util.swipe(300, 1400, 300, 300);
			LogManager.getLogger().info("-------swipe is performed--------\n \n");
			if (isDisplayed(element)) {
				LogManager.getLogger().info("Click on " + elementName);
				clickScreenshot(element);
			} else {
				LogManager.getLogger().info("-------Again go to swipe operation--------\n \n");
			}

		}

	}
}


public void ScrolltoelementForAddress(AndroidElement element, int scrollNumber, String elementName) throws InterruptedException {
//	AndroidDriver driver = BaseTest.driver;
	for(int i=1;i<scrollNumber;i++) {
		try {
			element.isDisplayed();
			System.out.println("Element is not present");
			ReporterClass.ReportLoggerScreenshot(info, elementName + " is Scroll");
			LogManager.getLogger().info(elementName + " is clicked");
			break;
		}
		catch (Exception e) {
			
			util.swipe(200, 1000, 200, 200);
			System.out.println("swipe is performed");
			LogManager.getLogger().info(elementName + " unable to Scroll");
//			Assert.fail();
		}
	}
}
}
