package fr.hytoria.api.Utils;

/**
 * Created by Sywoo on 06/02/2017.
 */
public enum Rank {


    ADMINISTRATEUR(Integer.valueOf(19)),
    RESPONSABLEDEVELOPPEUR(Integer.valueOf(18)),
    RESPONSABLEMODERATEUR(Integer.valueOf(17)),
    DEVELOPPEUR(Integer.valueOf(16)),
    MODERATEUR(Integer.valueOf(15)),
    STAFF(Integer.valueOf(14)),
    ANIMATEUR(Integer.valueOf(13)),
    HELPER(Integer.valueOf(12)),
    YOUTUBEUR(Integer.valueOf(11)),
    FAMOUS(Integer.valueOf(10)),
    PARTNERS(Integer.valueOf(9)),
    FRIENDS(Integer.valueOf(8)),
    EMMERALD(Integer.valueOf(7)),
    OBSIDIAN(Integer.valueOf(6)),
    DIAMOND(Integer.valueOf(5)),
    GOLD(Integer.valueOf(4)),
    SILVER(Integer.valueOf(3)),
    BRONZE(Integer.valueOf(2)),
    MEMBER(Integer.valueOf(1));

    private int rankId;

    private Rank(Integer value) {
        this.rankId = value.intValue();
    }

    public Integer getRankId()
    {
        return Integer.valueOf(this.rankId);
    }


}
