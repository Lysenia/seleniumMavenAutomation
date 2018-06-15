package com.dice;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {
	
	public static void main(String[] args) {
		
//		set up Driver
System.setProperty("webdriver.chrome.driver","/Users/lesia/Documents/selenium dependencies/drivers/chromedriver");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.dice.com/");
		
//		set up Chrome + full screen + wait time
		
		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
//	Step 1: Launch browser and navigate to https:dice.com/
//		Expected: dice home page should be displayed	
		
		
		String Url = "https://www.dice.com/";
		String keyWord = "java developer";
		String actualTitle = driver.getTitle();
		String expected = "Job Search for Technology Professionals | Dice.com ";
		String location = "20783";
		
		if(expected.equals(actualTitle)) {
			System.out.println("Step PASS. Dice homepage successfully loaded");
		}
		else {
			System.out.println("Step FAIL. Dice homepage was not successfully loaded");
			throw new RuntimeException("Step FAIL. Dice homepage was not successfully loaded");
		}
		
		
		
		driver.get(Url);
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyWord);
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		driver.findElement(By.id("findTechJobs")).click();
		
		
		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);
		
		int countResult = Integer.parseInt(count.replaceAll(",", ""));
		
		if(countResult>0) {
			System.out.println("Step PASS: KeyWord " + keyWord );
		}else {
				System.out.println("Step FAIL: KeyWord " + keyWord );
			}
		
		System.out.println("TEST COMPLETED: " + LocalDateTime.now());
		driver.close();
	
	}
}
