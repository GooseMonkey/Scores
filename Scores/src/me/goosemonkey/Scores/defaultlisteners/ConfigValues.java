package me.goosemonkey.Scores.defaultlisteners;

import me.goosemonkey.Scores.ConfigAbstract;
import me.goosemonkey.Scores.Scores;

public class ConfigValues extends ConfigAbstract
{
	public ConfigValues(Scores inst)
	{
		super(inst);
	}

	@Override
	public String getHeader()
	{
		return "Change how many points players get by performing ceratin actions here.\n" +
				"Set a score to 0 to not reward any points for an action.";
	}

	@Override
	public String getName()
	{
		return "Values.yml";
	}

	@Override
	public void setDefaultValues()
	{
		this.checkSet("mine.diamondOre", 100);
		this.checkSet("mine.emeraldOre", 100);
		this.checkSet("mine.goldOre", 40);
		this.checkSet("mine.lapisOre", 20);
		this.checkSet("mine.redstoneOre", 5);
		this.checkSet("mine.ironOre", 2);
		this.checkSet("mine.coalOre", 1);
		
		this.checkSet("mine.mobSpawner.dungeon", 100);
		this.checkSet("mine.mobSpawner.caveSpider", 20);
		this.checkSet("mine.mobSpawner.blaze", 250);
		this.checkSet("mine.mobSpawner.silverfish", 150);

		this.checkSet("place.cake", 10);
		
		this.checkSet("enchant.multiplier", 1.0);
		
		this.checkSet("death.death", -100);
		this.checkSet("death.killPlayer", 0);
		this.checkSet("death.minScoreOfVictimForPvpKillPoints", 100);
		
		this.checkSet("mobKill.ghast", 10);
		this.checkSet("mobKill.enderman", 5);
	}
}
