package me.Markyroson.MarkyCraft.lib;

import me.Markyroson.MarkyCraft.Example;

import org.bukkit.ChatColor;

public class Names {
	private static Example plugin = Example.getInstance();
	public static final class Items	//Name data for all items
	{
		public static final class Shop {
			/* Template
			 * public static final class
			 * {
			 * 		public static final String name = ChatColor.AQUA + "";	//name
			 * 		public static final String lore = ChatColor.WHITE + "Price: " + ChatColor.GOLD + "";
			 * 		public static final String lore2 = ChatColor.WHITE + "" + "";
			 * }
			 */
			public static final class Apple
			{
				public static final String name = ChatColor.DARK_AQUA + "Apple";	//apple item name
				public static final String lore = ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$5";	//apple lore
				public static final String lore2 = ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$5";
			}
			public static final class WaterBucket
			{
				public static final String name = ChatColor.AQUA + "Water Bucket";	//water bucket item name
				public static final String lore = ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$75";
				public static final String lore2 = ChatColor.WHITE + "Cannot be sold to shop";
			}
			public static final class Lava_Bucket
			{
				public static final String name = ChatColor.AQUA + "Lava Bucket";	//name
				public static final String lore = ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$50";
			}
			public static final class Obsidian
			{
				public static final String name = ChatColor.AQUA + "Obsidian";	//name
				public static final String lore1 = ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$175";
				public static final String lore2 = ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$125";
			}
			public static final class EndStone
			{
				public static final String name = ChatColor.AQUA + "EndStone";	//name
				public static final String lore1 = ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$150";
				public static final String lore2 = ChatColor.WHITE + "Right Click to sell for " + "$50";
			}
			public static final class Netherrack
			{
				public static final String name = ChatColor.AQUA + "Netherrack";	//name
				public static final String lore1 = ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$62.50";
				public static final String lore2 = ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$25";
			}
			public static final class GlowStone
			{
				public static final String name = ChatColor.AQUA + "Glowstone";	//name
				public static final String lore1 = ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$100";
				public static final String lore2 = ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$10";
			}
			public static final class Gravel
			{
				public static final String name = ChatColor.AQUA + "Gravel";	//name
				public static final String lore = ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$75";
				public static final String lore2 = ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$12.50";
			}
			
			//to add listener to & to shop
			public static final class Sand
			{
					public static final String name = ChatColor.AQUA + "Sand";	//name
			 		public static final String lore = ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$75";
			  		public static final String lore2 = ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$17.50";
			}
			public static final class CobbleStone
			{
					public static final String name = ChatColor.AQUA + "Cobblestone";	//name
			 		public static final String lore = ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$100";
			  		public static final String lore2 = ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$25";
			}
			public static final class Stone
			{
					public static final String name = ChatColor.AQUA + "Stone";	//name
			 		public static final String lore = ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$137.50";
			  		public static final String lore2 = ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$37.50";
			}
			public static final class Dirt
			{
					public static final String name = ChatColor.AQUA + "Dirt";	//name
			 		public static final String lore = ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$75";
			  		public static final String lore2 = ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$17.50";
			}
			public static final class Grass
			{
					public static final String name = ChatColor.AQUA + "Grass";	//name
			 		public static final String lore = ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$92.50";
			  		public static final String lore2 = ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$92.50";
			}
			public static final class Ice
			{
					public static final String name = ChatColor.AQUA + "Ice";	//name
			 		public static final String lore = ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$150";
			  		public static final String lore2 = ChatColor.WHITE + "Cannot be sold to shop";
			}
			public static final class PackedIce
			{
					public static final String name = ChatColor.AQUA + "PackedIce";	//name
			 		public static final String lore = ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$75";
			  		public static final String lore2 = ChatColor.WHITE + "Cannot be sold to shop";
			}
		}
			public static final class HubPotato
			{
				public static final String name = ChatColor.GOLD + "Hub";	//Hub teleporter (potato) name
				public static final String lore = "Teleports you to the " + ChatColor.GOLD + "Hub";	//Hub teleporter (potatp) lore
			}
			//sub class for ALL books and information
			public static final class Books
			{
				public static final class WebBook
				{
					public static final String name = ChatColor.GOLD + "Web Book";	//Web Book name
					public static final String lore = ChatColor.AQUA + "Click " + ChatColor.GOLD + "Me!";	//Web Book lore
				}
			}
	}
	public static final class Info	//Info for all custom inventories, most likely just names will be stored here but who knows
	{
		public static final class Shop	//sub class for shop
		{
			public static final String name = "My " + ChatColor.GOLD + "Custom " + ChatColor.BLACK + "Shop";	//name of custom inventory/UI
			public static final int size = 18;	//Number of inventory slots (a.k.a. slots)
		}
		public static final class ServerNavigator	//sub class for ServerNavigator
		{
			public static final String name = ChatColor.AQUA + "Server " + ChatColor.GOLD + "Navigator";	//name of custom inventory/UI
			public static final int size = 9;	//Number of inventory slots (a.k.a. slots)
		}
		public static final class Books
		{
			public static final String name = ChatColor.AQUA + "Server " + ChatColor.GOLD + "Books";	//name of custom inventory/UI
			public static final int size = 9;	//Number of inventory slots (a.k.a. slots)
		}
	}
}