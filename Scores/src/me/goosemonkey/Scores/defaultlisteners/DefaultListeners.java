package me.goosemonkey.Scores.defaultlisteners;

import me.goosemonkey.Scores.Scores;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class DefaultListeners implements Listener
{
	public Scores plugin;
	
	public DefaultListeners(Scores inst)
	{
		plugin = inst;
	}
	
	@EventHandler (priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onBlockBreak(BlockBreakEvent event)
	{
		Material material = event.getBlock().getType();
		
		switch (material)
		{
		case DIAMOND_ORE: Scores.reward(event.getPlayer(), Scores.getValuesConfig().getInt("mine.diamondOre", 75), "mining a diamond", plugin); return;
		case EMERALD_ORE: Scores.reward(event.getPlayer(), Scores.getValuesConfig().getInt("mine.emeraldOre", 75), "mining an emerald", plugin); return;
		case GOLD_ORE: Scores.reward(event.getPlayer(), Scores.getValuesConfig().getInt("mine.goldOre", 20), "mining gold", plugin); return;
		case LAPIS_ORE: Scores.reward(event.getPlayer(), Scores.getValuesConfig().getInt("mine.lapisOre", 10), "mining lapis lazuli", plugin); return;
		case IRON_ORE: Scores.reward(event.getPlayer(), Scores.getValuesConfig().getInt("mine.ironOre", 2), "mining iron", plugin); return;
		case COAL_ORE: Scores.reward(event.getPlayer(), Scores.getValuesConfig().getInt("mine.coalOre", 1), "mining coal", plugin); return;
		}
	}
}
