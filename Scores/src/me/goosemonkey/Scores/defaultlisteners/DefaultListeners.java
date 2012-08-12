package me.goosemonkey.Scores.defaultlisteners;

import me.goosemonkey.Scores.Scores;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

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
		if (event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH))
			return;
		
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
	
	@EventHandler (priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onBlockPlace(BlockPlaceEvent event)
	{
		Material material = event.getBlock().getType();
		
		switch (material)
		{
		case GOLD_ORE: Scores.reward(event.getPlayer(), 0 - Scores.getValuesConfig().getInt("mine.goldOre", 20), "placing gold ore", plugin); return;
		case IRON_ORE: Scores.reward(event.getPlayer(), 0 - Scores.getValuesConfig().getInt("mine.ironOre", 2), "placing iron ore", plugin); return;
		
		case CAKE_BLOCK: Scores.reward(event.getPlayer(),Scores.getValuesConfig().getInt("place.cake", 10), "baking a cake", plugin); return;
		}
	}
}
