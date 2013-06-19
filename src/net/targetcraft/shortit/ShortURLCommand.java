package net.targetcraft.shortit;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ShortURLCommand {
	public static void getLink(String URLGet, CommandSender sender, String defaultURL, List<String>userBlacklist) throws UnknownHostException
	{
		
		Pattern p = Pattern.compile("(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’]))");
		Matcher m = p.matcher(URLGet);
		StringBuffer sb = new StringBuffer();  
		while (m.find())  
		{  
		    URLGet=m.group(1);
		
		    if (URLGet.startsWith("www.")||(URLGet.startsWith("http://")))
		     {
			    try {
			    	boolean onBlackList=false;
			    	String[] blacklistArray = userBlacklist.toArray(new String[0]);
			    	
			    	if(sender.equals(blacklistArray))
			    	{
			    		onBlackList=true;
			    	}
			    	if(!sender.equals(blacklistArray))
			    	{
			    		onBlackList=false;
			    	}
			    	
			    	if (onBlackList==true)
			    	{
			    		sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"Error. Someone has put you on the Blacklist. You are not able to shorten URLs until you are unbanned");
			    	}
			    	if (onBlackList==false)
			    	{
			    	
				   String URL=defaultURL+URLGet;
				   DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	               DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	    		   Document doc = docBuilder.parse(new InputSource(new URL(URL).openStream()));
	               doc.getDocumentElement().normalize();
	               NodeList nodeList = doc.getElementsByTagName("shorturl");
			    
	                 for (int temp = 0; temp < nodeList.getLength(); temp++) {

	                   Node nNode = nodeList.item(temp);
	                
	                   Element eElement = (Element) nNode;
                         if(eElement.getAttribute("shorturl") != null)
                           {
                    	      sender.sendMessage(ChatColor.AQUA+"[ShortIt] Your shortened URL is: "+ChatColor.DARK_GREEN+eElement.getTextContent());

                           }
                         else
                          {
                        	 sender.sendMessage(ChatColor.DARK_RED+"[ShortIt] An error occured. Please make sure you entered in the URL correctly. begr.im URL Shortener might also be down");
                          }
	            }

		}
			    }
		
           catch (IOException e) {
        	   sender.sendMessage(ChatColor.DARK_RED+"[ShortIt] An error occured. Please make sure you entered in the URL correctly. begr.im URL Shortener might also be down");
		}  catch (SAXException e) {
			sender.sendMessage(ChatColor.DARK_RED+"[ShortIt] An error occured. Please make sure you entered in the URL correctly. begr.im URL Shortener might also be down");
		} catch (ParserConfigurationException e) {
			sender.sendMessage(ChatColor.DARK_RED+"[ShortIt] An error occured. Please make sure you entered in the URL correctly. begr.im URL Shortener might also be down");
		}
	}
		else 
		{
			if (URLGet.startsWith("https://"))
			{
			    sender.sendMessage(ChatColor.YELLOW+"[ShortIt] "+ChatColor.DARK_RED+"Sorry, HTTPS is not supported by YOURLS, and can cause MAJOR errors/lag for the server. Edit your URL so that it does not include the S");
			}
			else
			{
				sender.sendMessage(ChatColor.RED+"[ShortIt] I did not recognize the protocol that you entered. I only support http:// or www. Please try again");
			}
		}
		    m.appendReplacement(sb, "");
			sb.append(URLGet);
		 }
		m.appendTail(sb);
}
	
}
