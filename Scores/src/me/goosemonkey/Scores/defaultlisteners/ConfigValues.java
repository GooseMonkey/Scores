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
		return "Change how many points players get by performing ceratin actions here. Set a score to 0 to not reward any points for an action.";
	}

	@Override
	public String getName()
	{
		return "Values.yml";
	}

	@Override
	public void setDefaultValues()
	{
		this.checkSet("mine.diamondOre", 75);
		this.checkSet("mine.emeraldOre", 75);
		this.checkSet("mine.goldOre", 20);
		this.checkSet("mine.lapisOre", 10);
		this.checkSet("mine.redstoneOre", 5);
		this.checkSet("mine.ironOre", 2);
		this.checkSet("mine.coalOre", 1);

	}
}
