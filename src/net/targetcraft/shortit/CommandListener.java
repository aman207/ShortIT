package net.targetcraft.shortit;

import java.net.UnknownHostException;
import java.util.ArrayList;
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
	        			 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"To turn a URL into a short URL, just type it in the MAIN chat.");
	        			 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"Other commands:");
	        			 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"/link [url to shorten] Note: This only shows the link to you");
	        			 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"/link about");
	        			 if(sender.hasPermission("shortit.admin"))
	        			 {
	        				 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"/link ban");
	        				 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"/link unban");
	        				 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"/link banlist");
	        			 }
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
	        		 else if (args[0].equalsIgnoreCase("ban"))
	        		 {
	        			 if(sender.hasPermission("shortit.admin"))
	        			 {
	        				 if(args[1].equalsIgnoreCase(null)||args[1].equalsIgnoreCase(""))
	        				 {
	        					 sender.sendMessage(ChatColor.BLUE+"[ShortIt] "+ChatColor.DARK_RED+"Error. You can not ban nobody silly");
	        				 }
	        				 else
	        				 {
	        					 
	        					 List<String>blacklistUser = plugin.getConfig().getStringList("user-blacklist");
                                 List<String>blacklistUserCopy=new ArrayList<String>(blacklistUser);
	        					 
	        					 int count = 0;
	        				     while (blacklistUserCopy.contains(args[1]))
	        				     {
	        				         blacklistUserCopy.remove(args[1]);
	        				         count++;
	        				     }
	        					 if(count==1)
	        					 {
	        						 sender.sendMessage(ChatColor.BLUE+"[ShortIt] "+ChatColor.DARK_RED+"Error. That user is already banned! " +
	        						 		"To see the list of banned users, type /link banlist");
	        					 }
	        					 else
	        					 {
	        						 blacklistUser.add(args[1]);
		        					 plugin.getConfig().set("user-blacklist",blacklistUser);
		        					 plugin.saveConfig();
			        				 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.GREEN+"Successfully added "+args[1]+" to the banned list");
	        					 }
	                            
	        				 }
	        				 
	        				 
	        			 }
	        			 else
	        			 {
	        				 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.DARK_RED+"You do not have permission to do that!");
	        			 }
	        		 }
	        		 else if (args[0].equalsIgnoreCase("unban"))
	        		 {
	        			 if(sender.hasPermission("shortit.admin"))
	        			 {
	        				 if(args[1].equalsIgnoreCase(null)||args[1].equalsIgnoreCase(""))
	        				 {
	        					 sender.sendMessage(ChatColor.BLUE+"[ShortIt] "+ChatColor.DARK_RED+"Error. You can not unban nobody silly");
	        				 }
	        				 else
	        				 {
	        					 List<String>blacklistUser = plugin.getConfig().getStringList("user-blacklist");
	        					 List<String>blacklistUserCopy=new ArrayList<String>(blacklistUser);
	        					 
	        					 int count = 0;
	        				     while (blacklistUserCopy.contains(args[1]))
	        				     {
	        				         blacklistUserCopy.remove(args[1]);
	        				         count++;
	        				     }
	        					 
	        					 if(count==1)
	                             {
	                            	 blacklistUser.remove(args[1]);
		                             sender.sendMessage(blacklistUser.toString());
		        					 plugin.getConfig().set("user-blacklist",blacklistUser);
		        					 plugin.saveConfig();
			        				 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.GREEN+"Successfully removed "+args[1]+" from the banned list");
	                             }
	                             else
	                             {
	                            	 sender.sendMessage(ChatColor.BLUE+"[ShortIt] "+ChatColor.DARK_RED+"Error. I did not find the username you are trying to unban. " +
	                            	 		"To see the current list of banned users, type /link banlist");
	                             }
	                             
	        				 }
	        			 }
	        			 else
	        			 {
	        				 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.DARK_RED+"You do not have permission to do that!");
	        			 }
	        		 }
	        		 else if(args[0].equalsIgnoreCase("banlist"))
	        		 {
	        			 if(sender.hasPermission("shortit.admin"))
	        			 {
	        				 List<String>blacklistUser = plugin.getConfig().getStringList("user-blacklist");
		        			 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"The current list of banned users is: ");
		        			 sender.sendMessage(ChatColor.BLUE+blacklistUser.toString());
	        			 }
	        			 else
	        			 {
	        				 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.DARK_RED+"You do not have permission to do that!");
	        			 }
	        			 
	        		 }
	        		 else
	        		 {
	        			 try {
	        				 String defaultURL;
	        				 defaultURL=plugin.getConfig().getString("api-link");
	        				 /**if (plugin.getConfig().getBoolean("blacklist-boolean")==true&&plugin.getConfig().getBoolean("whitelist-boolean")==false)
	        				 {
	        					 List<String> blacklist =plugin.getConfig().getStringList("blacklist");
	        					 BlkListGetLink.getLink(args[0], sender, defaultURL, blacklist);
	        				 }
	        				 else if (plugin.getConfig().getBoolean("blacklist-boolean")==false&&plugin.getConfig().getBoolean("whitelist-boolean")==false)
	        				 {*/
	        				 List<String> userBlacklist=plugin.getConfig().getStringList("user-blacklist");
	        				 ShortURLCommand.getLink(args[0], sender, defaultURL, userBlacklist);
	        				 //}
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
	   		 sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"/link help");
	    
	        
	    }
	        catch (ArrayIndexOutOfBoundsException e)
	        {
	        	sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"Invalid Command Usage. Correct usage:");
	      		sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"/link [url to shorten]");
	      		sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"/link about");
	      		sender.sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"/link help");
	        }
	        return false;


	}
}
