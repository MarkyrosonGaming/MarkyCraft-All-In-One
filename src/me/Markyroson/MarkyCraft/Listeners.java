package me.Markyroson.MarkyCraft;

import java.util.Arrays;
import java.util.UUID;

import me.Markyroson.MarkyCraft.lib.Books;
import me.Markyroson.MarkyCraft.lib.Names;
//import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
//import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
//import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
//import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.inventory.meta.ItemMeta;

public class Listeners implements Listener {

	private Example plugin = Example.getInstance();
	//private Economy econ = null;
	//private Example ex;

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		UUID uuid = e.getPlayer().getUniqueId();
		Player p = e.getPlayer();
		
		if (!plugin.getConfig().contains(uuid.toString())) {
			plugin.getMoney().put(uuid, 0);
		} else {
			plugin.getMoney().put(uuid, plugin.getConfig().getInt(p.getUniqueId() + ".Silver"));
		}
	}

	@EventHandler
	public void onKill(EntityDeathEvent e) {
		if (e.getEntity() instanceof Monster) {
			if (e.getEntity().getKiller() instanceof Player) {
				Player p = e.getEntity().getKiller();
				plugin.getApi().giveSilver(p, 200);
			}
		} else if (e.getEntity() instanceof Villager) {
			if (e.getEntity().getKiller() instanceof Player) {
				Player p = e.getEntity().getKiller();
				plugin.getApi().takeSilver(p, 200);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		ItemStack item = event.getCurrentItem();

		if (event.getInventory().getName().equals(Names.Info.Shop.name)) {
			event.setCancelled(true);

			if (item != null && item.hasItemMeta()
					&& item.getItemMeta().hasDisplayName()) {
				if (item.getItemMeta().getDisplayName().equals(Names.Items.Shop.Apple.name)) {
					Player p = (Player) event.getWhoClicked();
					
					ItemStack apple = new ItemStack(Material.APPLE, 1);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Example.econ.withdrawPlayer(p, 5);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(apple);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought one apple for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.APPLE)) {
	                        	    EconomyResponse r = Example.econ.depositPlayer(p, 5);
	                        	    if(r.transactionSuccess()) {
	                        	    	p.getInventory().removeItem(new ItemStack[] {
	                                            new ItemStack(Material.APPLE, 1) });
	                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
	                        	    	p.sendMessage(String.format("You sold one apple for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));
	                        	    } else {
	                        	    	p.sendMessage(String.format("An error occured: %s", r.errorMessage));
	                        	    }                           
	                        }
	                        else {
		                    	p.sendMessage("You don't have any apples to sell!");
		                    }
	                    } 
					}
					}
				}
		if (event.getInventory().getName().equals(Names.Info.ServerNavigator.name)) {
			event.setCancelled(true);
			
			if (item != null && item.hasItemMeta()
					&& item.getItemMeta().hasDisplayName()) {
				if(item.getItemMeta().getDisplayName().equals(Names.Items.HubPotato.name)) {
					Player p = (Player) event.getWhoClicked();
					
					//if(plugin.getApi().hasEnough(p, 0)) {
					//	plugin.getApi().takeSilver(p, 0);
						//String hub = "hub" + p.getName(); // Command implemented
						//Bukkit.dispatchCommand(Bukkit.getConsoleSender(), hub);
						p.chat("/hub");
					//} else { //nothing should go wrong but here is an else anyways
						//else is merely an escape clause
				//	}
				}
			}
		}
		if (event.getInventory().getName().equals(Names.Info.Books.name)) {
			event.setCancelled(true);
			
			if (item != null && item.hasItemMeta()
					&& item.getItemMeta().hasDisplayName()) {
				if(item.getItemMeta().getDisplayName().equals(Names.Items.Books.WebBook.name)) {
					Player p = (Player) event.getWhoClicked();
					ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
			        BookMeta bm = (BookMeta) book.getItemMeta();
			        bm.addPage(Books.Setup.WebBook.Pages.page1, Books.Setup.WebBook.Pages.page2, Books.Setup.WebBook.Pages.page3, Books.Setup.WebBook.Pages.page4);	//set book pages
			                bm.setAuthor(Books.Setup.WebBook.Author.name);	//set book author
			                bm.setTitle(Books.Setup.WebBook.Title.name);	//set book name
			                book.setItemMeta(bm);
			        p.getInventory().addItem(book);
				}
			}
		}
	}
	
	/*@EventHandler
	public void onRightClick(PlayerInteractEvent event)
	{
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)​
		{​
			if(event.getItem().equals(yourCustomItemStack))​
			{​
			//do stuff here​
			}​
		}​
	}*/
	@EventHandler
	public void onTeleport(PlayerChangedWorldEvent e) {
		Player p = e.getPlayer();
		//World from = e.getFrom();
		String from = e.getFrom().getName();
		ItemStack compass = new ItemStack(Material.COMPASS);
		ItemMeta meta = compass.getItemMeta();
		meta.setDisplayName("Server Navigator");
		meta.setLore(Arrays.asList(ChatColor.GOLD + "Click Me!"));
		compass.setItemMeta(meta);
		//World to = e.getTo();
		
		/*if(!from.equals(to)) {
			if(to.getName().equals("yourTargetWorld")) {
				//Add item
			} else if (from.getName().equals("yourTargetWorld")) {
				//Remove item
			}*/
		//}
		if(from.equals("world")) {
			System.out.println("= world");
			//Add item
			p.getInventory().addItem(compass);
			p.updateInventory();
		} 
		//if(!p.getWorld().equals("world")) {
		else {
			System.out.println("!= world");
			 if (p.getInventory().contains(Material.COMPASS)) {
			p.getInventory().removeItem(new ItemStack[] {
                    new ItemStack(Material.COMPASS, 1) });
	    	p.updateInventory();
			 }
		}
	}
}