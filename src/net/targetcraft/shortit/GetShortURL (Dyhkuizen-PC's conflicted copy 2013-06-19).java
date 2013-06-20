package net.targetcraft.shortit;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class GetShortURL {
	private static String URLFinal;
	public static String shortURL (String URLGet, Player player, String defaultURL, String urlcolour)throws UnknownHostException
	{
		
		Pattern p = Pattern.compile("(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’]))");
		Matcher m = p.matcher(URLGet);
		StringBuffer sb = new StringBuffer();
		if(player.hasPermission("shortit.autoshort"))
		{
			while (m.find())  
			 {  
				URLGet=m.group(1);
				if (URLGet.startsWith("http://")||URLGet.startsWith("www."))
					{
						try {
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
			                    	URLGet=(urlcolour+eElement.getTextContent()+ChatColor.RESET);//Finds the shortURL and makes the link green and then the stuff after it white
			                    	URLFinal=ChatColor.translateAlternateColorCodes('&', URLGet);
			                    }
			                    else
			                    {
			                    	
			                    }
				            }

					}
					
			           catch (IOException e) {
			        	
					}  catch (SAXException e) {
						
					} catch (ParserConfigurationException e) {
						e.printStackTrace();
					}
						
					}
				else
				{
					if (URLGet.startsWith("https://"))
					{
					    player.sendMessage(ChatColor.YELLOW+"[ShortIt] "+ChatColor.DARK_RED+"Sorry, HTTPS is not supported by YOURLS, and can cause MAJOR errors/lag for the server.");
					}
				}
					m.appendReplacement(sb, "");
					sb.append(URLFinal);
				 }
			}
		
		m.appendTail(sb);
		return sb.toString();
			
	}
	public static String blockedURL(String URLGet, Player player, String defaultURL)
	{
		Pattern p = Pattern.compile("(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’]))");
		Matcher m = p.matcher(URLGet);
		StringBuffer sb = new StringBuffer();
		if(player.hasPermission("shortit.autoshort"))
		{
			while (m.find())  
			 {  
				URLGet=m.group(1);
				if (URLGet.startsWith("http://")||URLGet.startsWith("www."))
					{
					    URLGet=ChatColor.RED+"[URL Redacted]";
					}
			}
			m.appendReplacement(sb, "");
			sb.append(URLGet);
			
		}
		m.appendTail(sb);
		return sb.toString();
		
	}

}
