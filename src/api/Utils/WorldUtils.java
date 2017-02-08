package fr.hytoria.api.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import fr.hytoria.api.Utils.enumerations.Time;

public class WorldUtils {

	String name;


	public WorldUtils(String name) {
		this.name = name;
	}

	public void CreateOriginalWorld(){
		Bukkit.createWorld(new WorldCreator(this.name));	
	}
	public void CreateWorldCopy(World copyname){
		Bukkit.createWorld(new WorldCreator(name).copy(copyname));
	}
	public void DeleteAllWorld(){
		for(World world : Bukkit.getWorlds()){
			world.getWorldFolder().delete();
		}
	}
	public void setPVP(String name, boolean allow){
		Bukkit.getWorld(name).setPVP(allow);
	}
	public void setDifficulty(String name, Difficulty difficulty){
		Bukkit.getWorld(name).setDifficulty(difficulty);
	}
	public void WeatherThunder(String name, int duration){
		Bukkit.getWorld(name).setThundering(true);
		Bukkit.getWorld(name).setThunderDuration(duration);
	}
	public void WeatherClear(String name, int duration){
		Bukkit.getWorld(name).setThundering(false);
		Bukkit.getWorld(name).setStorm(false);
		Bukkit.getWorld(name).setWeatherDuration(duration);
	}
	public void WeatherRain(String name, int duration){
		Bukkit.getWorld(name).setThundering(false);
		Bukkit.getWorld(name).setStorm(true);
		Bukkit.getWorld(name).setWeatherDuration(duration);
	}
	public void setHour(String name, Time time){
		Bukkit.getWorld(name).setTime(time.getHour());
	}


}
