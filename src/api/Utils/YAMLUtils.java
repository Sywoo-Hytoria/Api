package fr.hytoria.api.Utils;

/**
 * Created by Sywoo on 05/02/2017.
 * Created First By OpperFx member of Hytoria
 */

import fr.hytoria.api.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

//import java.util.ArrayList;

public final class YAMLUtils {

	/*
	 *  @author OpperFx
	 *  @param Ce message doit rester dans la classe.
	 *  Merci Beacucoup a OpperFx Pour son aide
	 *
	 */

    private static String name;
    private static File file = new File(Main.getinstance().getDataFolder(), name+".yml");
    private FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    public YAMLUtils(String nameNoExtension){
        name = nameNoExtension;
    }

    public void createFile(){
        if(file.exists()){
            System.out.println("[YAMLUtils MSG] Desole mais le fichier "+name+".yml existe deja.");
            return;
        }
        if(!file.exists()){
            config.options().copyDefaults(true);
            saveFile();
        }
    }

//	public void setArrayList(String where, ArrayList<?> param){
//		config.set(where, param);
//		saveFile();
//	}

    public void setDouble(String where, double param){
        config.set(where, param);
        saveFile();
    }

    public void setInt(String where, int param){
        config.set(where, param);
        saveFile();
    }

    public void setString(String where, String param){
        config.set(where, param);
        saveFile();
    }

    public double getDouble(String where){
        if(config.contains(where)){
            return config.getDouble(where);
        }else{
            return 0.0D;
        }
    }

    public int getInt(String where){
        if(config.contains(where)){
            return config.getInt(where);
        }else{
            return 0;
        }
    }

    public String getString(String where){
        if(config.contains(where)){
            return config.getString(where);
        }else{
            return "teub";
        }
    }

//	public ArrayList<?> getArrayList(String where){
//		if(config.contains(where)){
//			return (ArrayList<?>) config.get(where);
//		}else{
//			return null;
//		}
//	}

    public void saveFile(){
        try{
            config.save(file);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static File getFile(String path){
        if(path == name){
            return file;
        }else{
            return null;
        }
    }

}


