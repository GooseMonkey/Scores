package me.goosemonkey.Scores;

import org.bukkit.command.CommandSender;
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
		
		this.getCommand("score").setExecutor(new ScoresCommandExecutor(this));
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
		return Scores.getScoresDataConfig().getInt("scores." + player.getName().toLowerCase(), 0);
	}
	
	/**
	 * Most basic method of setting a player's score, and nothing else.
	 * @param player Player to set score of
	 * @param score The score
	 */
	public static void setScore(Player player, int score)
	{
		Scores.getScoresDataConfig().set("scores." + player.getName().toLowerCase(), score);
	}
	
	/**
	 * Basic method to raise or lower a score by a certain amount
	 * @param player Player to modify score of
	 * @param amount Amount to change. Negative to take away points
	 * @return The player's new score
	 */
	public static int modifyScore(Player player, int amount)
	{
		Scores.setScore(player, Scores.getScore(player) + amount);
		
		return Scores.getScore(player);
	}
	
	/**
	 * Send a message prefixed with [Scores] to a receiver
	 * @param receiver CommandSender receiving the message
	 * @param message Message
	 */
	public static void sendScoresMessage(CommandSender receiver, String message)
	{
		receiver.sendMessage(Scores.getLocaleConfig().getString("messagePrefix", "§6[Scores]§c") + " " + message);
	}
	
	/**
	 * Manipulates a score via a plugin, tells the user about the score change, and logs it. Use for legitimate score changes (Non-command).
	 * Includes taking away points, pass amount as negative and the message will correspond.
	 * @param player Player
	 * @param amount Amount to reward. Negative to take away points
	 * @param reason Reason for score change. Y value of "You've earned/lost x points for y." Use -ing form, don't capitalize the first letter.
	 * @param plugin JavaPlugin this is being done from, for logging purposes
	 * @return Player's new score
	 */
	public static int reward(Player player, int amount, String reason, JavaPlugin plugin)
	{
		Scores.modifyScore(player, amount);
		
		if (player.isOnline())
		{		
			String path = "", def = "", msg = "";
			
			if (amount < -1)
			{
				path = "message.losePoints";
				def = "You've lost &score& points for &reason&.";
			}
			
			if (amount == -1)
			{
				path = "message.losePoint";
				def = "You've lost &score& point for &reason&.";
			}

			if (amount == 1)
			{
				path = "message.earnPoint";
				def = "You've earned &score& point for &reason&.";
			}

			if (amount == 0 || amount > 1)
			{
				path = "message.earnPoints";
				def = "You've earned &score& points for &reason&.";
			}
			
			msg = Scores.getLocaleConfig().getString(path, def);
			
			msg.replace("&score&", "" + amount);
			msg.replace("&reason&", reason);
			
			Scores.sendScoresMessage(player, msg);
		}
		
		plugin.getServer().getLogger().info(plugin.getName() + ": Modified " + player.getName() + "'s score by " + amount + " for: " + reason);
		
		return Scores.getScore(player);
	}
}
