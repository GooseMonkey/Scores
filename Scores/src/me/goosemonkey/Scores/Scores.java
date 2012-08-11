package me.goosemonkey.Scores;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
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
	
	/**
	 * Get the FileConfiguration object of Config.yml
	 * @return The main config's FileConfiguration
	 */
	public static FileConfiguration getMainConfig()
	{
		return Scores.configMain.getConfig();
	}
	
	/**
	 * Get the FileConfiguration object of Locale.yml
	 * @return The locale config's FileConfiguration
	 */
	public static FileConfiguration getLocaleConfig()
	{
		return Scores.configLocale.getConfig();
	}

	/**
	 * Get the FileConfiguration object of ScoresData.yml
	 * @return The score data config's FileConfiguration
	 */
	public static FileConfiguration getScoresDataConfig()
	{
		return Scores.configScores.getConfig();
	}
	
	/**
	 * Gets a player's current score from ScoresData.yml
	 * @param player Player to get score of
	 * @return Player's score, 0 if player isn't found
	 */
	public static int getScore(Player player)
	{
		return Scores.getScoresDataConfig().getInt("scores." + player.getName(), 0);
	}
	
	/**
	 * Most basic method of setting a player's score, and nothing else.
	 * @param player Player to set score of
	 * @param score The score
	 */
	public static void setScore(Player player, int score)
	{
		Scores.getScoresDataConfig().set("scores." + player.getName(), score);
	}
}
