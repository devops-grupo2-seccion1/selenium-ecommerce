package selenium;
import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.concurrent.TimeUnit;
import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.support.ui.Select;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static final String URL = "http://automationpractice.com/index.php";
    private static final String MAIL = "xxxqw@xxx.xxx";
    private static final String PASS = "12345";

    private WebDriver driver;
    String randomEmail;

    @Before
    public void setUp()
    {
        System.out.println("Iniciando configuración...");
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get(URL);
		driver.manage().window().maximize();
        System.out.println("Iniciando Pruebas...");
    }

    @After
    public void tearDown() 
    {
      driver.quit();
    }

    @Test
    public void shopWithCreateUser() {
        randomEmail = String.format("%s@mail.com", RandomString.make(15)).toLowerCase();
        String name="José";
        String lastName="Lopez";
        String company="Karibu";
        String address1="San Francisco";
        String address2="467";
        String city="Santiago";
        String phone="12345679";
        String phone_mobile="12345678";
        String postcode = "12345";
        String info = "Additional information";

        driver.findElement(By.cssSelector(".product_img_link:first-child")).click();
        driver.findElement(By.cssSelector(".exclusive:first-child")).click();
        driver.findElement(By.cssSelector(".exclusive > span")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector(".button-medium > span")).click();
        driver.findElement(By.cssSelector(".standard-checkout > span")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("email_create")).sendKeys(randomEmail);
		driver.findElement(By.id("SubmitCreate")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys(name);
		driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
		String email = driver.findElement(By.id("email")).getAttribute("value");
		assertEquals(randomEmail, email);
        driver.findElement(By.id("passwd")).sendKeys(PASS);
        new Select(driver.findElement(By.id("days"))).selectByValue("4");
		new Select(driver.findElement(By.id("months"))).selectByValue("9");
		new Select(driver.findElement(By.id("years"))).selectByValue("1985");
        driver.findElement(By.id("firstname")).clear();
		driver.findElement(By.id("firstname")).sendKeys(name);
		driver.findElement(By.id("lastname")).clear();
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("company")).sendKeys(company);
		driver.findElement(By.id("address1")).sendKeys(address1);
		driver.findElement(By.id("address2")).sendKeys(address2);
		driver.findElement(By.id("city")).sendKeys(city);
        new Select(driver.findElement(By.id("id_state"))).selectByValue("1");
        driver.findElement(By.id("postcode")).sendKeys(postcode);
		driver.findElement(By.id("other")).sendKeys(info);
		driver.findElement(By.id("phone")).sendKeys(phone);
		driver.findElement(By.id("phone_mobile")).sendKeys(phone_mobile);
        driver.findElement(By.id("submitAccount")).click();
        driver.findElement(By.name("processAddress")).click();
		driver.findElement(By.id("cgv")).click();
		driver.findElement(By.name("processCarrier")).click();
		driver.findElement(By.cssSelector(".bankwire")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'I confirm my order')]")).click();
    }

    @Test
    public void shopWithUser() {
        driver.findElement(By.cssSelector(".product_img_link:first-child")).click();
        driver.findElement(By.cssSelector(".exclusive:first-child")).click();
        driver.findElement(By.cssSelector(".exclusive > span")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector(".button-medium > span")).click();
        driver.findElement(By.cssSelector(".standard-checkout > span")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("email")).sendKeys(MAIL);
		driver.findElement(By.id("passwd")).sendKeys(PASS);
		driver.findElement(By.id("SubmitLogin")).click();
        driver.findElement(By.name("processAddress")).click();
		driver.findElement(By.id("cgv")).click();
		driver.findElement(By.name("processCarrier")).click();
		driver.findElement(By.cssSelector(".bankwire")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'I confirm my order')]")).click();
    }
}
