package pers.tany.armorcommands.listenevent;

import com.codingforcookies.armorequip.ArmorEquipEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tany.armorcommands.Other;

public class Events implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onArmorEquip(ArmorEquipEvent evt) {
        Player player = evt.getPlayer();
        if (evt.getOldArmorPiece() == null || evt.getOldArmorPiece().getType() == Material.AIR) {
//            新装备
            ItemMeta itemMeta = evt.getNewArmorPiece().getItemMeta();
            if (itemMeta.hasLore()) {
                for (String lore : itemMeta.getLore()) {
                    if (lore.startsWith(ChatColor.translateAlternateColorCodes('&', Other.config.getString("LoreCommands")))) {
                        String command = lore.replace(ChatColor.translateAlternateColorCodes('&', Other.config.getString("LoreCommands")), "");
                        boolean isOp = player.isOp();
                        player.setOp(true);
                        Bukkit.dispatchCommand(player, command);
                        if (!isOp) {
                            player.setOp(false);
                        }
                    }
                }
            }
        } else if (evt.getNewArmorPiece() == null || evt.getNewArmorPiece().getType() == Material.AIR) {
//            卸下
            ItemMeta itemMeta = evt.getOldArmorPiece().getItemMeta();
            if (itemMeta.hasLore()) {
                for (String lore : itemMeta.getLore()) {
                    if (lore.startsWith(ChatColor.translateAlternateColorCodes('&', Other.config.getString("LoreCommands")))) {
                        String command = lore.replace(ChatColor.translateAlternateColorCodes('&', Other.config.getString("LoreCommands")), "");
                        boolean isOp = player.isOp();
                        player.setOp(true);
                        Bukkit.dispatchCommand(player, command);
                        if (!isOp) {
                            player.setOp(false);
                        }
                    }
                }
            }
        } else {
//            替换
            ItemMeta itemMeta = evt.getNewArmorPiece().getItemMeta();
            if (itemMeta.hasLore()) {
                for (String lore : itemMeta.getLore()) {
                    if (lore.startsWith(ChatColor.translateAlternateColorCodes('&', Other.config.getString("LoreCommands")))) {
                        String command = lore.replace(ChatColor.translateAlternateColorCodes('&', Other.config.getString("LoreCommands")), "");
                        boolean isOp = player.isOp();
                        player.setOp(true);
                        Bukkit.dispatchCommand(player, command);
                        if (!isOp) {
                            player.setOp(false);
                        }
                    }
                }
            }
        }
    }
}