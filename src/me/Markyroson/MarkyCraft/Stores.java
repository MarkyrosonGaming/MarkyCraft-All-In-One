package me.Markyroson.MarkyCraft;

import me.Markyroson.MarkyCraft.lib.Names;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

/**
* @author Markyroson
*/
public class Stores {
	public static Inventory shop;	//Creates shop inventory
	public static Inventory navigator; //Creates server navigator inventory
	public static Inventory books;	//Creates books inventory
	
	private static Names.Items.Shop.Bedrock bedrock;
	private static Names.Items.Shop.EndStone endstone;
	private static Names.Items.Shop.Obsidian obsidian;
	private static Names.Items.Shop.WaterBucket waterbucket;
	private static Names.Items.Shop.Netherrack netherrack;
	private static Names.Items.Shop.Sand sand;
	private static Names.Items.Shop.Grass grass;
	private static Names.Items.Shop.Ice ice;
	private static Names.Items.Shop.PackedIce packedice;
	private static Names.Items.Shop.CobbleStone cobblestone;
	private static Names.Items.Shop.GlowStone glowstone;
	private static Names.Items.Shop.Dirt dirt;
	private static Names.Items.Shop.Stone stone;
	private static Names.Items.HubPotato hubpotato;
	private static Names.Items.Books.WebBook webBook;
	
	public final static class Setups
	{
		@SuppressWarnings("static-access")
		public static void Shop()	//Shop/store setup
		{
			shop = Bukkit.createInventory(null, Names.Info.Shop.size, Names.Info.Shop.name);
			shop.setItem(0, Api.createItem(Material.APPLE, 1, 0, Names.Items.Shop.Apple.name, Names.Items.Shop.Apple.lore, Names.Items.Shop.Apple.lore2));
			shop.setItem(1, Api.createItem(Material.WATER_BUCKET, 1, 0, waterbucket.name, waterbucket.lore, waterbucket.lore2));
			shop.setItem(2, Api.createItem(Material.OBSIDIAN, 64, 0, obsidian.name, obsidian.lore1, obsidian.lore2));
			shop.setItem(3, Api.createItem(Material.ENDER_STONE, 32, 0, endstone.name, endstone.lore1, endstone.lore2));
			shop.setItem(4, Api.createItem(Material.NETHERRACK, 64, 0, netherrack.name, netherrack.lore1, netherrack.lore2));
			shop.setItem(5, Api.createItem(Material.GLOWSTONE, 16, 0, glowstone.name, glowstone.lore1, glowstone.lore2));
			shop.setItem(6, Api.createItem(Material.SAND, 64, 0, sand.name, sand.lore, sand.lore2));
			shop.setItem(7, Api.createItem(Material.COBBLESTONE, 64, 0, cobblestone.name, cobblestone.lore, cobblestone.lore2));
			shop.setItem(8, Api.createItem(Material.STONE, 64, 0, stone.name, stone.lore, stone.lore2));
			shop.setItem(9, Api.createItem(Material.DIRT, 64, 0, dirt.name, dirt.lore, dirt.lore2));
			shop.setItem(10, Api.createItem(Material.GRASS, 64, 0, grass.name, grass.lore, grass.lore2));
			shop.setItem(11, Api.createItem(Material.ICE, 16, 0, ice.name, ice.lore, ice.lore2));
			shop.setItem(12, Api.createItem(Material.PACKED_ICE, 16, 0, packedice.name, packedice.lore, packedice.lore2));
			shop.setItem(13, Api.createItem(Material.BEDROCK, 1, 0, bedrock.name, bedrock.lore, null));
		}
		@SuppressWarnings("static-access")
		/**
		* This method is in charge of setting up Server Navigator
		*/
		public static void ServerNavigator()
		{
			navigator = Bukkit.createInventory(null, Names.Info.ServerNavigator.size, Names.Info.ServerNavigator.name);
			navigator.setItem(0, Api.createItem(Material.BAKED_POTATO, 1, 0, hubpotato.name, hubpotato.lore, null));
		}
		@SuppressWarnings("static-access")
		/**
		* This method is in charge of setting up books
		*/
		public static void Books()
		{
			books = Bukkit.createInventory(null, Names.Info.Books.size, Names.Info.Books.name);
			books.setItem(0, Api.createItem(Material.BOOK, 1, 0, webBook.name, webBook.lore, null));
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
