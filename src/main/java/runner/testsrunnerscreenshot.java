package runner;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

import Base.BaseTest;
import io.appium.java_client.android.AndroidElement;

public class testsrunnerscreenshot extends BaseTest {
	@Test
	public void test() {
		try {
			Thread.sleep(10000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File screenshotFile = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);

		// Add point on the element in the screenshot
		// You can use libraries like AWT or OpenCV to manipulate the image and draw the point.
		// Below is a simple example using AWT.
		try {
		    // Read the screenshot image from the captured file
		    BufferedImage screenshotImage = javax.imageio.ImageIO.read(screenshotFile);
		    Graphics2D graphics = screenshotImage.createGraphics();

		    AndroidElement element = driver.findElementById("com.threedi.ohioNFERs.qa:id/btnOhIdLogin");
			// Get the coordinates of the element to add the point
		    int x = element.getLocation().getX();
            int y = element.getLocation().getY();
            int width = element.getSize().getWidth();
            int height = element.getSize().getHeight();

            // Set the border color and draw the rectangle around the element
            graphics.setColor(Color.RED); // You can set the color of the border
            graphics.setStroke(new BasicStroke(10)); // Set the border width
            graphics.drawRect(x, y, width, height);

		    // Save the edited screenshot
		    javax.imageio.ImageIO.write(screenshotImage, "png", screenshotFile);
		    File editedScreenshotPath = new File( screenshotFile.getAbsolutePath());
		    System.out.println("Edited Screenshot Path: " + editedScreenshotPath);
		    File Destination = new File("D:\\OhioNFIRSAndroid\\Extent Report\\screen.png");
			FileUtils.copyFile(editedScreenshotPath, Destination );
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

}
