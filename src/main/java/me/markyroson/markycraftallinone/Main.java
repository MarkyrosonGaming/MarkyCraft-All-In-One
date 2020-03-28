package me.markyroson.markycraftallinone;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

import me.markyroson.markycraftallinone.Stores;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private Api api;
    private static Main plugin;    // reference to plugin
    private HashMap<UUID, Integer> money = new HashMap<>();
    //TODO: Sort out two references to same thing
    public static Economy econ = null;
    private static final Logger log = Logger.getLogger("Minecraft");    // set up logger

    public void onEnable() {
        if (!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        plugin = this;
        api = new Api();
        getServer().getPluginManager().registerEvents(new Listeners(), this);    //register Listener events
        Stores.register();    //register stores/GUIs
        getConfig().options().copyDefaults(true);    //copies defaults to config
        saveConfig();    //saves config
    }

    public void onDisable() {

    }

    public static Main getInstance() {
        return plugin;
    }

    /**
     * Get reference to API
     *
     * @returns API reference
     */
    public Api getApi() {
        return api;
    }

    /**
     * Get money
     *
     * @returns Money
     */
    public HashMap<UUID, Integer> getMoney() {
        return money;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // If not an instance of player, do not proceed
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("shop")) {
            if (player.hasPermission("markycraft.activate_shop_gui")) {
                player.openInventory(Stores.shop);
            } else {
                player.sendMessage("Go to the survival world to access this command!");
            }
        }
        if (cmd.getName().equalsIgnoreCase("nav")) {
            player.openInventory(Stores.navigator);
        }
        if (cmd.getName().equalsIgnoreCase("books")) {
            player.openInventory(Stores.books);
        }
        return false;
    }

    /**
     * Set up economy
     */
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }
}
