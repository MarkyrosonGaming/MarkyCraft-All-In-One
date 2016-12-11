package me.Markyroson.MarkyCraft;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;
import me.Markyroson.MarkyCraft.Stores;
import me.Markyroson.MarkyCraft.lib.Names;
import me.Markyroson.MarkyCraft.utils.VersionUtils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

//TODO: Clean up code
public class Example extends JavaPlugin implements Listener {
	
    private Api api;
	private static Example plugin;	// reference to plugin
	private HashMap<UUID, Integer> money = new HashMap<>();
	//TODO: Sort out two references to same thing
	private static Example instance;
	public static Economy econ = null;
	private static final Logger log = Logger.getLogger("Minecraft");	// set up logger
	private static Names.Permissons perms;
	
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
		else if("v1_8_R3".equals(version))
		{
			
		}
		else if("v1_8_R4".equals(version))
		{
			
		}
		else if("v1_9_R1".equals(version))
		{
			
		}
		else if("v1_9_R2".equals(version))
		{
			
		}
		else if("v1_10_R1".equals(version))
		{
			
		}
		else if("v1_10_R2".equals(version))
		{
			
		}
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
	    plugin = this;	//make plugin equal to this
	    api = new Api();
		getServer().getPluginManager().registerEvents(new Listeners(), this);	//register Listener events
		Stores.register();	//register stores/GUIs
		getConfig().options().copyDefaults(true);	//copies defaults to config
		saveConfig();	//saves config
	}
	
	public void onDisable() {
		/*for(Entry<UUID, Integer> entry : money.entrySet()) {
			getConfig().set(entry.getKey() + ".Silver", entry.getValue());
		}*/
		//saveConfig();	//save config
	}
	
	public static Example getInstance() {
	    return plugin;
	}
	
	/**
	* Get reference to API
	* @returns API reference
	*/
	public Api getApi(){
		return api;
	}
	
	/**
	* Get money
	* @returns Money
	*/
    	public HashMap<UUID, Integer> getMoney() {
	    return money;
	}

	@SuppressWarnings("static-access")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	
	    // If not an instance of player, do not proceed
	    if(!(sender instanceof Player)) {
		    return false;
	    }
	
		Player player = (Player) sender;
		
			if (cmd.getName().equalsIgnoreCase("shop")) {
				if (player.hasPermission(perms.activate_shop_gui)) {
					player.openInventory(Stores.shop);
				} else {
					player.sendMessage("Go to the survival world to access this command!");
				}
		}
		if(cmd.getName().equalsIgnoreCase("nav")) {
				player.openInventory(Stores.navigator);
		}
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
	/**
	* Print a warning in console and disable plugin
	* @param messages Message to display
	*/
	public static void printWarnAndDisable(String... messages)
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
		return econ != null;
    	}
}	// end of class
