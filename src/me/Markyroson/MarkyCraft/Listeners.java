package me.Markyroson.MarkyCraft;

import java.util.Arrays;
import java.util.UUID;

import me.Markyroson.MarkyCraft.lib.Books;
import me.Markyroson.MarkyCraft.lib.Names;
import me.Markyroson.MarkyCraft.lib.Prices;
import me.Markyroson.MarkyCraft.lib.Names.Info;
import me.Markyroson.MarkyCraft.lib.Names.Inventories;
import me.Markyroson.MarkyCraft.lib.Names.Shop;
import me.Markyroson.MarkyCraft.lib.Names.ShopItemsWithTwoLore;
import me.Markyroson.MarkyCraft.lib.Books.BookType;
import me.Markyroson.MarkyCraft.lib.Books.Web_BookPages;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Listeners implements Listener {

	private Main plugin = Main.getInstance();

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
				//plugin.getApi().giveSilver(p, 200);
				EconomyResponse r = Main.econ.depositPlayer(p, Prices.MONSTER_KILL_REWARD);
				if(r.transactionSuccess()) {
	            	p.sendMessage(String.format("You killed a monster! Good job! :-) %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
	            } else {
	                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
	            }
			}
		} else if (e.getEntity() instanceof Villager) {
			if (e.getEntity().getKiller() instanceof Player) {
				Player p = e.getEntity().getKiller();
				//plugin.getApi().takeSilver(p, 200);
				EconomyResponse r = Main.econ.withdrawPlayer(p, Prices.VILLAGER_KILL_PENALTY);
				if(r.transactionSuccess()) {
	            	p.sendMessage(String.format("You killed a villager! :-( That will cost you %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
	            } else {
	                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
	            }
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		ItemStack item = event.getCurrentItem();
	
		if (event.getInventory().getName().equals(Info.getName(Inventories.SHOP))) {
			event.setCancelled(true);
			
			//APPLE
			if (item != null && item.hasItemMeta()
					&& item.getItemMeta().hasDisplayName()) {
				if (item.getItemMeta().getDisplayName().equals(Names.getItemName(ShopItemsWithTwoLore.APPLE))) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Main.econ.getBalance(p);	//adds ability to check if player has enough $ to prevent negative balance
					if (balance >= 5){
					ItemStack apple = new ItemStack(Material.APPLE, 1);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Main.econ.withdrawPlayer(p, Prices.APPLE_BUY);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(apple);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought one apple for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
					} else {
						p.sendMessage("You don't have enough money!");
					}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.APPLE)) {
	                        	    EconomyResponse r = Main.econ.depositPlayer(p, Prices.APPLE_SELL);
	                        	    if(r.transactionSuccess()) {
	                        	    	p.getInventory().removeItem(new ItemStack[] {
	                                            new ItemStack(Material.APPLE, 1) });
	                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
	                        	    	p.sendMessage(String.format("You sold one apple for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));
	                        	    } else {
	                        	    	p.sendMessage(String.format("An error occured: %s", r.errorMessage));
	                        	    }                           
	                        }
	                        else {
		                    	p.sendMessage("You don't have enough apples to sell!");
		                    }
	                    }
						}
				}
				//Water Bucket
				if (item.getItemMeta().getDisplayName().equals(Names.getItemName(ShopItemsWithTwoLore.WATER_BUCKET))) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Main.econ.getBalance(p);
					if (balance >= 50)
					{
					ItemStack waterBucket = new ItemStack(Material.WATER_BUCKET, 1);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Main.econ.withdrawPlayer(p, Prices.WATER_BUCKET_BUY);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(waterBucket);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought one Water Bucket for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
					} else {
						p.sendMessage("You don't have enough money!");
					}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.WATER_BUCKET)) {
	                        		p.sendMessage("You can't sell a Water Bucket!");
	                        }
	                    }
				}
				//Lava Water
				if (item.getItemMeta().getDisplayName().equals(Names.getItemName(ShopItemsWithTwoLore.LAVA_BUCKET)))
				{
					Player p = (Player) event.getWhoClicked();
					Double balance = Main.econ.getBalance(p);
					if (balance >= 75){
						ItemStack lavaBucket = new ItemStack(Material.LAVA_BUCKET, 1);	//create itemstack to give player
						 if (event.getClick().isLeftClick()) {
							    EconomyResponse r = Main.econ.withdrawPlayer(p, Prices.LAVA_BUCKET_BUY);
					            if(r.transactionSuccess()) {
					                p.getInventory().addItem(lavaBucket);	//give player created item stack
					                p.updateInventory();	//update the player inventory
					            	p.sendMessage(String.format("You bought one Lava Bucket for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
					            } else {
					                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
					            }
		                    }
					}
	                if (event.getClick().isRightClick()) {
	                	if (p.getInventory().contains(Material.LAVA_BUCKET)) {
	                		p.sendMessage("You can't sell a Lava Bucket!");
	                    }
	                }
				}
				// Obsidian
				if (item.getItemMeta().getDisplayName().equals(Names.getItemName(ShopItemsWithTwoLore.OBSIDIAN)))
				{
					Player p = (Player) event.getWhoClicked();
					Double balance = Main.econ.getBalance(p);
					if (balance >= 175)
					{
						ItemStack obsidian = new ItemStack(Material.OBSIDIAN, 64);	//create itemstack to give player
						if (event.getClick().isLeftClick())
						{
						    EconomyResponse r = Main.econ.withdrawPlayer(p, Prices.OBSIDIAN_BUY);
				            if(r.transactionSuccess())
				            {
				                p.getInventory().addItem(obsidian);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought one Lava Bucket for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
					}
	                if (event.getClick().isRightClick()) {
	                	 if (p.getInventory().contains(Material.OBSIDIAN)) {
	                    	    EconomyResponse r = Main.econ.depositPlayer(p, Prices.OBSIDIAN_SELL);
	                    	    if(r.transactionSuccess()) {
	                    	    	p.getInventory().removeItem(new ItemStack[] {
	                                        new ItemStack(Material.OBSIDIAN, 64) });
	                      	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
	                       	    	p.sendMessage(String.format("You sold 64 Obsidian for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));
	                       	    } else {
	                       	    	p.sendMessage(String.format("An error occured: %s", r.errorMessage));
	                       	    }                           
	                       } else {
		                   		p.sendMessage("You don't have enough Obsidian to sell!");
		                   }
	                }
				}
				//EndStone
				if (item.getItemMeta().getDisplayName().equals(Names.getItemName(ShopItemsWithTwoLore.END_STONE)))
				{
					Player p = (Player) event.getWhoClicked();
					Double balance = Main.econ.getBalance(p);
					if (balance >= 150){
					ItemStack endStone = new ItemStack(Material.ENDER_STONE, 64);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Main.econ.withdrawPlayer(p, Prices.ENDER_STONE_BUY);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(endStone);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 64 End Stone for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
				}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.ENDER_STONE)) {
		                        	    EconomyResponse r = Main.econ.depositPlayer(p, Prices.ENDER_STONE_SELL);
		                        	    if(r.transactionSuccess()) {
		                        	    	p.getInventory().removeItem(new ItemStack[] {
		                                            new ItemStack(Material.ENDER_STONE, 32) });
		                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
		                        	    	p.sendMessage(String.format("You sold 32 End Stone for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));
		                        	    } else {
		                        	    	p.sendMessage(String.format("An error occured: %s", r.errorMessage));
		                        	    }                           
		                        }
		                        else {
			                    	p.sendMessage("You don't have enough End Stone to sell!");
			                    }
	                    } 
					}
				//Netherrack
				if (item.getItemMeta().getDisplayName().equals(Names.getItemName(ShopItemsWithTwoLore.NETHERRACK))) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Main.econ.getBalance(p);
					if (balance >= 62.50){
					ItemStack netherRack = new ItemStack(Material.NETHERRACK, 64);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Main.econ.withdrawPlayer(p, Prices.NETHER_RACK_BUY);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(netherRack);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 64 Netherrack for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
				}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.NETHERRACK)) {
		                        	    EconomyResponse r = Main.econ.depositPlayer(p, Prices.NETHER_RACK_SELL);
		                        	    if(r.transactionSuccess()) {
		                        	    	p.getInventory().removeItem(new ItemStack[] {
		                                            new ItemStack(Material.NETHERRACK, 64) });
		                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
		                        	    	p.sendMessage(String.format("You sold 64 Netherrack for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));
		                        	    } else {
		                        	    	p.sendMessage(String.format("An error occured: %s", r.errorMessage));
		                        	    }                           
		                        }
		                        else {
			                    	p.sendMessage("You don't have enough Netherrack to sell!");
			                    }
	                    } 
					}
				//Glowstone
				if (item.getItemMeta().getDisplayName().equals(Names.getItemName(ShopItemsWithTwoLore.GLOW_STONE))) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Main.econ.getBalance(p);
					if (balance >= 100){
					ItemStack glowstone = new ItemStack(Material.GLOWSTONE, 16);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Main.econ.withdrawPlayer(p, Prices.GLOW_STONE_BUY);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(glowstone);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 16 Glowstone for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
				}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.GLOWSTONE)) {
		                        	    EconomyResponse r = Main.econ.depositPlayer(p, Prices.GLOW_STONE_SELL);
		                        	    if(r.transactionSuccess()) {
		                        	    	p.getInventory().removeItem(new ItemStack[] {
		                                            new ItemStack(Material.GLOWSTONE, 16) });
		                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
		                        	    	p.sendMessage(String.format("You sold 16 Glowstone for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));
		                        	    } else {
		                        	    	p.sendMessage(String.format("An error occured: %s", r.errorMessage));
		                        	    }                           
		                        }
		                        else {
			                    	p.sendMessage("You don't have enough Glowstone to sell!");
			                    }
	                    } 
					}
				// Gravel
				if (item.getItemMeta().getDisplayName().equals(Names.getItemName(ShopItemsWithTwoLore.GRAVEL))) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Main.econ.getBalance(p);
					if (balance >= 75){
					ItemStack gravel = new ItemStack(Material.GRAVEL, 64);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Main.econ.withdrawPlayer(p, 75);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(gravel);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 64 Gravel for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
				}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.GRAVEL)) {
		                        	    EconomyResponse r = Main.econ.depositPlayer(p, 12.50);
		                        	    if(r.transactionSuccess()) {
		                        	    	p.getInventory().removeItem(new ItemStack[] {
		                                            new ItemStack(Material.GRAVEL, 64) });
		                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
		                        	    	p.sendMessage(String.format("You sold 64 Gravel for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));
		                        	    } else {
		                        	    	p.sendMessage(String.format("An error occured: %s", r.errorMessage));
		                        	    }                           
		                        }
		                        else {
			                    	p.sendMessage("You don't have enough Gravel to sell!");
			                    }
	                    } 
					}
				//Sand
				if (item.getItemMeta().getDisplayName().equals(Names.getItemName(ShopItemsWithTwoLore.SAND))) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Main.econ.getBalance(p);
					if (balance >= 75){
					ItemStack sand = new ItemStack(Material.SAND, 64);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Main.econ.withdrawPlayer(p, 75);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(sand);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 64 Sand for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
				}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.SAND)) {
		                        	    EconomyResponse r = Main.econ.depositPlayer(p, 17.50);
		                        	    if(r.transactionSuccess()) {
		                        	    	p.getInventory().removeItem(new ItemStack[] {
		                                            new ItemStack(Material.SAND, 64) });
		                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
		                        	    	p.sendMessage(String.format("You sold 64 Sand for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));
		                        	    } else {
		                        	    	p.sendMessage(String.format("An error occured: %s", r.errorMessage));
		                        	    }                           
		                        }
		                        else {
			                    	p.sendMessage("You don't have enough Sand to sell!");
			                    }
	                    } 
					}
				//Cobblestone
				if (item.getItemMeta().getDisplayName().equals(Names.getItemName(ShopItemsWithTwoLore.COBBLE_STONE))) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Main.econ.getBalance(p);
					if (balance >= 100){
					ItemStack cobble = new ItemStack(Material.COBBLESTONE, 64);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Main.econ.withdrawPlayer(p, 100);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(cobble);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 64 Cobblestone for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
				}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.COBBLESTONE)) {
		                        	    EconomyResponse r = Main.econ.depositPlayer(p, 25);
		                        	    if(r.transactionSuccess()) {
		                        	    	p.getInventory().removeItem(new ItemStack[] {
		                                            new ItemStack(Material.COBBLESTONE, 64) });
		                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
		                        	    	p.sendMessage(String.format("You sold 64 Gravel for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));
		                        	    } else {
		                        	    	p.sendMessage(String.format("An error occured: %s", r.errorMessage));
		                        	    }                           
		                        }
		                        else {
			                    	p.sendMessage("You don't have enough Cobblestone to sell!");
			                    }
	                    } 
					}
				//Stone
				if (item.getItemMeta().getDisplayName().equals(Names.getItemName(ShopItemsWithTwoLore.STONE))) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Main.econ.getBalance(p);
					if (balance >= 137.50){
					ItemStack stone = new ItemStack(Material.STONE, 64);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Main.econ.withdrawPlayer(p, 137.50);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(stone);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 64 Stone for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
				}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.STONE)) {
		                        	    EconomyResponse r = Main.econ.depositPlayer(p, 37.50);
		                        	    if(r.transactionSuccess()) {
		                        	    	p.getInventory().removeItem(new ItemStack[] {
		                                            new ItemStack(Material.STONE, 64) });
		                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
		                        	    	p.sendMessage(String.format("You sold 64 Stone for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));
		                        	    } else {
		                        	    	p.sendMessage(String.format("An error occured: %s", r.errorMessage));
		                        	    }                           
		                        }
		                        else {
			                    	p.sendMessage("You don't have enough Stone to sell!");
			                    }
	                    } 
					}
				//Dirt
				if (item.getItemMeta().getDisplayName().equals(Names.getItemName(ShopItemsWithTwoLore.DIRT))) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Main.econ.getBalance(p);
					if (balance >= 75){
					ItemStack dirt = new ItemStack(Material.DIRT, 64);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Main.econ.withdrawPlayer(p, 75);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(dirt);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 64 Dirt for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
				}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.DIRT)) {
		                        	    EconomyResponse r = Main.econ.depositPlayer(p, 75);
		                        	    if(r.transactionSuccess()) {
		                        	    	p.getInventory().removeItem(new ItemStack[] {
		                                            new ItemStack(Material.DIRT, 64) });
		                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
		                        	    	p.sendMessage(String.format("You sold 64 Dirt for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));
		                        	    } else {
		                        	    	p.sendMessage(String.format("An error occured: %s", r.errorMessage));
		                        	    }                           
		                        }
		                        else {
			                    	p.sendMessage("You don't have enough Dirt to sell!");
			                    }
	                    } 
					}
				//Grass
				if (item.getItemMeta().getDisplayName().equals(Names.getItemName(ShopItemsWithTwoLore.GRASS))) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Main.econ.getBalance(p);
					if (balance >= 75){
					ItemStack grass = new ItemStack(Material.GRASS, 64);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Main.econ.withdrawPlayer(p, 92.50);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(grass);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 64 Grass for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
				}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.GRASS)) {
		                        	    EconomyResponse r = Main.econ.depositPlayer(p, 92.50);
		                        	    if(r.transactionSuccess()) {
		                        	    	p.getInventory().removeItem(new ItemStack[] {
		                                            new ItemStack(Material.GRASS, 64) });
		                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
		                        	    	p.sendMessage(String.format("You sold 64 Grass for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));
		                        	    } else {
		                        	    	p.sendMessage(String.format("An error occured: %s", r.errorMessage));
		                        	    }                           
		                        }
		                        else {
			                    	p.sendMessage("You don't have enough Grass to sell!");
			                    }
	                    } 
					}
				//Ice
				if (item.getItemMeta().getDisplayName().equals(Names.getItemName(ShopItemsWithTwoLore.ICE))) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Main.econ.getBalance(p);
					if (balance >= 150) {
					ItemStack ice = new ItemStack(Material.ICE, 16);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Main.econ.withdrawPlayer(p, 150);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(ice);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 16 Ice for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
					}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.ICE)) {
	                        	p.sendMessage("You can't sell a Ice!");                         
		                        }
	                    } 
					}
				//Packed Ice
				if (item.getItemMeta().getDisplayName().equals(Names.getItemName(ShopItemsWithTwoLore.PACKED_ICE))) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Main.econ.getBalance(p);
					if (balance >= 75){
						ItemStack packedIce = new ItemStack(Material.PACKED_ICE, 16);	//create itemstack to give player
						if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Main.econ.withdrawPlayer(p, 75);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(packedIce);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 16 Packed Ice for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
					}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.PACKED_ICE)) {
	                        		p.sendMessage("You can't sell a Packed Ice!");                         
		                        }
	                    } 
					}
				// Bedrock
				if(item.getItemMeta().getDisplayName().equals(Names.getItemName(ShopItemsWithTwoLore.BEDROCK)))
				{
					Player p = (Player) event.getWhoClicked();
					Double balance = Main.econ.getBalance(p);

					ItemStack bedrock = new ItemStack(Material.BEDROCK, 1);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						 if(balance >= 1000000000000.0) {
						    EconomyResponse r = Main.econ.withdrawPlayer(p, 1000000000000.0);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(bedrock);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 1 Bedrock for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    } else {
						p.sendMessage("You don't have enough money!");
					}
						 }
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.BEDROCK)) {
	                        	EconomyResponse r = Main.econ.depositPlayer(p, 1000000000000.0);
					            if(r.transactionSuccess()) {
					            	p.getInventory().removeItem(new ItemStack[] {
                                            new ItemStack(Material.BEDROCK, 1) });	//give player created item stack
					                p.updateInventory();	//update the player inventory
					            	p.sendMessage(String.format("You sold 1 Bedrock for %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
					            } else {
					                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
					            }                     
		                        }
	                    }
			}
		}
		
		if (event.getInventory().getName().equals(Info.getName(Inventories.SERVER_NAVIGATOR))) {
			event.setCancelled(true);
			
			if (item != null && item.hasItemMeta()
					&& item.getItemMeta().hasDisplayName()) {
				if(item.getItemMeta().getDisplayName().equals(Names.getItemName(Shop.HUB_POTATO))) {
					Player p = (Player) event.getWhoClicked();
					/*if(plugin.getApi().hasEnough(p, 0)) {
						plugin.getApi().takeSilver(p, 0);
						String hub = "hub" + p.getName(); // Command implemented
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), hub);*/
						p.chat("/hub");
					/*} else { //nothing should go wrong but here is an else anyways
						else is merely an escape clause
				  	}*/
				}
			}
		}
		if (event.getInventory().getName().equals(Info.getName(Inventories.BOOKS))) {
			event.setCancelled(true);
			
			if (item != null && item.hasItemMeta()
					&& item.getItemMeta().hasDisplayName()) {
				if(item.getItemMeta().getDisplayName().equals(Names.getItemName(Shop.BOOK_WEB_BOOK))) {
					Player player = (Player) event.getWhoClicked();
					Api.createBook(Material.WRITTEN_BOOK, Books.getAuthor(BookType.WEB_BOOK), Books.getTitle(BookType.WEB_BOOK), player, Books.getBookPage(BookType.WEB_BOOK, Web_BookPages.PAGE1), Books.getBookPage(BookType.WEB_BOOK, Web_BookPages.PAGE2), Books.getBookPage(BookType.WEB_BOOK, Web_BookPages.PAGE3), Books.getBookPage(BookType.WEB_BOOK, Web_BookPages.PAGE4));	
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
	@SuppressWarnings("deprecation")
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
