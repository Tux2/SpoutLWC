package tux2.spoutlwc;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.event.screen.ScreenListener;
import org.getspout.spoutapi.gui.Button;
import org.getspout.spoutapi.player.SpoutPlayer;

public class LWCScreenListener extends ScreenListener {

	SpoutLWC plugin;

	public LWCScreenListener(SpoutLWC plugin) {
		this.plugin = plugin;
	}

	@Override
	public void onButtonClick(ButtonClickEvent event) {
		// See if we are the owners of this button...
		if (plugin == event.getButton().getPlugin()) {
			Button eventbutton = event.getButton();
			String completebutton = eventbutton.getText();
			SpoutPlayer player = event.getPlayer();
			if (completebutton.equalsIgnoreCase("close")) {
				player.closeActiveWindow();
			} /*else {

				if (plugin.hasPermissions(player, "spoutlwc.use")) {
					
				} else {
					
					player.closeActiveWindow();
				}
			}*/
		}
	}

}
