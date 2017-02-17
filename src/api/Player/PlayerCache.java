package fr.hytoria.api.Player;

import java.sql.Date;

public class PlayerCache
{
	private int rank_id;
	private Integer coins;
	private Integer payoutcoins;
	private String[] perm;

	private boolean ban;
	private String banReason;
	private Date bandate;
	private String banOwner;


	public PlayerCache(int rank_id, Integer coins, Integer payoutcoins, String[] perm, boolean ban, Date bandate, String banReason, String banOwner)
	{
		this.rank_id = rank_id;
		this.coins = coins;
		this.payoutcoins = payoutcoins;
		this.perm = perm;

		this.ban = ban;
		this.banReason = banReason;
		this.bandate = bandate;
		this.banOwner = banOwner;
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
	public boolean isBan() {
		return this.ban;
	}

	public String getBanReason() {
		return this.banReason;
	}

	public Date getBandate() {
		return this.bandate;
	}

	public String getBanOwner() {
		return this.banOwner;
	}
}
