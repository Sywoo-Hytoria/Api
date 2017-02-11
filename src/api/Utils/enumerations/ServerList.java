package fr.hytoria.api.Utils.enumerations;

public enum ServerList
{
	THE_WOLF("thewolf"),
	AKIRO20("akiro20"),
	GERARDTRAN("gerardtran"),
	TEZCAT_("tezcat"),
	ISWIIK("iswiik"),
	DEV1("dev1"),
	DEV2("dev2"),
	DEV3("dev3"),
	HUB("hub");

	private String server;

	private ServerList(String selectserver) {
		this.server = selectserver;
	}

	public String getServer()
	{
		return this.server;
	}
}
