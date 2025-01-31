package com.koirdsuzu.dropratecontroller;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DropRateController extends JavaPlugin implements Listener, TabCompleter {

    private double dropRate = 1.0; // Default drop rate is 1%
    private Random random;
    private FileConfiguration config;

    @Override
    public void onEnable() {
        // Initialize config and random
        saveDefaultConfig();
        config = getConfig();
        random = new Random();

        // Load drop rate from config
        dropRate = config.getDouble("dropRate", 1.0);

        // Register event listener
        Bukkit.getPluginManager().registerEvents(this, this);

        // Set tab completer
        getCommand("dropchance").setTabCompleter(this);

        getLogger().info("DropRateController enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("DropRateController disabled.");
    }

    // Handle mob drops
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            if (livingEntity.getKiller() != null) {
                if (random.nextDouble() < dropRate / 100.0) {
                    // Leave default drops as is if the drop condition is met
                    return;
                } else {
                    // Clear drops if the condition is not met
                    event.getDrops().clear();
                }
            }
        }
    }

    // Handle block drops
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("dropratecontroller.use")) {
            return;
        }

        List<ItemStack> drops = new ArrayList<>(event.getBlock().getDrops(player.getInventory().getItemInMainHand()));
        event.setDropItems(false); // Prevent default drops

        for (ItemStack drop : drops) {
            if (random.nextDouble() < dropRate / 100.0) {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), drop);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("dropchance")) {
            // Check permission
            if (!sender.hasPermission("dropratecontroller.use")) {
                sender.sendMessage(ChatColor.RED + config.getString("messages.no_permission", "You do not have permission to use this command."));
                return true;
            }

            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + config.getString("messages.usage", "Usage: /dropchance <get|set <value>>"));
                return true;
            }

            if (args[0].equalsIgnoreCase("get")) {
                sender.sendMessage(ChatColor.GREEN + String.format(config.getString("messages.get", "Current drop rate: %.2f%%"), dropRate));
                return true;
            }

            if (args[0].equalsIgnoreCase("set")) {
                if (args.length < 2) {
                    sender.sendMessage(ChatColor.RED + config.getString("messages.invalid", "Invalid value. Please provide a number between 0 and 100."));
                    return true;
                }
                try {
                    double newRate = Double.parseDouble(args[1]);
                    if (newRate < 0 || newRate > 100) {
                        sender.sendMessage(ChatColor.RED + config.getString("messages.range_error", "Please enter a number between 0 and 100."));
                        return true;
                    }
                    dropRate = newRate;
                    config.set("dropRate", dropRate);
                    saveConfig();
                    sender.sendMessage(ChatColor.GREEN + String.format(config.getString("messages.set", "Drop rate updated to %.2f%%"), dropRate));
                } catch (NumberFormatException e) {
                    sender.sendMessage(ChatColor.RED + config.getString("messages.invalid", "Invalid value. Please provide a number between 0 and 100."));
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        if (command.getName().equalsIgnoreCase("dropchance")) {
            if (args.length == 1) {
                if ("get".startsWith(args[0].toLowerCase())) completions.add("get");
                if ("set".startsWith(args[0].toLowerCase())) completions.add("set");
            } else if (args.length == 2 && args[0].equalsIgnoreCase("set")) {
                completions.add("0");
                completions.add("50");
                completions.add("100");
            }
        }
        return completions;
    }
}
