package me.goosemonkey.Scores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Listens for PlayerJoinEvents to a) announce scores if configured and b) give new players their default scores if configured
 */
public class ScoresLoginListener implements Listener
{
	private Scores plugin;
	
	public ScoresLoginListener(Scores inst)
	{
		plugin = inst;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		if (Scores.getMainConfig().getInt("login.newPlayerScore", 10) != 0 && !Scores.getScoresDataConfig().isInt("scores." + event.getPlayer().getName().toLowerCase()))
		{
			Scores.reward(event.getPlayer(), Scores.getMainConfig().getInt("newPlayerScore", 10), "joining the server", plugin);
		}
		
		if (Scores.getMainConfig().getBoolean("login.broadcastScoreOnLogin", true))
		{
			plugin.getServer().broadcastMessage(Scores.getLocaleConfig().getString("messagePrefix", "§6[Scores]§b") + " " + Scores.getLocaleConfig().getString("message.login.broadcast", "&player& has logged in with §a&score&§b points.").replace("&player&", event.getPlayer().getDisplayName()).replace("&score&", "" + Scores.getScore(event.getPlayer())));
		}
		else if (Scores.getMainConfig().getBoolean("login.tellScoreOnLogin", true))
		{
			Scores.sendScoresMessage(event.getPlayer(), Scores.getLocaleConfig().getString("message.login.tell", "Welcome, &player&. You have §a&score&§b points.").replace("&player&", event.getPlayer().getDisplayName()).replace("&score&", "" + Scores.getScore(event.getPlayer())));
		}
	}
}
