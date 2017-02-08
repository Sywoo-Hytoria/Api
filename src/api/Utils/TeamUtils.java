package fr.hytoria.api.Utils;

/**
 * Created by Sywoo on 05/02/2017.
 */

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;


public class TeamUtils {
    private String teamPrefix;
    private int maxSize;
    private boolean friendlyFire;
    private Team team;
    private ArrayList<Player> players = new ArrayList<>();
    private String teamName;

    public TeamUtils(String teamPrefix, int maxSize, boolean friendlyFire)
    {
        this.teamPrefix = teamPrefix;
        this.maxSize = maxSize;
        this.friendlyFire = friendlyFire;
    }

    public void setTeamPrefix(String teamPrefix)
    {
        this.teamPrefix = teamPrefix;
    }

    public String getTeamPrefix() {
        return this.teamPrefix;
    }

    public void addPlayer(Player player) {
        if (this.maxSize == this.players.size()) {
            player.sendMessage("§cVous ne pouvez pas rejoindre cette team, actuellement pleine.");
            return;
        }
        this.team.addEntry(player.getName());
        this.players.add(player);
    }

    public void removePlayer(Player player) {
        this.team.removeEntry(player.getName());
        this.players.remove(this.players);
    }
    public boolean contains(Player player) {
        return this.team.getEntries().contains(player.getName());
    }

    public Integer size() {
        return Integer.valueOf(this.team.getSize());
    }

    public Integer getMaxSize() {
        return Integer.valueOf(this.maxSize);
    }

    public String getTeamName() {
        return this.teamName;
    }

    public Player getRandomPlayer() {
        Random r = new Random();
        return (Player)this.players.get(r.nextInt(this.players.size()));
    }

    public ArrayList<Player> getPlayerList() {
        return this.players;
    }

    public void build(String teamName) {
        this.teamName = teamName;
        this.team = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(teamName);
        this.team.setPrefix(this.teamPrefix);
        this.team.setAllowFriendlyFire(this.friendlyFire);
    }

    public void delete()
    {
        Bukkit.getScoreboardManager().getMainScoreboard().getTeams().remove(this.team);
    }
}
