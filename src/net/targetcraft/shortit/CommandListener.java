package net.targetcraft.shortit;

import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

public class CommandListener implements Listener, CommandExecutor {
	Logger log;
	MainClass plugin;
	
	public CommandListener(MainClass config) {
		plugin = config;
		}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) throws CommandException {        
	        try 
	        {        	
	        	
	    	if (sender.hasPermission("shortit.getlink"))
	        {
	        	 if(cmd.getName().equalsIgnoreCase("link"))
	             {
	        		 if (args[0].equalsIgnoreCase("about"))
	        		 {
						AboutCommand.sendAbout(sender);
	        		 }
	        		 else if (args[0].equalsIgnoreCase(""))
	        		 {
	        			 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"Invalid Command Usage. Correct usage:");
	        			 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"/link [url to shorten]");
	        			 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"/link about");
	        		 }
	        		 else if (args[0].equalsIgnoreCase("help"))
	        		 {
	        			 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"To turn a URL into a short URL, just type it in the chat.");
	        			 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"Other commands:");
	        			 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"/link [url to shorten] Note: This only shows the link to you");
	        			 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"/link about");
	        		 }
	        		 else if (args[0].equalsIgnoreCase("reload"))
	        		 {
	        			 if(sender.hasPermission("shortit.admin"))
	        			 {
	        				 plugin.saveConfig();
	        				 plugin.reloadConfig();
		        			 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.GREEN+"Reload Complete");
	        			 }
	        			 else
	        			 {
	        				 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.DARK_RED+"You do not have permission to reload the config");
	        			 }
	        			 
	        		 }
	        		 else
	        		 {
	        			 try {
	        				 String defaultURL;
	        				 defaultURL=plugin.getConfig().getString("api-link");
	        				 if (plugin.getConfig().getBoolean("blacklist-boolean")==true&&plugin.getConfig().getBoolean("whitelist-boolean")==false)
	        				 {
	        					 List<String> blacklist =plugin.getConfig().getStringList("blacklist");
	        					 BlkListGetLink.getLink(args[0], sender, defaultURL, blacklist);
	        				 }
	        				 else if (plugin.getConfig().getBoolean("whitelist-boolean")==true&&plugin.getConfig().getBoolean("blacklist-boolean")==false)
	        				 {
	        					 List<String> whitelist =plugin.getConfig().getStringList("whitelist");
	        					 WhtListGetLink.getLink(args[0], sender, defaultURL, whitelist);
	        				 }
	        				 else if (plugin.getConfig().getBoolean("blacklist-boolean")==false&&plugin.getConfig().getBoolean("whitelist-boolean")==false)
	        				 {
	        					 ShortURLCommand.getLink(args[0], sender, defaultURL);
	        				 }
	     					 return true;
	     				} catch (UnknownHostException e1) {
	     				     sender.sendMessage(ChatColor.DARK_RED+"Invalid Syntax. Please enter in a URL after /link");
	     				}
	             		 catch (CommandException e1)
	             		 {
	             			 sender.sendMessage(ChatColor.DARK_RED+"Invalid Syntax. Please enter in a URL after /link");
	             		 }
	             		 catch (ArrayIndexOutOfBoundsException e1)
	             		 {
	             			 sender.sendMessage(ChatColor.DARK_RED+"Invalid Syntax. Please enter in a URL after /link");
	             		 }
	                   	 
	        		 }
	        		 
	             }
	        }     
			 
	     }catch (CommandException e)
	        {
	       	 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"Invalid Command Usage. Correct usage:");
	   		 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"/link [url to shorten]");
	   		 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"/link about");
	    
	        
	    }
	        catch (ArrayIndexOutOfBoundsException e)
	        {
	        	sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"Invalid Command Usage. Correct usage:");
	      		sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"/link [url to shorten]");
	      		sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"/link about");
	        }
	        return false;


	}
}
