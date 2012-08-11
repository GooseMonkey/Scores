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
			}
			else
			{
				Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.version", "Version &version& by GooseMonkey").replace("&version&", plugin.getDescription().getVersion()));
			}
		}
		
		return true;
	}
}
