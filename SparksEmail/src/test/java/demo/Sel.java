package demo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;



public class Sel 
{

	public static void main(String args[]) throws InterruptedException, IOException 
	{
		//imgDownload();
		
	}

	public static void emailSender() 
	{
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("testeryellow13@gmail.com", "lookatthestars"));
    	email.setSSL(true);
		try {
			email.setFrom("neha.tirumalasetti@gmail.com");
			email.setSubject("TestMail");
			email.setMsg("Look at the stars,\nLook how they shine for you,\nAnd all the things you do");
			email.addTo("adi18rox@gmail.com");//change sender email
			email.send();
			System.out.println("Email sent!");
		}
		catch (EmailException e) {
			e.printStackTrace();
		}
	 }
	
	
	
		
	public static void brokenLinks() throws InterruptedException
	{
		
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("http://www.newtours.demoaut.com/");
		// http://www.newtours.demoaut.com/
	    //  https://makemysushi.com/Recipes/how-to-make-california-sushi-rolls
		
			Thread.sleep(5000);
		
		
		List <WebElement> links = driver.findElements(By.tagName("a"));
		
		System.out.println("Number of Links = "+links.size());
			
		for(int i=0; i<links.size();i++)
		{
			WebElement el = links.get(i);
			String url = el.getAttribute("href");
			verifyLinkActive(url,i);
		}
	}
	
	
	public static void verifyLinkActive(String link,int i)
	{
		try 
		{
		URL url = new URL(link);
		
		HttpURLConnection httpConnect = (HttpURLConnection) url.openConnection();
		
		httpConnect.setConnectTimeout(3000);
		
		httpConnect.connect();
		
		if(httpConnect.getResponseCode()!=200)
		{
			System.out.println("Link "+(i+1)+" is broken");
			System.out.println(link+" - "+httpConnect.getResponseMessage());
		}
		
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
    }
	
	
	
/*	public static void imgDownload() throws IOException, InterruptedException
	{
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		
		String url = "https://www.google.com/search?safe=active&hl=en&biw=1280&bih=648&tbm=isch&sa=1&ei=l9wcXbaBCJXZz7sPh5iEuAk&q=dogs&oq=dogs&gs_l=img.3..0i67l2j0l8.9632.10563..10992...0.0..0.118.449.0j4......0....1..gws-wiz-img.......35i39.imo8aagI0Jk";
		
		driver.get(url);
		
		try
		{
			Thread.sleep(5000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		//List<WebElement> imgs = driver.findElements(By.tagName("img"));
		WebElement Image =driver.findElement(By.xpath("//img[@border='0']"));
		for(int i=0;i<10;i++)
		{
			WebElement el = imgs.get(i);
			String src = el.getAttribute("src");
			URL imgurl = new URL(src);
			
			BufferedImage save = ImageIO.read(imgurl);
			ImageIO.write(save, "png", new File("dog"+(i)+".png"));
			
			Thread.sleep(3000);
			
		}
		
	}*/
	
	
	
}
