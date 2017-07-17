package selenium_cucumber;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class JUnitTest1 {
	private static final String PATH_TO_EXE_FINAL = "J:\\chromedriver.exe";// Specify path to chromedriver.exe
	private static ChromeDriver driver;
	private static Test1 test1;
	
	
	@BeforeClass
	public static void openBrowser() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", PATH_TO_EXE_FINAL);
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("disable-extensions");
		opt.addArguments("--start-maximized");
		ChromeDriver driver = new ChromeDriver(opt);
		driver.get("http://yandex.ru");
		
		test1 = new Test1(driver);
	}

	@Test
	public void testLaptopAmount() {
		int laptopsAmount = test1.getLaptopsAmonut(30000);
		Assert.assertEquals(12, laptopsAmount);
	}
	
	@Test
	public void testCorrectLaptopNames() {
		boolean areThereTheBrands = test1.areThereTheBrands("Lenovo", "HP");
		Assert.assertEquals(true, areThereTheBrands);
	}



	
	@AfterClass
	public static void closeBrowser() {

		driver.quit();
	}
}

