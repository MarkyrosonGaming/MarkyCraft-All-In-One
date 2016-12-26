package me.Markyroson.MarkyCraft;

import java.util.HashMap;
import java.util.UUID;
import me.Markyroson.MarkyCraft.Stores;
import me.Markyroson.MarkyCraft.lib.Names.Permissons;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Main extends Base implements Listener {
	private HashMap<UUID, Integer> money = new HashMap<>();
	
	public void onEnable() {
		instance = this;
		super.onEnable();
		api = new Api();
	}
	
	public static Main getInstance() {
	    return instance;
	}
	
	/**
	* Get money
	* @returns Money
	*/
    public HashMap<UUID, Integer> getMoney() {
    	return money;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    // If not an instance of player, do not proceed
	    if(!(sender instanceof Player)) {
		    return false;
	    }
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("shop")) {
				if (player.hasPermission(Permissons.activate_shop_gui)) {
					player.openInventory(Stores.shop);
				} else {
					player.sendMessage("Go to the survival world to access this command!");
				}
		}
		else if(cmd.getName().equalsIgnoreCase("nav")) {
				player.openInventory(Stores.navigator);
		}
		else if(cmd.getName().equalsIgnoreCase("books")) {
			player.openInventory(Stores.books);
		}
	/*	 if(cmd.getLabel().equals("test-economy")) {
	            // Lets give the player 1.05 currency (note that SOME economic plugins require rounding!)
	            sender.sendMessage(String.format("You have %s", econ.format(econ.getBalance(player.getName()))));
	            EconomyResponse r = econ.depositPlayer(player, 1.05);
	            if(r.transactionSuccess()) {
	                sender.sendMessage(String.format("You were given %s and now have %s", econ.format(r.amount), econ.format(r.balance)));
	            } else {
	                sender.sendMessage(String.format("An error occured: %s", r.errorMessage));
	            }
	            return true;
	        }*/
		return false;
	}
}	// end of class