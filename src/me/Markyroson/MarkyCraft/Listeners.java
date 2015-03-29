package me.Markyroson.MarkyCraft;

import java.util.Arrays;
import java.util.UUID;

import me.Markyroson.MarkyCraft.lib.Books;
import me.Markyroson.MarkyCraft.lib.Names;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.Material;
//import org.bukkit.World;
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
//import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class Listeners implements Listener {

	private Example plugin = Example.getInstance();

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
				EconomyResponse r = Example.econ.depositPlayer(p, 200);
				if(r.transactionSuccess()) {
	            	p.sendMessage(String.format("You killed a monster! Good job! :-) %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
	            } else {
	                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
	            }
			}
		} else if (e.getEntity() instanceof Villager) {
			if (e.getEntity().getKiller() instanceof Player) {
				Player p = e.getEntity().getKiller();
				//plugin.getApi().takeSilver(p, 200);
				EconomyResponse r = Example.econ.withdrawPlayer(p, 200);
				if(r.transactionSuccess()) {
	            	p.sendMessage(String.format("You killed a villager! :-( That will cost you %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
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
	
		if (event.getInventory().getName().equals(Names.Info.Shop.name)) {
			event.setCancelled(true);
			
			//APPLE
			if (item != null && item.hasItemMeta()
					&& item.getItemMeta().hasDisplayName()) {
				if (item.getItemMeta().getDisplayName().equals(Names.Items.Shop.Apple.name)) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Example.econ.getBalance(p);	//adds ability to check if player has enough $ to prevent negative balance
					if (balance >= 5){
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
					} else {
						p.sendMessage("You don't have enough money!");
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
		                    	p.sendMessage("You don't have enough apples to sell!");
		                    }
	                    }
						}
					}
				//Water Bucket
				if (item.getItemMeta().getDisplayName().equals(Names.Items.Shop.WaterBucket.name)) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Example.econ.getBalance(p);
					if (balance >= 50){
					ItemStack waterBucket = new ItemStack(Material.WATER_BUCKET, 1);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Example.econ.withdrawPlayer(p, 50);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(waterBucket);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought one Water Bucket for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
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
				if (item.getItemMeta().getDisplayName().equals(Names.Items.Shop.Lava_Bucket.name)) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Example.econ.getBalance(p);
					if (balance >= 75){
					ItemStack lavaBucket = new ItemStack(Material.LAVA_BUCKET, 1);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Example.econ.withdrawPlayer(p, 75);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(lavaBucket);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought one Lava Bucket for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
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
				//Obsidian
				if (item.getItemMeta().getDisplayName().equals(Names.Items.Shop.Obsidian.name)) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Example.econ.getBalance(p);
					if (balance >= 175){
					ItemStack obsidian = new ItemStack(Material.OBSIDIAN, 64);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Example.econ.withdrawPlayer(p, 175);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(obsidian);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought one Lava Bucket for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
				}
	                    if (event.getClick().isRightClick()) {
	                    	 if (p.getInventory().contains(Material.OBSIDIAN)) {
	                        	    EconomyResponse r = Example.econ.depositPlayer(p, 125);
	                        	    if(r.transactionSuccess()) {
	                        	    	p.getInventory().removeItem(new ItemStack[] {
	                                            new ItemStack(Material.OBSIDIAN, 64) });
	                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
	                        	    	p.sendMessage(String.format("You sold 64 Obsidian for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));
	                        	    } else {
	                        	    	p.sendMessage(String.format("An error occured: %s", r.errorMessage));
	                        	    }                           
	                        }
	                        else {
		                    	p.sendMessage("You don't have enough Obsidian to sell!");
		                    }
	                    } 
					}
				//EndStone
				if (item.getItemMeta().getDisplayName().equals(Names.Items.Shop.EndStone.name)) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Example.econ.getBalance(p);
					if (balance >= 150){
					ItemStack endStone = new ItemStack(Material.ENDER_STONE, 64);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Example.econ.withdrawPlayer(p, 150);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(endStone);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 64 End Stone for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
				}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.ENDER_STONE)) {
		                        	    EconomyResponse r = Example.econ.depositPlayer(p, 50);
		                        	    if(r.transactionSuccess()) {
		                        	    	p.getInventory().removeItem(new ItemStack[] {
		                                            new ItemStack(Material.ENDER_STONE, 32) });
		                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
		                        	    	p.sendMessage(String.format("You sold 32 End Stone for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));
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
				if (item.getItemMeta().getDisplayName().equals(Names.Items.Shop.Netherrack.name)) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Example.econ.getBalance(p);
					if (balance >= 62.50){
					ItemStack netherRack = new ItemStack(Material.NETHERRACK, 64);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Example.econ.withdrawPlayer(p, 62.50);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(netherRack);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 64 Netherrack for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
				}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.NETHERRACK)) {
		                        	    EconomyResponse r = Example.econ.depositPlayer(p, 25);
		                        	    if(r.transactionSuccess()) {
		                        	    	p.getInventory().removeItem(new ItemStack[] {
		                                            new ItemStack(Material.NETHERRACK, 64) });
		                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
		                        	    	p.sendMessage(String.format("You sold 64 Netherrack for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));
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
				if (item.getItemMeta().getDisplayName().equals(Names.Items.Shop.GlowStone.name)) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Example.econ.getBalance(p);
					if (balance >= 100){
					ItemStack netherRack = new ItemStack(Material.GLOWSTONE, 16);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Example.econ.withdrawPlayer(p, 100);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(netherRack);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 16 Glowstone for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
				}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.GLOWSTONE)) {
		                        	    EconomyResponse r = Example.econ.depositPlayer(p, 10);
		                        	    if(r.transactionSuccess()) {
		                        	    	p.getInventory().removeItem(new ItemStack[] {
		                                            new ItemStack(Material.GLOWSTONE, 16) });
		                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
		                        	    	p.sendMessage(String.format("You sold 16 Glowstone for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));
		                        	    } else {
		                        	    	p.sendMessage(String.format("An error occured: %s", r.errorMessage));
		                        	    }                           
		                        }
		                        else {
			                    	p.sendMessage("You don't have enough Glowstone to sell!");
			                    }
	                    } 
					}
				//Gravel
				if (item.getItemMeta().getDisplayName().equals(Names.Items.Shop.Gravel.name)) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Example.econ.getBalance(p);
					if (balance >= 75){
					ItemStack netherRack = new ItemStack(Material.GRAVEL, 64);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Example.econ.withdrawPlayer(p, 75);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(netherRack);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 64 Gravel for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
				}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.GRAVEL)) {
		                        	    EconomyResponse r = Example.econ.depositPlayer(p, 12.50);
		                        	    if(r.transactionSuccess()) {
		                        	    	p.getInventory().removeItem(new ItemStack[] {
		                                            new ItemStack(Material.GRAVEL, 64) });
		                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
		                        	    	p.sendMessage(String.format("You sold 64 Gravel for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));
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
				if (item.getItemMeta().getDisplayName().equals(Names.Items.Shop.Sand.name)) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Example.econ.getBalance(p);
					if (balance >= 75){
					ItemStack netherRack = new ItemStack(Material.SAND, 64);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Example.econ.withdrawPlayer(p, 75);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(netherRack);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 64 Sand for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
				}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.SAND)) {
		                        	    EconomyResponse r = Example.econ.depositPlayer(p, 17.50);
		                        	    if(r.transactionSuccess()) {
		                        	    	p.getInventory().removeItem(new ItemStack[] {
		                                            new ItemStack(Material.SAND, 64) });
		                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
		                        	    	p.sendMessage(String.format("You sold 64 Sand for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));
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
				if (item.getItemMeta().getDisplayName().equals(Names.Items.Shop.CobbleStone.name)) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Example.econ.getBalance(p);
					if (balance >= 100){
					ItemStack netherRack = new ItemStack(Material.COBBLESTONE, 64);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Example.econ.withdrawPlayer(p, 100);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(netherRack);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 64 Cobblestone for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
				}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.COBBLESTONE)) {
		                        	    EconomyResponse r = Example.econ.depositPlayer(p, 25);
		                        	    if(r.transactionSuccess()) {
		                        	    	p.getInventory().removeItem(new ItemStack[] {
		                                            new ItemStack(Material.COBBLESTONE, 64) });
		                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
		                        	    	p.sendMessage(String.format("You sold 64 Gravel for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));
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
				if (item.getItemMeta().getDisplayName().equals(Names.Items.Shop.Stone.name)) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Example.econ.getBalance(p);
					if (balance >= 137.50){
					ItemStack netherRack = new ItemStack(Material.STONE, 64);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Example.econ.withdrawPlayer(p, 137.50);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(netherRack);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 64 Stone for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
				}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.STONE)) {
		                        	    EconomyResponse r = Example.econ.depositPlayer(p, 37.50);
		                        	    if(r.transactionSuccess()) {
		                        	    	p.getInventory().removeItem(new ItemStack[] {
		                                            new ItemStack(Material.STONE, 64) });
		                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
		                        	    	p.sendMessage(String.format("You sold 64 Stone for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));
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
				if (item.getItemMeta().getDisplayName().equals(Names.Items.Shop.Dirt.name)) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Example.econ.getBalance(p);
					if (balance >= 75){
					ItemStack netherRack = new ItemStack(Material.DIRT, 64);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Example.econ.withdrawPlayer(p, 75);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(netherRack);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 64 Dirt for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
				}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.DIRT)) {
		                        	    EconomyResponse r = Example.econ.depositPlayer(p, 75);
		                        	    if(r.transactionSuccess()) {
		                        	    	p.getInventory().removeItem(new ItemStack[] {
		                                            new ItemStack(Material.DIRT, 64) });
		                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
		                        	    	p.sendMessage(String.format("You sold 64 Dirt for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));
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
				if (item.getItemMeta().getDisplayName().equals(Names.Items.Shop.Grass.name)) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Example.econ.getBalance(p);
					if (balance >= 75){
					ItemStack netherRack = new ItemStack(Material.GRASS, 64);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Example.econ.withdrawPlayer(p, 92.50);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(netherRack);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 64 Grass for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
				            } else {
				                p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
				            }
	                    }
				}
	                    if (event.getClick().isRightClick()) {
	                        if (p.getInventory().contains(Material.GRASS)) {
		                        	    EconomyResponse r = Example.econ.depositPlayer(p, 92.50);
		                        	    if(r.transactionSuccess()) {
		                        	    	p.getInventory().removeItem(new ItemStack[] {
		                                            new ItemStack(Material.GRASS, 64) });
		                        	    	p.updateInventory();	//not sure why this is deprecated as it is essential to inventory removal
		                        	    	p.sendMessage(String.format("You sold 64 Grass for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));
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
				if (item.getItemMeta().getDisplayName().equals(Names.Items.Shop.Ice.name)) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Example.econ.getBalance(p);
					if (balance >= 150){
					ItemStack netherRack = new ItemStack(Material.ICE, 16);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Example.econ.withdrawPlayer(p, 150);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(netherRack);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 16 Ice for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
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
				if (item.getItemMeta().getDisplayName().equals(Names.Items.Shop.PackedIce.name)) {
					Player p = (Player) event.getWhoClicked();
					Double balance = Example.econ.getBalance(p);
					if (balance >= 75){
					ItemStack netherRack = new ItemStack(Material.PACKED_ICE, 16);	//create itemstack to give player
					 if (event.getClick().isLeftClick()) {
						    EconomyResponse r = Example.econ.withdrawPlayer(p, 75);
				            if(r.transactionSuccess()) {
				                p.getInventory().addItem(netherRack);	//give player created item stack
				                p.updateInventory();	//update the player inventory
				            	p.sendMessage(String.format("You bought 16 Packed Ice for %s and now have %s", Example.econ.format(r.amount), Example.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
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
					Api.createBook(Material.WRITTEN_BOOK, Books.Setup.WebBook.Author.name, Books.Setup.WebBook.Title.name, p, Books.Setup.WebBook.Pages.page1, Books.Setup.WebBook.Pages.page2, Books.Setup.WebBook.Pages.page3, Books.Setup.WebBook.Pages.page4);
				}
			}
		}
		}
	//}
	
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