package selenium_cucumber;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Test1 {

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

	public Test1(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

		navigateToLaptopsPage();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	public int getLaptopsAmonut(int upper_price) {
		// Set HP & Lenovo
		set_hp_lenovo();

		// Set upper_price
		upper_price_text_field.sendKeys(Integer.toString(upper_price));

		System.out.println(laptop_names);

		return laptop_names.size();
	}

	private void set_hp_lenovo() {
		hp_checkbox.sendKeys(Keys.SPACE);
		lenovo_checkbox.sendKeys(Keys.SPACE);
	}

	public boolean areThereTheBrands(String brand1, String brand2) {

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<WebElement> names = driver.findElements(By.ByClassName.className("snippet-card__header-text"));

		for (WebElement laptop_name : names) {
			String lp_name = laptop_name.getText().toLowerCase();

			// we return false if lp_name doesn't contain neither band1 or
			// brand2
			if (!(lp_name.contains(brand1.toLowerCase()) | lp_name.contains(brand2.toLowerCase()))) {
				return false;
			}
		}

		return true;
	}

	private void navigateToLaptopsPage() {
		market_reference.click();
		computers_reference.click();
		laptops_reference.click();
	}
}
