package selenium_cucumber;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class JUnitTest2 {
	private static final String PATH_TO_EXE_FINAL = "J:\\chromedriver.exe";// Specify path to chromedriver.exe
	private static ChromeDriver driver;
	private static Test2 test2;
	
	
	@BeforeClass
	public static void openBrowser() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", PATH_TO_EXE_FINAL);
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("disable-extensions");
		opt.addArguments("--start-maximized");
		ChromeDriver driver = new ChromeDriver(opt);
		driver.get("http://yandex.ru");
		
		test2 = new Test2(driver);
	}

	@Test
	public void testLaptopAmount() {
		int laptopsAmount = test2.getLaptopsAmonut(30000);
		Assert.assertEquals(12, laptopsAmount);
	}
	
	@Test
	public void TestPriceRange() {
		boolean areAllThepricesInTheRange = test2.areAllThepricesInTheRange(20000, 25000);
		Assert.assertEquals(true, areAllThepricesInTheRange);
	}

	
	@AfterClass
	public static void closeBrowser() {

		driver.quit();
	}
}

