package me.Markyroson.MarkyCraft;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.logging.Logger;

import me.Markyroson.MarkyCraft.Stores;
import me.Markyroson.MarkyCraft.utils.VersionUtils;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class Example extends JavaPlugin implements Listener {
	
    private Api api;
	private static Example plugin;
	private HashMap<UUID, Integer> money = new HashMap<>();
	private static Example instance;
	public static Economy econ = null;
	private static final Logger log = Logger.getLogger("Minecraft");
	//private Stores.Setups storeSetups;
	
	public void onEnable() {
		instance = this;
		 if (!setupEconomy() ) {
	            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
	            getServer().getPluginManager().disablePlugin(this);
	            return;
	        }
		 
		String version = VersionUtils.getBukkitVersion();
		if(version == null)
		{
			version = VersionUtils.getMinecraftVersion();
			if ("1.7.10".equals(version))
			{
				version = "v1_6_R3";
			}
			else if ("1.8".equals(version))
			{
				version = "v1_8_R1";
			}
			else if ("1.8.3".equals(version))
			{
				version = "v1_8_3_R2";
			}
			else
			{
				version = null;
			}
		}
		if("v1_7_R1".equals(version))
		{
			
		}
		else if("v1_7_R2".equals(version))
		{
			
		}
		else if("v1_7_R3".equals(version))
		{
			
		}
		else if("v1_7_R4".equals(version))
		{
			
		}
		else if("v1_8_R1".equals(version))
		{
			
		}
		else if("v1_8_R2".equals(version))
		{
			
		}
		//else if("v8_3_R1".equals(version))
	//	{
			
	//	}
		else
		{
			printWarnAndDisable(new String[] {
				"******************************************************",
				"     This version of MarkyCraft All-In-One can",
				"     only work on these server versions:",
				"     1.7 to 1.8.3.",
				"     The plugin will be disabled.",
				"******************************************************" });
			
			return;
		}
	    plugin = this;
	    api = new Api();
		getServer().getPluginManager().registerEvents(new Listeners(), this);
		Stores.register();
		getConfig().options().copyDefaults(true);
		saveConfig();

	      //  setupPermissions();
	      //  setupChat();
	}
	
	public void onDisable() {
		for(Entry<UUID, Integer> entry : money.entrySet()) {
			getConfig().set(entry.getKey() + ".Silver", entry.getValue());
		}

		saveConfig();
	}
	
	public static Example getInstance() {
	    return plugin;
	}
	
	public Api getApi(){
		return api;
	}
	
    public HashMap<UUID, Integer> getMoney() {
	    return money;
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	
	    if(!(sender instanceof Player)) {
		    return false;
		}
	
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("mcshop")) {
			player.openInventory(Stores.shop);
		}
		if(cmd.getName().equalsIgnoreCase("nav")) {
			//sender.sendMessage("Hello there matty! :-)");
			//if (args[0].equalsIgnoreCase("menu")) {
				player.openInventory(Stores.navigator);
			//}
		}/* else {
			sender.sendMessage("command is shop menu");
		}*/
		if(cmd.getName().equalsIgnoreCase("books")) {
			player.openInventory(Stores.books);
		}
	/*	 if(cmd.getLabel().equals("test-economy")) {
	            // Lets give the player 1.05 currency (note that SOME economic plugins require rounding!)
	            sender.sendMessage(String.format("You have %s", econ.format(econ.getBalance(player.getName()))));
	            EconomyResponse r = econ.depositPlayer(player, 1.05);
	            if(r.transactionSuccess()) {
	                sender.sendMessage(String.format("You were given %s and now have %s", econ.format(r.amount), econ.format(r.balance)));
	            } else {
	                sender.sendMessage(String.format("An error occured: %s", r.errorMessage));
	            }
	            return true;
	        }*/
		return false;
	}
	private static void printWarnAndDisable(String... messages)
	{
		StringBuffer buffer = new StringBuffer("\n ");
		String[] arraryOfString = messages;
		int j = messages.length;
		for (int i = 0; i <j; i++)
		{
			String message = arraryOfString[i];
			buffer.append('\n');
			buffer.append(message);
		}
		buffer.append('\n');
		System.out.println(buffer.toString());
		try
		{
			Thread.sleep(5000L);
		}
		catch (InterruptedException localInteruptedException) {}
		instance.setEnabled(false);
	}
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

}