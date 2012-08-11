package me.goosemonkey.Scores;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ScoresCommandExecutor implements CommandExecutor
{
	private Scores plugin = null;
	
	public ScoresCommandExecutor(Scores inst)
	{
		plugin = inst;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{		
		if (args.length == 0)
		{
			if (sender instanceof Player)
			{
				Player player = (Player) sender;
				
				Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.getScore", "You have &score& points.").replace("&score&", "" + Scores.getScore(player)));
				
				return true;
			}
			else
			{
				Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.version", "Version &version& by GooseMonkey").replace("&version&", plugin.getDescription().getVersion()));
			
				return true;
			}
		}
		
		if (args.length == 1)
		{
			if (plugin.getServer().getPlayer(args[0]) != null)
			{
				Player player = plugin.getServer().getPlayer(args[0]);
				
				Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.otherScore", "&player& has §a&score&§b points.").replace("&player&", player.getDisplayName()).replace("&score&", "" + Scores.getScore(player)));
			
				return true;
			}
			else
			{
				if (Scores.getScoresDataConfig().isInt("scores." + args[0].toLowerCase()))
				{
					Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.otherScore", "&player& has §a&score&§b points.").replace("&player&", args[0]).replace("&score&", "" + Scores.getScore(plugin.getServer().getOfflinePlayer(args[0]))));
				}
				else
				{
					Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.unknownPlayer", "Player &input& not found. If the player is offline, the full name must be entered case sensitively.").replace("&input&", args[0]));	
				}
			}
		}
		
		return true;
	}
}
