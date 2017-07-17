package selenium_cucumber;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Test2 {

	private WebDriver driver;

	/**
	 * Market Reference
	 */
	@FindBy(linkText = "Маркет")
	private WebElement market_reference;

	/**
	 * Computers Reference
	 */
	@FindBy(linkText = "Компьютеры")
	private WebElement computers_reference;

	/**
	 * Laptops Reference
	 */
	@FindBy(linkText = "Ноутбуки")
	private WebElement laptops_reference;

	/**
	 * Lower Price
	 */
	@FindBy(name = "glf-pricefrom-var")
	private WebElement lower_price_text_field;

	/**
	 * Upper Price
	 */
	@FindBy(name = "glf-priceto-var")
	private WebElement upper_price_text_field;

	/**
	 * HP Model CheckBox
	 */
	@FindBy(id = "glf-7893318-152722")
	private WebElement hp_checkbox;

	/**
	 * LENOVO Model CheckBox
	 */
	@FindBy(id = "glf-7893318-152981")
	private WebElement lenovo_checkbox;

	/**
	 * Acer Model CheckBox
	 */
	@FindBy(id = "glf-7893318-267101")
	private WebElement acer_checkbox;

	/**
	 * Dell Model CheckBox
	 */
	@FindBy(id = "glf-7893318-153080")
	private WebElement dell_checkbox;

	/**
	 * laptop_names
	 */
	@FindBy(className = "snippet-card__header-text")
	private List<WebElement> laptop_names;

	/**
	 * laptop_prices
	 */
	@FindBy(className = "price")
	private List<WebElement> laptop_prices;

	public Test2(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

		navigateToLaptopsPage();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	public int getLaptopsAmonut(int upper_price) {
		// Set Acer & Dell
		set_acer_dell();

		// Set upper_price
		upper_price_text_field.sendKeys(Integer.toString(upper_price));

		System.out.println(laptop_names);

		return laptop_names.size();
	}

	private void set_acer_dell() {
		acer_checkbox.sendKeys(Keys.SPACE);
		dell_checkbox.sendKeys(Keys.SPACE);
	}

	private void navigateToLaptopsPage() {
		market_reference.click();
		computers_reference.click();
		laptops_reference.click();
	}

	public boolean areAllThepricesInTheRange(int lower_price, int upper_price) {
		
		//Refresh page
		navigateToLaptopsPage();
		
		// Set Acer & Dell
		set_acer_dell();

		// Set lower
		lower_price_text_field.sendKeys(Integer.toString(lower_price));
		// Set upper_price
		upper_price_text_field.sendKeys(Integer.toString(upper_price));

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<WebElement> prices = driver.findElements(By.ByClassName.className("price"));

		for (WebElement web_price : prices) {

			// Text value of price like 'от 20 140 руб.'
			String str_price = web_price.getText();

			if (str_price.contains("от")) {
				int price = getIntPrice(str_price);

				if ((price < lower_price) | (price > upper_price)) {
					return false;
				}
			}
		}

		return true;
	}

	private int getIntPrice(String str) {
		String[] arr = str.split(" ");
		int price = Integer.parseInt(arr[1].concat(arr[2]));

		return price;
	}

}
