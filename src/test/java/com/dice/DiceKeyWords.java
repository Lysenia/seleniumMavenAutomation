package com.dice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DiceKeyWords {
	
	public static void main(String[] args) {
		
		String[] list = { "Scrum Master", "Java Developer", "Senior Business Analyst", "Senior Software Engineer",
				"Software Engineer", "System Administrator", "Selenium Automation Engineer",
				"Selenium Mobile Web service Tester", "Selenium WebDriver", "Ruby Developer", "RF Engineer",
				"Test Engineer", "Computer Operator", "Designer", "Linux Administrator", "Business Analyst",
				"Marketing Manager", "Network Administrator", "Network Engineer", "Business Analyst Project Manager" };

		List<String> keyWords = new ArrayList<>();
		for (int i = 0; i < list.length; i++) {
			keyWords.add(list[i]);
		}
		
		String[] zips = { "20783", "20930", "32004", "60001", "23455", "10001", "43001", "73301", "20040", "98001",
				"15001", "13010", "22306", "30303", "55001", "85001", "66002", "46001", "96601", "35004" };

		List<String> zipCode = new ArrayList<>();
		for (int i = 0; i < list.length; i++) {
			zipCode.add(zips[i]);
		}

		List<String> jobCount = new ArrayList<>();

		for (int i = 0; i < zipCode.size(); i++) {
			WebDriver driver = new ChromeDriver();
			checkKeyword(driver, keyWords.get(i), zipCode.get(i));
			jobCount.add(checkCount(driver, keyWords.get(i), zipCode.get(i)));
			driver.close();
		}
		System.out.println(jobCount);

	}

	public static void checkKeyword(WebDriver driver2, String key, String zip) {

		driver2.manage().window().fullscreen();
		driver2.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		String url = "https://dice.com";
		driver2.get(url);

		String actualTitle = driver2.getTitle();
		String expectedTitle = "Job Search for Technology Professionals | Dice.com";

		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Step PASS. Dice homepage successfully loaded");
		} else {
			System.out.println("Step FAIL. Dice homepage did not load successfully");
			throw new RuntimeException("Step FAIL. Dice homepage did not load successfully");
		}

		String keyword = key;
		driver2.findElement(By.id("search-field-keyword")).clear();
		driver2.findElement(By.id("search-field-keyword")).sendKeys(keyword);

		String location = zip;
		driver2.findElement(By.id("search-field-location")).clear();
		driver2.findElement(By.id("search-field-location")).sendKeys(location);

		driver2.findElement(By.id("findTechJobs")).click();

	}

	public static String checkCount(WebDriver driver2, String key, String zip) {
		String count = driver2.findElement(By.id("posiCountId")).getText();
		System.out.println(count);

		int countResult = Integer.parseInt(count.replaceAll(",", ""));

		if (countResult > 0) {
			return "Keyword : " + key + " search returned : " + countResult + " results in " + zip;
		} else {
			return "Step FAIL : Keyword : " + key + " search returned : " + countResult + " results in " + zip;
		}

	}



		
	}

