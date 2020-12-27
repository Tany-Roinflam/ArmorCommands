package pers.tany.armorcommands;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Other {
    public static File file=new File(Main.plugin.getDataFolder(),"config.yml");
	public static FileConfiguration config=YamlConfiguration.loadConfiguration(file);
}
