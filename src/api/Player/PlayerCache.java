package fr.hytoria.api.Player;

public class PlayerCache
{
	private int rank_id;
	private Integer coins;
	private Integer payoutcoins;
	private String[] perm;


	public PlayerCache(int rank_id, Integer coins, Integer payoutcoins, String[] perm)
	{
		this.rank_id = rank_id;
		this.coins = coins;
		this.payoutcoins = payoutcoins;
		this.perm = perm;
	}

	public int getRank_id() {
		return this.rank_id;
	}

	
	public Integer getScope() {
		return this.coins;
	}

	public Integer getScopecoins() {
		return this.payoutcoins;
	}

	public String[] getPerm() {
		return this.perm;
	}
}
