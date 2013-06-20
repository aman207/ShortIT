package net.targetcraft.shortit;

import java.io.File;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {
	
	public void onEnable() 
	{
		File file=new File(getDataFolder()+File.separator+"config.yml");
		if (!file.exists())
		{
			getLogger().info("[ShortIt] Configuration not found. Generating...");
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
		}
		getCommand("link").setExecutor(new CommandListener(this));
			
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new ChatListener(this), this);
		
    }
 
    public void onDisable() 
    {
        
    }

}
