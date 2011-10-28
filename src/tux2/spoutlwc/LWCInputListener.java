package tux2.spoutlwc;

import org.bukkit.block.Block;
import org.getspout.spoutapi.event.input.InputListener;
import org.getspout.spoutapi.event.input.KeyReleasedEvent;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.griefcraft.model.Protection;

public class LWCInputListener extends InputListener {
	
	SpoutLWC plugin;
	public LWCInputListener(SpoutLWC plugin) {
		super();
		this.plugin = plugin;
	}

    @Override
    public void onKeyReleasedEvent(KeyReleasedEvent event) {
    	if(event.getPlayer().isSpoutCraftEnabled() && event.getKey() == Keyboard.KEY_L && event.getScreenType() == ScreenType.GAME_SCREEN) {
    		SpoutPlayer player = event.getPlayer();
    		Block target = player.getTargetBlock(plugin.transparentBlocks, 40);
    		Protection protection = plugin.lwc.findProtection(target);

    		if(protection != null) {
    		    if(plugin.lwc.canAdminProtection(player, protection)) {
    		    	plugin.guiscreens.put(player, new PlayerLwcGUI(plugin, protection, player));
    		    }
    		}else {
		    	plugin.guiscreens.put(player, new PlayerLwcGUI(plugin, protection, player));
    		}
    	}
    }

}
