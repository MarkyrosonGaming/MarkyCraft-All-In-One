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
			shop.setItem(0, Api.createItem(Material.APPLE, 1, 0, Names.Items.Shop.Apple.name, Names.Items.Shop.Apple.lore, Names.Items.Shop.Apple.lore2));
			shop.setItem(1, Api.createItem(Material.WATER_BUCKET, 1, 0, Names.Items.Shop.WaterBucket.name, Names.Items.Shop.WaterBucket.lore, Names.Items.Shop.WaterBucket.lore2));
			shop.setItem(2, Api.createItem(Material.OBSIDIAN, 64, 0, Names.Items.Shop.Obsidian.name, Names.Items.Shop.Obsidian.lore1, Names.Items.Shop.Obsidian.lore2));
			shop.setItem(3, Api.createItem(Material.ENDER_STONE, 32, 0, Names.Items.Shop.EndStone.name, Names.Items.Shop.EndStone.lore1, Names.Items.Shop.EndStone.lore2));
			shop.setItem(4, Api.createItem(Material.NETHERRACK, 64, 0, Names.Items.Shop.Netherrack.name, Names.Items.Shop.Netherrack.lore1, Names.Items.Shop.Netherrack.lore2));
			shop.setItem(5, Api.createItem(Material.GLOWSTONE, 16, 0, Names.Items.Shop.GlowStone.name, Names.Items.Shop.GlowStone.lore1, Names.Items.Shop.GlowStone.lore2));
			//finish adding items (sand, cobblestone, stone, dirt, grass, ice, packed ice)
			shop.setItem(6, Api.createItem(Material.SAND, 64, 0, Names.Items.Shop.Sand.name, Names.Items.Shop.Sand.lore, Names.Items.Shop.Sand.lore2));
			shop.setItem(7, Api.createItem(Material.COBBLESTONE, 64, 0, Names.Items.Shop.CobbleStone.name, Names.Items.Shop.CobbleStone.lore, Names.Items.Shop.CobbleStone.lore2));
			shop.setItem(8, Api.createItem(Material.STONE, 64, 0, Names.Items.Shop.Stone.name, Names.Items.Shop.Stone.lore, Names.Items.Shop.Stone.lore2));
			shop.setItem(9, Api.createItem(Material.DIRT, 64, 0, Names.Items.Shop.Dirt.name, Names.Items.Shop.Dirt.lore, Names.Items.Shop.Dirt.lore2));
			shop.setItem(10, Api.createItem(Material.GRASS, 64, 0, Names.Items.Shop.Grass.name, Names.Items.Shop.Grass.lore, Names.Items.Shop.Grass.lore2));
			shop.setItem(11, Api.createItem(Material.ICE, 16, 0, Names.Items.Shop.Ice.name, Names.Items.Shop.Ice.lore, Names.Items.Shop.Ice.lore2));
			shop.setItem(12, Api.createItem(Material.PACKED_ICE, 16, 0, Names.Items.Shop.PackedIce.name, Names.Items.Shop.PackedIce.lore, Names.Items.Shop.PackedIce.lore2));
		}
		public static void ServerNavigator()	//ServerNavigator setup
		{
			navigator = Bukkit.createInventory(null, Names.Info.ServerNavigator.size, Names.Info.ServerNavigator.name);
			navigator.setItem(0, Api.createItem(Material.BAKED_POTATO, 1, 0, Names.Items.HubPotato.name, Names.Items.HubPotato.lore, null));
		}
		public static void Books()	//Books setup
		{
			books = Bukkit.createInventory(null, Names.Info.Books.size, Names.Info.Books.name);
			books.setItem(0, Api.createItem(Material.BOOK, 1, 0, Names.Items.Books.WebBook.name, Names.Items.Books.WebBook.lore, null));
		}
	}
	public static void register()
	{
		Setups.Shop();
		Setups.ServerNavigator();
		Setups.Books();
	}
}
