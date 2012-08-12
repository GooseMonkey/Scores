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
				
				if (Scores.hasPermission(player, "scores.check"))
				{
					Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.getScore", "You have &score& points.").replace("&score&", "" + Scores.getScore(player)));
				}
				else
				{
					Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.permissionDeny", "§cYou don't have permission."));
				}
				
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
			if (args[0].equalsIgnoreCase("save"))
			{
				if (!Scores.hasPermission(sender, "scores.save"))
				{
					Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.permissionDeny", "§cYou don't have permission."));

					return true;
				}				
				
				Scores.saveScores();
				
				Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.saved", "All players' scores saved."));
				
				return true;
			}
			
			if (!Scores.hasPermission(sender, "scores.check.others"))
			{
				Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.permissionDeny", "§cYou don't have permission."));

				return true;
			}

			if (plugin.getServer().getPlayer(args[0]) != null)
			{
				Player player = plugin.getServer().getPlayer(args[0]);
				
				Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.otherScore", "&player& has §a&score&§b points.").replace("&player&", player.getName()).replace("&score&", "" + Scores.getScore(player)));

				return true;
			}
			else
			{
				if (Scores.getScoresDataConfig().isInt("scores." + args[0].toLowerCase()))
				{
					if (Scores.hasPermission(sender, "scores.check.others"))
					{
						Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.otherScore", "&player& has §a&score&§b points.").replace("&player&", args[0]).replace("&score&", "" + Scores.getScore(plugin.getServer().getOfflinePlayer(args[0]))));
					}
					else
					{
						Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.permissionDeny", "§cYou don't have permission."));
					}
				}
				else
				{
					Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.unknownPlayer", "Player &input& not found. If the player is offline, the full name must be entered.").replace("&input&", args[0]));	
				}
			}
			
			return true;
		}
		
		if (args.length == 2)
		{
			// 0: Operation, 1: Argument
			
			plugin.getServer().dispatchCommand(sender, "scores " + args[0] + " " + sender.getName() + " " + args[1]);
			
			return true;
		}
		
		if (args.length == 3)
		{
			// 0: Operation, 1: Player, 2: Argument
			
			Player player = plugin.getServer().getPlayer(args[1]);
			int score;
			try
			{
				score = Integer.parseInt(args[2]);
			}
			catch (NumberFormatException e)
			{
				Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.invalidInteger", "&input& is not a valid integer.").replace("&input&", args[2]));
				
				return true;
			}
			
			if (args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("s"))
			{
				if (!Scores.hasPermission(sender, "scores.modify"))
				{
					Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.permissionDeny", "§cYou don't have permission."));

					return true;
				}
				
				if (player != null)
				{
					Scores.setScore(player, score);
					
					if (sender instanceof Player)
					{
						Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.setScore", "&player&'s score set to §a&score&§b.").replace("&player&", player.getName()).replace("&score&", "" + score));
					}
					
					plugin.getServer().getLogger().info("[Scores] " + sender.getName() + ": Set " + player.getName() + "'s score to " + score);
					
					return true;
				}
				else
				{
					Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.unknownPlayerOffline", "Player &input& not found. If the player is offline, add -e to the end of the command and use the exact name.").replace("&input&", args[1]));	
					
					return true;
				}
			}
			
			if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("a"))
			{
				if (!Scores.hasPermission(sender, "scores.modify"))
				{
					Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.permissionDeny", "§cYou don't have permission."));

					return true;
				}

				if (player != null)
				{
					Scores.modifyScore(player, score);
					
					if (sender instanceof Player)
					{
						Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.addScore", "§a&score&§b points added to &player&'s score. §a[&newscore&]§b").replace("&player&", player.getName()).replace("&score&", "" + score).replace("&newscore&", "" + Scores.getScore(player)));
					}
					
					plugin.getServer().getLogger().info("[Scores] " + sender.getName() + ": Added " + score + " points to " + player.getName() + "'s score. [" + Scores.getScore(player) + "]");
					
					return true;
				}
				else
				{
					Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.unknownPlayerOffline", "Player &input& not found. If the player is offline, add -e to the end of the command and use the exact name.").replace("&input&", args[1]));	
					
					return true;
				}
			}
			
			if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("r"))
			{
				if (!Scores.hasPermission(sender, "scores.modify"))
				{
					Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.permissionDeny", "§cYou don't have permission."));

					return true;
				}

				if (player != null)
				{
					Scores.modifyScore(player, 0 - score);
					
					if (sender instanceof Player)
					{
						Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.removeScore", "§a&score&§b points removed from &player&'s score. §a[&newscore&]§b").replace("&player&", player.getName()).replace("&score&", "" + score).replace("&newscore&", "" + Scores.getScore(player)));
					}
					
					plugin.getServer().getLogger().info("[Scores] " + sender.getName() + ": Removed " + score + " points from " + player.getName() + "'s score. [" + Scores.getScore(player) + "]");
					
					return true;
				}
				else
				{
					Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.unknownPlayerOffline", "Player &input& not found. If the player is offline, add -e to the end of the command and use the exact name.").replace("&input&", args[1]));	
					
					return true;
				}
			}
			
			return false;
		}
		
		if (args.length == 4)
		{
			// 0: Operation, 1: Player, 2: Argument, 3: -e
			
			int score;
			try
			{
				score = Integer.parseInt(args[2]);
			}
			catch (NumberFormatException e)
			{
				Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.invalidInteger", "&input& is not a valid integer.").replace("&input&", args[2]));
				
				return true;
			}

			if (!args[3].equalsIgnoreCase("-e"))
				return false;
			
			if (args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("s"))
			{
				if (!Scores.hasPermission(sender, "scores.modify"))
				{
					Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.permissionDeny", "§cYou don't have permission."));

					return true;
				}

				Scores.setScore(plugin.getServer().getOfflinePlayer(args[1]), score);
				
				if (sender instanceof Player)
				{
					Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.setScore", "&player&'s score set to §a&score&§b.").replace("&player&", args[1]).replace("&score&", "" + score));
				}
				
				plugin.getServer().getLogger().info("[Scores] " + sender.getName() + ": Set " + args[1] + "'s score to " + score);
				
				return true;
			}
			
			if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("a"))
			{
				if (!Scores.hasPermission(sender, "scores.modify"))
				{
					Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.permissionDeny", "§cYou don't have permission."));

					return true;
				}

				Scores.modifyScore(plugin.getServer().getOfflinePlayer(args[1]), score);
				
				if (sender instanceof Player)
				{
					Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.addScore", "§a&score&§b points added to &player&'s score. §a[&newscore&]§b").replace("&player&", args[1]).replace("&score&", "" + score).replace("&newscore&", "" + Scores.getScore(plugin.getServer().getOfflinePlayer(args[1]))));
				}
				
				plugin.getServer().getLogger().info("[Scores] " + sender.getName() + ": Added " + score + " points to " + args[1] + "'s score. [" + Scores.getScore(plugin.getServer().getOfflinePlayer(args[1])) + "]");
				
				return true;
			}

			if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("r"))
			{
				if (!Scores.hasPermission(sender, "scores.modify"))
				{
					Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.permissionDeny", "§cYou don't have permission."));

					return true;
				}

				Scores.modifyScore(plugin.getServer().getOfflinePlayer(args[1]), 0 - score);
				
				if (sender instanceof Player)
				{
					Scores.sendScoresMessage(sender, Scores.getLocaleConfig().getString("message.removeScore", "§a&score&§b points removed from &player&'s score. §a[&newscore&]§b").replace("&player&", args[1]).replace("&score&", "" + score).replace("&newscore&", "" + Scores.getScore(plugin.getServer().getOfflinePlayer(args[1]))));
				}
				
				plugin.getServer().getLogger().info("[Scores] " + sender.getName() + ": Removed " + score + " points from " + args[1] + "'s score. [" + Scores.getScore(plugin.getServer().getOfflinePlayer(args[1])) + "]");
				
				return true;
			}
			
			return false;
		}
		
		return false;
	}
}
