package me.Markyroson.MarkyCraft;

import me.Markyroson.MarkyCraft.lib.Names;
import me.Markyroson.MarkyCraft.lib.Names.Info;
import me.Markyroson.MarkyCraft.lib.Names.Inventories;
import me.Markyroson.MarkyCraft.lib.Names.ShopItemsWithTwoLore;
import me.Markyroson.MarkyCraft.lib.Names.Shop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

/**
* @author Markyroson
*/
public class Stores {
	public static Inventory shop;	// Creates shop inventory
	public static Inventory navigator; // Creates server navigator inventory
	public static Inventory books;	// Creates books inventory
		
	public final static class Setups
	{
		public static void Shop()	// Shop/store setup
		{
			shop = Bukkit.createInventory(null, Info.getInvSize(Inventories.SHOP), Info.getName(Inventories.SHOP));
			shop.setItem(0, Api.createItem(Material.APPLE, 1, 0, Names.getItemName(ShopItemsWithTwoLore.APPLE), Names.getLore(ShopItemsWithTwoLore.APPLE), Names.getLore2(ShopItemsWithTwoLore.APPLE)));
			shop.setItem(1, Api.createItem(Material.WATER_BUCKET, 1, 0, Names.getItemName(ShopItemsWithTwoLore.WATER_BUCKET), Names.getLore(ShopItemsWithTwoLore.WATER_BUCKET), Names.getLore2(ShopItemsWithTwoLore.WATER_BUCKET)));
			shop.setItem(2, Api.createItem(Material.OBSIDIAN, 64, 0, Names.getItemName(ShopItemsWithTwoLore.OBSIDIAN), Names.getLore(ShopItemsWithTwoLore.OBSIDIAN), Names.getLore2(ShopItemsWithTwoLore.OBSIDIAN)));
			shop.setItem(3, Api.createItem(Material.ENDER_STONE, 32, 0, Names.getItemName(ShopItemsWithTwoLore.END_STONE), Names.getLore(ShopItemsWithTwoLore.END_STONE), Names.getLore2(ShopItemsWithTwoLore.END_STONE)));
			shop.setItem(4, Api.createItem(Material.NETHERRACK, 64, 0, Names.getItemName(ShopItemsWithTwoLore.NETHERRACK), Names.getLore(ShopItemsWithTwoLore.NETHERRACK), Names.getLore2(ShopItemsWithTwoLore.NETHERRACK)));
			shop.setItem(5, Api.createItem(Material.GLOWSTONE, 16, 0, Names.getItemName(ShopItemsWithTwoLore.GLOW_STONE), Names.getLore(ShopItemsWithTwoLore.GLOW_STONE), Names.getLore2(ShopItemsWithTwoLore.GLOW_STONE)));
			shop.setItem(6, Api.createItem(Material.SAND, 64, 0, Names.getItemName(ShopItemsWithTwoLore.SAND), Names.getLore(ShopItemsWithTwoLore.SAND), Names.getLore2(ShopItemsWithTwoLore.SAND)));
			shop.setItem(7, Api.createItem(Material.COBBLESTONE, 64, 0, Names.getItemName(ShopItemsWithTwoLore.COBBLE_STONE), Names.getLore(ShopItemsWithTwoLore.COBBLE_STONE), Names.getLore2(ShopItemsWithTwoLore.COBBLE_STONE)));
			shop.setItem(8, Api.createItem(Material.STONE, 64, 0, Names.getItemName(ShopItemsWithTwoLore.STONE), Names.getLore(ShopItemsWithTwoLore.STONE), Names.getLore2(ShopItemsWithTwoLore.STONE)));
			shop.setItem(9, Api.createItem(Material.DIRT, 64, 0, Names.getItemName(ShopItemsWithTwoLore.DIRT), Names.getLore(ShopItemsWithTwoLore.DIRT), Names.getLore2(ShopItemsWithTwoLore.DIRT)));
			shop.setItem(10, Api.createItem(Material.GRASS, 64, 0, Names.getItemName(ShopItemsWithTwoLore.GRASS), Names.getLore(ShopItemsWithTwoLore.GRASS), Names.getLore2(ShopItemsWithTwoLore.GRASS)));
			shop.setItem(11, Api.createItem(Material.ICE, 16, 0, Names.getItemName(ShopItemsWithTwoLore.ICE), Names.getLore(ShopItemsWithTwoLore.ICE), Names.getLore2(ShopItemsWithTwoLore.ICE)));
			shop.setItem(12, Api.createItem(Material.PACKED_ICE, 16, 0, Names.getItemName(ShopItemsWithTwoLore.PACKED_ICE), Names.getLore(ShopItemsWithTwoLore.PACKED_ICE), Names.getLore2(ShopItemsWithTwoLore.PACKED_ICE)));
			shop.setItem(13, Api.createItem(Material.BEDROCK, 1, 0, Names.getItemName(ShopItemsWithTwoLore.BEDROCK), Names.getLore(ShopItemsWithTwoLore.BEDROCK)));
		}
		/**
		* This method is in charge of setting up Server Navigator
		*/
		public static void ServerNavigator()
		{
			navigator = Bukkit.createInventory(null, Info.getInvSize(Inventories.SERVER_NAVIGATOR), Info.getName(Inventories.SERVER_NAVIGATOR));
			navigator.setItem(0, Api.createItem(Material.BAKED_POTATO, 1, 0, Names.getItemName(Shop.HUB_POTATO), Names.getLore(Shop.HUB_POTATO)));
		}
		
		/**
		* This method is in charge of setting up books
		*/
		public static void Books()
		{
			books = Bukkit.createInventory(null, Info.getInvSize(Inventories.BOOKS), Info.getName(Inventories.BOOKS));
			books.setItem(0, Api.createItem(Material.BOOK, 1, 0, Names.getItemName(Shop.BOOK_WEB_BOOK), Names.getLore(Shop.BOOK_WEB_BOOK)));
		}
	}
	/**
	* Register shop, server navigator, and books
	*/
	public static void register()
	{
		Setups.Shop();
		Setups.ServerNavigator();
		Setups.Books();
	}
}
