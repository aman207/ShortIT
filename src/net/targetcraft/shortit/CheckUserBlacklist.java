package net.targetcraft.shortit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class CheckUserBlacklist {
    static MainClass plugin;
	
	public CheckUserBlacklist(MainClass config) {
		plugin = config;
		}
	
	public static boolean findUser(String string)
	{
		 List<String>blacklistUser = plugin.getConfig().getStringList("user-blacklist");
         List<String>blacklistUserCopy=new ArrayList<String>(blacklistUser);
		 
		 int count = 0;
	     while (blacklistUserCopy.contains(string))
	     {
	         blacklistUserCopy.remove(string);
	         count++;
	     }
	     if(count==1)
	     {
	    	 return true;
	     }
	     else
	    	 return false;
	}
	public static int findUser2(String string)
	{
		 List<String>blacklistUser = plugin.getConfig().getStringList("user-blacklist");
         List<String>blacklistUserCopy=new ArrayList<String>(blacklistUser);
		 string=ChatColor.RED+"It works";
		 int count = 0;
	     while (blacklistUserCopy.contains(string))
	     {
	         blacklistUserCopy.remove(string);
	         count++;
	     }
	     return count;
	}

}