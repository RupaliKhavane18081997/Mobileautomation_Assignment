package Base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import utils.FileUtility;
import utils.PropertyUtils;
import utils.Utilitycommon;
import utils.*;

public class BaseTest {

	public static FileUtility fLib = new FileUtility();
	public static String profilePassword = fLib.getPropertyData("profilePassword");
	public static String username = fLib.getPropertyData("username");
	public static String password = fLib.getPropertyData("password");
	public ExcelReader xls = new ExcelReader(ConstantsConfig.DATA_XLS_PATH);
	public SoftAssert softAssert = new SoftAssert();
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;
	public Utilitycommon util = new Utilitycommon();
	public static String otp=Utilitycommon.Otp1;
	public String currentRunningMethod;

	public ExtentReports report;
	public static ExtentTest test;
	public ExtentSparkReporter spark;
	public static String className;
	
	@BeforeSuite
	public void beforeSuite() {

	}

	@BeforeClass
	public void configBc(ITestContext context) throws Throwable {
		LogManager.getLogger().info("Runnig Before Class");
		className = this.getClass().getSimpleName();
		spark = new ExtentSparkReporter("./Extent Report/" +dateForReport()+"/"+ className + "/" + className+ dateTime() +".html");
		spark.config().setDocumentTitle("Ohio NFSR");
		spark.config().setReportName(className + " Automation Execution report");
		report = new ExtentReports();
		report.attachReporter(spark);
		//report.setSystemInfo("os", System.getProperty("os.name"));
		report.setSystemInfo("Platform Name", PropertyUtils.getTestConfiguration("platformName"));
		report.setSystemInfo("Device Version", PropertyUtils.getTestConfiguration("platformVersion"));
		report.setSystemInfo("Device Name", PropertyUtils.getTestConfiguration("deviceName"));

		capabilities();
		
		FileUtility.printSessionID(driver.getSessionId());
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 2);
		
	}
	
	@BeforeMethod
	public void configBm(ITestResult result) throws Throwable {
		
		
		test = report.createTest(result.getMethod().getMethodName());
		System.out.println("starting: " + result.getMethod().getMethodName());
		LogManager.getLogger().info("starting: " + result.getMethod().getMethodName());
	}
	
	@AfterMethod(alwaysRun = true)
	public void configAm(ITestResult result) throws Exception {
		
		
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getMethod().getMethodName(), MediaEntityBuilder
					.createScreenCaptureFromBase64String(((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64))
					.build());
			report.flush();
			Assert.fail("Test Case Fail");
			System.out.println("Test Case Fail");
		}

		if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getMethod().getMethodName());
		}

		if (result.getStatus() == ITestResult.SUCCESS) {
			Thread.sleep(1000);
			test.log(Status.PASS, "Test Case Passed", MediaEntityBuilder
					.createScreenCaptureFromBase64String(((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64))
					.build());
		}

		softAssert.assertAll();
		LogManager.getLogger().info(
				"---------------------------Execution of the Method is Finished--------------------------------------\n \n");
		LogManager.getLogger().info(
				"---------------------------report Flush Started--------------------------------------\n \n");
		report.flush();
		LogManager.getLogger().info(
				"---------------------------report Flush Finished--------------------------------------\n \n");
//		driver.quit();
		Thread.sleep(4000);
		System.out.println("quitting driver");
	}
	

	public static String getScreenshot(WebDriver driver) throws IOException {
		String dateName = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/Extent Report/" + className + "/Screenshot/" + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	public static AndroidDriver<AndroidElement> capabilities() throws IOException, InterruptedException {
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", PropertyUtils.getTestConfiguration("platformName"));
		cap.setCapability("platformVersion", PropertyUtils.getTestConfiguration("platformVersion"));
		cap.setCapability("appium:deviceName", PropertyUtils.getTestConfiguration("deviceName"));
		cap.setCapability("appium:udid", PropertyUtils.getTestConfiguration("udid"));
		cap.setCapability("appium:automationName", PropertyUtils.getTestConfiguration("automationName"));
		cap.setCapability("appium:appPackage", PropertyUtils.getTestConfiguration("appPackage"));
		cap.setCapability("appium:appActivity", PropertyUtils.getTestConfiguration("appActivity"));
		//cap.setCapability(className, false);
		//cap.setCapability("noReset", "false");
//		caps.setCapability("appTopLevelWindows", "handle");
		cap.setCapability("chromedriverExecutable",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
	   // cap.setCapability("applicationCacheEnabled", false);
		//cap.setCapability("chromedriverExecutable",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
		cap.setCapability("newCommandTimeout", 90000);
		//cap.setCapability("noReset", true); 
		cap.setCapability("clearSystemFiles", true);
		cap.setCapability("waitForIdleTimeout", 10);
		cap.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
		
		URL url = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver<AndroidElement>(url, cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}


	public static void getScreenshot(String s) throws IOException {
		File scrfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrfile, new File(System.getProperty("user.dir") + "\\" + s + ".png"));
	}


	public static void LogOut() {

	}


	private String dateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("_dd-MM-yyyy_HHmmss");  
	    LocalDateTime now = LocalDateTime.now();  
	    return dtf.format(now);
	}
	
	public String dateForReport() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
	    LocalDateTime now = LocalDateTime.now();  
	    return dtf.format(now);
	}
	
	public void fetchOTP() throws InterruptedException, IOException {
		
		Thread.sleep(3000);
		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("platformName", PropertyUtils.getTestConfiguration("platformName"));
			cap.setCapability("platformVersion", PropertyUtils.getTestConfiguration("platformVersion"));
			cap.setCapability("deviceName", PropertyUtils.getTestConfiguration("deviceName"));
			cap.setCapability("udid", PropertyUtils.getTestConfiguration("udid"));
			cap.setCapability("browserName", "Chrome");
			cap.setCapability("chromedriverExecutable","C:\\Users\\Administrator\\Desktop\\Raj\\ChromeDriver\\chromedriver.exe");
			URL url;
			url = new URL("http://0.0.0.0:4723/wd/hub");
			driver = new AndroidDriver<AndroidElement>(url, cap);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//			driver.activateApp("com.android.chrome");
			Thread.sleep(2000);
			driver.get("https://muat.intouch.onlinesbi.com/mfp/api/adapters/UtilsAdapter/getOtp/UAT/7400426831");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		


	}

}
