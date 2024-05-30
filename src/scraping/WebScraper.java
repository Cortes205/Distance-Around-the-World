package scraping;

import main.components.Map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebScraper implements ActionListener {
	
	private Map map;
	
	private Timer waitForSite;
	
	private WebDriver page;
	private WebElement searchField;
	private WebElement button;
	private WebElement latitudeField;
	private WebElement longitudeField;
	
	private double latitude;
	private double longitude;
	private String title;
	
	public WebScraper(Map map, String input) {
		this.map = map;

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless=new");
		options.addArguments("--deny-permission-prompts");
		options.addArguments("--disable-popup-blocking");
		page = new ChromeDriver(options);
		page.get("https://www.gps-coordinates.net/");
		
		searchField = page.findElement(By.id("address"));
		searchField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		searchField.clear();
		searchField.sendKeys(input);
		
		button = page.findElement(By.cssSelector(".btn.btn-primary"));
		button.click();
		
		// Wait for site to update after button click
		waitForSite = new Timer(0, this);
		waitForSite.setInitialDelay(1000);
		waitForSite.setRepeats(false);
		waitForSite.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == waitForSite) {
			latitudeField = page.findElement(By.id("latitude"));
			latitude = Double.parseDouble(latitudeField.getAttribute("value"));
			
			longitudeField = page.findElement(By.id("longitude"));
			longitude = Double.parseDouble(longitudeField.getAttribute("value"));
						
			map.setWaypoint(latitude, longitude);
			title = searchField.getAttribute("value");
			
			page.quit();
		}
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public String getTitle() {
		return title;
	}

}
