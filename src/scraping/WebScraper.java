package scraping;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScraper {
	
	private Document doc;

	public WebScraper(String input) {
		try {
			doc = Jsoup.connect("https://www.gps-coordinates.net/").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36").header("Accept-Language", "*").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Element search = doc.getElementById("address");
		Element button = doc.getElementsByClass("btn btn-primary").first();
		
		search.val(input);
		
		System.out.println(search.val());
	}

}
