package me.markyroson.markycraftallinone;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

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

    /*@EventHandler
    public void onJoin(PlayerJoinEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        Player p = e.getPlayer();

        if (!plugin.getConfig().contains(uuid.toString()))
            plugin.getMoney().put(uuid, 0);
        else
            plugin.getMoney().put(uuid, plugin.getConfig().getInt(p.getUniqueId() + ".Silver"));
    }*/

    @EventHandler
    public void onKill(EntityDeathEvent e) {
        if (e.getEntity() instanceof Monster) {
            if (e.getEntity().getKiller() != null) {
                Player p = e.getEntity().getKiller();
                //plugin.getApi().giveSilver(p, 200);
                EconomyResponse r = Main.econ.depositPlayer(p, 200);
                if(r.transactionSuccess())
                    p.sendMessage(String.format("You killed a monster! Good job! :-) Take %s as a reward. You now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
                else
                    p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
            }
        } else if (e.getEntity() instanceof Villager) {
            if (e.getEntity().getKiller() != null) {
                Player p = e.getEntity().getKiller();
                //plugin.getApi().takeSilver(p, 200);
                EconomyResponse r = Main.econ.withdrawPlayer(p, 200);
                if(r.transactionSuccess())
                    p.sendMessage(String.format("You killed a villager! :-( That will cost you %s and now have %s", Main.econ.format(r.amount), Main.econ.format(r.balance)));	//send success message &^ tell player what they spent/remaining balance
                else
                    p.sendMessage(String.format("An error occured: %s", r.errorMessage));	//send error message
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();

        if (event.getView().getTitle().equals("Marky" + ChatColor.GOLD + "Craft " + ChatColor.DARK_GREEN + "Survival " + ChatColor.BLACK + "Shop")) {
            event.setCancelled(true);

            //APPLE
            if (item != null && item.hasItemMeta()
                    && Objects.requireNonNull(item.getItemMeta()).hasDisplayName()) {
                if (item.getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA + "Apple")) {
                    Player p = (Player) event.getWhoClicked();
                    Double balance = 0.0;
                    try {
                        balance = Main.econ.getBalance(p);	//adds ability to check if player has enough $ to prevent negative balance
                    } catch(NullPointerException e) {
                        assert false;
                    }

                    if (balance >= 5){
                        if (event.getClick().isLeftClick())
                            transact(p, new ItemStack(Material.APPLE, 1), "apple", 5.00, true);
                    } else {
                        p.sendMessage("You don't have enough money!");
                    }
                    if (event.getClick().isRightClick()) {
                        if (p.getInventory().contains(Material.APPLE))
                            transact(p, new ItemStack(Material.APPLE, 1), "apple", 5.00, false);
                        else
                            p.sendMessage("You don't have enough apples to sell!");
                    }
                }
            }
            //Water Bucket
            if (item != null && Objects.requireNonNull(item.getItemMeta()).getDisplayName().equals(ChatColor.AQUA + "Water Bucket")) {
                Player p = (Player) event.getWhoClicked();
                Double balance = Main.econ.getBalance(p);
                if (balance >= 50){
                    if (event.getClick().isLeftClick())
                        transact(p, new ItemStack(Material.WATER_BUCKET, 1), "Water Bucket", 50.00, true);
                } else {
                    p.sendMessage("You don't have enough money!");
                }
                if (event.getClick().isRightClick()) {
                    if (p.getInventory().contains(Material.WATER_BUCKET))
                        p.sendMessage("You can't sell a Water Bucket!");
                }
            }
            //Lava Water
            if (item != null && item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Lava Bucket")) {
                Player p = (Player) event.getWhoClicked();
                Double balance = Main.econ.getBalance(p);
                if (balance >= 75){
                    if (event.getClick().isLeftClick())
                        transact(p, new ItemStack(Material.LAVA_BUCKET, 1), "Lava Bucket", 75.00, true);
                }
                if (event.getClick().isRightClick()) {
                    if (p.getInventory().contains(Material.LAVA_BUCKET))
                        p.sendMessage("You can't sell a Lava Bucket!");
                }
            }
            //Obsidian
            if (item != null && item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Obsidian")) {
                Player p = (Player) event.getWhoClicked();
                Double balance = Main.econ.getBalance(p);
                if (balance >= 175){
                    if (event.getClick().isLeftClick())
                        transact(p, new ItemStack(Material.OBSIDIAN, 64), "obsidian", 175.00, true);
                }
                if (event.getClick().isRightClick()) {
                    if (p.getInventory().contains(Material.OBSIDIAN))
                        transact(p, new ItemStack(Material.OBSIDIAN, 64), "Obsidian", 125.00, false);
                    else
                        p.sendMessage("You don't have enough Obsidian to sell!");
                }
            }
            //EndStone
            if (item != null && item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "EndStone")) {
                Player p = (Player) event.getWhoClicked();
                Double balance = Main.econ.getBalance(p);
                if (balance >= 150){
                    if (event.getClick().isLeftClick())
                        transact(p, new ItemStack(Material.END_STONE, 32), "End Stone", 150.00, true);
                }
                if (event.getClick().isRightClick()) {
                    if (p.getInventory().contains(Material.END_STONE))
                        transact(p, new ItemStack(Material.END_STONE, 32), "End Stone", 50.00, false);
                    else
                        p.sendMessage("You don't have enough End Stone to sell!");
                }
            }
            //Netherrack
            if (item != null && item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Netherrack")) {
                Player p = (Player) event.getWhoClicked();
                Double balance = Main.econ.getBalance(p);
                if (balance >= 62.50) {
                    if (event.getClick().isLeftClick())
                        transact(p, new ItemStack(Material.NETHERRACK, 64), "Netherrack", 62.50, true);
                }
                if (event.getClick().isRightClick()) {
                    if (p.getInventory().contains(Material.NETHERRACK))
                        transact(p, new ItemStack(Material.NETHERRACK, 64), "Netherrack", 25.00, false);
                    else
                        p.sendMessage("You don't have enough Netherrack to sell!");
                }
            }
            //Glowstone
            if (item != null && item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Glowstone")) {
                Player p = (Player) event.getWhoClicked();
                Double balance = Main.econ.getBalance(p);
                if (balance >= 100){
                    if (event.getClick().isLeftClick())
                        transact(p, new ItemStack(Material.GLOWSTONE, 16), "Glowstone", 100.00, true);
                }
                if (event.getClick().isRightClick()) {
                    if (p.getInventory().contains(Material.GLOWSTONE))
                        transact(p, new ItemStack(Material.GLOWSTONE, 16), "Glowstone", 10.00, false);
                    else
                        p.sendMessage("You don't have enough Glowstone to sell!");
                }
            }
            //Gravel
            if (item != null && item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Gravel")) {
                Player p = (Player) event.getWhoClicked();
                Double balance = Main.econ.getBalance(p);
                if (balance >= 75) {
                    if (event.getClick().isLeftClick())
                        transact(p, new ItemStack(Material.GRAVEL, 64), "Gravel", 75.00, true);
                }
                if (event.getClick().isRightClick()) {
                    if (p.getInventory().contains(Material.GRAVEL))
                        transact(p, new ItemStack(Material.GRAVEL, 64), "Gravel", 12.50, false);
                    else
                        p.sendMessage("You don't have enough Gravel to sell!");
                }
            }
            //Sand
            if (item != null && item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Sand")) {
                Player p = (Player) event.getWhoClicked();
                Double balance = Main.econ.getBalance(p);
                if (balance >= 75){
                    if (event.getClick().isLeftClick())
                        transact(p, new ItemStack(Material.SAND, 64), "Sand", 75.00, true);
                }
                if (event.getClick().isRightClick()) {
                    if (p.getInventory().contains(Material.SAND))
                        transact(p, new ItemStack(Material.SAND, 64), "Sand", 17.50, false);
                    else
                        p.sendMessage("You don't have enough Sand to sell!");
                }
            }
            //Cobblestone
            if (item != null && item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Cobblestone")) {
                Player p = (Player) event.getWhoClicked();
                Double balance = Main.econ.getBalance(p);
                if (balance >= 100) {
                    if (event.getClick().isLeftClick())
                        transact(p, new ItemStack(Material.COBBLESTONE, 64), "Cobblestone", 100.00, true);
                }
                if (event.getClick().isRightClick()) {
                    if (p.getInventory().contains(Material.COBBLESTONE))
                        transact(p, new ItemStack(Material.COBBLESTONE, 64), "Gravel", 25.00, false);
                    else
                        p.sendMessage("You don't have enough Cobblestone to sell!");
                }
            }
            //Stone
            if (item != null && item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Stone")) {
                Player p = (Player) event.getWhoClicked();
                Double balance = Main.econ.getBalance(p);
                if (balance >= 137.50) {
                    if (event.getClick().isLeftClick())
                        transact(p, new ItemStack(Material.STONE, 64), "Stone", 137.50, true);
                }
                if (event.getClick().isRightClick()) {
                    if (p.getInventory().contains(Material.STONE))
                        transact(p, new ItemStack(Material.STONE, 64), "Stone", 37.50, false);
                    else
                        p.sendMessage("You don't have enough Stone to sell!");
                }
            }
            //Dirt
            if (item != null && item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Dirt")) {
                Player p = (Player) event.getWhoClicked();
                Double balance = Main.econ.getBalance(p);
                if (balance >= 75){
                    if (event.getClick().isLeftClick())
                        transact(p, new ItemStack(Material.DIRT, 64), "Dirt", 75.00, true);
                }
                if (event.getClick().isRightClick()) {
                    if (p.getInventory().contains(Material.DIRT))
                        transact(p, new ItemStack(Material.DIRT, 64), "Dirt", 75.00, false);
                    else
                        p.sendMessage("You don't have enough Dirt to sell!");
                }
            }
            //Grass
            if (item != null && item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Grass")) {
                Player p = (Player) event.getWhoClicked();
                Double balance = Main.econ.getBalance(p);
                if (balance >= 75) {
                    if (event.getClick().isLeftClick())
                        transact(p, new ItemStack(Material.GRASS, 64), "Grass", 92.50, true);
                }
                if (event.getClick().isRightClick()) {
                    if (p.getInventory().contains(Material.GRASS))
                        transact(p, new ItemStack(Material.GRASS, 64), "Grass", 92.50, false);
                    else
                        p.sendMessage("You don't have enough Grass to sell!");
                }
            }
            //Ice
            if (item != null && item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Ice")) {
                Player p = (Player) event.getWhoClicked();
                Double balance = Main.econ.getBalance(p);
                if (balance >= 150) {
                    if (event.getClick().isLeftClick())
                        transact(p, new ItemStack(Material.ICE, 16), "Ice", 150.00, true);
                }
                if (event.getClick().isRightClick()) {
                    if (p.getInventory().contains(Material.ICE)) {
                        p.sendMessage("You can't sell Ice!");
                    }
                }
            }
            //Packed Ice
            if (item != null && item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "PackedIce")) {
                Player p = (Player) event.getWhoClicked();
                Double balance = Main.econ.getBalance(p);
                if (balance >= 75) {
                    if (event.getClick().isLeftClick())
                        transact(p, new ItemStack(Material.PACKED_ICE, 16), "Packed Ice", 75.00, true);
                }
                if (event.getClick().isRightClick()) {
                    if (p.getInventory().contains(Material.PACKED_ICE)) {
                        p.sendMessage("You can't sell a Packed Ice!");
                    }
                }
            }
        }
        if (event.getView().getTitle().equals(ChatColor.AQUA + "Server " + ChatColor.GOLD + "Navigator")) {
            event.setCancelled(true);

            if (item != null && item.hasItemMeta()
                    && Objects.requireNonNull(item.getItemMeta()).hasDisplayName()) {
                if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Hub")) {
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
        if (event.getView().getTitle().equals(ChatColor.AQUA + "Server " + ChatColor.GOLD + "Books")) {
            event.setCancelled(true);

            if (item != null && item.hasItemMeta()
                    && Objects.requireNonNull(item.getItemMeta()).hasDisplayName()) {
                if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Web Book")) {
                    Player p = (Player) event.getWhoClicked();
                    Api.createBook2(Material.WRITTEN_BOOK, "The Server", "Websites", p, "Hello! This is the book of web addresses!", "http://www.youtube.com/Markyroson", "http://www.twitter.com/Markyroson", "http://www.facebook.com/Markyroson");
                }
            }
        }
    }

    private void transact(Player p, ItemStack stack, String name, Double price, boolean isBuying) {
        EconomyResponse economyResponse = null;
        if(!isBuying)
            economyResponse = Main.econ.depositPlayer(p, price);
        else
            economyResponse = Main.econ.withdrawPlayer(p, price);
        assert economyResponse != null;   // should never be null
        if(economyResponse.transactionSuccess()) {
            if(!isBuying) { // selling
                p.getInventory().removeItem(stack);
            }
            else {

                p.getInventory().addItem(stack);    //give player created item stack
            }
            //p.updateInventory();	//update the player inventory
            if(!isBuying)
                p.sendMessage(String.format("You sold " + stack.getAmount() + " " + name + " for %s and now have %s",
                        Main.econ.format(economyResponse.amount), Main.econ.format(economyResponse.balance)));
            else
                p.sendMessage(String.format("You bought " + stack.getAmount() + " " + name + " for %s and now have %s",
                        Main.econ.format(economyResponse.amount), Main.econ.format(economyResponse.balance))); //send success message & tell player what they spent/remaining balance
        } else {
            p.sendMessage(String.format("An error occured: %s", economyResponse.errorMessage));	//send error message
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
    //@SuppressWarnings("deprecation")
    @EventHandler
    public void onTeleport(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        //World from = e.getFrom();
        String from = e.getFrom().getName();
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta meta = compass.getItemMeta();
        assert meta != null;
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
            //p.updateInventory();
        }
        //if(!p.getWorld().equals("world")) {
        else {
            System.out.println("!= world");
            if (p.getInventory().contains(Material.COMPASS)) {
                p.getInventory().removeItem(new ItemStack(Material.COMPASS, 1));
                //p.updateInventory();
            }
        }
    }
}