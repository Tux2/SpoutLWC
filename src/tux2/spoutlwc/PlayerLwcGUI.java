package tux2.spoutlwc;

import org.bukkit.block.Block;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.griefcraft.model.Protection;

public class PlayerLwcGUI {
	
	GenericTextField owner = new GenericTextField();
	
	public PlayerLwcGUI(SpoutLWC plugin, Protection protection, SpoutPlayer splayer) {
		this(plugin, protection, splayer, protection.getBlock());
	}
	
	public PlayerLwcGUI(SpoutLWC plugin, Protection protection, SpoutPlayer splayer, Block target) {
		GenericPopup ppane = new GenericPopup();
		GenericLabel label = new GenericLabel("LWC Locking System");
		label.setTextColor(new Color(0, 200, 0)); //This makes the label green.
		label.setAlign(WidgetAnchor.TOP_CENTER).setAnchor(WidgetAnchor.TOP_CENTER); //This puts the label at top center and align the text correctly.
		label.shiftYPos(5);
		GenericLabel olabel = new GenericLabel("Owner:");
		olabel.setX(100).setY(10);
		owner.setX(100).setY(60);
		ppane.attachWidget(plugin, label);
		GenericButton closebutton = new GenericButton("Close");
		closebutton.setX(200).setY(210);
		closebutton.setWidth(80).setHeight(20);
		ppane.attachWidget(plugin, closebutton);
		splayer.getMainScreen().attachPopupScreen(ppane);
	}

}
