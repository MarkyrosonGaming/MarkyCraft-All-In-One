package me.Markyroson.MarkyCraft;

import java.util.logging.Logger;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.Markyroson.MarkyCraft.utils.VersionUtils;
import net.milkbowl.vault.economy.Economy;

public class Base extends JavaPlugin {
	protected static final Logger log = Logger.getLogger("Minecraft");	// set up logger
	protected Api api;
	protected static Example instance;	// reference to plugin
	public static Economy econ = null;
	/**
	* Get reference to API
	* @returns API reference
	*/
	public Api getApi(){
		return api;
	}
	public void onEnable() {
		//instance = this;
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
	   // plugin = this;	//make plugin equal to this
	    //api = new Api();
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
	protected boolean setupEconomy() {
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
