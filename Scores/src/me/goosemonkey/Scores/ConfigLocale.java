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
		this.checkSet("message.otherScore", "&player& has §a&score&§b points.");
		this.checkSet("message.unknownPlayer", "Player &input& not found. If the player is offline, the full name must be entered case sensitively.");
		this.checkSet("message.unknownPlayerOffline", "Player &input& not found. If the player is offline, add -e to the end of the command and use the exact name.");
		this.checkSet("message.invalidInteger", "&input& is not a valid integer.");
		
		this.checkSet("message.setScore", "&player&'s score set to §a&score&§b.");
		this.checkSet("message.addScore", "§a&score&§b points added to &player&'s score. §a[&newscore&]§b");
		this.checkSet("message.removeScore", "§a&score&§b points removed from &player&'s score. §a[&newscore&]§b");
		
		this.checkSet("message.earnPoints", "You've earned §a&score&§b points for &reason&.");
		this.checkSet("message.earnPoint", "You've earned §a&score&§b point for &reason&.");
		this.checkSet("message.losePoints", "You've lost §a&score&§b points for &reason&.");
		this.checkSet("message.losePoint", "You've lost §a&score&§b point for &reason&.");
		
	}
}
