package net.targetcraft.shortit;

import java.net.UnknownHostException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener{
	MainClass plugin;
	
	public ChatListener(MainClass instance) {
		plugin = instance;
		
		}
	@EventHandler
	public void playerChat (AsyncPlayerChatEvent e)
	{
		String defaultURL=plugin.getConfig().getString("api-link");
			try {
				boolean onBlackList=false;
				List<String>userBlacklist=plugin.getConfig().getStringList("user-blacklist");
		    	if (e.getPlayer().hasPermission("shortit.autoshort"))	
				{
		    		String[] blacklistArray = userBlacklist.toArray(new String[0]);
				    e.getPlayer().sendMessage(blacklistArray);
				    	
				    if(e.getPlayer().equals(blacklistArray))
				    {
				    	onBlackList=true;
				    }
				    if(!e.getPlayer().equals(blacklistArray))
				    {
				    	onBlackList=false;
				    }
				    	
				    if (onBlackList==true)
				    {
				    	e.setMessage(GetShortURL.blockedURL(e.getMessage(), e.getPlayer(), defaultURL));
				    	e.getPlayer().sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"Error. Someone has put you on the User Blacklist. You are not able to shorten URLs until someone unbans you");
				    }
				    else
				    {
				    	e.setMessage(GetShortURL.shortURL(e.getMessage(),e.getPlayer(), defaultURL));
				    }	
				}
				else
				{
					e.getPlayer().sendMessage(ChatColor.RED+"[ShortIt] "+ChatColor.YELLOW+"Error. You do not have permission to auto shorten URLs");
				}
		    	
				}				
			catch (UnknownHostException e1) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED+"[ShortIt] Your YOURLS API link is invalid. Please check it in the config and try again");
	   }
	}
}
