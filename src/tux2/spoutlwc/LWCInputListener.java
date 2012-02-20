package tux2.spoutlwc;

import com.griefcraft.model.Protection;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.getspout.spoutapi.event.input.KeyReleasedEvent;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

public class LWCInputListener implements Listener {
	
	SpoutLWC plugin;
	public LWCInputListener(SpoutLWC plugin) {
		super();
		this.plugin = plugin;
	}

    @EventHandler
    public void onKeyReleasedEvent(KeyReleasedEvent event) {
    	if(plugin.lwc != null) {
        	if(event.getPlayer().isSpoutCraftEnabled() && plugin.lwc.hasPermission(event.getPlayer(), "lwc.protect") && event.getKey() == Keyboard.KEY_L && event.getScreenType() == ScreenType.GAME_SCREEN) {
        		SpoutPlayer player = event.getPlayer();
        		Block target = player.getTargetBlock(plugin.transparentBlocks, 40);
        		Protection protection = plugin.lwc.findProtection(target);

        		if(protection != null) {
        		    if(plugin.lwc.canAdminProtection(player, protection)) {
        		    	plugin.guiscreens.put(player, new PlayerLwcGUI(plugin, protection, player));
        		    }else {
        		    	player.sendNotification("Unauthorized", "You can't edit this!", Material.FIRE);
        		    	//System.out.println("This person doesn't own the protection...");
        		    }
        		}else {
        			if(plugin.lwc.isProtectable(target) && plugin.lwc.hasPermission(player, "lwc.protect")) {
        		    	plugin.guiscreens.put(player, new PlayerLwcGUI(plugin, protection, player, target));
        			}else {
        				player.sendNotification("Invalid Block", "You can't lock that!", Material.FIRE);
        			}
        		}
        	}else if(event.getPlayer().isSpoutCraftEnabled() && plugin.lwc.hasPermission(event.getPlayer(), "lwc.unlock") && event.getKey() == Keyboard.KEY_U && event.getScreenType() == ScreenType.GAME_SCREEN) {
        		SpoutPlayer player = event.getPlayer();
        		Block target = player.getTargetBlock(plugin.transparentBlocks, 40);
        		Protection protection = plugin.lwc.findProtection(target);

        		if(protection != null) {
        		    if(protection.getType() == Protection.Type.PASSWORD) {
        		    	plugin.unlockscreens.put(player, new UnlockGUI(plugin, protection, player));
        		    }else {
        		    	player.sendNotification("No Password Here", "You can't unlock this!", Material.FIRE);
        		    }
        		}else {
        			player.sendNotification("Not Locked!", "You can't unlock that!", Material.FIRE);
        		}
        	}
    	}
    }

}
