package steps;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Optional;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefinitions {

	//private final String HOSTNAME = Optional.ofNullable(System.getenv("CONTAINER_IP")).orElse("localhost");
	//private final String HOSTNAME = System.getenv().getOrDefault("CONTAINER_IP", "localhost");
	private final String APP_URL = System.getenv().getOrDefault("APP_URL", "http://localhost:4200/petclinic");

	private WebDriver driver;

	@Given("I visit the HOMEPAGE")
	public void i_visit_the_HOMEPAGE() throws MalformedURLException {

		if (APP_URL.contains("localhost")) {
			// Local
			//WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver-win64/chromedriver.exe");
			driver = new ChromeDriver();

		} else {
			// Remote
			ChromeOptions chromeOptions = new ChromeOptions();
			driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), chromeOptions);
		}
		//driver.get("http://" + HOSTNAME + ":4200/petclinic");
		driver.get(APP_URL);
	}

	@When("I click on the OWNERS option")
	public void i_click_on_the_OWNERS_option() {
		driver.findElement(By.xpath("/html/body/app-root/div[1]/nav/div/ul/li[2]/a")).click();

	}

	@When("I click on the ALL option")
	public void i_click_on_the_ALL_option() {
		driver.findElement(By.xpath("/html/body/app-root/div[1]/nav/div/ul/li[2]/ul/li[1]/a")).click();
	}

	@Then("I should see a list of all owners is displayed")
	public void i_should_see_a_list_of_all_owners_is_displayed() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Assert.assertEquals(
				driver.findElements(By.xpath("/html/body/app-root/app-owner-list/div/div/div/table/tbody")).size(), 1);
	}

	@When("I click on the ADD NEW option")
	public void i_click_on_the_ADD_NEW_option() {
		driver.findElement(By.xpath("/html/body/app-root/div[1]/nav/div/ul/li[2]/ul/li[2]/a")).click();
	}

	@When("I fill in {string} with {string}")
	public void i_fill_in_with(String property, String value) {
		driver.findElement(By.id(property)).sendKeys(value);
	}

	@When("I press Add Owner")
	public void i_press_Add_Owner() {
		driver.findElement(By.xpath("/html/body/app-root/app-owner-add/div/div/form/div[7]/div/button[2]")).click();
	}

	@After()
	public void closeBrowser() {
		driver.quit();
	}

}