package com.lws.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.lws.utilities.ExcelReader;
import com.lws.utilities.ExtentManager;
import com.lws.utilities.Utilities;

public class Page {
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static Logger log = LogManager.getLogger();
	FileInputStream fisConfig = null, fisOR = null, fislog = null;
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports reports = ExtentManager.getInstance();
	public static ExtentTest test;
	static WebElement dropDown;
	public static String browser;
	public static TopMenu menu;

	public Page() {
		if (driver == null) {

			try {
				fisConfig = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
				fisOR = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
				fislog = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\log4j2.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				config.load(fisConfig);
				log.debug("Config file loaded !!!");
				OR.load(fisOR);
				log.debug("OR file loaded !!!");
				Configurator.initialize(null,
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\log4j2.properties");
				log.debug("Log4j2 configuration loaded !!!");
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Jenkins browser filter configuration
//
//			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
//				browser = System.getenv("browser");
//			} else {
//				browser = System.getProperty(browser);
//			}
//
//			config.setProperty("browser", browser);
			String excelBrowser = excel.getCellData("Configuration", "Browser", 2); // Assuming the sheet is
			// 'Configuration', column is
			// 'Browser', and row 2
			if (excelBrowser != null && !excelBrowser.isEmpty()) {
				browser = excelBrowser;
			} else {
				browser = config.getProperty("browser");
			}

			log.debug("Browser selected: " + browser);

			if (browser.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
				log.debug("Firefox launched");
			} else if (browser.equalsIgnoreCase("chrome")) {
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
				options.addArguments("--disable-extensions", "--disable-infobars");
				driver = new ChromeDriver(options);
				log.debug("Chrome launched");
			} else if (browser.equalsIgnoreCase("edge")) {
				driver=new EdgeDriver();
				driver = new EdgeDriver();
				log.debug("Edge launched");
			}

			Integer implicitWaitTimeInSeconds = Integer.parseInt(config.getProperty("implicit.wait"));
			Integer explicitWaitTimeInSeconds = Integer.parseInt(config.getProperty("explicit.wait"));

			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTimeInSeconds));
			wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTimeInSeconds));

			menu = new TopMenu(driver);
		}
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			log.debug("element found");
			return true;
		} catch (NoSuchElementException e) {
			log.debug("element not found");
			return false;
		}
	}

	public void clicking(String locator) {
		if (locator.endsWith("_css")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_xpath")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
			test.log(Status.INFO, "Clicking on: " + locator);
		} else if (locator.endsWith("_class")) {
			driver.findElement(By.className(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_id")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		} else {
			driver.findElement(By.linkText(OR.getProperty(locator))).click();
		}
	}

	public void typing(String locator, String value) {
		if (locator.endsWith("_css")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
			test.log(Status.INFO, "Typing on: " + locator + " Entered Value is: " + value);
		} else if (locator.endsWith("_xpath")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
			test.log(Status.INFO, "Typing on: " + locator + " Entered Value is: " + value);
		} else if (locator.endsWith("_class")) {
			driver.findElement(By.className(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_id")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		} else {
			driver.findElement(By.linkText(OR.getProperty(locator))).sendKeys(value);
		}
	}

	public void select(String locator, String value) {
		if (locator.endsWith("_css")) {
			dropDown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
			test.log(Status.INFO, "Typing on: " + locator + " Entered Value is: " + value);
		} else if (locator.endsWith("_xpath")) {
			dropDown = driver.findElement(By.xpath(OR.getProperty(locator)));
			test.log(Status.INFO, "Typing on: " + locator + " Entered Value is: " + value);
		} else if (locator.endsWith("_class")) {
			dropDown = driver.findElement(By.className(OR.getProperty(locator)));
		} else if (locator.endsWith("_id")) {
			dropDown = driver.findElement(By.id(OR.getProperty(locator)));
		} else {
			dropDown = driver.findElement(By.linkText(OR.getProperty(locator)));
		}

		Select select = new Select(dropDown);
		select.selectByVisibleText(value);
		test.log(Status.INFO, "Selecting from dropdown " + locator + " Entered Value is: " + value);
	}

	public static void verifyEquals(String expected, String actual) throws IOException {
		try {
			Assert.assertEquals(expected, actual);
		} catch (Throwable t) {
			Utilities.CaptureScreenshot();
			Reporter.log("<br>" + "verification failure" + t.getMessage() + "</br>");
			Reporter.log("<a target=\"_blank\" href=\"" + Utilities.screenshotPath + "\"><img src=\""
					+ Utilities.screenshotPath + "\" height=200 width=200></img></a>");
			test.log(Status.FAIL, "Verification got Failed with exception: " + t.getMessage());
			test.fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(Utilities.screenshotPath).build());
		}

	}

	public static void quit() {
		driver.quit();
	}
}
