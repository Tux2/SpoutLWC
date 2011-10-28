package tux2.spoutlwc;

import java.util.List;

import org.bukkit.block.Block;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.griefcraft.model.AccessRight;
import com.griefcraft.model.Protection;

public class PlayerLwcGUI {
	
	//All the Text boxes.
	GenericTextField owner = new GenericTextField();
	GenericTextField admins = new GenericTextField();
	GenericTextField users = new GenericTextField();
	GenericTextField password = new GenericTextField();
	
	public PlayerLwcGUI(SpoutLWC plugin, Protection protection, SpoutPlayer splayer) {
		this(plugin, protection, splayer, protection.getBlock());
	}
	
	public PlayerLwcGUI(SpoutLWC plugin, Protection protection, SpoutPlayer splayer, Block target) {
		//Let's create a new popup
		GenericPopup ppane = new GenericPopup();
		//Add the label at the top of the window
		GenericLabel label = new GenericLabel("LWC Locking System");
		label.setTextColor(new Color(0, 200, 0)); //This makes the label green.
		label.setAlign(WidgetAnchor.TOP_CENTER).setAnchor(WidgetAnchor.TOP_CENTER); //This puts the label at top center and align the text correctly.
		label.shiftYPos(5);
		ppane.attachWidget(plugin, label);
		//Create the owner label
		GenericLabel olabel = new GenericLabel("Owner:");
		//Set it's position on the screen (in pixels)
		olabel.setX(50).setY(100);
		//set it's position
		olabel.setHeight(20);
		//add the label to the popup
		ppane.attachWidget(plugin, olabel);
		//set properties on the Owner text box.
		owner.setX(95).setY(95);
		owner.setWidth(80).setHeight(15);
		//Add the Owner Text box to the popup.
		ppane.attachWidget(plugin, owner);
		GenericLabel plabel = new GenericLabel("Password:");
		plabel.setX(220).setY(100);
		plabel.setHeight(20);
		ppane.attachWidget(plugin, plabel);
		password.setX(275).setY(95);
		password.setWidth(80).setHeight(15);
		ppane.attachWidget(plugin, password);
		GenericLabel alabel = new GenericLabel("Admins:");
		alabel.setX(20).setY(130);
		alabel.setHeight(20);
		ppane.attachWidget(plugin, alabel);
		admins.setX(65).setY(125);
		admins.setWidth(340).setHeight(15);
		admins.setMaximumCharacters(500);
		ppane.attachWidget(plugin, admins);
		GenericLabel ulabel = new GenericLabel("Users:");
		ulabel.setX(20).setY(160);
		ulabel.setHeight(20);
		ppane.attachWidget(plugin, ulabel);
		users.setX(65).setY(155);
		users.setWidth(340).setHeight(15);
		users.setMaximumCharacters(500);
		ppane.attachWidget(plugin, users);
		GenericButton closebutton = new GenericButton("Close");
		closebutton.setX(160).setY(210);
		closebutton.setWidth(80).setHeight(20);
		ppane.attachWidget(plugin, closebutton);
		if(protection != null) {
			owner.setText(protection.getOwner());
			List<AccessRight> rights = protection.getAccessRights();
			String sadmins = "";
			String susers = "";
			for(AccessRight right : rights) {
				if(right.getType() == AccessRight.PLAYER) {
					if(right.getRights() == AccessRight.RIGHT_ADMIN) {
						//right.
						if(!sadmins.equals("")) {
							sadmins = sadmins + ", ";
						}
						sadmins = sadmins + right.getName();
					}else if(right.getRights() == AccessRight.RIGHT_PLAYER) {
						if(!susers.equals("")) {
							susers = susers + ", ";
						}
						susers = susers + right.getName();
					}
				}else if(right.getType() == AccessRight.GROUP) {
						if(right.getRights() == AccessRight.RIGHT_ADMIN) {
							//right.
							if(!sadmins.equals("")) {
								sadmins = sadmins + ", ";
							}
							sadmins = sadmins + "g:" + right.getName();
						}else if(right.getRights() == AccessRight.RIGHT_PLAYER) {
							if(!susers.equals("")) {
								susers = susers + ", ";
							}
							susers = susers + "g:" + right.getName();
						}
					}
			}
			password.setText(protection.getData());
			admins.setText(sadmins);
			users.setText(susers);
		}
		splayer.getMainScreen().attachPopupScreen(ppane);
	}

}
