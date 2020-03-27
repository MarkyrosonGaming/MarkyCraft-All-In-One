package me.Markyroson.MarkyCraft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

/**
 * @author Markyroson
 */
public class Stores {
    protected static Inventory shop;        // shop inventory
    protected static Inventory navigator;   // server navigator inventory
    protected static Inventory books;        // books inventory

    private static void Shop()    // Shop/store setup
    {
        shop = Bukkit.createInventory(null, 18, "Marky" + ChatColor.GOLD + "Craft " + ChatColor.DARK_GREEN + "Survival " + ChatColor.BLACK + "Shop");
        shop.setItem(0, Api.createItem(Material.APPLE, 1, 0, ChatColor.DARK_AQUA + "Apple", ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$5", ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$5"));
        shop.setItem(1, Api.createItem(Material.WATER_BUCKET, 1, 0, ChatColor.AQUA + "Water Bucket", ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$75", ChatColor.WHITE + "Cannot be sold to shop"));
        shop.setItem(2, Api.createItem(Material.OBSIDIAN, 64, 0, ChatColor.AQUA + "Obsidian", ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$175", ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$125"));
        shop.setItem(3, Api.createItem(Material.ENDER_STONE, 32, 0, ChatColor.AQUA + "EndStone", ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$150", ChatColor.WHITE + "Right Click to sell for " + "$50"));
        shop.setItem(4, Api.createItem(Material.NETHERRACK, 64, 0, ChatColor.AQUA + "Netherrack", ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$62.50", ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$25"));
        shop.setItem(5, Api.createItem(Material.GLOWSTONE, 16, 0, ChatColor.AQUA + "Glowstone", ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$100", ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$10"));
        shop.setItem(6, Api.createItem(Material.SAND, 64, 0, ChatColor.AQUA + "Sand", ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$75", ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$17.50"));
        shop.setItem(7, Api.createItem(Material.COBBLESTONE, 64, 0, ChatColor.AQUA + "Cobblestone", ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$100", ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$25"));
        shop.setItem(8, Api.createItem(Material.STONE, 64, 0, ChatColor.AQUA + "Stone", ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$137.50", ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$37.50"));
        shop.setItem(9, Api.createItem(Material.DIRT, 64, 0, ChatColor.AQUA + "Dirt", ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$75", ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$17.50"));
        shop.setItem(10, Api.createItem(Material.GRASS, 64, 0, ChatColor.AQUA + "Grass", ChatColor.WHITE + "Price: " + ChatColor.GOLD + "$92.50", ChatColor.WHITE + "Right Click to sell for " + ChatColor.GOLD + "$92.50"));
        shop.setItem(11, Api.createItem(Material.ICE, 16, 0, ChatColor.AQUA + "Ice", "Price: " + ChatColor.GOLD + "$150", ChatColor.WHITE + "Cannot be sold to shop"));
        //shop.setItem(12, Api.createItem(Material.PACKED_ICE, 16, 0, packedice.name, packedice.lore, packedice.lore2));
        shop.setItem(12, Api.createItem(Material.PACKED_ICE, 16, 0, ChatColor.AQUA + "PackedIce", "Price: " + ChatColor.GOLD + "$75", "Cannot be sold to shop"));
        shop.setItem(13, Api.createItem(Material.BEDROCK, 1, 0, ChatColor.AQUA + "Bedrock", ChatColor.WHITE + "Right click to sell for " + ChatColor.GOLD + "$99999999999999999"));
    }

    /**
     * This method is in charge of setting up Server Navigator
     */
    private static void ServerNavigator() {
        navigator = Bukkit.createInventory(null, 9, ChatColor.AQUA + "Server " + ChatColor.GOLD + "Navigator");
        navigator.setItem(0, Api.createItem(Material.BAKED_POTATO, 1, 0, ChatColor.GOLD + "Hub", "Teleports you to the " + ChatColor.GOLD + "Hub"));
    }

    /**
     * This method is in charge of setting up books
     */
    private static void Books() {
        books = Bukkit.createInventory(null, 9, ChatColor.AQUA + "Server " + ChatColor.GOLD + "Books");
        books.setItem(0, Api.createItem(Material.BOOK, 1, 0, ChatColor.GOLD + "Web Book", ChatColor.AQUA + "Click " + ChatColor.GOLD + "Me!"));
    }

    /**
     * Register shop, server navigator, and books
     */
    public static void register() {
        Shop();
        ServerNavigator();
        Books();
    }
}    // end of class