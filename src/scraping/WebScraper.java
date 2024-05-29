package scraping;

import main.components.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebScraper {
	
	private Map map;
	
	private WebDriver page;
	
	public WebScraper(Map map, String input) {
		this.map = map;

		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--headless=new");
		options.addArguments("--deny-permission-prompts");
		options.addArguments("--disable-popup-blocking");
		page = new ChromeDriver(options);
		page.get("https://www.gps-coordinates.net/");
		
		WebElement search = page.findElement(By.id("address"));
		search.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		search.clear();
		search.sendKeys(input);
		
		WebElement button = page.findElement(By.cssSelector(".btn.btn-primary"));
		button.click();
		
		WebElement latitudeField = page.findElement(By.id("latitude"));
		System.out.println(latitudeField.getAttribute("value"));
		
//		double latitude = Double.parseDouble(latitudeField.getText());
//		
//		WebElement longitudeField = page.findElement(By.id("latitude"));
//		double longitude = Double.parseDouble(longitudeField.getText());
		
//		System.out.println(latitude + " " + longitude);
		
//		map.setWaypoint(latitude, longitude);
	}

}
