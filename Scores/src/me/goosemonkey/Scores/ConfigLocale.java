package me.goosemonkey.Scores;

/**
 * Locale config
 */
public class ConfigLocale extends ConfigAbstract
{
	public ConfigLocale(Scores inst)
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
		return "Locale.yml";
	}

	@Override
	public void setDefaultValues()
	{
		this.checkSet("messagePrefix", "§6[Scores]§c");
		
		this.checkSet("message.earnPoints", "You've earned &score& points for &reason&.");
		this.checkSet("message.earnPoint", "You've earned &score& point for &reason&.");
		this.checkSet("message.losePoints", "You've lost &score& points for &reason&.");
		this.checkSet("message.losePoint", "You've lost &score& point for &reason&.");
	}
}
