package me.goosemonkey.Scores;

/**
 * Main plugin configuration
 */
public class ConfigMain extends ConfigAbstract
{
	public ConfigMain(Scores inst)
	{
		super(inst);
	}

	@Override
	public String getHeader()
	{
		return null;
	}

	@Override
	public String getName()
	{
		return "Config.yml";
	}

	@Override
	public void setDefaultValues()
	{
		this.checkSet("saveOnScoreChange", true);
		
		this.checkSet("log.rewards", true);
		this.checkSet("log.commands", true);
		
		this.checkSet("notify.tellPlayerOnCommandScoreChange", true);		
	}
}
