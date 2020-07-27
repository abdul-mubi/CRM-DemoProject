package com.php.qa.testcases;

import org.testng.annotations.Test;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.php.qa.base.BaseClass;
import com.php.qa.pages.HomePage;
import com.php.qa.pages.LoginPage;
import com.php.qa.pages.DealPage;
import com.php.qa.util.TestUtils;

public class HomePageTest extends BaseClass {
	
	LoginPage loginpage;
	HomePage homepage;
	DealPage dealpage;
	
	public HomePageTest() {
		
		super();
	}
	
	@BeforeMethod
	public void setup() {
		
		initialization();
		loginpage = new LoginPage();
		//dealpage =new DealPage();
		homepage = loginpage.login(prop.getProperty("USERNAME"), prop.getProperty("PASSWORD"));
		
	}
	
	@Test(priority = 1)
	public void HomePageTitleTest() throws Exception {
		
		String Title = driver.getTitle();
		Robot robot = new Robot();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rectangle = new Rectangle(screenSize);
		BufferedImage source =robot.createScreenCapture(rectangle);
		File destinationFile = new File("/Users/abdul/eclipse-workspace/DemoProject/reports/RobotImg.png");
		ImageIO.write(source, "png", destinationFile);
		Assert.assertEquals(Title, "CRMPRO");
		
	}
	
	@Test(priority = 2, enabled = false)
	public void ClickNewDealTest() {
		
		TestUtils.switchToFrame();
		dealpage = homepage.ClickNewDealLink();
	}
	
	@AfterMethod
	public void teardown(ITestResult result) {
		
        if(result.getStatus()==ITestResult.FAILURE) {
        	
			TestUtils.screenshot();
		}
        
		driver.quit();
	}

}
