package me.goosemonkey.Scores.defaultlisteners;

import me.goosemonkey.Scores.Scores;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

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
		
		case MOB_SPAWNER: {
			
			String spawnerType = "dungeon";
			
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getState();
			
			switch (spawner.getSpawnedType())
			{
			case BLAZE: spawnerType = "blaze";
			case SILVERFISH: spawnerType = "silverfish";
			case CAVE_SPIDER: spawnerType = "caveSpider";
			}
			Scores.reward(event.getPlayer(), Scores.getValuesConfig().getInt("mine.mobSpawner." + spawnerType, 100), "breaking a Mob Spawner", plugin); return;
		}
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
	
	@EventHandler (priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onEnchant(EnchantItemEvent event)
	{
		Scores.reward(event.getEnchanter(), (int) (event.getExpLevelCost() * Scores.getValuesConfig().getDouble("enchant.multiplier", 1.0)), "enchanting an item", plugin);
	}
	
	@EventHandler (priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerDeath(PlayerDeathEvent event)
	{
		Scores.reward(event.getEntity(), Scores.getValuesConfig().getInt("death.death", -100), "dying", plugin);
		
		if (event.getEntity().getKiller() != null && Scores.getScore(event.getEntity()) >= Scores.getValuesConfig().getInt("death.minScoreOfVictimForPvpKillPoints", 100))
		{
			Scores.reward(event.getEntity().getKiller(), Scores.getValuesConfig().getInt("death.killPlayer", 0), "murder", plugin);
		}
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onEntityDeath(EntityDeathEvent event)
	{
		if (!(event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent))
            return;
		
		EntityDamageByEntityEvent event2 = (EntityDamageByEntityEvent) event.getEntity().getLastDamageCause();
		
		if (!(event2.getDamager() instanceof Player) && !(event2.getDamager() instanceof Arrow))
			return;
		
		Player damager = null;
		
		try
		{
			damager = (Player) event2.getDamager();
		}
		catch (ClassCastException e)
		{
			try
			{
				damager = (Player) ((Arrow) event2.getDamager()).getShooter();
			}
			catch (ClassCastException e2)
			{
				return;
			}
		}
		
		String mob = "";
		
		switch (event2.getEntityType())
		{
		case ENDERMAN: mob = "enderman"; break;
		case GHAST: mob = "ghast"; break;
		default: return;
		}
		
		Scores.reward(damager, Scores.getValuesConfig().getInt("mobKill." + mob, 0), mob.equals("enderman") ? "killing an Enderman" : "killing a Ghast", plugin);		
	}
}
