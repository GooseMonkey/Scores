package me.goosemonkey.Scores;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class ConfigAbstract
{
	private FileConfiguration config = null;
	private File configFile = null;
	
	private JavaPlugin plugin;
	
	private String fileName;
	
	public ConfigAbstract(JavaPlugin inst)
	{
		plugin = inst;
		this.fileName = this.getName();
		
		this.reload();
		
		this.setDefaultValues();
		
		this.getConfig().options().header(this.getHeader());
		
		try
		{
			this.getConfig().save(configFile);
		}
		catch (IOException e)
		{
			plugin.getLogger().severe("Could not save config!");
		}
	}
	
	public void reload()
	{
		if (configFile == null)
		{
			configFile = new File(plugin.getDataFolder(), fileName);
		}
		
		config = YamlConfiguration.loadConfiguration(configFile);
		
		// Look for defaults in the jar
		InputStream defConfigStream = plugin.getResource(fileName);
		
		if (defConfigStream != null)
		{
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			
			config.setDefaults(defConfig);
		}
	}
		
	public FileConfiguration getConfig()
	{
		if (config == null)
		{
			reload();
		}
		
		return config;
	}
	
	/**
	 * Sets a configuration value if it isn't already set
	 * @param path Path to set
	 * @param def Object to set
	 */
	public void checkSet(String path, Object def)
	{
		FileConfiguration f = this.getConfig();
		
		if (!f.isSet(path))
		{
			f.set(path, def);
		}
	}
	
	public abstract String getHeader();
	
	public abstract String getName();
	
	public abstract void setDefaultValues();
}