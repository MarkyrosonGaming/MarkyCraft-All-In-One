package me.Markyroson.MarkyCraft;

import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Markyroson.MarkyCraft.Example;

public class Api {
/*
 * This is the class for a custom virtual currency API that I created following a
 * tutorial. I am working to move away from this API and hope to eventually work it
 * out of the project completely.
 */
	private Example plugin = Example.getInstance();
/**
 * @param p the player
 * @param i the amount of silver you wish to give the player
 */
	public void giveSilver(Player p, int i) {
		UUID uuid = p.getUniqueId();
		plugin.getMoney().put(uuid, plugin.getMoney().get(uuid) + i);
		p.sendMessage("§2§l$" + i + " silver received!");
	}
/**
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
}