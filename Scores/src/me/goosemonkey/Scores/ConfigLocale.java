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
		this.checkSet("messagePrefix", "§6[Scores]§b");
		
		this.checkSet("message.version", "Version &version& by GooseMonkey");
		
		this.checkSet("message.getScore", "You have §a&score&§b points.");
		
		this.checkSet("message.earnPoints", "You've earned §a&score&§b points for &reason&.");
		this.checkSet("message.earnPoint", "You've earned §a&score&§b point for &reason&.");
		this.checkSet("message.losePoints", "You've lost §a&score&§b points for &reason&.");
		this.checkSet("message.losePoint", "You've lost §a&score&§b point for &reason&.");
		
	}
}
