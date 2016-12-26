package me.Markyroson.MarkyCraft;

import java.util.Arrays;
import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import me.Markyroson.MarkyCraft.Main;

public class Api {
/*
 * This is the class for a custom virtual currency API that I created following a
 * tutorial. I am working to move away from this API's currency systems and implement
 * vault more so and hope to eventually work out the silver currency from the project
 * completely.
 * 
 * UPDATE: As of 27 March 2015 the giveSilver and takeSilver functions have been
 * deprecated and have now been completely moved away from. Mission complete on this front :-)
 */
	private Main plugin = Main.getInstance();
/**
 * @deprecated
 * @param p the player
 * @param i the amount of silver you wish to give the player
 */
	public void giveSilver(Player p, int i) {
		UUID uuid = p.getUniqueId();
		plugin.getMoney().put(uuid, plugin.getMoney().get(uuid) + i);
		p.sendMessage("§2§l$" + i + " silver received!");
	}
/**
 * @deprecated
 * @param p the player
 * @param i the amount of silver you wish to take from the player
 */
	public void takeSilver(Player p, int i) {
		UUID uuid = p.getUniqueId();
		plugin.getMoney().put(uuid, plugin.getMoney().get(uuid) - i);
		p.sendMessage("§c§l$" + i + " silver taken!");
	}
/**
 * @param p the player
 * @param i amount to check for from player
 */
	public boolean hasEnough(Player p, int i) {
		return plugin.getMoney().get(p.getUniqueId()) >= i;
	}
	/**
	 * @param material (ie Material.APPLE is a material)
	 * @param amount amount to create
	 * @param shrt set to 0
	 * @param displayname name of material
	 * @param lore lore of material
	 * @param lore2 usually null but is not null if item has two lines of lore
	 * @param return returns item
	 */
	
	public static ItemStack createItem(Material material, int amount, int shrt, String displayname, String lore, String lore2) {
		ItemStack item = new ItemStack(material, amount, (short) shrt);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		meta.setLore(Arrays.asList(lore, lore2));
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack createItem(Material material, int amount, int shrt, String displayname, String lore) {
		ItemStack item = new ItemStack(material, amount, (short) shrt);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * 
	 * @param material Material.WRITTEN_BOOK
	 * @param Author Author name
	 * @param Title Book title
	 * @param p Player
	 * @param page1 Page 1
	 * @param page2 Page 2
	 * @param page3 Page 3
	 * @param page4 Page 4 (any page can be nulled if it does not exist in current context etc)
	 * @return
	 */
	public static ItemStack createBook(Material material, String Author, String Title, Player p, String page1, String page2, String page3, String page4) {
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
	    BookMeta bm = (BookMeta) book.getItemMeta();
	    bm.addPage(page1, page2, page3, page4);	//set book pages
	            bm.setAuthor(Author);	//set book author
	            bm.setTitle(Title);	//set book name
	            book.setItemMeta(bm);
	    p.getInventory().addItem(book);
		return book;
	}
}