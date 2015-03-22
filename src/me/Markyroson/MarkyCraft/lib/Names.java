package me.Markyroson.MarkyCraft.lib;

import org.bukkit.ChatColor;

public class Names {
	public static final class Items	//Name data for all items
	{
		public static final class Apple
		{
			public static final String name = ChatColor.DARK_AQUA + "Apple";	//apple item name
			public static final String lore = ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$5" + "\n" + ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$5";	//apple lore
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
			public static final int size = 9;	//Number of inventory slots (a.k.a. slots)
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