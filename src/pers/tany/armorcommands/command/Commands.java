package pers.tany.armorcommands.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tany.armorcommands.CommonlyWay;
import pers.tany.armorcommands.Main;
import pers.tany.armorcommands.Other;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (CommonlyWay.opUseCommand(sender)) {
            sender.sendMessage("§c你没有权限执行此命令");
            return true;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                File file = new File(Main.plugin.getDataFolder(), "config.yml");
                Other.config = YamlConfiguration.loadConfiguration(file);
                sender.sendMessage("§a重载成功");
                return true;
            }
        }
        if (CommonlyWay.consoleUse(sender)) {
            sender.sendMessage("§e/ac reload  §6重载");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("del")) {
                if (CommonlyWay.emptyItem(player)) {
                    player.sendMessage("§c不能空手！");
                    return true;
                }
                ItemStack item = player.getItemInHand();
                ItemMeta itemMeta = item.getItemMeta();
                if (!itemMeta.hasLore()) {
                    player.sendMessage("§c这个物品没有lore");
                    return true;
                }
                List<String> loreList = itemMeta.getLore();
                boolean remove = false;
                for (String lore : new ArrayList<>(itemMeta.getLore())) {
                    if (lore.startsWith(ChatColor.translateAlternateColorCodes('&', Other.config.getString("LoreCommands")))) {
                        loreList.remove(lore);
                        remove = true;
                    }
                }
                itemMeta.setLore(loreList);
                item.setItemMeta(itemMeta);
                if (remove) {
                    player.sendMessage("§c移除成功");
                } else {
                    player.sendMessage("§c没有对应的lore！");
                }
                return true;
            }
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("add")) {
                if (CommonlyWay.emptyItem(player)) {
                    player.sendMessage("§c不能空手！");
                    return true;
                }
                ItemStack item = player.getItemInHand();
                ItemMeta itemMeta = item.getItemMeta();
                List<String> lore = new ArrayList<>();
                if (itemMeta.hasLore()) {
                    lore.addAll(itemMeta.getLore());
                }
                lore.add(ChatColor.translateAlternateColorCodes('&', Other.config.getString("LoreCommands") + args[1].replace("空格", " ")));
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
                player.sendMessage("§a添加完成！");
                return true;
            }
        }
        player.sendMessage("§e/ac del  §6移除手上物品绑定的指令");
        player.sendMessage("§e/ac add 指令  §6为手上物品添加指令");
        player.sendMessage("§e/ac reload  §6重载");
        player.sendMessage("§e（“空格”会自动替换成空格，无需带/）");
        return true;
    }

}
