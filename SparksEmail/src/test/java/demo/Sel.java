package demo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;



public class Sel 
{

	public static void main(String args[]) throws IOException 
	{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int ch = 1;
	    
	    do
	    {
		System.out.println("Choose between the following options:\n1. Send an email\n2. Find Broken links on a webpage");
	    ch = Integer.parseInt(br.readLine());
		
		switch(ch)
		{
			case 1:
				System.out.println("Enter recipients Full Email ID");
				String em = br.readLine();
				em.trim();
				System.out.println("Enter text to be sent");
				String text = br.readLine();
				text.trim();
				emailSender(em,text);
				break;
			case 2:
				System.out.println("Enter the url of the page to be checked");
				String url = br.readLine();
				url.trim();
				brokenLinks(url);
				break;
			default:
				System.out.println("Invalid choice");
				
		}
	    }while(ch>0&&ch<=2);
	}

	public static void emailSender(String em, String text) 
	{
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");//specifying which email service provider
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("testeryellow13@gmail.com", "lookatthestars"));//logging in
    	email.setSSL(true);
		try {
			email.setFrom("testeryellow13@gmail.com");//sender email
			email.setSubject("TestMail");//subject of mail
			email.setMsg(text);//body of mail
			email.addTo(em);//recipient address
			email.send();
			System.out.println("Email sent!");
		}
		catch (EmailException e) {
			e.printStackTrace();
		}
	 }
	
	
	
		
	public static void brokenLinks(String u)
	{
		
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get(u);
		
			try
			{
				Thread.sleep(5000);
			} catch (InterruptedException e)
			{
				
				e.printStackTrace();
			}
		
		
		List <WebElement> links = driver.findElements(By.tagName("a"));//finds all links on web page
		
		System.out.println("Number of Links = "+links.size());
			
		for(int i=0; i<links.size();i++)
		{
			WebElement el = links.get(i);
			String url = el.getAttribute("href");
			verifyLinkActive(url,i);
		}
		driver.close();
	}
	
	
	public static void verifyLinkActive(String link,int i)
	{
		try 
		{
		URL url = new URL(link);
		
		HttpURLConnection httpConnect = (HttpURLConnection) url.openConnection();
		
		httpConnect.setConnectTimeout(3000);
		
		httpConnect.connect();
		
		if(httpConnect.getResponseCode()!=200)//if response code is not 200, say its 404 the link is said to be broken
		{
			System.out.println("Link "+(i+1)+" is broken");
			System.out.println(link+" - "+httpConnect.getResponseMessage());
		}
		else
		{
			System.out.println("Link "+(i+1)+" is okay");
		}
		
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
    }
	
}
