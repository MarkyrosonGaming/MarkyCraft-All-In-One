package me.Markyroson.MarkyCraft;

import me.Markyroson.MarkyCraft.lib.Names;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

/**@author Markyroson
 */
public class Stores {
	public static Inventory shop;	//Creates shop inventory
	public static Inventory navigator; //Creates server navigator inventory
	public static Inventory books;	//Creates books inventory
	
	public final static class Setups
	{
		public static void Shop()	//Shop/store setup
		{
			shop = Bukkit.createInventory(null, Names.Info.Shop.size, Names.Info.Shop.name);
			shop.setItem(0, Api.createItem(Material.APPLE, 1, 0, Names.Items.Apple.name, Names.Items.Apple.lore));
		}
		public static void ServerNavigator()	//ServerNavigator setup
		{
			navigator = Bukkit.createInventory(null, Names.Info.ServerNavigator.size, Names.Info.ServerNavigator.name);
			navigator.setItem(0, Api.createItem(Material.BAKED_POTATO, 1, 0, Names.Items.HubPotato.name, Names.Items.HubPotato.lore));
		}
		public static void Books()	//Books setup
		{
			books = Bukkit.createInventory(null, Names.Info.Books.size, Names.Info.Books.name);
			books.setItem(0, Api.createItem(Material.BOOK, 1, 0, Names.Items.Books.WebBook.name, Names.Items.Books.WebBook.lore));
		}
	}
	public static void register()
	{
		Setups.Shop();
		Setups.ServerNavigator();
		Setups.Books();
	}
}
