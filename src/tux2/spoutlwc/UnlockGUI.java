package tux2.spoutlwc;

import com.griefcraft.model.Protection;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.gui.*;
import org.getspout.spoutapi.player.SpoutPlayer;

public class UnlockGUI {
	
	GenericTextField password = new GenericTextField();
	Protection protection;
	Block target;

	public UnlockGUI(SpoutLWC plugin, Protection protection, SpoutPlayer splayer) {
		this.protection = protection;
		this.target = protection.getBlock();
		
		//Let's create a new popup
		GenericPopup ppane = new GenericPopup();
		//Add the label at the top of the window
		GenericLabel label = new GenericLabel("LWC Password Unlock");
                label.setHeight(20).setWidth(220);
		label.setTextColor(new Color(0, 200, 0)); //This makes the label green.
		label.setAlign(WidgetAnchor.TOP_CENTER).setAnchor(WidgetAnchor.TOP_CENTER); //This puts the label at top center and align the text correctly.
		label.shiftYPos(5);
		splayer.getClipboardText();
		ppane.attachWidget(plugin, label);
		
		int y = 50, height = 15;
		int x = 170;
		GenericItemWidget chesticon = new GenericItemWidget(new ItemStack(LWCButton.getDisplayItem(target.getType())));
		chesticon.setX(x + 2 * height).setY(y);
		chesticon.setHeight(height * 2).setWidth(height * 2)
				.setDepth(height * 2);
		chesticon.setTooltip("Unlock that " + target.getType().toString().replace('_', ' ') + "!");
		ppane.attachWidget(plugin, chesticon);
		GenericLabel lpassword = new GenericLabel("Enter Password:");
                lpassword.setHeight(20).setWidth(170);
		lpassword.setAlign(WidgetAnchor.TOP_CENTER).setAnchor(WidgetAnchor.CENTER_CENTER);
		ppane.attachWidget(plugin, lpassword);
		password.setWidth(80).setHeight(15);
		password.setAnchor(WidgetAnchor.CENTER_CENTER);
		password.shiftYPos(20);
		password.shiftXPos(-40);
		ppane.attachWidget(plugin, password);
		LWCButton unlockbutton = new LWCButton("Unlock", plugin);
		unlockbutton.setWidth(80).setHeight(20);
		unlockbutton.setAnchor(WidgetAnchor.CENTER_CENTER).shiftYPos(40).shiftXPos(-40);
		unlockbutton.setColor(new Color(0, 150, 0));
		LWCButton cancelbutton = new LWCButton("Cancel", plugin);
		cancelbutton.setWidth(80).setHeight(20);
		cancelbutton.setAnchor(WidgetAnchor.CENTER_CENTER).shiftYPos(65).shiftXPos(-40);
		ppane.attachWidget(plugin, unlockbutton);
		ppane.attachWidget(plugin, cancelbutton);
		splayer.getMainScreen().attachPopupScreen(ppane);
	}

}
