package utils;

public  class DefineIosDriver {
//		
//		public static IOSDriver iosddr = BaseTest.driver;
//	
//
//	public void minimizeApplication() {
//
//		System.out.println("before iosddr typecasting");
//		try {
//			iosddr.runAppInBackground(Duration.ofSeconds(-1));
//			iosddr.findElement(By.id("Safari"));
//		}catch (Exception e) {
//			try {
//				iosddr.runAppInBackground(Duration.ofSeconds(-1));
//				iosddr.findElement(By.id("Safari"));
//			}catch (Exception e2) {
//				iosddr.runAppInBackground(Duration.ofSeconds(-1));
//			}
//			
//		}
//		System.out.println("after iosddr typecasting");
//
//	
//	}
//	
//	public void takeScreenShot( String methName) {
//	
//		
//		File src= ((TakesScreenshot)iosddr).getScreenshotAs(OutputType.FILE);
//		try {
//			// now copy the  screenshot to desired location using copyFile method
//			File file = new File("/Users/uat_artp/Desktop/ScreenShot/SbiYono/"+ methName);
//			if (file.exists()) { 	
//				System.out.println("File exists");
//			}else 
//			{
//				System.out.println("File not exists");
//				file.mkdir();        	
//			}
//
//	
//			FileUtils.copyFile(src, new File("/Users/uat_artp/Desktop/ScreenShot/SbiYono/"+".png"));
//
//		}
//
//		catch (IOException e){ System.out.println(e.getMessage()); }
//
//
//	}
//	
//	public void quitApp() {
//		iosddr.quit();
//	}
//	
//	public void launchSMSapp() {
//		iosddr.activateApp("com.apple.MobileSMS");
//	}
//	
//	public void launchYonoLiteapp() {
//		iosddr.activateApp("com.sbi.sbfreedomplus.ios");
//	}
//	
}
