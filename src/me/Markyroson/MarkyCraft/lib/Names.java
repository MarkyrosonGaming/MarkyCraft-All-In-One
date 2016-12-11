package me.Markyroson.MarkyCraft.lib;

import org.bukkit.ChatColor;

/**
 * 
 * @author Markyroson
 *
 */
public class Names {
	/**
	 * Inventories
	 * @author Markyroson
	 *
	 */
	public static enum Inventories
	{
		SHOP,
		SERVER_NAVIGATOR,
		BOOKS
	}
	/**
	 * Shop items with two lore
	 * @author Markyroson
	 *
	 */
	public static enum ShopItemsWithTwoLore
	{
		APPLE,
		COBBLE_STONE,
		DIRT,
		END_STONE,
		GLOW_STONE,
		GRASS,
		GRAVEL,
		ICE,
		LAVA_BUCKET,
		NETHERRACK,
		OBSIDIAN,
		PACKED_ICE,
		WATER_BUCKET,
		SAND,
		STONE,
		BEDROCK
	}
	/**
	 * Non-shop items (only have 1 lore)
	 * @author Markyroson
	 *
	 */
	public static enum Shop
	{
		BEDROCK,
		HUB_POTATO,
		BOOK_WEB_BOOK
	}
	/**
	 * Get name of item (non-shop related)
	 * @param shop Which item?
	 * @return Item name
	 */
	public static String getItemName(Shop shop)
	{
		switch(shop)
		{
			case HUB_POTATO:
				return ChatColor.GOLD + "Hub";
			case BOOK_WEB_BOOK:
				return ChatColor.GOLD + "Web Book";
			default:
				return null;
		}
	}
	/**
	 * Get name of item (shop-related)
	 * @param shop Which item?
	 * @return Item name
	 */
	public static String getItemName(ShopItemsWithTwoLore shop)
	{
		switch(shop)
		{
			case BEDROCK:
				return ChatColor.AQUA + "Bedrock";
			case APPLE:
				return ChatColor.DARK_AQUA + "Apple";
			case COBBLE_STONE:
				return ChatColor.AQUA + "Cobblestone";
			case DIRT:
				return ChatColor.AQUA + "Dirt";
			case END_STONE:
				return ChatColor.AQUA + "EndStone";
			case GLOW_STONE:
				return ChatColor.AQUA + "Glowstone";
			case GRASS:
				return ChatColor.AQUA + "Grass";
			case GRAVEL:
				return ChatColor.AQUA + "Gravel";
			case ICE:
				return ChatColor.AQUA + "Ice";
			case LAVA_BUCKET:
				return ChatColor.AQUA + "Lava Bucket";
			case NETHERRACK:
				return ChatColor.AQUA + "Netherrack";
			case OBSIDIAN:
				return ChatColor.AQUA + "Obsidian";
			case PACKED_ICE:
				return ChatColor.AQUA + "PackedIce";
			case SAND:
				return ChatColor.AQUA + "Sand";
			case STONE:
				return ChatColor.AQUA + "Stone";
			case WATER_BUCKET:
				return ChatColor.AQUA + "Water Bucket";
			default:
				return null;
		}
	}
	/**
	 * Get the first line of lore (non-shop)
	 * @param lore Which item?
	 * @return Item lore
	 */
	public static String getLore(Shop lore)
	{
		switch(lore)
		{
			case HUB_POTATO:
				return "Teleports you to the " + ChatColor.GOLD + "Hub";
			case BOOK_WEB_BOOK:
				return ChatColor.AQUA + "Click " + ChatColor.GOLD + "Me!";
			default:
				return null;
		}
	}
	/**
	 * Get first line of lore (shop itself)
	 * @param lore Which item?
	 * @return Item lore
	 */
	public static String getLore(ShopItemsWithTwoLore lore)
	{
		switch(lore)
		{
			case BEDROCK:
				return ChatColor.WHITE + "Right click to sell for " + ChatColor.GOLD + "$1000000000000";
			case APPLE:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$5";
			case COBBLE_STONE:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$100";
			case DIRT:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$75";
			case END_STONE:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$150";
			case GLOW_STONE:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$100";
			case GRASS:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$92.50";
			case GRAVEL:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$75";
			case ICE:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$150";
			case LAVA_BUCKET:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$50";
			case NETHERRACK:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$62.50";
			case OBSIDIAN:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$175";
			case PACKED_ICE:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$75";
			case SAND:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$75";
			case STONE:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$137.50";
			case WATER_BUCKET:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$75";
			default:
				return null;
		}
	}
	/**
	 * Get second line of lore (shop itself)
	 * @param lore Which item?
	 * @return Item lore
	 */
	public static String getLore2(ShopItemsWithTwoLore lore)
	{
		switch(lore)
		{
			case APPLE:
				return ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$5";
			case COBBLE_STONE:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$100";
			case DIRT:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$75";
			case END_STONE:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$150";
			case GLOW_STONE:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$100";
			case GRASS:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$92.50";
			case GRAVEL:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$75";
			case ICE:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$150";
			case NETHERRACK:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$62.50";
			case OBSIDIAN:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$175";
			case PACKED_ICE:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$75";
			case SAND:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$75";
			case STONE:
				return ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$137.50";
			case WATER_BUCKET:
				return ChatColor.WHITE + "Cannot be sold to shop";
			default:
				return null;
		}
	}
	public static final class Info	//Info for all custom inventories, most likely just names will be stored here but who knows
	{
		/**
		 * Get inventory name
		 * @param inv Which inventory
		 * @return Name of inventory
		 */
		public static String getName(Inventories inv)
		{
			switch(inv)
			{
				case SHOP:
					return "Marky" + ChatColor.GOLD + "Craft " + ChatColor.DARK_GREEN + "Survival " + ChatColor.BLACK + "Shop";
				case SERVER_NAVIGATOR:
					return ChatColor.AQUA + "Server " + ChatColor.GOLD + "Navigator";
				case BOOKS:
					return ChatColor.AQUA + "Server " + ChatColor.GOLD + "Books";
				default:
					return null;	// should never get here
			}
		}
		/**
		 * Get the inventory size
		 * @param inv Which inventory?
		 * @return Number of inventory slots
		 */
		public static int getInvSize(Inventories inv)
		{
			switch(inv)
			{
				case SHOP:
					return 18;
				case SERVER_NAVIGATOR:
					return 9;
				case BOOKS:
					return 9;
				default:
					return 0;	// should never get here
			}
		}
	}
	public static class Permissons
	{
		public static final String activate_shop_gui = "activate_shop_gui";
	}
}