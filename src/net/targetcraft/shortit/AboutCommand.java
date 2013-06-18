package net.targetcraft.shortit;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class AboutCommand {
	public static void sendAbout(CommandSender sender)
	{
		sender.sendMessage(ChatColor.YELLOW+"***************************************");
		sender.sendMessage("");
		sender.sendMessage(ChatColor.RED+"ShortIt Version 0.4");
		sender.sendMessage(ChatColor.RED+"Plugin Developed by: aman207");
		sender.sendMessage(ChatColor.RED+"Plugin tested and debugged by AzroWear");
		sender.sendMessage("");
		sender.sendMessage(ChatColor.YELLOW+"***************************************");
	}

}
