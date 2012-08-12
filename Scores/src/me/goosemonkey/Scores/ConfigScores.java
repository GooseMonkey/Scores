package me.goosemonkey.Scores;

/**
 * Stores scores
 */
public class ConfigScores extends ConfigAbstract
{
	public ConfigScores(Scores inst)
	{
		super(inst);
	}

	@Override
	public String getHeader()
	{
		return "This file holds the scores of all players on a server.\n" +
				"While the server is running, editing this file is NOT a reliable way to\n" +
				"modify scores. Use /score set <name> <score> [-e] instead!";
	}

	@Override
	public String getName()
	{
		return "ScoresData.yml";
	}

	@Override
	public void setDefaultValues()
	{
	}
}
