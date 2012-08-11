package me.goosemonkey.Scores;

import org.bukkit.plugin.java.JavaPlugin;

public class Scores extends JavaPlugin
{
	static ConfigMain configMain;
	static ConfigLocale configLocale;
	static ConfigScores configScores;
	
	public void onEnable()
	{
		this.setupConfig();
	}
	
	private void setupConfig()
	{
		configMain = new ConfigMain(this);
		configLocale = new ConfigLocale(this);
		configScores = new ConfigScores(this);
	}
}
