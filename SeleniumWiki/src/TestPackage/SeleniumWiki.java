package TestPackage;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;



public class SeleniumWiki {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Jar\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://en.wikipedia.org/wiki/Selenium");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		
		String Expected = "External links";
		String Actual = driver.findElement(By.xpath("//span[@class='toctext'][contains(text(),'External links')]")).getText();
		
		
		if (Actual.equals(Expected))
		{
			System.out.println("External Links section present in section work");
		}
		else
		{
			System.out.println("External Links not present in the Page");
		}
		
		driver.findElement(By.xpath("//div[@class='navbox']//tr[3]//td[7]//a[1]//span[1]")).click();
		Thread.sleep(3000);
		
		String Title = driver.getTitle();
		
		Boolean featured = driver.findElement(By.xpath("//div[@id='mw-indicator-featured-star']//a//img")).isDisplayed();
		if (featured==true){
			System.out.println(Title + "page is featured article");
		}
		else {
			System.out.println(Title + "Page is not a featured article");
		} 
		
		
		
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);		
		FileHandler.copy(screenshotFile, new File("C:\\Users\\PavanAshrit\\Desktop\\Selenium_Proj\\Testing\\test.png"));
		
		List<WebElement> Links = driver.findElements(By.xpath("//a[@href]"));
		
		
		System.out.println("Totla no.of links in page "+Links.size());
		Iterator<WebElement> it = Links.iterator();
		
		
		int count = 0;
		  for(int i=0;i<=Links.size();i++) 
		  { 
			  while (it.hasNext()) 
			  { 
				  String url = it.next().getAttribute("href");
				  
			
			  String extension = url.substring(url.lastIndexOf("."));
			  
			
			  String string1 = new String(extension);
			  String string2 = new String(".pdf");
			if (string1.equals(string2))
			{
				//System.out.println(extension);
				count = count + 1;
				
			}
			  }
		  }
		  
		 System.out.println("Final the number of pdf links is:"+count);
		
		 WebElement element = driver.findElement(By.xpath("//input[@id='searchInput']"));
		 
		 element.sendKeys("pluto");	 
		 Thread.sleep(3000);
		 //Took xpath of the second element in search
		 String Actual1 = driver.findElement(By.xpath("/html[1]/body[1]/div[7]/div[1]/a[2]")).getText();
		 String Actual2 = new String(Actual1);
		 
		String Expected2 = new String("Plutonium");
		 
		 if(Actual2.equals(Expected2))
		 {
			 System.out.println("Plutonium is second element");
		 }	
		 
		 driver.close();

	}

}
