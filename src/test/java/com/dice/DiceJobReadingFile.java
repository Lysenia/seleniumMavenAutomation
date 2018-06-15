package com.dice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobReadingFile {
	
	public static List<String> keyWords = new ArrayList<>();


	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();

		try (FileReader fr = new FileReader("KeyWordsList.txt"); 
				BufferedReader br = new BufferedReader(fr);) {

			String value = null;
			while ((value = br.readLine()) != null) {
				keyWords.add(value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		for (int i = 0; i < keyWords.size(); i++) {
			WebDriver driver = new ChromeDriver();
			driver.manage().window().fullscreen();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

			String url = "https://dice.com";
			driver.get(url);
	
		
		driver.findElement(By.id("search-field-keyword")).clear();
		
		for(int j=0; i<keyWords.size();j++) {
		
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyWords.get(j));

		driver.findElement(By.id("findTechJobs")).click();
		
		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);

		int countResult = Integer.parseInt(count.replaceAll(",", ""));

		if (countResult > 0) {
			System.out.println("Keyword : " + keyWords.get(i) + " search returned : " + countResult);
		} else {
			System.out.println("Step FAIL : Keyword : " +  keyWords.get(i) + " search returned : " + countResult);
		}

		}
			driver.quit();
		}

		

	}
}


