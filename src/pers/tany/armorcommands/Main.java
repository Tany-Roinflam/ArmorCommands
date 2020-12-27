package pers.tany.armorcommands;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pers.tany.armorcommands.command.Commands;
import pers.tany.armorcommands.listenevent.Events;

import java.io.File;

public class Main extends JavaPlugin {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveResource("config.yml", false);
        }
        Bukkit.getConsoleSender().sendMessage("§6[§eArmorCommands§6]§a已启用");
        this.getServer().getPluginManager().registerEvents(new Events(), this);
        getCommand("ac").setExecutor(new Commands());
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§6[§eArmorCommands§6]§a已卸载");
    }
}
