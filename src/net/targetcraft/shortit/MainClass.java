package net.targetcraft.shortit;

import java.io.File;

import org.bukkit.ChatColor;
//import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {
	
	public void onEnable() 
	{
		File file=new File(getDataFolder()+File.separator+"config.yml");
		if (!file.exists())
		{
			getConfig().options().copyDefaults(true);
			this.saveConfig();
			getLogger().info("[ShortIt] Configuration not found. Generating...");
		}
		getCommand("link").setExecutor(new CommandListener(this));
			
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new ChatListener(this), this);
		if (this.getConfig().getBoolean("whitelist-boolean")==true&&this.getConfig().getBoolean("blacklist-boolean"))
		{
			getLogger().severe("[ShortIt] MAJOR ERROR. You have whitelist-boolean AND blacklist-boolean both set to TRUE. "+ChatColor.DARK_RED+"ShortIt CAN NOT RUN LIKE THIS");
		}
		
    }
 
    public void onDisable() 
    {
        
    }

}
